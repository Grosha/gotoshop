package new_arch.category_sign_in.recovery_password;

import io.appium.java_client.android.AndroidDriver;
import new_arch.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by groshkka on 26.10.17.
 */
public class RecoveryPasswordHelper extends BaseHelper {
    private By locatorRecoveryTextInfo = By.xpath("//android.widget.TextView[ancestor::android.widget.Button[@resource-id,'email_sign_in_button']]");
    private By locatorRecoveryEmailField = By.id("email");
    private By locatorRecoverySendButton = By.id("email_sign_in_button");
    public static String recoverytextInfo = "Введите email адрес, с которым Вы регистрировались. На этот email Вам придет инструкция по восстановлению пароля.";

    public RecoveryPasswordHelper(AndroidDriver driver) {
        super(driver);
    }

    public WebElement findRecoveryEmailFiled(){
        return $(locatorRecoveryEmailField);
    }

    public WebElement findRecoveryTextInfo(){
        return $(locatorRecoveryTextInfo);
    }

    public WebElement findRecoverySendButton(){
        return $(locatorRecoverySendButton);
    }
}
