package Scenerio_Component;

import Generic_Component.BaseClass;
import Generic_Component.CustomizeReport;
import PageObject_Component.FeatureMappingPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by rohit on 15/5/18.
 */
@Listeners(CustomizeReport.class)
public class ScenerioFeatureMappingTest  extends BaseClass {

    @Test(priority = 1,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "validFeatureMappingDelatils",dependsOnMethods = "ScenerioBidderTest.testAddValidBidder")
    public void testAddFeatureMapping(String featureName,String data) throws InterruptedException, IOException {
        //to delete existing entry present in feature mapping for bidder 1
        deleteFeatureMappingData("1");
        log.info("Executing the add feature mapping");
        extenttest = extentreport.startTest("Excuting the add feature mapping");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC1" + " add feature mapping");
        driver.navigate().to(featureMappingUrl);
        waitFor(1000);
        FeatureMappingPage featurePage=new FeatureMappingPage(driver);
        featurePage.clickOnAddFeatureMapping();
        Assert.assertEquals(featurePage.getHeaderText(),"Add New Feature Mapping");
        featurePage.clickOnEntitySelectOption();
        waitFor(5000);
        featurePage.selectOption("BIDDER");
        waitFor(500);
        featurePage.clickOnEntityNameSelectOption();
        featurePage.selectOption("testBidderName (1)");
        featurePage.clickOnFeatureNameSelectOption();
        featurePage.selectOption(featureName);
        featurePage.enterDataField(data);
        featurePage.clickOnSaveButton();
        waitFor(3000);
        Assert.assertTrue(featurePage.isFeatureNameDisplayed("1",featureName));
        Assert.assertTrue(featurePage.isFeatureDataDisplayed("1",data));
        extenttest.log(LogStatus.PASS, "add feature mapping", extenttest.addScreenCapture(captureScreenshot("tc1", "order_set1")));
    }

    @Test(priority= 2)
    public void testEditFeatureMapping() throws InterruptedException, IOException {
        String editData="{\"ALL\":100}";
        log.info("Executing the edit feature mapping");
        extenttest = extentreport.startTest("Excuting the edit feature mapping");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC2" + " edit feature mapping");
        driver.navigate().to(featureMappingUrl);
        waitFor(1000);
        FeatureMappingPage featurePage=new FeatureMappingPage(driver);
        featurePage.clickOnEdit("1");
        featurePage.clearAndUpdateData(editData);
        featurePage.clickOnEditSaveButton();
        waitFor(3000);
        Assert.assertTrue(featurePage.isFeatureDataDisplayed("1",editData));
        extenttest.log(LogStatus.PASS, "add feature mapping", extenttest.addScreenCapture(captureScreenshot("tc2", "order_set2")));
    }

    @Test(priority=3,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "invalidFeatureMappingDetails")
    public void testInvalidAddFeatureMapping(String featureName,String data) throws InterruptedException,IOException
    {
        log.info("Executing the add  feature mapping with invalid data");
        extenttest = extentreport.startTest("Excuting the add feature mapping");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC3" + " add feature mapping");
        driver.navigate().to(featureMappingUrl);
        waitFor(2000);
        FeatureMappingPage featurePage=new FeatureMappingPage(driver);
        featurePage.clickOnAddFeatureMapping();
        Assert.assertEquals(featurePage.getHeaderText(),"Add New Feature Mapping");
        featurePage.clickOnEntitySelectOption();
        waitFor(500);
        featurePage.selectOption("BIDDER");
        waitFor(500);
        featurePage.clickOnEntityNameSelectOption();
        featurePage.selectOption("testBidderName (1)");
        featurePage.clickOnFeatureNameSelectOption();
        featurePage.selectOption(featureName);
        featurePage.enterDataField(data);
        featurePage.clickOnSaveButton();
        waitFor(1000);
        Assert.assertEquals(featurePage.getDataErrorTest(),"Value does not satisfy the feature Validations");
        extenttest.log(LogStatus.PASS, "add invalid feature mapping", extenttest.addScreenCapture(captureScreenshot("tc3", "order_set3")));
    }

}
