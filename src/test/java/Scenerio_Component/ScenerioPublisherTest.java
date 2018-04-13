package Scenerio_Component;

import Generic_Component.BaseClass;
import Generic_Component.CustomizeReport;
import Generic_Component.DBConnection;
import PageObject_Component.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.jayway.restassured.response.Response;


/**
 * Created by rohit on 8/2/18.
 */
@Listeners(CustomizeReport.class)
public class ScenerioPublisherTest extends BaseClass {

    public static Logger log = Logger.getLogger(ScenerioPublisherTest.class);

    @Test(priority = 1,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "PublisherDetails")
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

    @Test(priority = 2,dataProviderClass=Dataprovider_Component.DataProviderClass.class,dataProvider = "AdvDomainDetails")
    public void testAddAdvertiserDomain(String advDomain) throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Excuting the add Advertiser domain test case");
        extenttest = extentreport.startTest("add advertiser domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC2" + " add advertiser domain");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        Thread.sleep(2000);
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        Assert.assertTrue(prefPage.isAdvertiserDomainTabDisplayed());
        Assert.assertTrue(prefPage.isAdCategoryTabDisplayed());
        Assert.assertTrue(prefPage.isCreativeIdTabDisplayed());
        Assert.assertTrue(prefPage.isAttributeTabDisplayed());
        prefPage.clickOnAddDomainMapping();
        Assert.assertEquals(prefPage.getPublisherNameInSelectOption(), "testName (12345)");
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("Appnexus*","YBNCA (A)");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField(advDomain);
        prefPage.clickOnSaveButton();
        Thread.sleep(2000);
        extenttest.log(LogStatus.PASS, "add advertiser domain", extenttest.addScreenCapture(captureScreenshot("tc2", "order_set2")));
        Assert.assertTrue(prefPage.isAdvDomainDisplayed(advDomain));
        log.info("test case executed");

    }

    @Test(priority =3,alwaysRun =true)
    public void testAddAdCategory() throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
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
        Thread.sleep(2000);
        prefPage.clickOnAdCategoryTab();
        Thread.sleep(2000);
        prefPage.clickOnAddCategoryButton();
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("Appnexus*","YBNCA (A)");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField("IAB1,IAB2,IAB3");
        prefPage.clickOnSaveButton();
        Thread.sleep(2000);
        extenttest.log(LogStatus.PASS, "add ad crative id", extenttest.addScreenCapture(captureScreenshot("tc3", "order_set3")));
        Assert.assertTrue(prefPage.isCategoryDisplayed("IAB1"));
        log.info("test case executed");


    }


    @Test(priority =4, alwaysRun =true)
    public void testAddCreativeId() throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
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
        Thread.sleep(2000);
        prefPage.clickOnCreativeIdTab();
        Thread.sleep(1000);
        prefPage.clickOnAddCreativeIdButton();
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("Appnexus*","YBNCA (A)");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField("6112312,6322312");
        prefPage.clickOnSaveButton();
        Thread.sleep(2000);
        extenttest.log(LogStatus.PASS, "add ad creative id", extenttest.addScreenCapture(captureScreenshot("tc4", "order_set4")));
        Assert.assertTrue(prefPage.isCreativeIdDisplayed("6112312"));
        log.info("test case executed");

    }
    @Test(priority = 5)
    public void testDeleteAdvertiserDomain() throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
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
        prefPage.clickOnDeleteIconWithProviderName("maps.google.com","YBNCA (A)");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        prefPage.clickOnDeleteIconWithProviderName("maps.google.com","Appnexus");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete advertiser domain", extenttest.addScreenCapture(captureScreenshot("tc5", "order_set5.1")));
        log.info("test case executed");

    }

    @Test(priority= 6)
    public void testDeleteAdCategoty() throws InterruptedException, IOException
    {
        driver.navigate().to(publisherListUrl);
        log.info("Excuting the delete ad category test case");
        extenttest = extentreport.startTest("delete ad category");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC6" + " delete ad category");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Thread.sleep(2000);
        prefPage.clickOnAdCategoryTab();
        Thread.sleep(2000);
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

    @Test(priority= 7)
    public void testDeleteCreativeId() throws InterruptedException, IOException
    {
        driver.navigate().to(publisherListUrl);
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

    @Test(priority = 8,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "InvalidPubId")
    public void testAddPublisherWithNoPubId(String pubId) throws InterruptedException, IOException
    {
        driver.navigate().to(publisherListUrl);
        log.info("Excuting the add publisher without pubid test case");
        extenttest = extentreport.startTest("add publisher without pubid");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC8" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        Thread.sleep(1000);
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

    @Test(priority = 9,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "InvalidEmailAddressPart1")
    public void testInvaildEmailIdFieldValidation(String email) throws InterruptedException, IOException
    {

        driver.navigate().to(publisherListUrl);
        log.info("Excuting the add publisher with with invalid email");
        extenttest = extentreport.startTest("add publisher with  invalid email");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC9" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        Thread.sleep(1000);
        plp.clickOnAddNewPublisher();
        AddPublisherPage app = new AddPublisherPage(driver);
        Assert.assertEquals(app.getSubHeaderText(),"New Publisher");
        app.enterEmail(email);
        app.clickOnSaveButton();
        Assert.assertEquals(app.getErrorMessageForEmail(),"Entered value is not a proper email");
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "add publisher with invalid email", extenttest.addScreenCapture(captureScreenshot("tc9", "order_set9")));

    }
    @Test(priority=10,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "InvalidEmailAddressPart2")
    public void testInvalidEmailIdServerValidation(String email)throws InterruptedException, IOException
    {
        driver.navigate().to(publisherListUrl);
        log.info("Excuting the add publisher with with invalid email");
        extenttest = extentreport.startTest("add publisher with invalid email");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC10" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        Thread.sleep(1000);
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

    @Test(priority = 11,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "InvalidDomain")
    public void testDomainFieldValidation(String domain)  throws InterruptedException, IOException
    {
        driver.navigate().to(publisherListUrl);
        log.info("Excuting the add publisher with with invalid domain");
        extenttest = extentreport.startTest("add publisher with invalid domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC11" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        Thread.sleep(1000);
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

    @Test(priority = 12,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "BidderDetails")
    public void testAddValidBidder(String bidderId ,String bidderName)  throws InterruptedException, IOException
    {
        Response response = deleteBidderApi(bidderId);
        log.info("Excuting the add valid bidder");
        extenttest = extentreport.startTest("Excuting the add valid bidder");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC12" + " add bidder");
        driver.navigate().to(bidderListUrl);
        Thread.sleep(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnAddNewBidderButton();
        Assert.assertEquals(blp.getHeaderText(), "Add New Bidder");
        blp.enterBidderId(bidderId);
        blp.enterBidderName(bidderName);
        blp.clickOnAdFormatSelectOption();
        blp.selectOption("VAST","BANNER","NATIVE");
        blp.clickOnSupportedClientSelectOption();
        blp.selectOption("HB","STREAM","HB_POST_ENCODED","CM");
        blp.clickOnDataCenterSelectOption();
        blp.selectOption("EAST");
        blp.enterEndPoint("http://10.6.33.130/bidder5");
        blp.clickOnShowParam();
        Assert.assertTrue(blp.isPlusIconDisplayed());
        blp.enterQueryParamField("cc=");
        blp.enterMacroField("MNET_COUNTRY2");
        blp.clickOnSaveButton();
        Thread.sleep(2000);
        Assert.assertTrue(blp.isBidderIdDisplayed(bidderId),"bidder id not displayed in list");
        Assert.assertTrue(blp.isAdFormatDisplayedForBidder(bidderId,"VAST"));
        Assert.assertTrue(blp.isAdFormatDisplayedForBidder(bidderId,"BANNER"));
        Assert.assertTrue(blp.isAdFormatDisplayedForBidder(bidderId,"NATIVE"));
        Assert.assertTrue(blp.isSupportedClientDisplayedForBidder(bidderId,"CM"));
        Assert.assertTrue(blp.isSupportedClientDisplayedForBidder(bidderId,"HB"));
        Assert.assertTrue(blp.isSupportedClientDisplayedForBidder(bidderId,"STREAM"));
        Assert.assertTrue(blp.isSupportedClientDisplayedForBidder(bidderId,"HB_POST_ENCODED"));
        extenttest.log(LogStatus.PASS, "add valid bidder", extenttest.addScreenCapture(captureScreenshot("tc12", "order_set12")));
    }

    @Test(priority = 13,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "BidderDetails")
    public void testEditBidder(String bidderId,String bidderName ) throws InterruptedException, IOException
    {
        log.info("Excuting the edit  bidder");
        extenttest = extentreport.startTest("Excuting the edit bidder");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC13" + " edit bidder");
        driver.navigate().to(bidderListUrl);
        Thread.sleep(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnBidderEditIcon(bidderId);
        blp.clickOnShowParam();
        blp.clickOnDeleteParamIcon();
        blp.clickOnSaveButton();
        Thread.sleep(1000);
        Assert.assertTrue(blp.isSucessfullMessageDisplayed());
        extenttest.log(LogStatus.PASS, "edit bidder", extenttest.addScreenCapture(captureScreenshot("tc13", "order_set13")));
    }

    @Test(priority = 14,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "validFeatureMappingDelatils")
    public void testAddFeatureMapping(String featureName,String data) throws InterruptedException, IOException {
        //to delete existing entry present in feature mapping for bidder 1
        deleteFeatureMappingData("1");
        log.info("Excuting the add feature mapping");
        extenttest = extentreport.startTest("Excuting the add feature mapping");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC14" + " add feature mapping");
        driver.navigate().to(featureMappingUrl);
        Thread.sleep(1000);
        FeatureMappingPage featurePage=new FeatureMappingPage(driver);
        featurePage.clickOnAddFeatureMapping();
        Assert.assertEquals(featurePage.getHeaderText(),"Add New Feature Mapping");
        featurePage.clickOnEntitySelectOption();
        featurePage.selectOption("BIDDER");
        featurePage.clickOnEntityNameSelectOption();
        featurePage.selectOption("testBidderName (1)");
        featurePage.clickOnFeatureNameSelectOption();
        featurePage.selectOption(featureName);
        featurePage.enterDataField(data);
        featurePage.clickOnSaveButton();
        Thread.sleep(3000);
        Assert.assertTrue(featurePage.isFeatureNameDisplayed("1",featureName));
        Assert.assertTrue(featurePage.isFeatureDataDisplayed("1",data));
        extenttest.log(LogStatus.PASS, "add feature mapping", extenttest.addScreenCapture(captureScreenshot("tc14", "order_set14")));
    }

    @Test(priority= 15)
    public void testEditFeatureMapping() throws InterruptedException, IOException {
        String editData="{\"ALL\":100}";
        log.info("Excuting the edit feature mapping");
        extenttest = extentreport.startTest("Excuting the edit feature mapping");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC15" + " edit feature mapping");
        driver.navigate().to(featureMappingUrl);
        Thread.sleep(1000);
        FeatureMappingPage featurePage=new FeatureMappingPage(driver);
        featurePage.clickOnEdit("1");
        featurePage.clearAndUpdateData(editData);
        featurePage.clickOnEditSaveButton();
        Thread.sleep(3000);
        Assert.assertTrue(featurePage.isFeatureDataDisplayed("1",editData));
        extenttest.log(LogStatus.PASS, "add feature mapping", extenttest.addScreenCapture(captureScreenshot("tc15", "order_set15")));
    }

    @Test(priority=16,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "invalidFeatureMappingDetails")
    public void testInvalidAddFeatureMapping(String featureName,String data) throws InterruptedException,IOException
    {
        log.info("Excuting the add  feature mapping with invalid data");
        extenttest = extentreport.startTest("Excuting the add feature mapping");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC16" + " add feature mapping");
        driver.navigate().to(featureMappingUrl);
        Thread.sleep(1000);
        FeatureMappingPage featurePage=new FeatureMappingPage(driver);
        featurePage.clickOnAddFeatureMapping();
        Assert.assertEquals(featurePage.getHeaderText(),"Add New Feature Mapping");
        featurePage.clickOnEntitySelectOption();
        Thread.sleep(500);
        featurePage.selectOption("BIDDER");
        Thread.sleep(1000);
        featurePage.clickOnEntityNameSelectOption();
        featurePage.selectOption("testBidderName (1)");
        featurePage.clickOnFeatureNameSelectOption();
        featurePage.selectOption(featureName);
        featurePage.enterDataField(data);
        featurePage.clickOnSaveButton();
        Thread.sleep(1000);
        Assert.assertEquals(featurePage.getDataErrorTest(),"Value does not satisfy the feature Validations");
        extenttest.log(LogStatus.PASS, "add invalid feature mapping", extenttest.addScreenCapture(captureScreenshot("tc16", "order_set16")));

    }


}
