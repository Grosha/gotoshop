package com.po;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;

/**
 * Created by groshkka on 4/18/17.
 */
public class AutorizationTest extends AutorizationPage {

    @Test
    public void openMyAccountTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        //checkBoxIsSelected(locatorCheckboxRememberMe);
    }

    @Test
    public void logInOldUserTest() throws InterruptedException {
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        timeOut(1l);
        if (isElementPresented(locatorLogOut) == true) {
            logOut();
        }
        signIn(email, password);
        logOut();
    }

    @Test
    public void registerNewUserTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        timeOut(1l);
        if (isElementPresented(locatorLogOut) == true) {
            logOut();
        }
        createNewUser();
        logOut();
    }

    @Test
    public void changePasswordAndSignIn() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        try {
            $(locatorRegistration).click();
        } catch (NoSuchElementException e) {
            $(locatorLogOut).click();
            $(locatorRegistration).click();
        }
        assertEquals($(locatorRegistration).getText(), toolbarRegistration);
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
        //change password
        changePassword(password, newPassword);
        //log out
        logOut();
        //sign in with new password
        enterUserData(email, newPassword, false);
        //check account's data
        checkAccountData(name, email);
        //log out
        logOut();
    }

}
