package Generic_Component;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * Created by rohit on 8/2/18.
 */
public class BaseClass {

    public  static String publisherListUrl="http://10.6.33.131:8088/publisher/list";
    public static String baserUri="http://10.6.33.131:8088/api";
    public static String bidderListUrl="http://10.6.33.131:8088/bidder/list";
    public static String featureMappingUrl="http://10.6.33.131:8088/featuremapping";
    public  static String partnerListUrl="http://10.6.33.131:8088/partner/list";
    public static String exchangeListUrl="http://10.6.33.131:8088/exchange/list";
    public static WebDriver driver;
    public static ExtentReports extentreport;
    public static ExtentTest extenttest;
    public static Logger log = Logger.getLogger(BaseClass.class);


    /**
     * to take sacreenshot in selenium
     * @param TC_ID
     * @param Order_Set
     * @return
     * @throws IOException
     */
    public String captureScreenshot(String TC_ID, String Order_Set) throws IOException
    {
        String path = System.getProperty("user.dir");
        Date date= new Date();
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");

        String str=df.format(date)+".png";

        TakesScreenshot screenshot=(TakesScreenshot) driver;
        File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAs, new File(path+"/reports/screenshot/"+TC_ID+"-"+Order_Set+"-"+str));
        return path+"/reports/screenshot/"+TC_ID+"-"+Order_Set+"-"+str;
    }

    /**
     * to explicit wait for element to be displayed
     * @param ele
     * @param t
     */
    public void explicitWait(WebElement ele, long t)
    {
        WebDriverWait wait=new WebDriverWait(driver,t);
        wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
    }

    /**
     * to explicit wait for element to be displayed
     * @param ele
     * @param t
     */
    public void explicitWaitForAllElements(List<WebElement> ele, long t)
    {
        WebDriverWait wait=new WebDriverWait(driver,t);
        wait.until(ExpectedConditions.visibilityOfAllElements(ele));
    }
    /**
     * for wait
     * @param durationInMilliSeconds
     */
    public void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * To launch the browser
     * also initialise the extent report
     */
    @BeforeSuite
    public static void extentReport() throws IOException {
        Date date= new Date();
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        String str2=df.format(date);
        extentreport= new ExtentReports(System.getProperty("user.dir")+"/reports/"+"admin_dashboard"+"-"+str2+".html",false);
        setDriverPath();
        driver=new ChromeDriver();
        driver.get(publisherListUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        FileUtils.cleanDirectory(new File(System.getProperty("user.dir")+"/reports/"));

    }

    /**
     * to quit the driver
     */
    @AfterSuite
    public void tearDown()
    {
        driver.quit();
        extentreport.endTest(extenttest);
        extentreport.flush();
    }

    /**
     * to delete an publisher using the delete api
     * @param pubid
     * @return
     */
    public Response deletePublisherApi(String pubid)
    {
        RestAssured.baseURI=baserUri;
        Response response = RestAssured.given()
                .when()
                .contentType((ContentType.JSON))
                .delete("/publishers/" + pubid);

        return response;
    }

    /**
     * to delete an bidder using the delete api
     * @param bidderId
     * @return
     */
    public Response deleteBidderApi(String bidderId)
    {
        RestAssured.baseURI=baserUri;
        Response response = RestAssured.given()
                .when()
                .contentType((ContentType.JSON))
                .delete("/bidders/" + bidderId);

        return response;
    }

    /**
     * to delete an feature mapping data using the delete api
     * @param bidderId
     * @return
     */
    public Response deleteFeatureMappingData(String bidderId)
    {
        RestAssured.baseURI=baserUri;
        Response response = RestAssured.given()
                .when()
                .contentType((ContentType.JSON))
                .delete("/featuremapping/bidder/" + bidderId);

        return response;
    }

    /**
     * to delete an pmp deal data using the delete api
     * @param bidderId
     * @return
     */
    public Response deletePmpDealData(String bidderId)
    {
        RestAssured.baseURI=baserUri;
        Response response = RestAssured.given()
                .when()
                .contentType((ContentType.JSON))
                .delete("/pmpdeal/bidder/" + bidderId);

        return response;
    }
    /**
     * to delete an partner using the delete api
     * @param ptrId
     * @return
     */
    public Response deletePartnerApi(String ptrId)
    {
        RestAssured.baseURI=baserUri;
        Response response = RestAssured.given()
                .when()
                .contentType((ContentType.JSON))
                .delete("/partners/" + ptrId);

        return response;
    }


    /**
     * to delete an publisher using the delete api
     * @param excid
     * @return
     */
    public Response deleteAdvertiserApi(String excid)
    {
        RestAssured.baseURI=baserUri;
        Response response = RestAssured.given()
                .when()
                .contentType((ContentType.JSON))
                .delete("/advertisers/" + excid);

        return response;
    }

    private static void setDriverPath() {
        String osNameMatch=System.getProperty("os.name").toLowerCase();

        if (osNameMatch.contains("mac os")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        }
        if (osNameMatch.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        }
        if (osNameMatch.contains("linux")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver_linux");
        }
    }

}