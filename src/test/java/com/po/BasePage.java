package com.po;

import com.Locators;
import com.SetUpAppium;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * Created by groshkka on 4/18/17.
 */
public class BasePage extends SetUpAppium implements Locators {

    public WebElement $(By by) {
        waitElement(by);
        return driver.findElement(by);
    }

    public void waitElement(By element) {
        //wait comment
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException exception) {
            System.out.println(element + " does not apear");
        } catch (NoSuchElementException e) {
            System.out.println(element + " not found");
        }
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
        assertEquals($(locatorToolbarTitle).getText(), toolbarTitle);
    }

    protected boolean isElementPresented(By by){
        return $(by).isDisplayed();
    }

}
