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

        Response response = deletePartnerApi(ptrId);
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

}