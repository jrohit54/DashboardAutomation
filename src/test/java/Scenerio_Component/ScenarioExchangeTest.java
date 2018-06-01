package Scenerio_Component;

import Generic_Component.BaseClass;
import Generic_Component.CustomizeReport;
import PageObject_Component.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;

import org.apache.log4j.Logger;
import com.jayway.restassured.response.Response;

/**
 * Created by shilpy on 11/5/18.
 */
@Listeners(CustomizeReport.class)

public class ScenarioExchangeTest extends BaseClass {

    public static Logger log = Logger.getLogger(ScenarioExchangeTest.class);

    @Test(priority = 1, dataProviderClass = Dataprovider_Component.DataProviderClass.class, dataProvider = "ExchangeDetails")
    public void testAddValidExchange(String excId, String name, String email, String compName, String fName, String lName, String compUrl, String type) throws IOException {
        Response response = deleteAdvertiserApi(excId);
        Assert.assertEquals(response.statusCode(), 200);
        driver.navigate().to(exchangeListUrl);
        log.info("Executing Add Exchange Test Case");
        extenttest = extentreport.startTest("add exchange");
        extenttest.log(LogStatus.PASS, "Executing Testcase " + "TC1" + "add exchange");
        ExchangeListPage elp = new ExchangeListPage(driver);
        waitFor(1000);
        elp.clickOnAddNewExchange();
        AddExchangePage aep = new AddExchangePage(driver);
        aep.enterExcId(excId);
        aep.enterName(name);
        aep.enterEmail(email);
        aep.enterCompanyName(compName);
        aep.enterFirstName(fName);
        aep.enterLastName(lName);
        aep.enterComapnyUrl(compUrl);
        aep.enterType(type);
        aep.clickOnSaveButton();
        waitFor(1000);
        extenttest.log(LogStatus.PASS, "add valid exchange", extenttest.addScreenCapture(captureScreenshot("TC1", "order_set1")));
        Assert.assertTrue(elp.isExchangeAdded_SucessfullMessageDisplayed());
        log.info("Test case executed");
        waitFor(2000);
        elp.enterExchangeToSerach("111");
        elp.clickOnAutoComplete();
        Assert.assertTrue(elp.isExchangeIdDisplayed(excId));

    }

    @Test(priority = 2, dataProviderClass = Dataprovider_Component.DataProviderClass.class, dataProvider = "InValidExchangeId")
    public void testAddInValidExchange(String excId, String name, String email, String compName, String fName, String lName, String compUrl, String type) throws InterruptedException, IOException {
        driver.navigate().to(exchangeListUrl);
        log.info("Executing Add Invalid Exchange Id Test Case");
        extenttest = extentreport.startTest("invalid exchange id");
        extenttest.log(LogStatus.PASS, "Executing Testcase " + "TC2" + "add exchange");
        ExchangeListPage elp = new ExchangeListPage(driver);
        waitFor(1000);
        elp.clickOnAddNewExchange();
        AddExchangePage aep = new AddExchangePage(driver);
        Assert.assertEquals(aep.getSubHeaderText(), "New Exchange");
        aep.enterExcId(excId);
        aep.enterName(name);
        Assert.assertEquals(aep.getErrorMessageForExcId(), "Exchange Id should be a positive integer");
        waitFor(1000);
        extenttest.log(LogStatus.PASS, "add exchange with excid", extenttest.addScreenCapture(captureScreenshot("tc2", "order_set2")));
    }

    @Test(priority = 3, dataProviderClass = Dataprovider_Component.DataProviderClass.class, dataProvider = "InValidExchangeEmail")
    public void testAddInValidEmailExchange(String excId, String name, String email, String compName, String fName, String lName, String compUrl, String type) throws InterruptedException, IOException {
        driver.navigate().to(exchangeListUrl);
        log.info("Executing Add Invalid Exchange Id Test Case");
        extenttest = extentreport.startTest("invalid exchange id");
        extenttest.log(LogStatus.PASS, "Executing Testcase " + "TC3" + "add exchange");
        ExchangeListPage elp = new ExchangeListPage(driver);
        waitFor(1000);
        elp.clickOnAddNewExchange();
        AddExchangePage aep = new AddExchangePage(driver);
        Assert.assertEquals(aep.getSubHeaderText(), "New Exchange");
        aep.enterExcId(excId);
        aep.enterName(name);
        aep.enterEmail(email);
        aep.enterCompanyName(compName);
        Assert.assertEquals(aep.getErrorMessageForEmail(), "Entered value is not a proper email");
        waitFor(1000);
        extenttest.log(LogStatus.PASS, "add publisher with invalid email", extenttest.addScreenCapture(captureScreenshot("tc3", "order_set3")));

    }
}
