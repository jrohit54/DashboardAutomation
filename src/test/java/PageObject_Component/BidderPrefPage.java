package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by rohit on 18/4/18.
 */
public class BidderPrefPage extends BaseClass {


    String selectOptionXpath = "//*[text()='text1']";
    String sizeElementXpath = "//label[@class='custom-check-inline' and text()='size']/span";
    String deleteIconXpath="//div[@class='label-wrap ']/label[contains(text(),'size')]/parent::div/following::div//i[@class='ico-delete1']";
    String textXpath="//span[text()='text1']";

    @FindBy(xpath = "//button[text()='Add Size']")
    public WebElement sizeElement;

    @FindBy(xpath = "(//div[@class='Select-placeholder' and text()='Select Publisher'])[2]")
    public WebElement allSelectPublisherElement;

    @FindBy(xpath = "//span[@class='Select-value-label' and text()='Blacklist']")
    public WebElement prefSelectOptionElement;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveElement;

    @FindBy(xpath = "//div[@class='label-wrap ']/label")
    public List<WebElement> allSizeData;

    @FindBy(xpath="//button[text()='Delete']")
    public WebElement deleteButtonElement;

    @FindBy(xpath="//div[@class='tab']/span[text()='Publisher Domain']")
    public WebElement pubDomainElement;

    @FindBy(xpath="//button[text()='Add Domain Mapping']")
    public WebElement addDomainButtonElement;

    @FindBy(xpath="//textarea[@id='formControlsTextarea']")
    public WebElement textFieldElement;

    @FindBy(xpath="//div[@class='tab']/span[text()='Tag ID']")
    public WebElement tagIdTabElement;

    @FindBy(xpath="//button[text()='Add Tag ID']")
    public WebElement addTagIdButtonElement;

    @FindBy(xpath="//div[@class='tab']/span[text()='Publisher SLD']")
    public WebElement pubSLDTabElement;

    @FindBy(xpath="//button[text()='Add Publisher SLD']")
    public WebElement addPubSLDButtonElement;

    @FindBy(xpath="//div[@class='tab']/span[text()='Device Type']")
    public WebElement deviceTypeTabElement;

    @FindBy(xpath = "//button[text()='Add Device Type']")
    public WebElement addDeviceTypeButtonElement;

    @FindBy(xpath="//div[@class='tab']/span[text()='Country']")
    public WebElement countryTabElement;

    @FindBy(xpath="//button[text()='Add Country']")
    public WebElement addCountryButton;

    public BidderPrefPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * To click on add size button
     */
    public void clickOnAddSize() {
        explicitWait(sizeElement, 2);
        sizeElement.click();
    }

    /**
     * To click on select publisher select option
     */
    public void clickOnSelectPublisherSelectOption() {
        explicitWait(allSelectPublisherElement, 1);
        allSelectPublisherElement.click();
    }


    /**
     * to select available option in select fields
     *
     * @param name
     */
    public void selectOption(String... name) {
        for (String name1 : name) {
            String selectOption = selectOptionXpath.replace("text1", name1);
            WebElement element = driver.findElement(By.xpath(selectOption));
            explicitWait(element, 3);
            element.click();
        }
    }

    /**
     * To click on select preference select option
     */
    public void clickOnPrefereceSelectOption() {
        explicitWait(prefSelectOptionElement, 1);
        prefSelectOptionElement.click();

    }

    /**
     * to select size checkbox
     *
     * @param size
     */
    public void selectSize(String size) {

        String newXpath = sizeElementXpath.replace("size", size);
        WebElement element = driver.findElement(By.xpath(newXpath));
        explicitWait(element, 3);
        element.click();
    }

    /**
     * To click on save button
     */
    public void clickOnSaveButton() {
        explicitWait(saveElement, 2);
        saveElement.click();
    }

    /**
     * To validate if size is present in list
     * @param size
     * @return
     */
    public boolean isDataDisplayed(String size)
    {
        boolean flag=false;
        for (WebElement element:allSizeData) {

            String text = element.getText();
            if (text.equals(size)) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    /**
     * to select size checkbox
     *
     * @param size
     */
    public void clickOnDeleteIcon(String size) {

        String newXpath = deleteIconXpath.replace("size", size);
        WebElement element = driver.findElement(By.xpath(newXpath));
        explicitWait(element, 3);
        element.click();
    }

    /**
     * To validate if text is displayed or not
     */
    public boolean isMessageDisplayed(String message)
    {
        boolean flag=false;
        String newXpath = textXpath.replace("text1", message);
        if(driver.findElements(By.xpath(newXpath)).size()>0)
        {
            flag=true;
        }
        return flag;
    }

    /**
     * To click on delete button in confirmation popup
     */
     public void clickOnDeleteConfirmationButton()
     {
         explicitWait(deleteButtonElement,2);
         deleteButtonElement.click();

     }

    /**
     * To click on publisher domain tab in bidder preference
     */
    public void clickOnPublisherDomainTab()
    {
        explicitWait(pubDomainElement,2);
        pubDomainElement.click();
    }

    /**
     * To click on add domain mapping button
     */
    public void clickOnAddDomainMappingButton()
    {
        explicitWait(addDomainButtonElement,1);
        addDomainButtonElement.click();
    }

    /**
     * To enter text in textfield
     */
    public void enterText(String text)
    {
        explicitWait(textFieldElement,2);
        textFieldElement.sendKeys(text);

    }

    /**
     * To click on tag id tab in bidder preference
     */
    public void clickOnTagIdTab()
    {
        explicitWait(tagIdTabElement,2);
        tagIdTabElement.click();
    }

    /**
     * To click on add tag id button
     */
     public void clickOnAddTagIdButton()
     {
         explicitWait(addTagIdButtonElement,2);
         addTagIdButtonElement.click();
     }

    /**
     * To click on publisher SLD tab in Bidder Preference
     */
     public void clickOnPublisherSLDTab()
     {
         explicitWait(pubSLDTabElement,2);
         pubSLDTabElement.click();
     }
    /**
     * To click on add publisher SLD button
     */
     public void clickOnAddPublisherSLDButton()
     {
         explicitWait(addPubSLDButtonElement,2);
         addPubSLDButtonElement.click();

     }

    /**
     * To click on device type tab in Bidder Preference
     */
    public void clickOnDeviceTypeTab()
    {
        explicitWait(deviceTypeTabElement,2);
        deviceTypeTabElement.click();
    }

    /**
     * To click on add device type button
     */
    public void clickOnAddDeviceTypeButton()
    {
        explicitWait(addDeviceTypeButtonElement,2);
        addDeviceTypeButtonElement.click();
    }

    /**
     * To click on country tab in bidder preference
     */
    public void clickOnCountryTab()
    {
        explicitWait(countryTabElement,2);
        countryTabElement.click();;
    }

    /**
     * To click on add country button
     */
    public void clickOnAddCoutryButton()
    {
        explicitWait(addCountryButton,2);
        addCountryButton.click();
    }
}
