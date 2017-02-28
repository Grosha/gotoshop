package com.landscape;

import com.SetUpAppium;
import io.appium.java_client.AppiumDriver;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseLandscapeTest extends SetUpAppium {

    public void assertEqualsText(By locator, String text) {
        String textElement = $(locator).getText();
        Assert.assertEquals(text, textElement);
    }

    public void assertEqualsInt(By locator, int number) {
        int numberElement = Integer.parseInt($(locator).getText());
        Assert.assertEquals(numberElement, number);
    }

    public AppiumDriver<WebElement> timeOut(Long seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        return driver;
    }

    public void openDrawer() throws InterruptedException {
        //open drawer
        Thread.sleep(5000);
        $(locatorOpenDrawer).click();
        timeOut(10L);
    }

    public void enterToTheFeatureFromDrawer(By drawerFeatureName, String toolbarTitle) throws InterruptedException {
        //open drawer
        openDrawer();
        //enter to the feature from drawer
        $(drawerFeatureName).click();
        timeOut(10L);
        //check title
        assertEqualsText(locatorToolbarTitle, toolbarTitle);
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
            $(shopInput).sendKeys(shopName);
        } else {
            //get random shop

            shopName = shops.get(random).getText();
        }
        //find random shop and enter
        $(By.name(shopName)).click();
        //check title
        assertEqualsText(locatorToolbarTitle, shopName);
    }

    public void enterUserData(String email, String password, boolean remember) {
        //enter email and password - sign in
        $(locatorFieldEmail).clear();
        $(locatorFieldEmail).sendKeys(email);
        //driver.navigate().back();
        $(locatorFieldPassword).sendKeys(password);
        //driver.navigate().back();
        if (remember == true) {
            rememberMeClick();
        }
        $(locatorButtonEnter).click();
    }

    public void rememberMeClick() {
        $(locatorCheckboxRememberMe).click();
    }

    public void clickLogOut() {
        //log out
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        $(locatorLogOut).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorButtonEnter));
        $(locatorButtonEnter).isDisplayed();
    }

    public void checkAccountData(String name, String email) {
        //check account's data
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorFieldNickname));
        assertEqualsText(locatorToolbarTitle, drawerMyAccountRU);
        assertEqualsText(locatorFieldNickname, name);
        assertEqualsText(locatorFieldEmail, email);
    }

    public void signIn() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, apkName);
        //sign in
        enterUserData(email, password, false);
        //check account's data
        checkAccountData(nickName, email);
    }

    public void logOut() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        //log out
        clickLogOut();
    }

    public void waitElement(By element) {
        //wait comment
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void checkCountSalesDrawer(int count) throws InterruptedException {
        //open drawer
        openDrawer();
        //compare count sales
        Thread.sleep(500);
        assertEqualsInt(locatorNumberSales, count);
    }

    public int getCountSales() throws InterruptedException {
        //open drawer
        openDrawer();
        openDrawer();
        //save number love sales
        Thread.sleep(500);
        int numberSales = Integer.parseInt($(locatorNumberSales).getText());
        System.out.println(numberSales);
        return numberSales;
    }

    public WebElement $(By by) {
        return driver.findElement(by);
    }

    public void rotetaLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
}
