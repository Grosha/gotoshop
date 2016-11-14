
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by groshkka on 09.11.2016.
 */
public class AutorizationTest extends BaseTest {

    @Test
    public void openMyAccountTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization);
        //check title
        assertEqualsText(toolbarTitle, drawerMyAccountRU);
    }

    @Test
    public void loginGoogleTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization);

        //check title
        //assertEqualsText(toolbarTitle, drawerMyAccountRU);

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

        assertEqualsText(toolbarTitle, drawerMyAccountRU);
        assertEqualsText(locatorFieldEmail, email);
    }

    @Test
    public void logInOldUserTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization);

        //check title
        //assertEqualsText(toolbarTitle, drawerMyAccountRU);

        //sign in
        signIn(email, password, false);
        //check account's data
        checkAccountData(nickName, email);
    }

    @Test
    public void logInLogOutOldUserTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization);

        //check title
        //assertEqualsText(toolbarTitle, drawerMyAccountRU);

        //sign in
        signIn(email, password, true);
        //check account's data
        checkAccountData(nickName, email);
        //log out
        logOut();
        //check function remember me
        assertEqualsText(locatorFieldEmail, email);
        //off checkbox remember me
        rememberMeClick();
    }

    @Test
    public void logOutUserTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization);
        //check title
        assertEqualsText(toolbarTitle, drawerMyAccountRU);
        //log out
        logOut();
    }

    @Test
    public void registerNewUserTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization);

        //check title
        //assertEqualsText(toolbarTitle, drawerMyAccountRU);

        driver.findElement(locatorRegistration).click();
        assertEqualsText(locatorRegistration, toolbarRegistration);
        //create new user name and email
        String temp = String.valueOf(new Date().getTime());
        String name = temp + testNickName;
        String email = temp + emailTest;
        //form filling
        enterNewUserData(name, email);
        //sign in
        signIn(email, password, false);
        //check account's data
        checkAccountData(name, email);
    }

    @Test
    public void changePasswordAndSignIn() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerAutorization);

        //check title
        //assertEqualsText(toolbarTitle, drawerMyAccountRU);

        driver.findElement(locatorRegistration).click();
        assertEqualsText(locatorRegistration, toolbarRegistration);
        //create new user name and email
        String temp = String.valueOf(new Date().getTime());
        String name = temp + testNickName;
        String email = temp + emailTest;
        //form filling
        enterNewUserData(name, email);
        //sign in
        signIn(email, password, false);
        //check account's data
        checkAccountData(name, email);
        //change password
        changePassword(password, newPassword);
        //log out
        logOut();
        //sign in with new password
        signIn(email, newPassword, false);
        //check account's data
        checkAccountData(name, email);
        //log out
        logOut();
    }

    private void checkAccountData(String name, String email) {
        //check account's data
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorFieldNickname));
        assertEqualsText(toolbarTitle, drawerMyAccountRU);
        assertEqualsText(locatorFieldNickname, name);
        assertEqualsText(locatorFieldEmail, email);
    }

    private void logOut() {
        //log out
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        driver.findElement(locatorLogOut).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorButtonEnter));
        driver.findElement(locatorButtonEnter).isDisplayed();
    }

    private void signIn(String email, String password, boolean remember) {
        //enter email and password - sign in
        driver.findElement(locatorFieldEmail).clear();
        driver.findElement(locatorFieldEmail).sendKeys(email);
        driver.findElement(locatorFieldPassword).sendKeys(password);
        if (remember == true) {
            rememberMeClick();
        }
        driver.findElement(locatorButtonEnter).click();
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

    private void rememberMeClick(){
        driver.findElement(locatorCheckboxRememberMe).click();
    }

}
