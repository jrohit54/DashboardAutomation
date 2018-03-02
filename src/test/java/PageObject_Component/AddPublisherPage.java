package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by rohit on 8/2/18.
 */
public class AddPublisherPage extends BaseClass {


    @FindBy(xpath = "//div[@class='list-item header']/h2")
    public WebElement newPublisher;

    @FindBy(xpath="//button[contains(text(),'Back')]")
    public WebElement backButton;

    @FindBy(id="pubId")
    public WebElement pubId;

    @FindBy(id="email")
    public WebElement email;

    @FindBy(id="name")
    public WebElement name;

    @FindBy(id="companyName")
    public WebElement companyName;

    @FindBy(id="firstName")
    public WebElement firstName;

    @FindBy(id="lastName")
    public WebElement lastName;

    @FindBy(id="domain")
    public WebElement domain;

    @FindBy(id="categories")
    public WebElement categories;


    @FindBy(xpath="//button[@type=\'submit\']")
    public WebElement saveButton;


    @FindBy(xpath = "//button[contains(text(),'Cancel')][@name='button']")
    public WebElement cancelB;

    @FindBy(xpath="//label[contains(@class, 'control-label') and text() = 'Category']")
    public WebElement categoryLabel;

    @FindBy(xpath="//div[@class='list-item header']/h2")
    public WebElement subHeaderElement;

    @FindBy(xpath="//header[@class='dashboard-header']/h1")
    public WebElement headerElement;

    @FindBy(xpath="//input[@id='pubId']/parent::div/span[2]")
    public WebElement pubIdErrorMessage;

    @FindBy(xpath="//input[@id='email']/parent::div/span[2]")
    public WebElement emailErrorMessage;

    @FindBy(xpath="//div[contains(text(),'Validation failed for classes')]")
    public WebElement serverFailureMessage;

    @FindBy(xpath="//input[@id='domain']/parent::div/span[2]")
    public WebElement domainErrorMessage;

    @FindBy(xpath="//div[contains(text(),'Some validations are failing')]")
    public WebElement validationFailedMessage;

    public AddPublisherPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    /**
     * to enter pub id in the add publisher page
     * @param text
     */
    public void enterPubId(String text)  {
        explicitWait(pubId,6000);
        explicitWait(categoryLabel,3000);
        pubId.clear();
        pubId.sendKeys(text);
    }

    /**
     * to enter email in the add publisher page
     * @param text
     */
    public void enterEmail(String text)
    {
        email.sendKeys(text);
    }
    /**
     *to enter name in the add publisher page
     * @param text
     */
    public void enterName(String text)
    {
        name.sendKeys(text);
    }

    /**
     * to enter the company name in the add publisher page
     * @param text
     */
    public void enterCompanyName(String text)
    {
        companyName.sendKeys(text);
    }

    /**
     * to enter first name in the add publisher page
     * @param text
     */
    public void enterFirstName(String text)
    {
        firstName.sendKeys(text);
    }

    /**
     * to enter last name in add publisher page
     * @param text
     */
    public void enterLastName(String text)
    {
        lastName.sendKeys(text);
    }

    /**
     * to enter domain in the add publisher page
     * @param text
     */
    public void enterDomain(String text)
    {
        domain.sendKeys(text);
    }

    /**
     * to enter the category name in the add publisher page
     * @param text
     */
    public void enterCategory(String text)
    {
        categories.sendKeys(text);
    }

    /**
     * to click on save button is the add publisher page
     */
    public void clickOnSaveButton()
    {
        saveButton.click();
    }

    /**
     * to get the header text
     * @return
     */
    public String getHeaderText()
    {
        return headerElement.getText();
    }

    /**
     * to get the sub header text
     * @return
     */
    public String getSubHeaderText()
    {
        return subHeaderElement.getText();
    }

    /**
     * to get the error message for invalid pubid
     * @return
     */
    public String getErrorMessageForPubId()
    {
        return pubIdErrorMessage.getText();
    }

    /**
     * to get the  error message for invalid email
     */
     public String getErrorMessageForEmail()
     {
         return emailErrorMessage.getText();
     }

    /**
     * to get server side message
     */
    public String getServersideMessage()
    {
        return serverFailureMessage.getText();
    }

    /**
     * to get the error message for invalid domain
     * @return
     */
    public String getErrorMessageForDomain()
    {
        return domainErrorMessage.getText();
    }


    /**
     * to get validation failed message
     */
    public String getValidationFailedMessage()
    {
        return validationFailedMessage.getText();
    }
}
