package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by rohit on 8/2/18.
 */
public class PublisherListPage extends BaseClass{


    String deleteMessageXpath="//div[contains(text(),'text1')]";
    String advertiserDomainXpath="//div[@class='list-item']//div/span[contains(@class, 'pub-id') and text() = 'idValue']/parent::div/parent::div/following::div[1]/a/button[contains(text(),'advertiser Domain')]";
    String advertiserCategoryXpath="//div[@class='list-item']//div/span[contains(@class, 'pub-id') and text() = 'idValue']/parent::div/parent::div/following::div[1]/a/button[contains(text(),'ad Category')]";
    String creativeIdXpath="//div[@class='list-item']//div/span[contains(@class, 'pub-id') and text() = 'idValue']/parent::div/parent::div/following::div[1]/a/button[contains(text(),'Creative id')]";
    String selectOptionXpath="//*[text()='text1']";
    String preferenceXpath="//div[@class='list-item']//div/span[contains(@class, 'pub-id') and text() = 'idValue']/parent::div/following::div[1]/a";

    @FindBy(xpath = "//button[contains(text(),'Add New Publisher')]")
    public WebElement addNewPublisher;

    @FindBy(xpath="//div[@class='Select-value']")
    public WebElement selectNumberOfPublisherRecords;


    @FindBy(xpath="//div[contains(text(),'Entry was successfully entered!')]")
    public  List<WebElement> sucessfullAddedMessage;

    @FindBy(xpath="//div[@class='list-item']//span[@class='pub-id']")
    public List<WebElement> allPublisherList;


    public PublisherListPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);

    }

    /**
     * will check if add new publisher button is present or not
     * @return
     */
    public boolean isAddNewPublisherButtonPresent()
    {

        return addNewPublisher.isDisplayed();
    }

    /**
     * To click on add new publisher button
     */
    public void clickOnAddNewPublisher()
    {
        explicitWait(addNewPublisher,5000);
        addNewPublisher.click();

    }



    /**
     * will check if sucessfull message displayed after add publisher
     * @return
     */
    public boolean isPublisherAdded_SucessfullMessageDisplayed()
    {
        int size = sucessfullAddedMessage.size();
        return size==1;
    }

    /**
     * will check if sucessfull message displayed after delete publisher
     * @param pubId
     * @return
     */
    public boolean isPublisherDeleted_SucessfullMessageDisplayed(String pubId)
    {
        String deleteMessage="Successfully deleted publisher "+pubId;
        String newXpath = deleteMessageXpath.replace("text1", pubId);
        WebElement element = driver.findElement(By.xpath(newXpath));
        explicitWait(element,2000);
        return element.isDisplayed();

    }

    /**
     * will click on advertiser domain button corresponding to publisher id
     * @param id
     */
    public void clickOnAdvertiserDomain(String id)
    {
        String advertiserButtonXpath=advertiserDomainXpath.replace("idValue", id);
        WebElement element = driver.findElement(By.xpath(advertiserButtonXpath));
        explicitWait(element,3000);
        element.click();
    }

    /**
     * will click on ad category button corresponding to publisher id
     * @param id
     */
    public void clickOnAdCategory(String id)
    {
        String advertiserButtonXpath=advertiserCategoryXpath.replace("idValue", id);
        WebElement element = driver.findElement(By.xpath(advertiserButtonXpath));
        explicitWait(element,3000);
        element.click();
    }

    /**
     * will click on creative id corresponding to publisher id
     * @param id
     */
    public void clickOnCreativeId(String id)
    {
        String advertiserButtonXpath=creativeIdXpath.replace("idValue", id);
        WebElement element = driver.findElement(By.xpath(advertiserButtonXpath));
        explicitWait(element,3000);
        element.click();
    }
    public void clickOnSelectNumberOfPublisherDisplayed()
    {
        Actions action=new Actions(driver);
        action.moveToElement(selectNumberOfPublisherRecords).click().build().perform();

    }

    /**
     * to select the number  of records displayed
     * @param number
     */
    public void selectNumberOfRecords(String number)
    {
        String selectOption=selectOptionXpath.replace("text1", number);
        WebElement element = driver.findElement(By.xpath(selectOption));
        explicitWait(element,3000);
        element.click();
    }

    /**
     * To verify if publisher id is being  displayed once sucessfully added
     * @param pubId
     * @return
     */
    public boolean isPublisherIdDisplayed(String pubId)
    {
        boolean flag=false;
        explicitWait(allPublisherList.get(1),3000);
        for (WebElement element:allPublisherList) {

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
        explicitWait(element,3000);
        element.click();
    }


}





