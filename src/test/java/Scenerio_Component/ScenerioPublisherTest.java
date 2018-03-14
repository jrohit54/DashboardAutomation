package Scenerio_Component;

import Generic_Component.BaseClass;
import PageObject_Component.AddPublisherPage;
import PageObject_Component.PublisherListPage;
import PageObject_Component.PublisherPrefPage;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.jayway.restassured.response.Response;


/**
 * Created by rohit on 8/2/18.
 */
public class ScenerioPublisherTest extends BaseClass {

    public static Logger log = Logger.getLogger(ScenerioPublisherTest.class);

    @Test(priority = 1,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "PublisherDetails")
    public void testAddValidPublisher(String pubId, String email, String name, String compName, String fName, String lName, String domName, String catName) throws InterruptedException, IOException {

        Response response = deletePublisherApi(pubId);
        Assert.assertEquals(response.statusCode(),200);
        driver.navigate().to(baseUrl);
        Thread.sleep(2000);
        log.info("Excuting the add publisher test case");
        extenttest = extentreport.startTest("add publisher");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC1" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
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

   // @Test(priority = 2,dataProviderClass=Dataprovider_Component.DataProviderClass.class,dataProvider = "AdvDomainDetails")
    public void testAddAdvertiserDomain(String advDomain) throws InterruptedException, IOException {
        driver.navigate().to(baseUrl);
        log.info("Excuting the add Advertiser domain test case");
        extenttest = extentreport.startTest("add advertiser domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC2" + " add advertiser domain");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        Assert.assertTrue(prefPage.isAdvertiserDomainTabDisplayed());
        Assert.assertTrue(prefPage.isAdCategoryTabDisplayed());
        Assert.assertTrue(prefPage.isCreativeIdTabDisplayed());
        Assert.assertTrue(prefPage.isAttributeTabDisplayed());
        prefPage.clickOnAddDomainMapping();
        Assert.assertEquals(prefPage.getPublisherNameInSelectOption(), "testName (12345)");
        prefPage.clickOnSelectProvider();
        prefPage.selectProviderOption("DummyBidder");
        prefPage.selectProviderOption("Appnexus*");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField(advDomain);
        prefPage.clickOnSaveButton();
        Thread.sleep(2000);
        extenttest.log(LogStatus.PASS, "add advertiser domain", extenttest.addScreenCapture(captureScreenshot("tc2", "order_set2")));
        Assert.assertTrue(prefPage.isAdvDomainDisplayed(advDomain));
        log.info("test case executed");

    }

   // @Test(priority =3,alwaysRun =true)
    public void testAddAdCategory() throws InterruptedException, IOException {
        driver.navigate().to(baseUrl);
        log.info("Excuting the add category test case");
        extenttest = extentreport.startTest("add ad category");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC3" + " add ad category");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        Assert.assertTrue(prefPage.isAdvertiserDomainTabDisplayed());
        Assert.assertTrue(prefPage.isAdCategoryTabDisplayed());
        Assert.assertTrue(prefPage.isCreativeIdTabDisplayed());
        Assert.assertTrue(prefPage.isAttributeTabDisplayed());
        prefPage.clickOnAdCategoryTab();
        Thread.sleep(2000);
        prefPage.clickOnAddCategoryButton();
        prefPage.clickOnSelectProvider();
        prefPage.selectProviderOption("DummyBidder");
        prefPage.selectProviderOption("Appnexus*");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField("IAB1,IAB2,IAB3");
        prefPage.clickOnSaveButton();
        Thread.sleep(2000);
        extenttest.log(LogStatus.PASS, "add ad crative id", extenttest.addScreenCapture(captureScreenshot("tc3", "order_set3")));
        Assert.assertTrue(prefPage.isCategoryDisplayed("IAB1"));
        log.info("test case executed");


    }


    //@Test(priority =4, alwaysRun =true)
    public void testAddCreativeId() throws InterruptedException, IOException {
        driver.navigate().to(baseUrl);
        log.info("Excuting the add crative id test case");
        extenttest = extentreport.startTest("add creative id");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC4" + " add creative id");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        Assert.assertTrue(prefPage.isAdvertiserDomainTabDisplayed());
        Assert.assertTrue(prefPage.isAdCategoryTabDisplayed());
        Assert.assertTrue(prefPage.isCreativeIdTabDisplayed());
        Assert.assertTrue(prefPage.isAttributeTabDisplayed());
        prefPage.clickOnCreativeIdTab();
        Thread.sleep(1000);
        prefPage.clickOnAddCreativeIdButton();
        prefPage.clickOnSelectProvider();
        prefPage.selectProviderOption("DummyBidder");
        prefPage.selectProviderOption("Appnexus*");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField("6112312,6322312");
        prefPage.clickOnSaveButton();
        Thread.sleep(2000);
        extenttest.log(LogStatus.PASS, "add ad creative id", extenttest.addScreenCapture(captureScreenshot("tc4", "order_set4")));
        Assert.assertTrue(prefPage.isCreativeIdDisplayed("6112312"));
        log.info("test case executed");

    }
    //@Test(priority = 5)
    public void testDeleteAdvertiserDomain() throws InterruptedException, IOException {
        driver.navigate().to(baseUrl);
        log.info("Excuting the delete advertiser domain test case");
        extenttest = extentreport.startTest("delete advertiser domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC5" + " delete advertiser domain");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        prefPage.clickOnDeleteIconContainsAllProviders("http://maps.google.com");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete advertiser domain", extenttest.addScreenCapture(captureScreenshot("tc5", "order_set5")));
        Assert.assertTrue(prefPage.isAdvDomainDeleteMessageDisplayed("http://maps.google.com"));
        Assert.assertFalse(prefPage.isAdvDomainDisplayed("http://maps.google.com"));
        prefPage.clickOnProvidersLink("maps.google.com");
        prefPage.clickOnDeleteIconWithProviderName("maps.google.com","DummyBidder");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        prefPage.clickOnDeleteIconWithProviderName("maps.google.com","Appnexus");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete advertiser domain", extenttest.addScreenCapture(captureScreenshot("tc5", "order_set5.1")));
        log.info("test case executed");

    }

    //@Test(priority= 6)
    public void testDeleteAdCategoty() throws InterruptedException, IOException
    {
        driver.navigate().to(baseUrl);
        log.info("Excuting the delete ad category test case");
        extenttest = extentreport.startTest("delete ad category");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC6" + " delete ad category");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Thread.sleep(2000);
        prefPage.clickOnAdCategoryTab();
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        prefPage.clickOnDeleteIconContainsAllProviders("IAB1");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete ad category", extenttest.addScreenCapture(captureScreenshot("tc6", "order_set6")));
        prefPage.clickOnDeleteIconContainsAllProviders("IAB2");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        prefPage.clickOnDeleteIconContainsAllProviders("IAB3");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete advertiser category", extenttest.addScreenCapture(captureScreenshot("tc6", "order_set6.1")));
        log.info("test case executed");
    }

    //@Test(priority= 7)
    public void testDeleteCreativeId() throws InterruptedException, IOException
    {
        driver.navigate().to(baseUrl);
        log.info("Excuting the delete creative id test case");
        extenttest = extentreport.startTest("delete creative id");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC7" + " delete creative id");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Thread.sleep(1000);
        prefPage.clickOnCreativeIdTab();
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        prefPage.clickOnDeleteIconContainsAllProviders("6322312");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete creative id", extenttest.addScreenCapture(captureScreenshot("tc7", "order_set7")));
        prefPage.clickOnDeleteIconContainsAllProviders("6112312");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete creative id", extenttest.addScreenCapture(captureScreenshot("tc7", "order_set7.1")));
        log.info("test case executed");
    }

    //@Test(priority = 8,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "InvalidPubId")
    public void testAddPublisherWithNoPubId(String pubId) throws InterruptedException, IOException
    {
        driver.navigate().to(baseUrl);
        Thread.sleep(2000);
        log.info("Excuting the add publisher without pubid test case");
        extenttest = extentreport.startTest("add publisher without pubid");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC8" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnAddNewPublisher();
        AddPublisherPage app = new AddPublisherPage(driver);
        Assert.assertEquals(app.getSubHeaderText(),"New Publisher");
        app.enterPubId(pubId);
        app.clickOnSaveButton();
        app.clickOnSaveButton();
        Assert.assertEquals(app.getErrorMessageForPubId(),"Publisher Id is a compulsory and Alphanumeric field");
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "add publisher without pubid", extenttest.addScreenCapture(captureScreenshot("tc8", "order_set8")));
    }

