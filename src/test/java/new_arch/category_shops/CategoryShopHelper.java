package new_arch.category_shops;

import io.appium.java_client.android.AndroidDriver;
import new_arch.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by groshkka on 22.10.17.
 */
public class CategoryShopHelper extends BaseHelper {
    private By locatorShopInput = By.id("shop_input");

    public CategoryShopHelper(AndroidDriver driver) {
        super(driver);
    }

    public WebElement findShopInputField(){
        return $(locatorShopInput);
    }
}
