package Scenerio_Component;

import Generic_Component.BaseClass;
import PageObject_Component.BidderListPage;
import PageObject_Component.BidderPrefPage;
import PageObject_Component.PmpDealPage;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 *  Created by rohit on 23/4/18.
 */


public class ScenerioBidderTest extends BaseClass {


    @Test(priority = 1,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "BidderDetails")
    public void testAddValidBidder(String bidderId ,String bidderName)  throws InterruptedException, IOException
    {
        Response response = deleteBidderApi(bidderId);
        log.info("Executing the add valid bidder");
        extenttest = extentreport.startTest("Excuting the add valid bidder");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC1" + " add bidder");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
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
        waitFor(2000);
        Assert.assertTrue(blp.isBidderIdDisplayed(bidderId),"bidder id not displayed in list");
        Assert.assertTrue(blp.isAdFormatDisplayedForBidder(bidderId,"VAST"));
        Assert.assertTrue(blp.isAdFormatDisplayedForBidder(bidderId,"BANNER"));
        Assert.assertTrue(blp.isAdFormatDisplayedForBidder(bidderId,"NATIVE"));
        Assert.assertTrue(blp.isSupportedClientDisplayedForBidder(bidderId,"CM"));
        Assert.assertTrue(blp.isSupportedClientDisplayedForBidder(bidderId,"HB"));
        Assert.assertTrue(blp.isSupportedClientDisplayedForBidder(bidderId,"STREAM"));
        Assert.assertTrue(blp.isSupportedClientDisplayedForBidder(bidderId,"HB_POST_ENCODED"));
        extenttest.log(LogStatus.PASS, "add valid bidder", extenttest.addScreenCapture(captureScreenshot("tc1", "order_set1")));
    }

    @Test(priority = 2,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "BidderDetails")
    public void testEditBidder(String bidderId,String bidderName ) throws InterruptedException, IOException
    {
        log.info("Executing the edit  bidder");
        extenttest = extentreport.startTest("Excuting the edit bidder");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC2" + " edit bidder");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnBidderEditIcon(bidderId);
        blp.clickOnShowParam();
        blp.clickOnDeleteParamIcon();
        blp.clickOnSaveButton();
        waitFor(1000);
        Assert.assertTrue(blp.isSucessfullMessageDisplayed());
        extenttest.log(LogStatus.PASS, "edit bidder", extenttest.addScreenCapture(captureScreenshot("tc2", "order_set2")));
    }


