package new_arch.category_sign_in;

import io.appium.java_client.android.AndroidDriver;
import new_arch.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by groshkka on 22.10.17.
 */
public class SignInHelper extends BaseHelper {
    private By locatorEmailLayout = By.id("email_layout"); //Введите email адрес…
    private By locatorPasswordLayout = By.id("password_layout"); //Введите пароль…
    private By locatorEmailField = By.id("email");
    private By locatorPasswordField = By.id("password");
    private By locatorToggleStatusPassword = By.id("text_input_password_toggle");
    private By locatorCheckBoxRememberPassword = By.id("checkBox"); //Запомнить меня
    private By locatorSignInButton = By.id("email_sign_in_button");
    private By locatorLoginGoogle = By.id("gp");
    private By locatorLoginFB = By.id("fb");
    private By locatorLoginVK = By.id("vk");
    private By locatorTextInputError = By.id("textinput_error"); //Обязательное поле, Email адрес некорректный
    private By locatorRegistrationButton = By.xpath("android.widget.TextView[@text='Регистрация']");
    private By locatorRecoveryPasswordButton = By.xpath("android.widget.TextView[@text='Восстановление пароля']");

    public SignInHelper(AndroidDriver driver) {
        super(driver);
    }

    public WebElement findEmailField(){
        return $(locatorEmailField);
    }

    public WebElement findTextInputError(){
        return $(locatorTextInputError);
    }

    public WebElement findEmailFieldLayout(){
        return $(locatorEmailLayout);
    }

    public WebElement findPasswordField(){
        return $(locatorPasswordField);
    }

    public WebElement findPasswordFieldLayout(){
        return $(locatorPasswordLayout);
    }

    public WebElement findToggleStatusPasswordImage(){
        return $(locatorToggleStatusPassword);
    }

    public WebElement findRemmeberPasswordCheckBox(){
        return $(locatorCheckBoxRememberPassword);
    }

    public WebElement findSignInButton(){
        return $(locatorSignInButton);
    }

    public WebElement findRegistrationButton(){
        return $(locatorRegistrationButton);
    }

    public WebElement findRecoveryPasswordButton(){
        return $(locatorRecoveryPasswordButton);
    }

    public WebElement findLoginGoogleImage(){
        return $(locatorLoginGoogle);
    }

    public WebElement findLoginFBImage(){
        return $(locatorLoginFB);
    }

    public WebElement findLoginVKImage(){
        return $(locatorLoginVK);
    }

    public void waitRegistrationButton(){
        waitElement(locatorRegistrationButton, 5l);
    }
}
