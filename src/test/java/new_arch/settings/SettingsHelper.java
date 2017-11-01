package new_arch.settings;

import io.appium.java_client.android.AndroidDriver;
import new_arch.BaseHelper;
import org.openqa.selenium.By;

/**
 * Created by groshkka on 01.11.17.
 */
public class SettingsHelper extends BaseHelper {

    private By locatorDrawer = By.xpath("//android.widget.ImageButton");

    public SettingsHelper(AndroidDriver driver) {
        super(driver);
    }
}
