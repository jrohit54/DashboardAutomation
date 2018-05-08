package Scenerio_Component;

import Generic_Component.BaseClass;
import PageObject_Component.*;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ScenerioPartnerTest extends BaseClass {

    public static Logger log = Logger.getLogger(ScenerioPartnerTest.class);

    @Test(priority = 33,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "PartnerDetails")
    public void testAddValidPartner(String ptrId, String name, String parentId, String email, String pymtModeId, String ptrApiKey, String ptrPaswd, String dfltTmpltId) throws InterruptedException, IOException {

        Response response = deletePartnerApi(ptrId);http://tree.mn/rohit.jai/mowX-dashboard-automation/merge_requests/new?merge_request%5Bsource_branch%5D=dashboard_partner
        Assert.assertEquals(response.statusCode(),200);
        driver.navigate().to(partnerListUrl);
        log.info("Executing the add partner test case");
        extenttest = extentreport.startTest("add partner");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC33" + " add partner");
        PartnerListPage prtlp = new PartnerListPage(driver);
        Thread.sleep(1000);
        prtlp.clickOnAddNewPartner();
        AddPartnerPage app = new AddPartnerPage(driver);
        app.enterPtrId(ptrId);
        app.enterName(name);
        app.enterparentId(parentId);
        app.enteremail(email);
        // app.enterStatus(status);
        app.enterPaymentModeId(pymtModeId);
        app.enterPartnerApiId(ptrApiKey);
        app.enterPassword(ptrPaswd);
        app.enterTemplateId(dfltTmpltId);
        app.clickOnSaveButton();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "add valid partner", extenttest.addScreenCapture(captureScreenshot("tc1", "order_set1")));
        Assert.assertTrue(prtlp.isPartnerAdded_SucessfullMessageDisplayed());
        log.info("test case executed");
        Thread.sleep(2000);
        prtlp.clickOnSelectNumberOfPartnerDisplayed();
        prtlp.selectNumberOfRecords("50");
        Assert.assertTrue(prtlp.isPartnerIdDisplayed(ptrId));

    }

    @Test(priority = 34,dataProviderClass=Dataprovider_Component.DataProviderClass.class,dataProvider = "AdvDomainDetails")
    public void testAddAdvertiserDomain(String advDomain) throws InterruptedException, IOException {
        driver.navigate().to(partnerListUrl);
        log.info("Excuting the add Advertiser domain test case for partner");
        extenttest = extentreport.startTest("add advertiser domain for partner");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC34" + " add advertiser domain for partner");
        PartnerListPage ptrlp = new PartnerListPage(driver);
        ptrlp.clickOnPreference("TEST123");
        Thread.sleep(2000);
        PartnerPrefPage prefPage = new PartnerPrefPage(driver);
      //  Assert.assertEquals(prefPage.getHeaderText(), "preference");
        Assert.assertTrue(prefPage.isAdvertiserDomainTabDisplayed());
        Assert.assertTrue(prefPage.isAdCategoryTabDisplayed());
        Assert.assertTrue(prefPage.isCreativeIdTabDisplayed());
        Assert.assertTrue(prefPage.isAttributeTabDisplayed());
        prefPage.clickOnAddDomainMapping();
        Assert.assertEquals(prefPage.getPartnerNameInSelectOption(), "TESTPARTNER (TEST123)");
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("Appnexus* (2)","YBNCA (A) (4)");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Blacklist");
        prefPage.enterDomainField(advDomain);
        prefPage.clickOnSaveButton();
        Thread.sleep(2000);
        extenttest.log(LogStatus.PASS, "add advertiser domain for partner", extenttest.addScreenCapture(captureScreenshot("tc34", "order_set2")));
        Assert.assertTrue(prefPage.isAdvDomainDisplayed(advDomain));
        log.info("test case executed");

    }

    @Test(priority =35,alwaysRun =true)
    public void testAddAdCategory() throws InterruptedException, IOException {
        driver.navigate().to(partnerListUrl);
        log.info("Executing the add category test case for Partner");
        extenttest = extentreport.startTest("add ad category for Partner");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC35" + " add ad category for Partner");
        PartnerListPage ptrlp = new PartnerListPage(driver);
        ptrlp.clickOnPreference("TEST123");
        PartnerPrefPage prefPage = new PartnerPrefPage(driver);
        //Assert.assertEquals(prefPage.getHeaderText(), "Partner Preference");
        Assert.assertTrue(prefPage.isAdvertiserDomainTabDisplayed());
        Assert.assertTrue(prefPage.isAdCategoryTabDisplayed());
        Assert.assertTrue(prefPage.isCreativeIdTabDisplayed());
        Assert.assertTrue(prefPage.isAttributeTabDisplayed());
        Thread.sleep(2000);
        prefPage.clickOnAdCategoryTab();
        Thread.sleep(2000);
        prefPage.clickOnAddCategoryButton();
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("Appnexus* (2)","YBNCA (A) (4)");
        Thread.sleep(1000);
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        Thread.sleep(1000);
        prefPage.enterDomainField("IAB1,IAB2,IAB3");
        prefPage.clickOnSaveButton();
        Thread.sleep(2000);
        extenttest.log(LogStatus.PASS, "add ad creative id for Partner", extenttest.addScreenCapture(captureScreenshot("tc3", "order_set3")));
        Assert.assertTrue(prefPage.isCategoryDisplayed("IAB1"));
        log.info("test case executed");

    }
    @Test(priority =36, alwaysRun =true)
    public void testAddCreativeId() throws InterruptedException, IOException {
        driver.navigate().to(partnerListUrl);
        log.info("Executing the add creative id test case for Partner");
        extenttest = extentreport.startTest("add creative id for Partner");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC36" + " add creative id for Partner");
        PartnerListPage ptrlp = new PartnerListPage(driver);
        ptrlp.clickOnPreference("TEST123");
        PartnerPrefPage prefPage = new PartnerPrefPage(driver);
       // Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        Assert.assertTrue(prefPage.isAdvertiserDomainTabDisplayed());
        Assert.assertTrue(prefPage.isAdCategoryTabDisplayed());
        Assert.assertTrue(prefPage.isCreativeIdTabDisplayed());
        Assert.assertTrue(prefPage.isAttributeTabDisplayed());
        Thread.sleep(2000);
        prefPage.clickOnCreativeIdTab();
        Thread.sleep(3000);
        prefPage.clickOnAddCreativeIdButton();
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("All Advertisers");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField("6112312,6322312");
        prefPage.clickOnSaveButton();
        Thread.sleep(2000);
        extenttest.log(LogStatus.PASS, "add ad creative id", extenttest.addScreenCapture(captureScreenshot("tc4", "order_set4")));
        Assert.assertTrue(prefPage.isCreativeIdDisplayed("6112312"));
        log.info("test case executed");

    }

    @Test(priority =37, alwaysRun =true)
    public void testAddAttributeId() throws InterruptedException, IOException {
        driver.navigate().to(partnerListUrl);
        log.info("Executing the add attribute id test case for Partner");
        extenttest = extentreport.startTest("add attribute id for Partner");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC37" + " add attribute id for Partner");
        PartnerListPage ptrlp = new PartnerListPage(driver);
        ptrlp.clickOnPreference("TEST123");
        PartnerPrefPage prefPage = new PartnerPrefPage(driver);
        // Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        Assert.assertTrue(prefPage.isAdvertiserDomainTabDisplayed());
        Assert.assertTrue(prefPage.isAdCategoryTabDisplayed());
        Assert.assertTrue(prefPage.isCreativeIdTabDisplayed());
        Assert.assertTrue(prefPage.isAttributeTabDisplayed());
        Thread.sleep(2000);
        prefPage.clickOnAdAttributeTab();
        Thread.sleep(2000);
        prefPage.clickOnAddAttributeIdButton();
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("Appnexus* (2)","YBNCA (A) (4)");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField("1,2,3");
        prefPage.clickOnSaveButton();
        Thread.sleep(2000);
        extenttest.log(LogStatus.PASS, "add ad attribute id for Partner", extenttest.addScreenCapture(captureScreenshot("tc3", "order_set3")));
        Assert.assertTrue(prefPage.isAttributeDisplayed("1"));
        log.info("test case executed");

    }

    @Test(priority = 38)
    public void testDeleteAdvertiserDomain() throws InterruptedException, IOException {
        driver.navigate().to(partnerListUrl);
        log.info("Executing the delete advertiser domain test case for Partner");
        extenttest = extentreport.startTest("delete advertiser domain for Partner");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC38" + " delete advertiser domain for Partner");
        PartnerListPage ptrlp = new PartnerListPage(driver);
        ptrlp.clickOnPreference("TEST123");
        PartnerPrefPage prefPage = new PartnerPrefPage(driver);
        //Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        prefPage.clickOnDeleteIconContainsAllProviders("http://maps.google.com");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete advertiser domain for Partner", extenttest.addScreenCapture(captureScreenshot("tc5", "order_set5")));
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

    @Test(priority= 39)
    public void testDeleteAdCategoty() throws InterruptedException, IOException
    {
        driver.navigate().to(partnerListUrl);
        log.info("Executing the delete ad category test case for Partner");
        extenttest = extentreport.startTest("delete ad category for Partner");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC39" + " delete ad category for Partner");
        PartnerListPage plp = new PartnerListPage(driver);
        plp.clickOnPreference("TEST123");
        PartnerPrefPage prefPage = new PartnerPrefPage(driver);
        Thread.sleep(2000);
        prefPage.clickOnAdCategoryTab();
        Thread.sleep(2000);
        //Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
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

    @Test(priority= 40)
    public void testDeleteCreativeId() throws InterruptedException, IOException
    {
        driver.navigate().to(partnerListUrl);
        log.info("Executing the delete creative id test case for Partner");
        extenttest = extentreport.startTest("delete creative id for Partner");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC40" + " delete creative id");
        PartnerListPage ptrlp = new PartnerListPage(driver);
        ptrlp.clickOnPreference("TEST123");
        PartnerPrefPage prefPage = new PartnerPrefPage(driver);
        Thread.sleep(1000);
        prefPage.clickOnCreativeIdTab();
        //Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        prefPage.clickOnDeleteIconContainsAllProviders("6322312");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(3000);
        extenttest.log(LogStatus.PASS, "delete creative id", extenttest.addScreenCapture(captureScreenshot("tc7", "order_set7")));
        prefPage.clickOnDeleteIconContainsAllProviders("6112312");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete creative id", extenttest.addScreenCapture(captureScreenshot("tc7", "order_set7.1")));
        log.info("test case executed");
    }

    @Test(priority= 41)
    public void testDeleteAttribute() throws InterruptedException, IOException
    {
        driver.navigate().to(partnerListUrl);
        log.info("Executing the delete attribute test case for Partner");
        extenttest = extentreport.startTest("delete attribute for Partner");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC41" + " delete attribute for Partner");
        PartnerListPage plp = new PartnerListPage(driver);
        plp.clickOnPreference("TEST123");
        PartnerPrefPage prefPage = new PartnerPrefPage(driver);
        Thread.sleep(2000);
        prefPage.clickOnAdAttributeTab();
        Thread.sleep(2000);
        //Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        prefPage.clickOnDeleteIconContainsAllProviders("1");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete attribute", extenttest.addScreenCapture(captureScreenshot("tc6", "order_set6")));
        prefPage.clickOnDeleteIconContainsAllProviders("2");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        prefPage.clickOnDeleteIconContainsAllProviders("3");
        prefPage.clickOnDeleteButtonInConfirmPopup();
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "delete advertiser category", extenttest.addScreenCapture(captureScreenshot("tc6", "order_set6.1")));
        log.info("test case executed");
    }

}