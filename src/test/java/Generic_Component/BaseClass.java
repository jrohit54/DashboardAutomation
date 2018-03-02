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
import java.util.concurrent.TimeUnit;
/**
 * Created by rohit on 8/2/18.
 */
public class BaseClass {

    public  static String baseUrl="http://10.6.33.131:8088/publishers";
    public static String baserUri="http://10.6.33.131:8088/api";
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
        FileUtils.copyFile(screenshotAs, new File(path+"/screenshot/"+TC_ID+"-"+Order_Set+"-"+str));
        return path+"\\screenshot\\"+TC_ID+"-"+Order_Set+"-"+str;
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
     * To launch the browser
     * also initialise the extent report
     */
   @BeforeSuite
    public static void extentReport()
    {
        Date date= new Date();
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        String str2=df.format(date);
        extentreport= new ExtentReports(System.getProperty("user.dir")+"/reports/"+"admin_dashboard"+"-"+str2+".html",false);

        driver=new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


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


}
