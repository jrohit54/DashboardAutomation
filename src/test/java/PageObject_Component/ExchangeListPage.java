package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class ExchangeListPage extends BaseClass {
    String deleteMessageXpath="//div[contains(text(),'text1')]";
    String advertiserDomainXpath="//div[@class='list-item']//div/span[contains(@class, 'pub-id') and text() = 'idValue']/parent::div/parent::div/following::div[1]/a/button[contains(text(),'advertiser Domain')]";
    String advertiserCategoryXpath="//div[@class='list-item']//div/span[contains(@class, 'pub-id') and text() = 'idValue']/parent::div/parent::div/following::div[1]/a/button[contains(text(),'ad Category')]";
    String creativeIdXpath="//div[@class='list-item']//div/span[contains(@class, 'pub-id') and text() = 'idValue']/parent::div/parent::div/following::div[1]/a/button[contains(text(),'Creative id')]";
    String selectOptionXpath="//*[text()='text1']";
    String preferenceXpath="//div[@class='list-item']//div/span[contains(@class, 'pub-id') and text() = 'idValue']/parent::div/following::div[1]/a";

    @FindBy(xpath = "//div[@class='autocomplete active']")
    public WebElement autoComplete;

    @FindBy(id="search")
    public WebElement exchange;

    @FindBy(xpath = "//button[contains(text(),'Add Exchange')]")
    public WebElement addNewExchange;

    @FindBy(xpath="//div[@class='Select-value']")
    public WebElement selectNumberOfExchangeRecords;


    @FindBy(xpath="//div[contains(text(),'Entry made successfully')]")
    public List<WebElement> sucessfullAddedMessage;

    @FindBy(xpath="//div[@class='list-item']//span[@class='pub-id']")
    public List<WebElement> allExchangeList;


    public ExchangeListPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);

    }

    /**
     * will check if add new publisher button is present or not
     * @return
     */
    public boolean isAddNewExchangeButtonPresent()
    {

        return addNewExchange.isDisplayed();
    }

    /**
     * To click on add new publisher button
     */
    public void clickOnAddNewExchange()
    {
        explicitWait(addNewExchange,5);
        addNewExchange.click();

    }

    /**
     * will check if sucessfull message displayed after add publisher
     * @return
     */
    public boolean isExchangeAdded_SucessfullMessageDisplayed()
    {
        int size = sucessfullAddedMessage.size();
        return size==1;
    }
    public void clickOnSelectNumberOfExchangeDisplayed()
    {
        Actions action=new Actions(driver);
        action.moveToElement(selectNumberOfExchangeRecords).click().build().perform();

    }

    /**
     * to select the number  of records displayed
     * @param number
     */
    public void selectNumberOfRecords(String number)
    {
        String selectOption=selectOptionXpath.replace("text1", number);
        WebElement element = driver.findElement(By.xpath(selectOption));
        explicitWait(element,3);
        element.click();
    }

    /**
     * To verify if publisher id is being  displayed once sucessfully added
     * @param pubId
     * @return
     */
    public boolean isExchangeIdDisplayed(String pubId)
    {
        boolean flag=false;
        explicitWait(allExchangeList.get(0),3);
        for (WebElement element:allExchangeList) {

            String text = element.getText().replaceAll("[^A-Za-z0-9]", "");
            if (text.equalsIgnoreCase(pubId)) {
                flag = true;
                return flag;
            }

        }
        return flag;
    }

    /**
     * to click on the preference link on publisher list page
     * @param pubId
     */

    public void clickOnPreference(String pubId)
    {
        String preferenceLinkXpath=preferenceXpath.replace("idValue", pubId);
        WebElement element = driver.findElement(By.xpath(preferenceLinkXpath));
        explicitWait(element,3);
        element.click();
    }


    /**
     * to enter domain in the add publisher page
     * @param text
     */
    public void enterExchangeToSerach(String text)
    {
        exchange.sendKeys(text);
    }

    /**
     * to click on select exchange
     */
    public void clickOnAutoComplete()
    {
        autoComplete.click();
    }

}
