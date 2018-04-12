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

    @FindBy(xpath="//div[@class='Select-placeholder' and text()='Select Entity']")
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
        explicitWait(headerElement,3000);
        return headerElement.getText();
    }

    public void clickOnEntitySelectOption()
    {
        explicitWait(entityElement,3000);
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
            explicitWait(element, 3000);
            element.click();
        }
    }

    public void clickOnEntityNameSelectOption()
    {
        explicitWait(entityNameElement,3000);
        entityNameElement.click();
    }

    public void clickOnFeatureNameSelectOption()
    {
        explicitWait(selectFeatureElement,3000);
        selectFeatureElement.click();
    }

    public void enterDataField(String data)
    {
        explicitWait(dataElement,3000);
        dataElement.sendKeys(data);

    }

    public void clickOnSaveButton()
    {
        explicitWait(saveElement,3000);
        saveElement.click();
    }

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

    public void clickOnEdit(String bidderId)
    {

            String newXpath = editIconXpath.replace("bidderId", bidderId);
            WebElement element = driver.findElement(By.xpath(newXpath));
            explicitWait(element, 3000);
            element.click();

    }

    public void clearAndUpdateData(String data)
    {
        explicitWait(editDataField,3);
        editDataField.clear();
        editDataField.sendKeys(data);
    }

    public void clickOnEditSaveButton()
    {
        explicitWait(editSaveButton,3);
        editSaveButton.click();
    }
}
