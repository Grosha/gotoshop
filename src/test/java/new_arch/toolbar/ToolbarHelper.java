package new_arch.toolbar;

import io.appium.java_client.android.AndroidDriver;
import new_arch.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by groshkka on 22.10.17.
 */
public class ToolbarHelper extends BaseHelper {
    private By locatorToolbarTitle = By.className("android.widget.TextView");
    private By locatorToolbarSearch = By.id("action_search_category");

    public ToolbarHelper(AndroidDriver driver) {
        super(driver);
    }

    public WebElement findToolbarTitle(){
        return $(locatorToolbarTitle);
    }

    public WebElement findToolbarSearch(){
        return $(locatorToolbarSearch);
    }
}
