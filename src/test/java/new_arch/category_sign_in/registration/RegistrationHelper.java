package new_arch.category_sign_in.registration;

import io.appium.java_client.android.AndroidDriver;
import new_arch.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by groshkka on 26.10.17.
 */
public class RegistrationHelper extends BaseHelper {
    private By locatorRegistrationNameField = By.id("nickname");
    private By locatorRegistrationEmailField = By.id("email");
    private By locatorRegistrationPasswordField = By.id("password");
    private By locatorRegistrationRepeatPasswordField = By.id("password_again");
    private By locatorRegistrationSignInButton = By.id("email_sign_in_button");

    public RegistrationHelper(AndroidDriver driver) {
        super(driver);
    }

    public WebElement findRegistrationNameField(){
        return $(locatorRegistrationNameField);
    }

    public WebElement findRegistrationEmailField(){
        return $(locatorRegistrationEmailField);
    }

    public WebElement findRegistrationPasswordField(){
        return $(locatorRegistrationPasswordField);
    }

    public WebElement findRegistrationRepeatPasswordField(){
        return $(locatorRegistrationRepeatPasswordField);
    }

    public WebElement findRegistrationSignInButton(){
        return $(locatorRegistrationSignInButton);
    }

    public void waitRegistrationSignInButton(){
        waitElement(locatorRegistrationSignInButton, 5l);
    }
}
