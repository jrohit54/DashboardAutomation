package PageObject_Component;

import Generic_Component.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by rohit on 24/5/18.
 */
public class LoginPage extends BaseClass {

    @FindBy(id="buttonGauth")
    public List<WebElement> signInButtonElements;

    @FindBy(id="identifierId")
    public WebElement emailElement;

    @FindBy(xpath="//span[text()='Next']")
    public WebElement nextButtonElement;

    @FindBy(name="password")
    public WebElement passwordElement;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * to check if sign in  with google displayed
     * @return
     */
    public boolean isSignInWithGoogleDisplayed()
    {
        boolean flag=false;
        if(signInButtonElements.size()>0)
        {
            flag=true;
            return  flag;
        }

        return flag;
    }

    public void clickOnSignInWithGoogle()
    {
        signInButtonElements.get(0).click();
    }


    public void enterEmailField(String text)
    {
        explicitWait(emailElement,6);
        emailElement.sendKeys(text);

    }


    public void clickOnNextButton()
    {
        explicitWait(nextButtonElement,3);
        nextButtonElement.click();

    }


    public void enterPasswordField(String text)
    {
        explicitWait(passwordElement,6);
        passwordElement.sendKeys(text);

    }

}
