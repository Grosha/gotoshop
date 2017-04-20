package com.po;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

import static org.testng.Assert.assertEquals;

/**
 * Created by groshkka on 4/18/17.
 */
public class AutorizationPage extends BasePage {

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

    public void checkAccountData(String name, String email) {
        //check account's data
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorFieldNickname));
        assertEquals($(locatorToolbarTitle).getText(),drawerMyAccountRU);
        assertEquals($(locatorFieldNickname).getText(),name);
        assertEquals($(locatorFieldEmail).getText(),email);
    }

    public void signIn(String email, String password) throws InterruptedException {
        //enter to the feature from drawer
        //sign in
        enterUserData(email, password, false);
        //check account's data
        checkAccountData(nickName, email);
    }

    public void logOut(String featureName) throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, featureName);
        //log out
        logOut();
    }

    public void rememberMeClick() {
        $(locatorCheckboxRememberMe).click();
    }

    public void logOut() {
        //log out
        $(locatorLogOut).click();
        WebDriverWait wait = new WebDriverWait(driver, 1L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorButtonEnter));
        $(locatorButtonEnter).isDisplayed();
    }



    public void enterNewUserData(String name, String email) {
        //form filling
        $(locatorFieldNickname).sendKeys(name);
        $(locatorFieldEmail).sendKeys(email);
        $(locatorFieldPassword).sendKeys(password);
        $(locatorFieldRepeatPassword).sendKeys(password);
        driver.navigate().back();
        $(locatorButtonRegistration).click();
        timeOut(10L);
    }

    public void changePassword(String password, String newPassword) {
        //change password
        $(locatorFieldOldPassword).sendKeys(password);
        $(locatorFieldPassword).sendKeys(newPassword);
        $(locatorFieldRepeatPassword).sendKeys(newPassword);
        driver.navigate().back();
        $(locatorButtonSave).click();
    }

    public void createNewUser() throws InterruptedException {
        try {
            $(locatorRegistration).click();
        }catch (NoSuchElementException e){
            $(locatorLogOut).click();
            $(locatorRegistration).click();
        }
        assertEquals($(locatorRegistration).getText(),toolbarRegistration);
        //create new user name and email
        String temp = String.valueOf(new Date().getTime());
        String name = temp + testNickName;
        String email = temp + emailTest;
        //form filling
        enterNewUserData(name, email);
        //sign in
        enterUserData(email, password, false);
        //check account's data
        checkAccountData(name, email);
    }
}
