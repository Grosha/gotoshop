package com.portret;

import com.SetUpAppium;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class BaseTest extends SetUpAppium {

    public void assertEqualsInt(By locator, int number) {
        int numberElement = Integer.parseInt($(locator).getText());
        System.out.println(numberElement);
        System.out.println(number);
        assertEquals(numberElement, number);
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
        assertEquals($(locatorToolbarTitle).getText(),toolbarTitle);
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
        assertEquals($(locatorToolbarTitle).getText(),shopName);
    }

    public void enterUserData(String email, String password, boolean remember) {
        //enter email and password - sign in
        $(locatorFieldEmail).clear();
        $(locatorFieldEmail).sendKeys(email);
        $(locatorFieldPassword).sendKeys(password);
        if (remember == true) {
            //checkBoxIsSelected(locatorCheckboxRememberMe);
            //rememberMeClick();
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
        assertEquals($(locatorToolbarTitle).getText(),drawerMyAccountRU);
        assertEquals($(locatorFieldNickname).getText(),name);
        assertEquals($(locatorFieldEmail).getText(),email);
    }

    public void signIn() throws InterruptedException {
        //enter to the feature from drawer
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
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }catch (TimeoutException exception){
            System.out.println(element + " does not apear");
        }catch (NoSuchElementException e){
            System.out.println(element + " not found");
        }


    }

    public void checkCountSalesDrawer(int count) throws InterruptedException {
        System.out.println("count - " + count);
        //open drawer
        $(locatorToolbarTitle);
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
        System.out.println("numberSales - "+numberSales);
        return numberSales;
    }

    public WebElement $(By by) {
        waitElement(by);
        return driver.findElement(by);
    }

    public void rotetaLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void checkBoxIsSelected(By element) {
        boolean is = $(element).isSelected();
        System.out.println(is);
        if (is == false) {
            System.out.println("no select");
            $(element).click();
        }
    }

    public void openShop(String shopName) {
        //enter shop's name
        $(shopInput).sendKeys(shopName);
        //find random shop and enter
        //$(By.linkText(shopName)).click();
        $(locatorShopName).click();
        //check title
        assertEquals($(locatorToolbarTitle).getText(),shopName,"Wrong toolbar title with shop");
    }
}
