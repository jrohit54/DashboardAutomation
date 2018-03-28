package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by rohit on 9/2/18.
 */
public class PublisherPrefPage extends BaseClass{

    String selectOptionXpath="//*[text()='text1']";
    String deleteAllProviderXpath="//div[@class='flex-container']/div//label[text()='domainName']/parent::div/following::div[1]//i[@class='ico-delete1']";
    String providerXpath="//div[@class='flex-container']/div//label[text()='domainName']/following::span[1]";
    String deleteMessageXpath="//div[contains(text(),'text1')]";
    String deleteDomainWithProviderXpath="//div[@class='flex-container']/div//label[text()='domainName']/parent::div/parent::div/following::ul[1]//span[contains(text(),'providerName')]/parent::div/following::div[1]/i";



    @FindBy(xpath="//header[@class='dashboard-header']/h1")
    public WebElement headerElement;

    @FindBy(xpath="//span[contains(text(),'Advertiser Domain')]")
    public WebElement advDomainTab;

    @FindBy(xpath="//span[contains(text(),'Ad Category')]")
    public WebElement adCategoryTab;

    @FindBy(xpath="//span[contains(text(),'Creative ID')]")
    public WebElement creativeIdTab;

    @FindBy(xpath="//span[contains(text(),'Attribute')]")
    public WebElement attributeTab;

    @FindBy(xpath="//input[@id='search'][@placeholder='Search by Domain Names']")
    public WebElement domainSearchField;

    @FindBy(xpath="//button[contains(text(),'Add Domain Mapping')]")
    public WebElement addDomainMappingButton;

    @FindBy(xpath="//button[contains(text(),'Remove Multiple')]")
    public WebElement removeMultiple;

    @FindBy(xpath = "//div[@class='Select-placeholder']")
    public  List<WebElement> selectProvider;

    @FindBy(xpath="//div[@class='Select-value']/span")
    public WebElement topRightSelectOption;

    @FindBy(xpath = "//div[@class='Select-value']/span[contains(text(),'Blacklist')]")
    public  WebElement selectPreference;

    @FindBy(xpath="//*[@id='formControlsTextarea']")
    public WebElement domainField;

    @FindBy(xpath="//button[contains(text(),'Save')]")
    public WebElement saveButton;

    @FindBy(css = "label.custom-check-inline")
    public  List<WebElement> listDomains;

    @FindBy(xpath="//button[@class='btn' and text()='Add Category']")
    public WebElement adCategoryButton;

    @FindBy(xpath="//button[@class='btn' and text()='Add Creative ID']")
    public WebElement addCreativeIdButton;

    @FindBy(xpath="//section[@class='body-wrap']//button[contains(text(),'Delete')]")
    public WebElement deleteButton;

    public PublisherPrefPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    /**
     * to get the header text
     * @return
     */
    public String getHeaderText()
    {
        explicitWait(headerElement,3000);
        return headerElement.getText();
    }

    /**
     * will check if advertiser domain tab is present
     * @return
     */
    public boolean isAdvertiserDomainTabDisplayed()
    {
        return advDomainTab.isDisplayed();
    }

    /**
     * will check if category domain tab is present
     * @return
     */
    public boolean isAdCategoryTabDisplayed()
    {
        return adCategoryTab.isDisplayed();
    }

    /**
     * will check if creative tab is present
     * @return
     */
    public boolean isCreativeIdTabDisplayed()
    {
        return creativeIdTab.isDisplayed();
    }

    /**
     * will check if attribute tab is present
     * @return
     */
    public boolean isAttributeTabDisplayed()
    {
      return attributeTab.isDisplayed();
    }

    /**
     * will click on add domain mapping button
     */
    public  void clickOnAddDomainMapping()
    {
        explicitWait(addDomainMappingButton,3000);
        addDomainMappingButton.click();
    }

    /**
     * to get the publisher name from the top right select option
     * @return
     */
    public String getPublisherNameInSelectOption()
    {
        explicitWait(topRightSelectOption,3000);
        return topRightSelectOption.getText();
    }

    /**
     * to click on click on select provider in add domain mapping page
     */
    public void clickOnSelectProvider()
    {
        selectProvider.get(1).click();
    }

    /**
     * to select the bidder from the list
     * @param name
     */
    public void selectOption(String ...name)
    {
        for (String name1: name) {
            String selectOption = selectOptionXpath.replace("text1", name1);
            WebElement element = driver.findElement(By.xpath(selectOption));
            explicitWait(element, 3000);
            element.click();
        }
    }
    /**
     * to click on click on preference select option
     *
     * */
    public void clickOnSelectPreference()
    {
        explicitWait(selectPreference,2000);
        selectPreference.click();
    }

