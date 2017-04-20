package com.portret;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class AutorizationTest extends BaseTest {

    @Test
    public void openMyAccountTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        //checkBoxIsSelected(locatorCheckboxRememberMe);
    }

    //@Test
    public void loginGoogleTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        $(locatorLoginGoogle).click();
        timeOut(3L);

        String email = $(locatorEmailName).getText();
        $(locatorGoogleAccount).click();
        driver.close();

        assertEquals($(locatorToolbarTitle).getText(),drawerMyAccountRU);
        assertEquals($(locatorFieldEmail).getText(),email);
    }

    @Test
    public void logInOldUserTest() throws InterruptedException {
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        signIn();
    }

    @Test
    public void logInLogOutOldUserTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        //sign in
        try {
            $(locatorButtonEnter);
        }catch (NoSuchElementException e){
            $(locatorLogOut).click();
            $(locatorButtonEnter);
        }
        enterUserData(email, password, true);
        //check account's data
        checkAccountData(nickName, email);
        //log out
        clickLogOut();
        //off checkbox remember me
        //rememberMeClick();
        //check function remember me
        //assertEqualsText(locatorFieldEmail, email);
    }

    @Test
    public void logOutUserTest() throws InterruptedException {
        //log out
        logOut();
    }

    @Test
    public void registerNewUserTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        createNewUser();
        clickLogOut();
    }

    @Test
    public void changePasswordAndSignIn() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
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
        //change password
        changePassword(password, newPassword);
        //log out
        clickLogOut();
        //sign in with new password
        enterUserData(email, newPassword, false);
        //check account's data
        checkAccountData(name, email);
        //log out
        clickLogOut();
    }

    private void enterNewUserData(String name, String email) {
        //form filling
        $(locatorFieldNickname).sendKeys(name);
        $(locatorFieldEmail).sendKeys(email);
        $(locatorFieldPassword).sendKeys(password);
        $(locatorFieldRepeatPassword).sendKeys(password);
        driver.navigate().back();
        $(locatorButtonRegistration).click();
        timeOut(10L);
    }

    private void changePassword(String password, String newPassword) {
        //change password
        $(locatorFieldOldPassword).sendKeys(password);
        $(locatorFieldPassword).sendKeys(newPassword);
        $(locatorFieldRepeatPassword).sendKeys(newPassword);
        driver.navigate().back();
        $(locatorButtonSave).click();
    }

    private void createNewUser() throws InterruptedException {
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
