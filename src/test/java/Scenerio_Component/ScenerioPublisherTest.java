package Scenerio_Component;

import Generic_Component.BaseClass;
import Generic_Component.CustomizeReport;
import PageObject_Component.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.jayway.restassured.response.Response;


/**
 * Created by rohit on 8/2/18.
 */
@Listeners(CustomizeReport.class)
public class ScenerioPublisherTest extends BaseClass
{

    public static Logger log = Logger.getLogger(ScenerioPublisherTest.class);

    @Test(dataProviderClass = Dataprovider_Component.DataProviderClass.class, dataProvider = "PublisherDetails")
    public void testAddValidPublisher(String pubId, String email, String name, String compName, String fName, String lName, String domName, String catName) throws InterruptedException, IOException,SQLException {

        Response response = deletePublisherApi(pubId);
        Assert.assertEquals(response.statusCode(), 200);
        String query="select count(*) from publishers where pubId= '"+pubId+"'";
        statement=connection.createStatement();
        rs=statement.executeQuery(query);
        while(rs.next()) {
           final int count=rs.getInt(1);
           Assert.assertEquals(count,0,"count not matching in the data base");
        }
        log.info("verify that the publisher id not present in the DB");
        driver.navigate().to(publisherListUrl);
        log.info("Excuting the add publisher test case");
        extenttest = extentreport.startTest("add publisher");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC1" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        waitFor(2000);
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
        app.clickOnSelectPartner();
        waitFor(200);
        app.selectOption("AAX (8PR6YK195)");
        app.clickOnSaveButton();
        waitFor(1000);
        extenttest.log(LogStatus.PASS, "add valid publisher", extenttest.addScreenCapture(captureScreenshot("tc1", "order_set1")));
        Assert.assertTrue(plp.isPublisherAdded_SucessfullMessageDisplayed());
        waitFor(2000);
        plp.enterPublisherToSerach("12345");
        plp.clickOnAutoComplete();
        Assert.assertTrue(plp.isPublisherIdDisplayed(pubId));
        statement=connection.createStatement();
        rs=statement.executeQuery(query);
        while(rs.next()) {
            final int count=rs.getInt(1);
            Assert.assertEquals(count,1,"count is matching in the data base");
        }
        log.info("verify that the publisher id is present in the DB");
        log.info("test case executed");

    }

