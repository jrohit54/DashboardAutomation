package Scenerio_Component;

import Generic_Component.BaseClass;
import PageObject_Component.AddPublisherPage;
import PageObject_Component.PublisherListPage;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ScenarioExchangeTest extends BaseClass {

    public static Logger log = Logger.getLogger(ScenarioExchangeTest.class);

    @Test(priority = 50,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "PublisherDetails")
    public void testAddValidPublisher(String pubId, String email, String name, String compName, String fName, String lName, String domName, String catName) throws InterruptedException, IOException {

        Response response = deletePublisherApi(pubId);
        Assert.assertEquals(response.statusCode(),200);
        driver.navigate().to(publisherListUrl);
        log.info("Excuting the add publisher test case");
        extenttest = extentreport.startTest("add publisher");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC1" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        Thread.sleep(1000);
        plp.clickOnAddNewPublisher();
        AddPublisherPage app = new AddPublisherPage(driver);
        app.enterPubId(pubId);
        app.enterEmail(email);
        app.enterName(name);
        app.enterCompanyName(compName);
        app.enterFirstName(fName);
        app.enterLastName(lName);
        app.enterDomain(domName);
        app.enterCategory(catName);
        app.clickOnSaveButton();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "add valid publisher", extenttest.addScreenCapture(captureScreenshot("tc1", "order_set1")));
        Assert.assertTrue(plp.isPublisherAdded_SucessfullMessageDisplayed());
        log.info("test case executed");
        Thread.sleep(2000);
        plp.clickOnSelectNumberOfPublisherDisplayed();
        plp.selectNumberOfRecords("50");
        Assert.assertTrue(plp.isPublisherIdDisplayed(pubId));

    }

}
