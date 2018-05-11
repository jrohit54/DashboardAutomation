package Scenerio_Component;

import Generic_Component.BaseClass;
import org.testng.annotations.DataProvider;
import Generic_Component.BaseClass;
import Generic_Component.CustomizeReport;
import Generic_Component.DBConnection;
import PageObject_Component.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
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
    @Test(priority = 51,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "ExchangeDetails")
    public void testAddValidExchange(String excId, String name, String email, String compName, String fName, String lName, String compUrl, String type) throws InterruptedException, IOException{
    Response response = deleteAdvertiserApi(excId);
    Assert.assertEquals(response.statusCode(),200);
    driver.navigate().to(exchangeListUrl);
    log.info("Executing Add Exchange Test Case");
    extenttest = extentreport.startTest("add exchange");
    extenttest.log(LogStatus.PASS,"Executing Testcase " + "TC51" + "add exchange");
    ExchangeListPage elp = new ExchangeListPage(driver);
    Thread.sleep(1000);
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
    Thread.sleep(1000);
    extenttest.log(LogStatus.PASS,"add valid exchange", extenttest.addScreenCapture(captureScreenshot("TC51","order_set1")));
    Assert.assertTrue(elp.isExchangeAdded_SucessfullMessageDisplayed());
    log.info("Test case executed");
    Thread.sleep(5000);
        elp.enterExchangeToSerach("111");
        elp.clickOnAutoComplete();
        Assert.assertTrue(elp.isExchangeIdDisplayed(excId));

    }

    @Test(priority = 52,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "InValidExchangeId")
    public void testAddInValidExchange(String excId, String name, String email, String compName, String fName, String lName, String compUrl, String type) throws InterruptedException, IOException{
        driver.navigate().to(exchangeListUrl);
        log.info("Executing Add Invalid Exchange Id Test Case");
        extenttest = extentreport.startTest("invalid exchange id");
        extenttest.log(LogStatus.PASS,"Executing Testcase " + "TC52" + "add exchange");
        ExchangeListPage elp = new ExchangeListPage(driver);
        Thread.sleep(1000);
        elp.clickOnAddNewExchange();
        AddExchangePage aep = new AddExchangePage(driver);
        Assert.assertEquals(aep.getSubHeaderText(),"New Exchange");
        aep.enterExcId(excId);
        aep.enterName(name);
        Assert.assertEquals(aep.getErrorMessageForExcId(),"Exchange Id should be a positive integer");
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "add exchange with excid", extenttest.addScreenCapture(captureScreenshot("tc52", "order_set52")));
    }

    @Test(priority = 53,dataProviderClass =Dataprovider_Component.DataProviderClass.class,dataProvider = "InValidExchangeEmail")
    public void testAddInValidEmailExchange(String excId, String name, String email, String compName, String fName, String lName, String compUrl, String type) throws InterruptedException, IOException{
        driver.navigate().to(exchangeListUrl);
        log.info("Executing Add Invalid Exchange Id Test Case");
        extenttest = extentreport.startTest("invalid exchange id");
        extenttest.log(LogStatus.PASS,"Executing Testcase " + "TC53" + "add exchange");
        ExchangeListPage elp = new ExchangeListPage(driver);
        Thread.sleep(1000);
        elp.clickOnAddNewExchange();
        AddExchangePage aep = new AddExchangePage(driver);
        Assert.assertEquals(aep.getSubHeaderText(),"New Exchange");
        aep.enterExcId(excId);
        aep.enterName(name);
        aep.enterEmail(email);
        aep.enterCompanyName(compName);
        Assert.assertEquals(aep.getErrorMessageForEmail(),"Entered value is not a proper email");
        Thread.sleep(1000);
        extenttest.log(LogStatus.PASS, "add publisher with invalid email", extenttest.addScreenCapture(captureScreenshot("tc9", "order_set9")));

    }
}