    /**
     * to select the preference option
     * @param name
     */
    public void selectPreference(String name)
    {
        String selectOption=selectOptionXpath.replace("text1", name);
        WebElement element = driver.findElement(By.xpath(selectOption));
        explicitWait(element,3000);
        element.click();
    }

    /**
     * to enter the domain name in the field
     * @param domain
     */
    public void enterDomainField(String domain)
    {
        explicitWait(domainField,3000);
        domainField.sendKeys(domain);
    }

    /**
     * to click on save button while adding different target property
     * like advertiser domain, ad category ,creative id etc
     */
    public void clickOnSaveButton()
    {
        explicitWait(saveButton,2000);
        saveButton.click();
    }

    /**
     * To verify if adv domain is being  displayed once sucessfully added
     * @param domain
     * @return
     */
    public boolean isAdvDomainDisplayed(String domain)
    {
        boolean flag=false;
        explicitWait(listDomains.get(0),3000);
        for (WebElement element:listDomains) {

            String text = element.getText();
            if (text.equals(domain)) {
                flag = true;
                return flag;
            }

        }
        return flag;
    }

    /**
     * To click on ad category menu tab
     */
    public void clickOnAdCategoryTab()
    {
        explicitWait(adCategoryTab,2000);
        adCategoryTab.click();
    }



    /**
     * To click on creative id menu tab
     */
    public void clickOnCreativeIdTab()
    {
        explicitWait(creativeIdTab,2000);
        creativeIdTab.click();
    }
    /**
     * To click on ad category button under ad category tab
     */
    public void clickOnAddCategoryButton()
    {
        explicitWait(adCategoryButton,4000);
        adCategoryButton.click();

    }
    /**
     * To verify if ad category is being  displayed once sucessfully added
     * @param category
     * @return
     */
    public boolean isCategoryDisplayed(String category)
    {
        boolean flag=false;
        explicitWait(listDomains.get(0),3000);
        for (WebElement element:listDomains) {

            String text = element.getText();
            if (text.contains(category)) {
                flag = true;
                return flag;
            }

        }
        return flag;
    }

    /**
     * To click on add  creative id button under creative id tab
     */
    public void clickOnAddCreativeIdButton()
    {
        explicitWait(addCreativeIdButton,4000);
        addCreativeIdButton.click();

    }
    /**
     * To verify if creative id is being  displayed once sucessfully added
     * @param crid
     * @return
     */
    public boolean isCreativeIdDisplayed(String crid)
    {
        boolean flag=false;
        explicitWait(listDomains.get(1),3000);
        for (WebElement element:listDomains) {

            String text = element.getText();
            if (text.equals(crid)) {
                flag = true;
                return flag;
            }

        }
        return flag;
    }

    /**
     * will click on delete icon which include all child entries as well
     * @param value
     */

    public void clickOnDeleteIconContainsAllProviders(String value)
    {
        String deleteIconXpath=deleteAllProviderXpath.replace("domainName",value);
        WebElement element = driver.findElement(By.xpath(deleteIconXpath));
        explicitWait(element,3000);
        element.click();
    }

    /**
     * will click on delete button in the confirm popup
     * */
    public void clickOnDeleteButtonInConfirmPopup()
    {
        explicitWait(deleteButton,3000);
        deleteButton.click();

    }
    /**
     * will check if sucessfull message displayed after delete advertiser domain
     * @param domain
     * @return
     */
    public boolean isAdvDomainDeleteMessageDisplayed(String domain)
    {
        String deleteMessage="Successfully deleted Domain "+domain;
        String newXpath = deleteMessageXpath.replace("text1", domain);
        WebElement element = driver.findElement(By.xpath(newXpath));
        explicitWait(element,2000);
        return element.isDisplayed();

    }

    /**
     * to click on provider link coreesponding to adv domain
     * @param domain
     */

    public void clickOnProvidersLink(String domain)
    {

        String newXpath = providerXpath.replace("domainName", domain);
        WebElement element = driver.findElement(By.xpath(newXpath));
        explicitWait(element,2000);
        element.click();;
    }

    /**
     * To click on delete icon corresponding to provider name
     * @param domainName
     * @param providerName
     */

    public void clickOnDeleteIconWithProviderName(String domainName,String providerName)
    {

        String newXpath= deleteDomainWithProviderXpath.replace("domainName",domainName).replace("providerName",providerName);
        WebElement element = driver.findElement(By.xpath(newXpath));
        explicitWait(element,2000);
        element.click();
    }


}