    //@Test(priority = 9,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "InvalidEmailAddressPart1")
    public void testInvaildEmailIdFieldValidation(String email) throws InterruptedException, IOException
    {

        driver.navigate().to(baseUrl);
        Thread.sleep(2000);
        log.info("Excuting the add publisher with with invalid email");
        extenttest = extentreport.startTest("add publisher with  invalid email");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC9" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnAddNewPublisher();
        AddPublisherPage app = new AddPublisherPage(driver);
        Assert.assertEquals(app.getSubHeaderText(),"New Publisher");
        app.enterEmail(email);
        app.clickOnSaveButton();
        Assert.assertEquals(app.getErrorMessageForEmail(),"Entered value is not a proper email");
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "add publisher with invalid email", extenttest.addScreenCapture(captureScreenshot("tc9", "order_set9")));

    }
    //@Test(priority=10,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "InvalidEmailAddressPart2")
    public void testInvalidEmailIdServerValidation(String email)throws InterruptedException, IOException
    {
        driver.navigate().to(baseUrl);
        Thread.sleep(2000);
        log.info("Excuting the add publisher with with invalid email");
        extenttest = extentreport.startTest("add publisher with invalid email");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC10" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnAddNewPublisher();
        AddPublisherPage app = new AddPublisherPage(driver);
        Assert.assertEquals(app.getSubHeaderText(),"New Publisher");
        app.enterPubId("123");
        app.enterEmail(email);
        app.clickOnSaveButton();
        Thread.sleep(1000);
        String message=app.getServersideMessage();
        Assert.assertTrue(message.contains("Validation failed for classes"));
        extenttest.log(LogStatus.PASS, "add publisher with invalid email", extenttest.addScreenCapture(captureScreenshot("tc10", "order_set10")));
    }

    //@Test(priority = 11,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "InvalidDomain")
    public void testDomainFieldValidation(String domain)  throws InterruptedException, IOException
    {
        driver.navigate().to(baseUrl);
        Thread.sleep(2000);
        log.info("Excuting the add publisher with with invalid domain");
        extenttest = extentreport.startTest("add publisher with invalid domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC10" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnAddNewPublisher();
        AddPublisherPage app = new AddPublisherPage(driver);
        Assert.assertEquals(app.getSubHeaderText(),"New Publisher");
        app.enterPubId("123");
        app.enterDomain(domain);
        app.clickOnSaveButton();
        Thread.sleep(1000);
        Assert.assertEquals(app.getErrorMessageForDomain(),"Entered value is not a proper domain");
        String message=app.getValidationFailedMessage();
        Assert.assertTrue(message.contains("Some validations are failing"));
        extenttest.log(LogStatus.PASS, "add publisher with invalid email", extenttest.addScreenCapture(captureScreenshot("tc11", "order_set11")));
    }


}
