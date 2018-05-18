package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPartnerPage extends BaseClass {
    @FindBy(xpath = "//div[@class='list-item header']/h2")
    public WebElement newPublisher;

    @FindBy(xpath = "//button[contains(text(),'Back')]")
    public WebElement backButton;

    @FindBy(id = "id")
    public WebElement ptrId;

    @FindBy(id = "name")
    public WebElement name;

    @FindBy(id = "parentId")
    public WebElement parentId;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(xpath = "//label[@class='radio-inline' and text()='Active']")
    public WebElement statusActive;

    @FindBy(xpath = "//label[@class='radio-inline' and text()='inactive']")
    public WebElement statusInactive;

    @FindBy(id = "partnerApiKey")
    public WebElement ptrApiKey;

    @FindBy(id = "paymentModeId")
    public WebElement pymtModeId;

    @FindBy(id = "partnerPassword")
    public WebElement ptrPaswd;

    @FindBy(id = "defaultTemplateId")
    public WebElement dfltTmpltId;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(xpath = "//button[contains(text(),'Cancel')][@name='button']")
    public WebElement cancelB;

    @FindBy(xpath = "//div[contains(text(),'Validation failed for classes')]")
    public WebElement serverFailureMessage;

    @FindBy(xpath = "//input[@id='domain']/parent::div/span[2]")
    public WebElement domainErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'Some validations are failing')]")
    public WebElement validationFailedMessage;

    public AddPartnerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * to enter pub id in the add publisher page
     *
     * @param text
     */
    public void enterPtrId(String text) {
        explicitWait(ptrId, 6);
        //explicitWait(categoryLabel,3);
        ptrId.clear();
        ptrId.sendKeys(text);
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
    public void enterparentId(String text) {
        parentId.clear();
        parentId.sendKeys(text);
    }

    /**
     * to enter email in the add publisher page
     *
     * @param text
     */
    public void enteremail(String text) {
        email.sendKeys(text);
    }

    /**
     * to enter first name in the add publisher page
     *
     * @param text
     */
    public void enterPaymentModeId(String text) {
        pymtModeId.sendKeys(text);
    }

    /**
     * to enter first name in the add publisher page
     *
     * @param text
     */
    public void enterPartnerApiId(String text) {
        ptrApiKey.sendKeys(text);
    }

    /**
     * to enter last name in add publisher page
     *
     * @param text
     */
    public void enterPassword(String text) {
        ptrPaswd.sendKeys(text);
    }

    /**
     * to enter domain in the add publisher page
     *
     * @param text
     */
    public void enterTemplateId(String text) {
        dfltTmpltId.sendKeys(text);
    }


    /**
     * to click on save button is the add publisher page
     */
    public void clickOnSaveButton() {
        saveButton.click();
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