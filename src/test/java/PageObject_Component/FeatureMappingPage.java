package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


/**
 * Created by rohit on 12/4/18.
 */
public class FeatureMappingPage extends BaseClass{
    String selectOptionXpath="//*[text()='text1']";
    String featureXpath="//span[@class='pub-id' and text()='bidderId' ]/parent::div/following::div[1]/div/div[@class='flex-row']/div[contains(@class,'td-feature')]";
    String featureValueXpath="//span[@class='pub-id' and text()='bidderId' ]/parent::div/following::div[1]/div/div[@class='flex-row']/div[2]";
    String editIconXpath="//span[@class='pub-id' and text()='bidderId' ]/parent::div/following::div[1]/div/div[2]/div[4]/span[contains(@class,'edit')]";


    @FindBy(xpath="//button[text()='Add Feature Mapping']")
    public WebElement addFeatureButton;

    @FindBy(xpath="//header[@class='dashboard-header']/h1")
    public WebElement headerElement;

    @FindBy(xpath="//div[@class='Select-placeholder' and text()='Select Entity Type']")
    public WebElement entityElement;

    @FindBy(xpath="//div[@class='Select-placeholder' and text()='Select Entity Id']")
    public WebElement entityNameElement;

    @FindBy(xpath="//div[@class='Select-placeholder' and text()='Select Feature']")
    public WebElement selectFeatureElement;

    @FindBy(id="data")
    public WebElement dataElement;

    @FindBy(xpath="//button[@type='submit']")
    public WebElement saveElement;

    @FindBy(id="value")
    public WebElement editDataField;

    @FindBy(id="save")
    public WebElement editSaveButton;

    public FeatureMappingPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    /**
     * to click on add feature mapping button
     */
    public void clickOnAddFeatureMapping()
    {
        explicitWait(addFeatureButton,2);
        addFeatureButton.click();
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
     * to click on entity select option
     */
    public void clickOnEntitySelectOption()
    {
        explicitWait(entityElement,3);
        entityElement.click();
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
     * to click on entity id select option
     */
    public void clickOnEntityNameSelectOption()
    {
        explicitWait(entityNameElement,3);
        entityNameElement.click();
    }

    /**
     * to click on feature name select option
     */
    public void clickOnFeatureNameSelectOption()
    {
        explicitWait(selectFeatureElement,3);
        selectFeatureElement.click();
    }

    /**
     * to enter the value in the data field
     * @param data
     */
    public void enterDataField(String data)
    {
        explicitWait(dataElement,3);
        dataElement.sendKeys(data);

    }

    /**
     * to click on save button in add new feature mapping page
     */
    public void clickOnSaveButton()
    {
        explicitWait(saveElement,3);
        saveElement.click();
    }

    /**
     * to validate if feature name is displayed
     * @param bidderId
     * @param featureName
     * @return
     */
    public boolean isFeatureNameDisplayed(String bidderId,String featureName)
    {
        boolean flag=false;
        String newXpath = featureXpath.replace("bidderId", bidderId);
        List<WebElement> elements = driver.findElements(By.xpath(newXpath));
        for (WebElement element:elements) {

            String text = element.getText();
            if (text.equals(featureName)) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    /**
     * to validate if data is displayed in feature mapping page
     * @param bidderId
     * @param featureData
     * @return
     */
    public boolean isFeatureDataDisplayed(String bidderId,String featureData)
    {
        boolean flag=false;
        String newXpath = featureValueXpath.replace("bidderId", bidderId);
        List<WebElement> elements = driver.findElements(By.xpath(newXpath));
        for (WebElement element:elements) {

            String text = element.getText();
            if (text.equals(featureData)) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    /**
     * to click on edit feature mapping entry
     * @param bidderId
     */
    public void clickOnEdit(String bidderId)
    {

            String newXpath = editIconXpath.replace("bidderId", bidderId);
            WebElement element = driver.findElement(By.xpath(newXpath));
            explicitWait(element, 3);
            element.click();

    }

    /**
     * to edit an feature mapping data
     * @param data
     */
    public void clearAndUpdateData(String data)
    {
        explicitWait(editDataField,3);
        editDataField.clear();
        editDataField.sendKeys(data);
    }

    /**
     * to click on save button in edit scenerio
     */
    public void clickOnEditSaveButton()
    {
        explicitWait(editSaveButton,3);
        editSaveButton.click();
    }
}
