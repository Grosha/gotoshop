package com.landscape;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class AutorizationLandscapeTest extends BaseLandscapeTest {

    @Test
    public void openMyAccountLandscapeTest() throws InterruptedException {
        rotetaLandscape();
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, apkName);
    }

    @Test
    public void loginGoogleTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, apkName);
        $(locatorLoginGoogle).click();
        timeOut(3L);

        Set<String> windowId = driver.getWindowHandles();    // get  window id of current window
        Iterator<String> itererator = windowId.iterator();

        String mainWinID = itererator.next();
        String newAdwinID = itererator.next();

        driver.switchTo().window(newAdwinID);
        System.out.println(driver.getTitle());
        timeOut(3L);
        String email = $(locatorEmailName).getText();
        $(locatorGoogleAccount).click();
        driver.close();

        driver.switchTo().window(mainWinID);
        timeOut(3L);

        assertEqualsText(locatorToolbarTitle, drawerMyAccountRU);
        assertEqualsText(locatorFieldEmail, email);
    }

    @Test
    public void logInOldUserLandscapeTest() throws InterruptedException, IOException {
        rotetaLandscape();
        //sign in
        signIn();
    }

    @Test
    public void logInLogOutOldUserLandscapeTest() throws InterruptedException {
        rotetaLandscape();
        signIn();
        driver.scrollTo(textLogOutRU);
        clickLogOut();
        //logOut();
        //off checkbox remember me
        rememberMeClick();
    }

    @Test
    public void logOutUserLandscapeTest() throws InterruptedException {
        rotetaLandscape();
        //log out
        logOut();
    }

    @Test
    public void registerNewUserLandscapeTest() throws InterruptedException {
        rotetaLandscape();
        //enter to the feature from dr;awer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, apkName);
        createNewUser();
        clickLogOut();
    }

    @Test
    public void changePasswordAndSignLandScapeIn() throws InterruptedException {
        rotetaLandscape();
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, apkName);
        $(locatorRegistration).click();
        assertEqualsText(locatorRegistration, toolbarRegistration);
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
        driver.scrollTo("Введите пароль…");
        $(locatorFieldPassword).sendKeys(password);
        driver.scrollTo("Повторите пароль…");
        $(locatorFieldRepeatPassword).sendKeys(password);
        //driver.navigate().back();
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

    private void createNewUser() {
        driver.scrollTo(textRegistrationRU);
        $(locatorRegistration).click();
        assertEqualsText(locatorRegistration, toolbarRegistration);
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