    @Test(dependsOnMethods = "testAddValidPublisher", dataProviderClass = Dataprovider_Component.DataProviderClass.class, dataProvider = "AdvDomainDetails")
    public void testAddAdvertiserDomain(String advDomain) throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Executing the add Advertiser domain test case");
        extenttest = extentreport.startTest("add advertiser domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC2" + " add advertiser domain");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        waitFor(3000);
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        Assert.assertTrue(prefPage.isAdvertiserDomainTabDisplayed());
        Assert.assertTrue(prefPage.isAdCategoryTabDisplayed());
        Assert.assertTrue(prefPage.isCreativeIdTabDisplayed());
        Assert.assertTrue(prefPage.isAttributeTabDisplayed());
        prefPage.clickOnAddDomainMapping();
        Assert.assertEquals(prefPage.getPublisherNameInSelectOption(), "testName (12345)");
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("Appnexus* (2)", "YBNCA (A) (4)");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField(advDomain);
        prefPage.clickOnSaveButton();
        waitFor(2000);
        extenttest.log(LogStatus.PASS, "add advertiser domain", extenttest.addScreenCapture(captureScreenshot("tc2", "order_set2")));
        Assert.assertTrue(prefPage.isAdvDomainDisplayed(advDomain));
        log.info("test case executed");

    }

    @Test(dependsOnMethods = "testAddValidPublisher")
    public void testAddAdCategory() throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Executing the add category test case");
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
        waitFor(2000);
        prefPage.clickOnAdCategoryTab();
        waitFor(2000);
        prefPage.clickOnAddCategoryButton();
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("Appnexus* (2)", "YBNCA (A) (4)");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField("IAB1,IAB2");
        prefPage.clickOnSaveButton();
        waitFor(2000);
        extenttest.log(LogStatus.PASS, "add ad category", extenttest.addScreenCapture(captureScreenshot("tc3", "order_set3")));
        Assert.assertTrue(prefPage.isCategoryDisplayed("IAB1"));
        log.info("test case executed");


    }


    @Test(dependsOnMethods = "testAddValidPublisher")
    public void testAddCreativeId() throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Executing the add crative id test case");
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
        waitFor(2000);
        prefPage.clickOnCreativeIdTab();
        waitFor(1000);
        prefPage.clickOnAddCreativeIdButton();
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("All Advertisers");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField("6112312,6322312");
        prefPage.clickOnSaveButton();
        waitFor(2000);
        extenttest.log(LogStatus.PASS, "add ad creative id", extenttest.addScreenCapture(captureScreenshot("tc4", "order_set4")));
        Assert.assertTrue(prefPage.isCreativeIdDisplayed("6112312"));
        log.info("test case executed");

    }

    @Test(dependsOnMethods = "testAddValidPublisher")
    public void testAddAttribute() throws InterruptedException, IOException {

        driver.navigate().to(publisherListUrl);
        log.info("Executing the add attribute id test case for Publisher");
        extenttest = extentreport.startTest("add attribute id for Publisher");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC5" + " add attribute id for Publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        Assert.assertTrue(prefPage.isAdvertiserDomainTabDisplayed());
        Assert.assertTrue(prefPage.isAdCategoryTabDisplayed());
        Assert.assertTrue(prefPage.isCreativeIdTabDisplayed());
        Assert.assertTrue(prefPage.isAttributeTabDisplayed());
        waitFor(2000);
        prefPage.clickOnAdAttributeTab();
        waitFor(2000);
        prefPage.clickOnAddAttributeIdButton();
        prefPage.clickOnSelectProvider();
        prefPage.selectOption("Appnexus* (2)", "YBNCA (A) (4)");
        prefPage.clickOnSelectPreference();
        prefPage.selectPreference("Whitelist");
        prefPage.enterDomainField("1,2");
        prefPage.clickOnSaveButton();
        waitFor(2000);
        extenttest.log(LogStatus.PASS, "add ad attribute id for Publisher", extenttest.addScreenCapture(captureScreenshot("tc5", "order_set5")));
        Assert.assertTrue(prefPage.isAttributeDisplayed("1"));
        log.info("test case executed");
    }


    @Test(dependsOnMethods = "testAddAdvertiserDomain")
    public void testDeleteAdvertiserDomain() throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Executing the delete advertiser domain test case");
        extenttest = extentreport.startTest("delete advertiser domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC6" + " delete advertiser domain");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        if (prefPage.isAdvDomainDisplayed("http://maps.google.com")) {
            prefPage.clickOnDeleteIconContainsAllProviders("http://maps.google.com");
            prefPage.clickOnDeleteButtonInConfirmPopup();
            waitFor(1000);
            extenttest.log(LogStatus.PASS, "delete advertiser domain", extenttest.addScreenCapture(captureScreenshot("tc6", "order_set6")));
            Assert.assertTrue(prefPage.isAdvDomainDeleteMessageDisplayed("http://maps.google.com"));
            waitFor(1000);
            Assert.assertFalse(prefPage.isAdvDomainDisplayed("http://maps.google.com"));
        }

        if (prefPage.isAdvDomainDisplayed("maps.google.com")) {
            prefPage.clickOnProvidersLink("maps.google.com");
            prefPage.clickOnDeleteIconWithProviderName("maps.google.com", "YBNCA (A)");
            prefPage.clickOnDeleteButtonInConfirmPopup();
            waitFor(1000);
            prefPage.clickOnDeleteIconWithProviderName("maps.google.com", "Appnexus");
            prefPage.clickOnDeleteButtonInConfirmPopup();
            waitFor(1000);
        }
        extenttest.log(LogStatus.PASS, "delete advertiser domain", extenttest.addScreenCapture(captureScreenshot("tc6", "order_set6.1")));
        log.info("test case executed");

    }

    @Test(dependsOnMethods = "testAddAdCategory")
    public void testDeleteAdCategory() throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Executing the delete ad category test case");
        extenttest = extentreport.startTest("delete ad category");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC6" + " delete ad category");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnAdCategoryTab();
        waitFor(2000);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        if (prefPage.isCategoryDisplayed("IAB1")) {
            prefPage.clickOnDeleteIconContainsAllProviders("IAB1");
            prefPage.clickOnDeleteButtonInConfirmPopup();
            waitFor(2000);
            Assert.assertFalse(prefPage.isCategoryDisplayed("IAB1"));
            extenttest.log(LogStatus.PASS, "delete ad category", extenttest.addScreenCapture(captureScreenshot("tc7", "order_set7")));
        }

        if (prefPage.isCategoryDisplayed("IAB2")) {
            prefPage.clickOnDeleteIconContainsAllProviders("IAB2");
            prefPage.clickOnDeleteButtonInConfirmPopup();
            waitFor(1000);
        }
        extenttest.log(LogStatus.PASS, "delete advertiser category", extenttest.addScreenCapture(captureScreenshot("tc7", "order_set7.1")));
        log.info("test case executed");
    }

    @Test(dependsOnMethods = "testAddCreativeId")
    public void testDeleteCreativeId() throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Executing the delete creative id test case");
        extenttest = extentreport.startTest("delete creative id");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC8" + " delete creative id");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        waitFor(1000);
        prefPage.clickOnCreativeIdTab();
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        if (prefPage.isCreativeIdDisplayed("6322312")) {
            prefPage.clickOnDeleteIconContainsAllProviders("6322312");
            prefPage.clickOnDeleteButtonInConfirmPopup();
            waitFor(1000);
            Assert.assertFalse(prefPage.isCreativeIdDisplayed("6322312"));
            extenttest.log(LogStatus.PASS, "delete creative id", extenttest.addScreenCapture(captureScreenshot("tc8", "order_set8")));
        }
        if (prefPage.isCreativeIdDisplayed("6112312")) {
            prefPage.clickOnDeleteIconContainsAllProviders("6112312");
            prefPage.clickOnDeleteButtonInConfirmPopup();
            waitFor(1000);
        }
        extenttest.log(LogStatus.PASS, "delete creative id", extenttest.addScreenCapture(captureScreenshot("tc8", "order_set8.1")));
        log.info("test case executed");
    }

    @Test(dependsOnMethods = "testAddAttribute")
    public void testDeleteAttribute() throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Executing the delete attribute test case for Publisher");
        extenttest = extentreport.startTest("delete attribute for Publisher");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC9" + " delete attribute for Publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        plp.clickOnPreference("12345");
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        waitFor(2000);
        prefPage.clickOnAdAttributeTab();
        waitFor(2000);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        if (prefPage.isAttributeDisplayed("1 (Audio Ad (Auto-Play))")) {
            prefPage.clickOnDeleteIconContainsAllProviders("1");
            prefPage.clickOnDeleteButtonInConfirmPopup();
            waitFor(1000);
            extenttest.log(LogStatus.PASS, "delete attribute", extenttest.addScreenCapture(captureScreenshot("tc9", "order_set9")));
            Assert.assertFalse(prefPage.isAttributeDisplayed("1 (Audio Ad (Auto-Play))"));
        }
        if (prefPage.isAttributeDisplayed("2 (Audio Ad (User Initiated))")) {
            prefPage.clickOnDeleteIconContainsAllProviders("2");
            prefPage.clickOnDeleteButtonInConfirmPopup();
        }
        extenttest.log(LogStatus.PASS, "delete advertiser category", extenttest.addScreenCapture(captureScreenshot("tc10", "order_set10.1")));
        log.info("test case executed");
    }

    @Test(dataProviderClass = Dataprovider_Component.DataProviderClass.class, dataProvider = "InvalidPubId")
    public void testAddPublisherWithNoPubId(String pubId) throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Executing the add publisher without pubid test case");
        extenttest = extentreport.startTest("add publisher without pubid");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC10" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        waitFor(2000);
        plp.clickOnAddNewPublisher();
        AddPublisherPage app = new AddPublisherPage(driver);
        Assert.assertEquals(app.getSubHeaderText(), "New Publisher");
        app.enterPubId(pubId);
        app.clickOnSelectPartner();
        waitFor(200);
        app.selectOption("AAX (8PR6YK195)");
        app.clickOnSaveButton();
        app.clickOnSaveButton();
        Assert.assertEquals(app.getErrorMessageForPubId(), "Publisher Id is a compulsory and Alphanumeric field");
        waitFor(1000);
        extenttest.log(LogStatus.PASS, "add publisher without pubid", extenttest.addScreenCapture(captureScreenshot("tc10", "order_set10")));
    }


    @Test(dataProviderClass = Dataprovider_Component.DataProviderClass.class, dataProvider = "InvalidEmailAddressPart1")
    public void testInvaildEmailIdFieldValidation(String email) throws InterruptedException, IOException {

        driver.navigate().to(publisherListUrl);
        log.info("Executing the add publisher with with invalid email");
        extenttest = extentreport.startTest("add publisher with  invalid email");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC11" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        waitFor(3000);
        plp.clickOnAddNewPublisher();
        AddPublisherPage app = new AddPublisherPage(driver);
        Assert.assertEquals(app.getSubHeaderText(), "New Publisher");
        app.enterEmail(email);
        app.clickOnSaveButton();
        waitFor(1000);
        Assert.assertEquals(app.getErrorMessageForEmail(), "Entered value is not a proper email");
        extenttest.log(LogStatus.PASS, "add publisher with invalid email", extenttest.addScreenCapture(captureScreenshot("tc11", "order_set11")));

    }

    @Test(dataProviderClass = Dataprovider_Component.DataProviderClass.class, dataProvider = "InvalidEmailAddressPart2")
    public void testInvalidEmailIdServerValidation(String email) throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Executing the add publisher with with invalid email");
        extenttest = extentreport.startTest("add publisher with invalid email");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC12" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        waitFor(2000);
        plp.clickOnAddNewPublisher();
        AddPublisherPage app = new AddPublisherPage(driver);
        Assert.assertEquals(app.getSubHeaderText(), "New Publisher");
        app.enterPubId("123");
        app.enterEmail(email);
        app.clickOnSelectPartner();
        waitFor(200);
        app.selectOption("AAX (8PR6YK195)");
        app.clickOnSaveButton();
        waitFor(1000);
        String message = app.getServersideMessage();
        Assert.assertTrue(message.contains("Validation failed for classes"));
        extenttest.log(LogStatus.PASS, "add publisher with invalid email", extenttest.addScreenCapture(captureScreenshot("tc12", "order_set12")));
    }

    @Test(dataProviderClass = Dataprovider_Component.DataProviderClass.class, dataProvider = "InvalidDomain")
    public void testDomainFieldValidation(String domain) throws InterruptedException, IOException {
        driver.navigate().to(publisherListUrl);
        log.info("Executing the add publisher with with invalid domain");
        extenttest = extentreport.startTest("add publisher with invalid domain");
        extenttest.log(LogStatus.PASS, "Executing the Testcase  " + "TC11" + " add publisher");
        PublisherListPage plp = new PublisherListPage(driver);
        waitFor(3000);
        plp.clickOnAddNewPublisher();
        AddPublisherPage app = new AddPublisherPage(driver);
        Assert.assertEquals(app.getSubHeaderText(), "New Publisher");
        app.enterPubId("123");
        app.enterDomain(domain);
        app.clickOnSelectPartner();
        waitFor(200);
        app.selectOption("AAX (8PR6YK195)");
        app.clickOnSaveButton();
        waitFor(1000);
        Assert.assertEquals(app.getErrorMessageForDomain(), "Entered value is not a proper domain");
        String message = app.getValidationFailedMessage();
        Assert.assertTrue(message.contains("Some validations are failing"));
        extenttest.log(LogStatus.PASS, "add publisher with invalid email", extenttest.addScreenCapture(captureScreenshot("tc13", "order_set13")));
    }
    @Test(dependsOnMethods = "testAddValidPublisher")
    public void testADdomainWithSpaceInDBAndVerifyUI() throws  SQLException
    {
        log.info("Executing the add ad domain with space in DB and verify in the UI");
        String query="insert into publisher_entity_preference(supply_entity,supply_id,demand_entity,demand_id,targetProperty,value,preference) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,"PUBLISHER");
        preparedStatement.setString(2,"12345");
        preparedStatement.setString(3,"BIDDER");
        preparedStatement.setString(4,"ALL");
        preparedStatement.setString(5,"AD_DOMAIN");
        preparedStatement.setString(6,"     aaaaaa.com");
        preparedStatement.setString(7,"BLACK_LIST");

        try {
             preparedStatement.execute();
        }
        catch (MySQLIntegrityConstraintViolationException e)
        {

            log.info("Got the exception while entry in the DB");
        }
        driver.navigate().to(publisherListUrl);
        PublisherListPage plp = new PublisherListPage(driver);
        waitFor(2000);
        plp.clickOnPreference("12345");
        waitFor(3000);
        PublisherPrefPage prefPage = new PublisherPrefPage(driver);
        Assert.assertEquals(prefPage.getHeaderText(), "Publisher Preference");
        prefPage.enterSeachTextInPublisherPref("aaaaaa.com");
        waitFor(1000);
        prefPage.clickOnAutoComplete();
        if (prefPage.isAdvDomainDisplayed("aaaaaa.com")) {
            prefPage.clickOnDeleteIconContainsAllProviders("aaaaaa.com");
            prefPage.clickOnDeleteButtonInConfirmPopup();
            waitFor(1000);
            Assert.assertTrue(prefPage.isAdvDomainDeleteMessageDisplayed("aaaaaa.com"));
            waitFor(1000);
            Assert.assertFalse(prefPage.isAdvDomainDisplayed("aaaaaa.com"));
        }

    }

}
