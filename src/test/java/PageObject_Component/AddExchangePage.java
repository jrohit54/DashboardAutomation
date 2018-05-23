package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddExchangePage extends BaseClass {

    @FindBy(xpath = "//div[@class='list-item header']/h2")
    public WebElement newExchange;

    @FindBy(xpath = "//button[contains(text(),'Back')]")
    public WebElement backButton;

    @FindBy(id = "id")
    public WebElement excId;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "name")
    public WebElement name;

    @FindBy(id = "companyName")
    public WebElement companyName;

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(id = "companyUrl")
    public WebElement domain;

    @FindBy(id = "type")
    public WebElement type;


    @FindBy(xpath = "//button[@type=\'submit\']")
    public WebElement saveButton;


    @FindBy(xpath = "//button[contains(text(),'Cancel')][@name='button']")
    public WebElement cancelB;

    @FindBy(xpath = "//label[contains(@class, 'control-label') and text() = 'Category']")
    public WebElement categoryLabel;

    @FindBy(xpath = "//div[@class='list-item header']/h2")
    public WebElement subHeaderElement;

    @FindBy(xpath = "//header[@class='dashboard-header']/h1")
    public WebElement headerElement;

    @FindBy(xpath = "//input[@id='id']/parent::div/span[2]")
    public WebElement excIdErrorMessage;

    @FindBy(xpath = "//input[@id='email']/parent::div/span[2]")
    public WebElement emailErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'Validation failed for classes')]")
    public WebElement serverFailureMessage;

    @FindBy(xpath = "//input[@id='domain']/parent::div/span[2]")
    public WebElement domainErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'Some validations are failing')]")
    public WebElement validationFailedMessage;

    public AddExchangePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * to enter pub id in the add publisher page
     *
     * @param text
     */
    public void enterExcId(String text) {
        explicitWait(excId, 6);
        // explicitWait(categoryLabel,3);
        excId.clear();
        excId.sendKeys(text);
    }

    /**
     * to enter email in the add publisher page
     *
     * @param text
     */
    public void enterEmail(String text) {
        email.sendKeys(text);
    }

    /**
     * to enter name in the add publisher page
     *
     * @param text
     */
    public void enterName(String text) {
        name.sendKeys(text);
    }

    /**
     * to enter the company name in the add publisher page
     *
     * @param text
     */
    public void enterCompanyName(String text) {
        companyName.sendKeys(text);
    }

    /**
     * to enter first name in the add publisher page
     *
     * @param text
     */
    public void enterFirstName(String text) {
        firstName.sendKeys(text);
    }

    /**
     * to enter last name in add publisher page
     *
     * @param text
     */
    public void enterLastName(String text) {
        lastName.sendKeys(text);
    }

    /**
     * to enter domain in the add publisher page
     *
     * @param text
     */
    public void enterComapnyUrl(String text) {
        domain.sendKeys(text);
    }

    /**
     * to enter the category name in the add publisher page
     *
     * @param text
     */
    public void enterType(String text) {
        type.sendKeys(text);
    }

    /**
     * to click on save button is the add publisher page
     */
    public void clickOnSaveButton() {
        saveButton.click();
    }

    /**
     * to get the header text
     *
     * @return
     */
    public String getHeaderText() {
        return headerElement.getText();
    }

    /**
     * to get the sub header text
     *
     * @return
     */
    public String getSubHeaderText() {
        return subHeaderElement.getText();
    }

    /**
     * to get the error message for invalid pubid
     *
     * @return
     */
    public String getErrorMessageForExcId() {
        return excIdErrorMessage.getText();
    }

    /**
     * to get the  error message for invalid email
     */
    public String getErrorMessageForEmail() {
        return emailErrorMessage.getText();
    }

    /**
     * to get server side message
     */
    public String getServersideMessage() {
        return serverFailureMessage.getText();
    }

    /**
     * to get the error message for invalid domain
     *
     * @return
     */
    public String getErrorMessageForDomain() {
        return domainErrorMessage.getText();
    }


    /**
     * to get validation failed message
     */
    public String getValidationFailedMessage() {
        return validationFailedMessage.getText();
    }

}
