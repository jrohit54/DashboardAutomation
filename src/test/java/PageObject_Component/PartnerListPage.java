package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PartnerListPage extends BaseClass {
    String selectOptionXpath = "//*[text()='text1']";
    String preferenceXpath = "//div[@class='list-item']//div/span[contains(@class, 'pub-id') and text() = 'idValue']/parent::div/following::div[1]/a";
    String editIconXpath = "//span[@class='pub-id' and text()='partnerId']/parent::div/parent::div/parent::div/div[2]//a[2]";
    String parentIdXpath = "//span[@class='pub-id' and text()='partnerId']/parent::div/parent::div/div[3]/div/span[text()='Parent ID']/following::span[1]";

    @FindBy(xpath = "//div[@class='autocomplete active']")
    public WebElement autoComplete;

    @FindBy(id = "search")
    public WebElement partner;

    @FindBy(xpath = "//button[contains(text(),'Add New Partner')]")
    public WebElement addNewPartner;

    @FindBy(xpath = "//div[@class='Select-value']")
    public WebElement selectNumberOfPartnerRecords;


    @FindBy(xpath = "//div[contains(text(),'Entry made successfully')]")
    public List<WebElement> sucessfullAddedMessage;

    @FindBy(xpath = "//div[@class='list-item']//span[@class='pub-id']")
    public List<WebElement> allPartnerList;


    public PartnerListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    /**
     * will check if add new publisher button is present or not
     *
     * @return
     */
    public boolean isAddNewPartnerButtonPresent() {

        return addNewPartner.isDisplayed();
    }

    /**
     * To click on add new publisher button
     */
    public void clickOnAddNewPartner() {
        explicitWait(addNewPartner, 5);
        addNewPartner.click();

    }

    /**
     * will check if sucessfull message displayed after add publisher
     *
     * @return
     */
    public boolean isPartnerAdded_SucessfullMessageDisplayed() {
        int size = sucessfullAddedMessage.size();
        return size == 1;
    }
    public void clickOnSelectNumberOfPartnerDisplayed() {
        Actions action = new Actions(driver);
        action.moveToElement(selectNumberOfPartnerRecords).click().build().perform();

    }

    /**
     * to select the number  of records displayed
     *
     * @param number
     */
    public void selectNumberOfRecords(String number) {
        String selectOption = selectOptionXpath.replace("text1", number);
        WebElement element = driver.findElement(By.xpath(selectOption));
        explicitWait(element, 3);
        element.click();
    }

    /**
     * To verify if publisher id is being  displayed once sucessfully added
     *
     * @param ptrId
     * @return
     */
    public boolean isPartnerIdDisplayed(String ptrId) {
        boolean flag = false;
        explicitWait(allPartnerList.get(0), 3);
        for (WebElement element : allPartnerList) {

            String text = element.getText().replaceAll("[^A-Za-z0-9]", "");
            if (text.equalsIgnoreCase(ptrId)) {
                flag = true;
                return flag;
            }

        }
        return flag;
    }

    /**
     * to click on the preference link on publisher list page
     *
     * @param ptrId
     */

    public void clickOnPreference(String ptrId) {
        String preferenceLinkXpath = preferenceXpath.replace("idValue", ptrId);
        WebElement element = driver.findElement(By.xpath(preferenceLinkXpath));
        explicitWait(element, 3);
        element.click();
    }

    /**
     * to enter domain in the add publisher page
     *
     * @param text
     */
    public void enterPartnerToSerach(String text) {
        partner.sendKeys(text);
    }

    /**
     * to click on select exchange
     */
    public void clickOnAutoComplete() {
        autoComplete.click();
    }


    /**
     * to click on the partner edit icon
     *
     * @param partnerId
     */
    public void clickOnPartnerEditIcon(String partnerId) {
        String newXpath = editIconXpath.replace("partnerId", partnerId);
        WebElement element = driver.findElement(By.xpath(newXpath));
        element.click();
    }

    /**
     * to get the parent id in list page
     *
     * @param partnerId
     */
    public String getParentIdForPartner(String partnerId) {
        String newXpath = parentIdXpath.replace("partnerId", partnerId);
        WebElement element = driver.findElement(By.xpath(newXpath));
        explicitWait(element, 3);
        return element.getText();
    }

}