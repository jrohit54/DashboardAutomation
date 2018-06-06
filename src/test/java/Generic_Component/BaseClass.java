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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.apache.log4j.Level.ALL;

/**
 * Created by rohit on 8/2/18.
 */
public class BaseClass {

    public static String endPoint="http://mowx-staging.srv.media.net:8088/";
    //public static String endPoint="http://localhost:8088/";
    public  static String publisherListUrl=endPoint+"publisher/list";
    public static String baserUri=endPoint+"api";
    public static String bidderListUrl=endPoint+"bidder/list";
    public static String featureMappingUrl=endPoint+"featuremapping";
    public  static String partnerListUrl=endPoint+"partner/list";
    public static String exchangeListUrl=endPoint+"exchange/list";
    public static String databaseURL = "jdbc:mysql://10.6.33.132:3306/mowgli_adminDashboard";
    //public static String databaseURL = "jdbc:mysql://localhost:3306/mowgli_adminDashboard";
    public static String user = "root";
    public static String password = "videoads";
    public static WebDriver driver;
    public static ExtentReports extentreport;
    public static ExtentTest extenttest;
    public static Connection connection;
    public static Statement statement;
    public static ResultSet rs;
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
        //for the java script error
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences loggingPreferences=new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS,loggingPreferences);
        Date date= new Date();
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        String str2=df.format(date);
        extentreport= new ExtentReports(System.getProperty("user.dir")+"/reports/"+"admin_dashboard"+"-"+str2+".html",false);
        setDriverPath();
        driver=new ChromeDriver(options);
        driver.get(publisherListUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        FileUtils.cleanDirectory(new File(System.getProperty("user.dir")+"/reports/"));
        setUp();

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
        closeConnection();
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
    public static void setUp()  {

        connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to Database...");
            connection = DriverManager.getConnection(databaseURL, user, password);
            if (connection != null) {
                System.out.println("Connected to the Database...");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void closeConnection() {
        if (connection != null) {
            try {
                System.out.println("Closing Database Connection...");
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void extractJSLogsInfo()
    {
        LogEntries logEntries=driver.manage().logs().get(LogType.BROWSER);
        for(LogEntry entry: logEntries)
        {
            Date date= new Date();
            SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
            String str2=df.format(date);
            log.info(str2+entry.getTimestamp()+" "+entry.getLevel()+" "+entry.getMessage());
        }

    }


}