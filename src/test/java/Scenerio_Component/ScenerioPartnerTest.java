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
        log.info("Excuting the add partner test case");
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


}