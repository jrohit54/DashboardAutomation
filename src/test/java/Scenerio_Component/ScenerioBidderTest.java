package Scenerio_Component;

import Generic_Component.BaseClass;
import PageObject_Component.BidderListPage;
import PageObject_Component.BidderPrefPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by rohit on 23/4/18.
 */
public class ScenerioBidderTest extends BaseClass {

    @Test(priority = 19)
    public void testAddBidderPreferenceSize() throws InterruptedException,IOException
    {
        extenttest = extentreport.startTest("Excuting the add bidder preference size");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC19" + " add bidder preference size");
        driver.navigate().to(bidderListUrl);
        Thread.sleep(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        Thread.sleep(1000);
        prefPage.clickOnAddSize();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.selectSize("70x70");
        prefPage.clickOnSaveButton();
        Assert.assertTrue(prefPage.isDataDisplayed("70x70"));
        extenttest.log(LogStatus.PASS, "add bidder preference size", extenttest.addScreenCapture(captureScreenshot("tc19", "order_set19")));

    }

    @Test(priority = 20)
    public void testDeletePreferenceSize() throws InterruptedException,IOException
    {
        extenttest = extentreport.startTest("Excuting the delete bidder preference size");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC20" + " delete bidder preference size");
        driver.navigate().to(bidderListUrl);
        Thread.sleep(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        Thread.sleep(1000);
        prefPage.clickOnDeleteIcon("70x70");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Size: 70x70"));
        prefPage.clickOnDeleteConfirmationButton();
        Thread.sleep(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("70x70"));
        extenttest.log(LogStatus.PASS, "delete bidder preference size", extenttest.addScreenCapture(captureScreenshot("tc20", "order_set20")));
    }

    @Test(priority = 21)
    public void testAddBidderPreferencePubliserDomian() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the add bidder preference publisher domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC21" + " add bidder preference publisher domain");
        driver.navigate().to(bidderListUrl);
        Thread.sleep(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        Thread.sleep(1000);
        prefPage.clickOnPublisherDomainTab();
        Thread.sleep(1000);
        prefPage.clickOnAddDomainMappingButton();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.enterText("http://maps.google.com");
        prefPage.clickOnSaveButton();
        Thread.sleep(1000);
        Assert.assertTrue(prefPage.isDataDisplayed("http://maps.google.com"));
        extenttest.log(LogStatus.PASS, "add bidder preference publisher domain", extenttest.addScreenCapture(captureScreenshot("tc21", "order_set21")));
    }

    @Test(priority = 22)
    public void testDeleteBidderPreferencePublisherDomain() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the delete bidder preference publisher domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC22" + " delete bidder preference publisher domain");
        driver.navigate().to(bidderListUrl);
        Thread.sleep(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        Thread.sleep(1000);
        prefPage.clickOnPublisherDomainTab();;
        prefPage.clickOnDeleteIcon("http://maps.google.com");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Domain: http://maps.google.com"));
        prefPage.clickOnDeleteConfirmationButton();
        Thread.sleep(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("http://maps.google.com"));
        extenttest.log(LogStatus.PASS, "delete bidder preference publisher domain", extenttest.addScreenCapture(captureScreenshot("tc22", "order_set22")));
    }

    @Test(priority = 23)
    public void testAddBidderPreferenceTagID() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the add bidder preference tag id");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC23" + " add bidder preference tag id ");
        driver.navigate().to(bidderListUrl);
        Thread.sleep(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        Thread.sleep(1000);
        prefPage.clickOnTagIdTab();
        Thread.sleep(1000);
        prefPage.clickOnAddTagIdButton();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.enterText("10528367");
        prefPage.clickOnSaveButton();
        Thread.sleep(1000);
        Assert.assertTrue(prefPage.isDataDisplayed("10528367"));
        extenttest.log(LogStatus.PASS, "add bidder preference tag id", extenttest.addScreenCapture(captureScreenshot("tc23", "order_set23")));
    }

    @Test(priority = 24)
    public void testDeleteBidderPreferenceTagId() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the delete bidder preference Tag id");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC24" + " delete bidder preference Tag id");
        driver.navigate().to(bidderListUrl);
        Thread.sleep(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        Thread.sleep(1000);
        prefPage.clickOnTagIdTab();
        prefPage.clickOnDeleteIcon("10528367");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Tag ID: 10528367"));
        prefPage.clickOnDeleteConfirmationButton();
        Thread.sleep(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("10528367"));
        extenttest.log(LogStatus.PASS, "delete bidder preference tag id", extenttest.addScreenCapture(captureScreenshot("tc24", "order_set24")));
    }

    @Test(priority = 25)
    public void testAddBidderPreferencePubSLD() throws InterruptedException, IOException {
        extenttest = extentreport.startTest("Excuting the add bidder preference publisher SLD");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC25" + " add bidder preference publisher SLD");
        driver.navigate().to(bidderListUrl);
        Thread.sleep(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        Thread.sleep(1000);
        prefPage.clickOnPublisherSLDTab();
        Thread.sleep(1000);
        prefPage.clickOnAddPublisherSLDButton();
        prefPage.clickOnSelectPublisherSelectOption();
        prefPage.selectOption("testName (12345)");
        prefPage.clickOnPrefereceSelectOption();
        prefPage.selectOption("Whitelist");
        prefPage.enterText("forbes");
        prefPage.clickOnSaveButton();
        Thread.sleep(1000);
        Assert.assertTrue(prefPage.isDataDisplayed("forbes"));
        extenttest.log(LogStatus.PASS, "add bidder preference publisher SLD", extenttest.addScreenCapture(captureScreenshot("tc25", "order_set25")));
    }

    @Test(priority =26)
    public void testDeleteBidderPreferencePubSLD() throws InterruptedException,IOException {
        extenttest = extentreport.startTest("Excuting the delete bidder preference Publisher SLD");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC24" + " delete bidder preference Publisher SLD");
        driver.navigate().to(bidderListUrl);
        Thread.sleep(1000);
        BidderListPage blp=new BidderListPage(driver);
        blp.clickOnPreference("1");
        BidderPrefPage prefPage=new BidderPrefPage(driver);
        Thread.sleep(1000);
        prefPage.clickOnPublisherSLDTab();
        Thread.sleep(1000);
        prefPage.clickOnDeleteIcon("forbes");
        Assert.assertTrue(prefPage.isMessageDisplayed("Delete Publisher SLD: forbes"));
        prefPage.clickOnDeleteConfirmationButton();
        Thread.sleep(1000);
        Assert.assertFalse(prefPage.isDataDisplayed("forbes"));
        extenttest.log(LogStatus.PASS, "delete bidder preference Publisher SLd", extenttest.addScreenCapture(captureScreenshot("tc26", "order_set26")));
    }



}