    @Test(priority = 3)
    public void testAddBidderPreferenceSize() throws InterruptedException,IOException
    {
        extenttest = extentreport.startTest("Executing the add bidder preference size");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC3" + " add bidder preference size");
        driver.navigate().to(bidderListUrl);
        waitFor(2000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(1000);
        prefPage.clickOnAddSize();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.selectSize("70x70");
        prefPage.clickOnSaveButton();
        Assert.assertTrue(prefPage.isDataDisplayed("70x70"));
        extenttest.log(LogStatus.PASS, "add bidder preference size", extenttest.addScreenCapture(captureScreenshot("tc3", "order_set3")));

    }

    @Test(priority = 4)
    public void testDeletePreferenceSize() throws InterruptedException,IOException
    {
        extenttest = extentreport.startTest("Excuting the delete bidder preference size");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC4" + " delete bidder preference size");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnDeleteIcon("70x70");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Size: 70x70"));
        prefPage.clickOnDeleteConfirmationButton();
        waitFor(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("70x70"));
        extenttest.log(LogStatus.PASS, "delete bidder preference size", extenttest.addScreenCapture(captureScreenshot("tc4", "order_set4")));
    }

    @Test(priority = 5)
    public void testAddBidderPreferencePubliserDomian() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the add bidder preference publisher domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC5" + " add bidder preference publisher domain");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnPublisherDomainTab();
        waitFor(2000);
        prefPage.clickOnAddDomainMappingButton();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.enterText("http://maps.google.com");
        prefPage.clickOnSaveButton();
        waitFor(1000);
        Assert.assertTrue(prefPage.isDataDisplayed("http://maps.google.com"));
        extenttest.log(LogStatus.PASS, "add bidder preference publisher domain", extenttest.addScreenCapture(captureScreenshot("tc5", "order_set5")));
    }

    @Test(priority = 6)
    public void testDeleteBidderPreferencePublisherDomain() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the delete bidder preference publisher domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC6" + " delete bidder preference publisher domain");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);;
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnPublisherDomainTab();;
        prefPage.clickOnDeleteIcon("http://maps.google.com");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Domain: http://maps.google.com"));
        prefPage.clickOnDeleteConfirmationButton();
        waitFor(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("http://maps.google.com"));
        extenttest.log(LogStatus.PASS, "delete bidder preference publisher domain", extenttest.addScreenCapture(captureScreenshot("tc6", "order_set6")));
    }

    @Test(priority = 7)
    public void testAddBidderPreferenceTagID() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the add bidder preference tag id");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC7" + " add bidder preference tag id ");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnTagIdTab();
        waitFor(1000);
        prefPage.clickOnAddTagIdButton();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.enterText("10528367");
        prefPage.clickOnSaveButton();
        waitFor(1000);
        Assert.assertTrue(prefPage.isDataDisplayed("10528367"));
        extenttest.log(LogStatus.PASS, "add bidder preference tag id", extenttest.addScreenCapture(captureScreenshot("tc7", "order_set7")));
    }

    @Test(priority = 8)
    public void testDeleteBidderPreferenceTagId() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the delete bidder preference Tag id");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC8" + " delete bidder preference Tag id");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnTagIdTab();
        prefPage.clickOnDeleteIcon("10528367");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Tag ID: 10528367"));
        prefPage.clickOnDeleteConfirmationButton();
        waitFor(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("10528367"));
        extenttest.log(LogStatus.PASS, "delete bidder preference tag id", extenttest.addScreenCapture(captureScreenshot("tc8", "order_set8")));
    }

    @Test(priority = 9)
    public void testAddBidderPreferencePubSLD() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the add bidder preference publisher SLD");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC9" + " add bidder preference publisher SLD");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnPublisherSLDTab();
        waitFor(1000);
        prefPage.clickOnAddPublisherSLDButton();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.enterText("forbes");
        prefPage.clickOnSaveButton();
        waitFor(1000);
        Assert.assertTrue(prefPage.isDataDisplayed("forbes"));
        extenttest.log(LogStatus.PASS, "add bidder preference publisher SLD", extenttest.addScreenCapture(captureScreenshot("tc9", "order_set9")));
    }

    @Test(priority =10)
    public void testDeleteBidderPreferencePubSLD() throws InterruptedException,IOException {
        extenttest = extentreport.startTest("Excuting the delete bidder preference Publisher SLD");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC10" + " delete bidder preference Publisher SLD");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnPublisherSLDTab();
        waitFor(2000);
        prefPage.clickOnDeleteIcon("forbes");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Publisher SLD: forbes"));
        prefPage.clickOnDeleteConfirmationButton();
        waitFor(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("forbes"));
        extenttest.log(LogStatus.PASS, "delete bidder preference Publisher SLd", extenttest.addScreenCapture(captureScreenshot("tc10", "order_set10")));
    }

    @Test(priority = 11)
    public void testAddBidderPreferenceDeviceType() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the add bidder preference Device Type");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC11" + " add bidder preference Device Type");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnDeviceTypeTab();
        waitFor(1000);
        prefPage.clickOnAddDeviceTypeButton();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.selectSize("MOBILE_TABLET");
        prefPage.clickOnSaveButton();
        waitFor(1000);
        Assert.assertTrue(prefPage.isDataDisplayed("MOBILE_TABLET"));
        extenttest.log(LogStatus.PASS, "add bidder preference Device Type", extenttest.addScreenCapture(captureScreenshot("tc11", "order_set11")));
    }

    @Test(priority =12)
    public void testDeleteBidderPreferenceDeviceType() throws InterruptedException,IOException {
        extenttest = extentreport.startTest("Excuting the delete bidder preference Publisher Device Type");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC12" + " delete bidder preference Device Type");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnDeviceTypeTab();
        waitFor(1000);
        prefPage.clickOnDeleteIcon("MOBILE_TABLET");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Device Type: MOBILE_TABLET"));
        prefPage.clickOnDeleteConfirmationButton();
        waitFor(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("MOBILE_TABLET"));
        extenttest.log(LogStatus.PASS, "delete bidder preference Publisher Device Type", extenttest.addScreenCapture(captureScreenshot("tc12", "order_set12")));
    }

    @Test(priority =13)
    public void testAddBidderPreferenceCountry() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the add bidder preference Country");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC13" + " add bidder preference Country");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnCountryTab();
        waitFor(1000);
        prefPage.clickOnAddCoutryButton();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.selectSize("AX (Aland Islands)");
        prefPage.clickOnSaveButton();
        waitFor(1000);
        Assert.assertTrue(prefPage.isDataDisplayed("AX (Aland Islands)"));
        extenttest.log(LogStatus.PASS, "add bidder preference  Country", extenttest.addScreenCapture(captureScreenshot("tc13", "order_set13")));
    }

    @Test(priority =14)
    public void testDeleteBidderPreferenceCountry() throws InterruptedException,IOException {
        extenttest = extentreport.startTest("Excuting the delete bidder preference Publisher Country");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC14" + " delete bidder preference Country");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(3000);
        prefPage.clickOnCountryTab();
        waitFor(1000);
        prefPage.clickOnDeleteIcon("AX");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Country: AX"));
        prefPage.clickOnDeleteConfirmationButton();
        waitFor(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("AX (Aland Islands)"));
        extenttest.log(LogStatus.PASS, "delete bidder preference Country", extenttest.addScreenCapture(captureScreenshot("tc14", "order_set14")));
    }

    @Test(priority =15)
    public void testAddBidderPreferenceOperatingSystem() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the add bidder preference Operating System");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC15" + " add bidder preference Operating System");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnOperatingSystemTab();
        waitFor(1000);
        prefPage.clickOnAddOperatingSystemButton();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.selectSize("Android");
        prefPage.clickOnSaveButton();
        waitFor(1000);
        Assert.assertTrue(prefPage.isDataDisplayed("Android"));
        extenttest.log(LogStatus.PASS, "add bidder preference  Operating System", extenttest.addScreenCapture(captureScreenshot("tc15", "order_set15")));
    }

    @Test(priority =16)
    public void testDeleteBidderPreferenceOperatingSystem() throws InterruptedException,IOException {
        extenttest = extentreport.startTest("Excuting the delete bidder preference  Operating System");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC16" + " delete bidder preference Operating System");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnOperatingSystemTab();
        waitFor(1000);
        prefPage.clickOnDeleteIcon("Android");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete OS: Android"));
        prefPage.clickOnDeleteConfirmationButton();
        waitFor(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("Android"));
        extenttest.log(LogStatus.PASS, "delete bidder preference operating system", extenttest.addScreenCapture(captureScreenshot("tc16", "order_set16")));
    }

    @Test(priority =17)
    public void testAddBidderPreferenceGender() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the add bidder preference Gender");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC17" + " add bidder preference Gender");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        waitFor(2000);
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        prefPage.clickOnGenderTab();
        waitFor(2000);
        prefPage.clickOnAddGenderButton();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.selectSize("FEMALE");
        prefPage.clickOnSaveButton();
        waitFor(1000);
        Assert.assertTrue(prefPage.isDataDisplayed("FEMALE"));
        extenttest.log(LogStatus.PASS, "add bidder preference  Gender", extenttest.addScreenCapture(captureScreenshot("tc17", "order_set17")));
    }

    @Test(priority =18)
    public void testDeleteBidderPreferenceGender() throws InterruptedException,IOException {
        extenttest = extentreport.startTest("Excuting the delete bidder preference  Gender");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC18" + " delete bidder preference Gender");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnGenderTab();
        waitFor(1000);
        prefPage.clickOnDeleteIcon("FEMALE");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Gender: FEMALE"));
        prefPage.clickOnDeleteConfirmationButton();
        waitFor(2000);
        Assert.assertFalse(prefPage.isDataDisplayed("FEMALE"));
        extenttest.log(LogStatus.PASS, "delete bidder preference Gender", extenttest.addScreenCapture(captureScreenshot("tc18", "order_set18")));
    }

    @Test(priority=19)
    public void testAddPmpDeal() throws InterruptedException,IOException
    {    deletePmpDealData("1");
        log.info("Excuting the add  valid pmp deal");
        extenttest = extentreport.startTest("Excuting the add valid pmp deal");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC19" + " add pmp deal");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPmpDeal("1");
        PmpDealPage pmpDeal=new PmpDealPage(driver);
        waitFor(1000);
        pmpDeal.clickOnPmpDealButton();
        pmpDeal.enterDealId("testDeal");
        pmpDeal.selectRadioOption("Publisher");
        pmpDeal.clickOnSelectPublisherId();
        waitFor(500);
        pmpDeal.selectOption("testName (12345)");
        pmpDeal.enterBidFloor("1.0");
        pmpDeal.enterBidFloorCurrency("USD");
        pmpDeal.selectRadioOption("Open");
        pmpDeal.selectRadioOption("Active");
        pmpDeal.clickOnSaveButton();
        Assert.assertTrue(pmpDeal.isPmpDealDisplayed("testDeal"));
        extenttest.log(LogStatus.PASS, "add valid pmp deal", extenttest.addScreenCapture(captureScreenshot("tc19", "order_set19")));
    }

    @Test(priority=20)
    public void testPmpDealTarget() throws InterruptedException,IOException
    {
        log.info("Excuting the add  pmp deal target");
        extenttest = extentreport.startTest("Excuting the add  pmp deal target");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC20" + " add pmp deal target");
        driver.navigate().to(bidderListUrl);
        waitFor(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPmpDeal("1");
        PmpDealPage pmpDeal=new PmpDealPage(driver);
        pmpDeal.clickOnDownArrowIcon("testDeal");
        pmpDeal.clickOnAddTargetButton("testDeal");
        pmpDeal.clickOnSelectTargetOption();
        pmpDeal.selectOption("IMP_SIZE");
        pmpDeal.enterTargetValue("10");
        pmpDeal.clickOnSaveTargetData();
        Assert.assertEquals(pmpDeal.getValidationMessage(),"Entered value does not satisfy validation");
        pmpDeal.enterTargetValue("10x20");
        pmpDeal.clickOnSaveTargetData();
        waitFor(2000);
        Assert.assertTrue(pmpDeal.isPmpdealTargetDetailsDisplayed("testDeal","IMP_SIZE"));
        Assert.assertTrue(pmpDeal.isPmpdealTargetDetailsDisplayed("testDeal","10x20"));
        extenttest.log(LogStatus.PASS, "add pmp deal target", extenttest.addScreenCapture(captureScreenshot("tc20", "order_set20")));
    }




}
