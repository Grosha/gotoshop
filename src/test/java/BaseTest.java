import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by groshkka on 07.11.2016.
 */
public class BaseTest implements Variable, Locators {
    protected AppiumDriver<WebElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "5.1");

        capabilities.setCapability("appPackage", packageGoToShop);
        capabilities.setCapability("appWaitPackage", packageGoToShop);
        capabilities.setCapability("appActivity", mainActivity);
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public void setDown() {
        driver.quit();
    }

    public void assertEqualsText(By locator, String text) {
        String textElement = driver.findElement(locator).getText();
        Assert.assertEquals(text, textElement);
    }

    public void assertEqualsInt(By locator, int number) {
        int numberElement = Integer.parseInt(driver.findElement(locator).getText());
        Assert.assertEquals(numberElement, number);
    }

    public AppiumDriver<WebElement> findElement(By by) {

        return driver;
    }

    public void timeOut(Long seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void openDrawer() throws InterruptedException {
        //open drawer
        Thread.sleep(5000);
        driver.findElement(locatorOpenDrawer).click();
        timeOut(10L);
    }

    public void enterToTheFeatureFromDrawer(By drawerFeatureName) throws InterruptedException {
        //open drawer
        openDrawer();
        //enter to the feature from drawer
        driver.findElement(drawerFeatureName).click();
        timeOut(10L);
    }

    public void openShop(By shopIdentofocation, boolean search) {
        //create list with shops
        List<WebElement> category = driver.findElements(openFirstShop);
        List<WebElement> shops = category.get(0).findElements(shopIdentofocation);

        String shopName = "";
        int random = new Random().nextInt(shops.size());
        if (search == true) {
            //get shop
            shopName = shops.get(random).getText();
            //enter shop's name
            driver.findElement(shopInput).sendKeys(shopName);
        } else {
            //get random shop

            shopName = shops.get(random).getText();
        }
        //find random shop and enter
        driver.findElement(By.name(shopName)).click();
        //check title
        assertEqualsText(toolbarTitle, shopName);
    }

    public void enterUserData(String email, String password, boolean remember) {
        //enter email and password - sign in
        driver.findElement(locatorFieldEmail).clear();
        driver.findElement(locatorFieldEmail).sendKeys(email);
        driver.findElement(locatorFieldPassword).sendKeys(password);
        if (remember == true) {
            rememberMeClick();
        }
        driver.findElement(locatorButtonEnter).click();
    }

    public void rememberMeClick(){
        driver.findElement(locatorCheckboxRememberMe).click();
    }

    public void logOut() {
        //log out
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        driver.findElement(locatorLogOut).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorButtonEnter));
        driver.findElement(locatorButtonEnter).isDisplayed();
    }

    public void checkAccountData(String name, String email) {
        //check account's data
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorFieldNickname));
        assertEqualsText(toolbarTitle, drawerMyAccountRU);
        assertEqualsText(locatorFieldNickname, name);
        assertEqualsText(locatorFieldEmail, email);
    }

    public void signIn() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization);

        //check title
        //assertEqualsText(toolbarTitle, drawerMyAccountRU);

        //sign in
        enterUserData(email, password, false);
        //check account's data
        checkAccountData(nickName, email);
    }
}