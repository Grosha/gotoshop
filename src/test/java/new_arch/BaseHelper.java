package new_arch;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by groshkka on 22.10.17.
 */
public class BaseHelper {
    private AndroidDriver driver;

    public BaseHelper(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement $(By by) {
        waitElement(by, 10);
        return driver.findElement(by);
    }

    public void waitElement(By element, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException exception) {
            System.out.println(element + " does not apear");
        } catch (NoSuchElementException e) {
            System.out.println(element + " not found");
        }
    }
}
