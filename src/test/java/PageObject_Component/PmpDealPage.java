package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by rohit on 16/4/18.
 */
public class PmpDealPage extends BaseClass {

    String radioButtonxpath="//label[@class='radio-inline' and text()='inputText']";
    String selectOptionXpath="//div[@class='Select-menu-outer']//*[text()='text1']";
    String downArrowXath="//div[ text()='dealTitle' and contains(@class,'deal-title')]/parent::div/preceding-sibling::div/span[@class='icn-btm']";
    String addTargetingButtonXpath="//div[ text()='dealTitle' and contains(@class,'deal-title')]/parent::div/parent::div/parent::div/following::div[1]//div[text()='ADD TARGETING' and contains(@class,'btn')]";
    String targetDetailsXpath="//div[ text()='dealTitle' and contains(@class,'deal-title')]/parent::div/parent::div/parent::div/following::div//div[contains(@class,'flex-row')]";

    @FindBy(xpath = "//button[text()='Add Deal']")
    public WebElement addDealElement;

    @FindBy(xpath = "//input[@id='id']")
    public WebElement dealIdElement;

    @FindBy(xpath="//div[@class='Select-placeholder' and text()='Select Publisher']")
    public WebElement publisherIdElement;

    @FindBy(xpath="//input[@id='bidFloor']")
    public WebElement bidFloorElement;

    @FindBy(xpath="//input[@id='bidFloorCurrency']")
    public WebElement currencyElement;

    @FindBy(xpath="//button[@type='submit']")
    public WebElement saveElement;


    @FindBy(xpath="//div[contains(@class,'deal-title')]")
    public List<WebElement> listDealTitle;

    @FindBy(xpath="//div[@class='Select-placeholder' and text()='Select Target']")
    public WebElement selectTargetElement;

    @FindBy(xpath="//input[@id='value']")
    public WebElement valueElement;

    @FindBy(xpath="//span[@class='help-block']")
    public WebElement validationMessageElement;

    @FindBy(xpath="//span[text()='Save']")
    public WebElement saveTargetElement;

    public PmpDealPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * To click on "Add Deal button in pmp deal"
     */
    public void clickOnPmpDealButton() {
        explicitWait(addDealElement, 2);
        addDealElement.click();
    }

    /**
     * to enter the deal id in the add new deal page
     * @param dealId
     */
    public void enterDealId(String dealId) {
        explicitWait(dealIdElement,1);
        dealIdElement.sendKeys(dealId);

    }

    /**
     * to select the radio button option in the add new deal page
     * @param text
     */
    public void selectRadioOption(String text) {
        String newXpath = radioButtonxpath.replace("inputText", text);
        WebElement element = driver.findElement(By.xpath(newXpath));
        explicitWait(element, 3);
        element.click();
    }

    /**
     * To click on publisher id select option
     */
    public void clickOnSelectPublisherId()
    {
        explicitWait(publisherIdElement,2);
        publisherIdElement.click();
    }


    /**
     * To select available option in select fields
     * @param name
     */
    public void selectOption(String ...name)
    {
        for (String name1: name) {
            String selectOption = selectOptionXpath.replace("text1", name1);
            WebElement element = driver.findElement(By.xpath(selectOption));
            explicitWait(element, 3);
            element.click();
        }
    }

    /**
     * To enter floor price in the bid floor field
     * @param text
     */
    public void enterBidFloor(String text)
    {
        explicitWait(bidFloorElement,2);
        bidFloorElement.sendKeys(text);
    }

    /**
     * To enter the currency in the bid floor currency field
     * @param text
     */
    public void enterBidFloorCurrency(String text)
    {
        explicitWait(currencyElement,2);
        currencyElement.sendKeys(text);
    }

    /**
     * To click on save button in add new deal page
     */
    public void clickOnSaveButton()
    {
        explicitWait(saveElement,2);
        saveElement.click();
    }


    /**
     * To verify if ad category is being  displayed once sucessfully added
     * @param pmpdeal
     * @return
     */
    public boolean isPmpDealDisplayed(String pmpdeal)
    {
        boolean flag=false;
        explicitWait(listDealTitle.get(0),3);
        for (WebElement element:listDealTitle) {

            String text = element.getText();
            if (text.equals(pmpdeal)) {
                flag = true;
                return flag;
            }

        }
        return flag;
    }

    /**
     * To click on down arrow icon in the deal list page
     * @param pmpdeal
     */
    public void clickOnDownArrowIcon(String pmpdeal) {
        String newXpath = downArrowXath.replace("dealTitle", pmpdeal);
        WebElement element = driver.findElement(By.xpath(newXpath));
        explicitWait(element, 3);
        element.click();
    }

    /**
     *  To click on "Add Targeting" button to add pmp deal filters
     * @param pmpdeal
     */
    public void clickOnAddTargetButton(String pmpdeal)
    {
        String newXpath = addTargetingButtonXpath.replace("dealTitle", pmpdeal);
        WebElement element = driver.findElement(By.xpath(newXpath));
        explicitWait(element, 3);
        element.click();
    }

    /**
     * To click on select target select option
     */
    public void clickOnSelectTargetOption()
    {
        explicitWait(selectTargetElement,2);
        selectTargetElement.click();
    }

    /**
     * To enter data in the value field for adding target
     * @param data
     */
    public void enterTargetValue(String data)
    {
       explicitWait(valueElement,2);
        valueElement.clear();
        valueElement.sendKeys(data);
    }

    /**
     * to get validation message for invalid data
     * @return
     */
    public String getValidationMessage()
    {
        explicitWait(validationMessageElement,4);
         return validationMessageElement.getText();
    }

    /**
     * To clcik on Save button for adding the target option
     */
    public void clickOnSaveTargetData()
    {
        explicitWait(saveTargetElement,2);
        saveTargetElement.click();
    }


    /**
     * to validate if data is displayed in feature mapping page
     * @param pmpDeal
     * @param targetData
     * @return
     */
    public boolean isPmpdealTargetDetailsDisplayed(String pmpDeal,String targetData)
    {
        boolean flag=false;
        String newXpath = targetDetailsXpath.replace("dealTitle", pmpDeal);
        List<WebElement> elements = driver.findElements(By.xpath(newXpath));
        for (WebElement element:elements) {

            String text = element.getText();
            if (text.equals(targetData)) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }
}
