package new_arch.drawer;

import io.appium.java_client.android.AndroidDriver;
import new_arch.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by groshkka on 22.10.17.
 */
public class DrawerHelper extends BaseHelper {
    private By locatorDrawer = By.xpath("//android.widget.ImageButton");
    private By locatorDrawerImage = By.id("imageView");
    private By locatorDrawerRealCity = By.id("text");
    private By locatorNumberSales = By.xpath("//android.widget.TextView[ancestor::android.widget.RelativeLayout[@resource-id,'nav_favorite']]");
    private By locatorNumberNotification = By.xpath("//android.widget.TextView[ancestor::android.widget.RelativeLayout[@resource-id,'nav_notification']]");
    private By locatorDrawerRealCity_ = By.xpath("//android.widget.TextView[ancestor::android.widget.RelativeLayout[@resource-id,'nav_city']]");

    public DrawerHelper(AndroidDriver driver) {
        super(driver);
    }

    public WebElement findDrawerCategory(String categoryName){
        String locator = "//android.widget.CheckedTextView[@text='" + categoryName + "']";
        return $(By.xpath(locator));
    }

    public WebElement findDrawer(){
        return $(locatorDrawer);
    }

    public WebElement findDrawerImage(){
        return $(locatorDrawerImage);
    }

    public WebElement findDrawerRealCity(){
        return $(locatorDrawerRealCity);
    }

    public WebElement findDrawerNumberSales(){
        return $(locatorNumberSales);
    }

    public WebElement findDrawerNumberNotification(){
        return $(locatorNumberNotification);
    }
}
