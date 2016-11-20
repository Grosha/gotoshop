
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class AutorizationTest extends BaseTest {

    @Test
    public void openMyAccountTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization,drawerMyAccountRU);
    }

    @Test
    public void loginGoogleTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization,apkName);
        driver.findElement(locatorLoginGoogle).click();
        timeOut(3L);

        Set<String> windowId = driver.getWindowHandles();    // get  window id of current window
        Iterator<String> itererator = windowId.iterator();

        String mainWinID = itererator.next();
        String newAdwinID = itererator.next();

        driver.switchTo().window(newAdwinID);
        System.out.println(driver.getTitle());
        timeOut(3L);
        String email = driver.findElement(locatorEmailName).getText();
        driver.findElement(locatorGoogleAccount).click();
        driver.close();

        driver.switchTo().window(mainWinID);
        timeOut(3L);

        assertEqualsText(locatorToolbarTitle, drawerMyAccountRU);
        assertEqualsText(locatorFieldEmail, email);
    }

    @Test
    public void logInOldUserTest() throws InterruptedException {
        signIn();
    }

    @Test
    public void logInLogOutOldUserTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization,apkName);
        //sign in
        enterUserData(email, password, true);
        //check account's data
        checkAccountData(nickName, email);
        //log out
        clickLogOut();
        //check function remember me
        assertEqualsText(locatorFieldEmail, email);
        //off checkbox remember me
        rememberMeClick();
    }

    @Test
    public void logOutUserTest() throws InterruptedException {
        //log out
        logOut();
    }

    @Test
    public void registerNewUserTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization,apkName);
        createNewUser();
        clickLogOut();
    }

    @Test
    public void changePasswordAndSignIn() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization,apkName);
        driver.findElement(locatorRegistration).click();
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
        driver.findElement(locatorFieldNickname).sendKeys(name);
        driver.findElement(locatorFieldEmail).sendKeys(email);
        driver.findElement(locatorFieldPassword).sendKeys(password);
        driver.findElement(locatorFieldRepeatPassword).sendKeys(password);
        driver.navigate().back();
        driver.findElement(locatorButtonRegistration).click();
        timeOut(10L);
    }

    private void changePassword(String password, String newPassword) {
        //change password
        driver.findElement(locatorFieldOldPassword).sendKeys(password);
        driver.findElement(locatorFieldPassword).sendKeys(newPassword);
        driver.findElement(locatorFieldRepeatPassword).sendKeys(newPassword);
        driver.navigate().back();
        driver.findElement(locatorButtonSave).click();
    }

    private void createNewUser() {
        driver.findElement(locatorRegistration).click();
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
