package new_arch.category_sign_in.person_info;

import io.appium.java_client.android.AndroidDriver;
import new_arch.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by groshkka on 23.10.17.
 */
public class PersonInfoHelper extends BaseHelper {
    private By locatorPersonFieldEmail = By.id("email");
    private By locatorPersonFieldPassword = By.id("password");
    private By locatorPersonFieldRepeatPassword = By.id("password_again");
    private By locatorPersonFieldOldPassword = By.id("passwordOld");
    private By locatorPersonButtonSave = By.id("saveButton");
    private By locatorPersonFieldNickname = By.id("nickname");
    private By locatorPersonLogOutButton = By.xpath("//android.widget.TextView[@text='Выйти из аккаунта']");

    public PersonInfoHelper(AndroidDriver driver) {
        super(driver);
    }

    public WebElement findPersonFieldPassword(){
        return $(locatorPersonFieldPassword);
    }

    public WebElement findPersonFieldRepeatPassword(){
        return $(locatorPersonFieldRepeatPassword);
    }

    public WebElement findPersonFieldEmail(){
        return $(locatorPersonFieldEmail);
    }

    public WebElement findPersonFieldOldPassword(){
        return $(locatorPersonFieldOldPassword);
    }

    public WebElement findPersonSaveButton(){
        return $(locatorPersonButtonSave);
    }

    public WebElement findPersonFieldNickName(){
        return $(locatorPersonFieldNickname);
    }

    public WebElement findPersonLogOutButton(){
        return $(locatorPersonLogOutButton);
    }
}
