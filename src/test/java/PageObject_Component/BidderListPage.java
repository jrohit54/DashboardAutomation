package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by rohit on 22/3/18.
 */
public class BidderListPage extends BaseClass {

    String selectOptionXpath="//*[text()='text1']";
    String bidderAdFormatsXpath="//div[@class='list-item']/div[@class='pub-data']//span[@class='pub-id' and text()='bidderId']/parent::div/following::div[2]//span[@class='label' and text()='AD Format' ]/following::span[1]/span";
    String bidderSupportedClientXpath="//div[@class='list-item']/div[@class='pub-data']//span[@class='pub-id' and text()='1']/parent::div/following::div[3]//span[@class='label' and text()='Supported Client']/following::span[1]/span";
    String plusIconXpath="//span[@class='add-row-icon']";
    String editIconXpath="//span[@class='pub-id' and text()='bidderId']/parent::div/parent::div/parent::div/div[2]//a[2]";

    @FindBy(xpath="//button[contains(text(),'Add New Bidder')]")
    public WebElement addNewBidder;

    @FindBy(xpath="//header[@class='dashboard-header']/h1")
    public WebElement headerElement;

    @FindBy(xpath="//input[@id='id']")
    public WebElement bidderIdElement;

    @FindBy(xpath="//input[@id='name']")
    public WebElement bidderNameElement;

    @FindBy(xpath="//div[@class='Select-placeholder' and text()='Select AD Format']")
    public WebElement adFormatElement;

    @FindBy(xpath="//div[@class='Select-placeholder' and text()='Select Supported Clients']")
    public WebElement supportedClientElement;

    @FindBy(xpath="//button[@type='submit']")
    public WebElement saveButtonElement;

    @FindBy(xpath="//div[@class='list-item']/div[@class='pub-data']//span[@class='pub-id']")
    List<WebElement> allBidderIds;

    @FindBy(xpath="//div[@class='Select-placeholder' and text()='Select Data Center']")
    public WebElement dataCenterElement;

    @FindBy(xpath="//input[@id='url']")
    public WebElement endPointElement;

    @FindBy(xpath="//a[text()='Show Param']")
    public WebElement showParamElement;

    @FindBy(xpath="//input[@id='queryParam']")
    public WebElement queryParamFieldElement;

    @FindBy(xpath="//input[@id='macro']")
    public WebElement macroFieldElement;

    @FindBy(xpath="//span[@class='delete-row-icon']")
    public WebElement deleteParamIcon;


    @FindBy(xpath="//div[contains(text(),'Changes were successfully done!')]")
    public  List<WebElement> sucessfullEditMessage;

    public BidderListPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    public void clickOnAddNewBidderButton()
    {
        explicitWait(addNewBidder,2);
        addNewBidder.click();
    }

    /**
     * to get the header text
     * @return
     */
    public String getHeaderText()
    {
        explicitWait(headerElement,3);
        return headerElement.getText();
    }

    /**
     * to enter bidder id
     * @param bidderId
     */
    public void enterBidderId(String bidderId)
    {
        explicitWait(bidderIdElement,3);
        bidderIdElement.sendKeys(bidderId);
    }

    /**
     * to enter bidder name
     * @param bidderName
     */
    public void enterBidderName(String bidderName)
    {
        explicitWait(bidderNameElement,3);
        bidderNameElement.sendKeys(bidderName);
    }

    /**
     * to click on ad format select option
     */
    public void clickOnAdFormatSelectOption()
    {
        explicitWait(adFormatElement,3);
        adFormatElement.click();
    }

    /**
     * to select available option in select fields
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
     * to click on support client select option
     */
    public void clickOnSupportedClientSelectOption()
    {
        explicitWait(supportedClientElement,3);
        supportedClientElement.click();
    }

    /**
     * to click on save button in add bidder page
     */
    public void clickOnSaveButton()
    {
        Actions action=new Actions(driver);
        action.moveToElement(saveButtonElement).build().perform();
        saveButtonElement.click();

    }

    /**
     * to find if bidder id is diplayed in bidder list page
     * @param bidderId
     * @return
     */
    public boolean isBidderIdDisplayed(String bidderId)
    {
        boolean flag=false;
        for(WebElement ele:allBidderIds)
        {
            String id=ele.getText().replaceAll("\\(|\\)","");
            if(id.equals(bidderId))
            {
                flag=true;
                return flag;
            }
        }
        return flag;
    }

    /**
     * to find if adformats are displayed in the bidder list page
     * @param bidderId
     * @param adFormatName
     * @return
     */
    public boolean isAdFormatDisplayedForBidder(String bidderId, String adFormatName)
    {
        boolean flag=false;
        String newXpath = bidderAdFormatsXpath.replace("bidderId", bidderId);
        List<WebElement> elements = driver.findElements(By.xpath(newXpath));
        for (WebElement element:elements) {

            String text = element.getText();
            if (text.equals(adFormatName)) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    /**
     * to find if supported client displayed in the bidder list page
     * @param bidderId
     * @param adFormatName
     * @return
     */
    public boolean isSupportedClientDisplayedForBidder(String bidderId, String adFormatName)
    {
        boolean flag=false;
        String newXpath = bidderSupportedClientXpath.replace("bidderId", bidderId);
        List<WebElement> elements = driver.findElements(By.xpath(newXpath));
        for (WebElement element:elements) {

            String text = element.getText();
            if (text.equals(adFormatName)) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    /**
     * to click on the data center select option
     */
    public void clickOnDataCenterSelectOption()
    {
        moveToElement(dataCenterElement);
        dataCenterElement.click();
    }

    /**
     * to enter the url in the endpoint field
     * @param endPoint
     */
    public void enterEndPoint(String endPoint)
    {
        moveToElement(endPointElement);
        endPointElement.sendKeys(endPoint);
    }

    /**
     * to click on show param button
     */
    public void clickOnShowParam()
    {
       moveToElement(showParamElement);
        showParamElement.click();
    }

    /**
     * to find if plus icon is getting displayed
     * @return
     */
    public boolean isPlusIconDisplayed()
    {
        if(driver.findElements(By.xpath(plusIconXpath)).size() !=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * to enter the data in the query param field
     * @param param
     */
    public void enterQueryParamField(String param)
    {
        moveToElement(queryParamFieldElement);
        queryParamFieldElement.sendKeys(param);

    }

    /**
     * to enter the data in the macro field
     * @param value
     */
    public void enterMacroField(String value)
    {
        moveToElement(macroFieldElement);
        macroFieldElement.sendKeys(value);

    }

    /**
     * to click on the bidder edit icon
     * @param bidderId
     */
    public void clickOnBidderEditIcon(String bidderId)
    {
        String newXpath = editIconXpath.replace("bidderId", bidderId);
        WebElement element = driver.findElement(By.xpath(newXpath));
        element.click();
    }

    /**
     * to move to element on the page using Actions
     * @param element
     */
    public void moveToElement(WebElement element)
    {
        Actions action=new Actions(driver);
        action.moveToElement(element).build().perform();

    }

    /**
     * to click on delete param icon
     */
    public void clickOnDeleteParamIcon()
    {
        moveToElement(deleteParamIcon);
        deleteParamIcon.click();
    }

    /**
     * will check if sucessfull message displayed after edit bidder
     * @return
     */
    public boolean isSucessfullMessageDisplayed()
    {
        int size = sucessfullEditMessage.size();
        return size==1;
    }

}
