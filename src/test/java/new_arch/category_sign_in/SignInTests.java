package new_arch.category_sign_in;

import io.appium.java_client.android.AndroidKeyCode;
import new_arch.SetUpAppium;
import new_arch.category_sign_in.person_info.PersonInfoHelper;
import new_arch.drawer.CategoryName;
import new_arch.drawer.DrawerHelper;
import new_arch.toolbar.ToolbarHelper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by groshkka on 22.10.17.
 */
public class SignInTests extends SetUpAppium {
    private DrawerHelper drawerHelper;
    private SignInHelper signInHelper;
    private ToolbarHelper toolbarHelper;
    private PersonInfoHelper personInfoHelper;
    private SoftAssert softAssert = new SoftAssert();

    public void initDriver() {
        drawerHelper = new DrawerHelper(driver);
        signInHelper = new SignInHelper(driver);
        toolbarHelper = new ToolbarHelper(driver);
        personInfoHelper = new PersonInfoHelper(driver);
    }

    @Test
    private void categoryAuthorizatioTests(){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        softAssert.assertTrue(signInHelper.findEmailField().isDisplayed(), "Email field wasn't shown");
        softAssert.assertEquals(signInHelper.findEmailFieldLayout().getText().toString(), "Введите email адрес…", "Email field description wasn't shown or incorrect");
        softAssert.assertTrue(signInHelper.findPasswordField().isDisplayed(), "Password field wasn't shown");
        softAssert.assertEquals(signInHelper.findPasswordFieldLayout().getText().toString(), "Введите пароль…", "Password field description wasn't shown or incorrect");
        softAssert.assertTrue(signInHelper.findSignInButton().isDisplayed(), "SignIn button wasn't shown");
        softAssert.assertTrue(signInHelper.findRecoveryPasswordButton().isDisplayed(), "Recovery Password button wasn't shown");
        softAssert.assertTrue(signInHelper.findRegistrationButton().isDisplayed(), "Registration button wasn't shown");
        softAssert.assertTrue(signInHelper.findLoginFBImage().isDisplayed(), "Login FB image wasn't shown");
        softAssert.assertTrue(signInHelper.findLoginVKImage().isDisplayed(), "Login VK image wasn't shown");
        softAssert.assertTrue(signInHelper.findLoginGoogleImage().isDisplayed(), "Login Google image wasn't shown");
        softAssert.assertFalse(signInHelper.findToggleStatusPasswordImage().isEnabled(), "Show password toggle is enable");
        softAssert.assertFalse(signInHelper.findRemmeberPasswordCheckBox().isEnabled(), "Show password toggle is enable");
        softAssert.assertEquals(toolbarHelper.findToolbarTitle().getText().toString(), CategoryName.AUTHORIZATION_RU.category(),"Incorrect toolbar title for category authorization");
        softAssert.assertAll();
    }

    @Test
    public void reopenCategoryAuthorizationTest(){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        driver.sendKeyEvent(AndroidKeyCode.HOME);
        try {
            driver.runAppInBackground(2);
        }
        catch(Exception e){}
        softAssert.assertTrue(signInHelper.findEmailField().isDisplayed(), "Email field wasn't shown");
    }

    @DataProvider(name = "personData")
    public static Object[][] signIn() {
        return new Object[][]{
                {PersonalData.EMAIL.data(), PersonalData.PASSWORD.data(), null},
                {"", PersonalData.PASSWORD.data(), "Обязательное поле"},
                {PersonalData.EMAIL_WITHOUT_AT.data(), PersonalData.PASSWORD.data(), "Email адрес некорректный"},
                {PersonalData.EMAIL_WITHOUT_NICK.data(), PersonalData.PASSWORD.data(), "Email адрес некорректный"},
                {PersonalData.EMAIL.data(), "", "Email адрес некорректный"},
                {PersonalData.EMAIL.data(), PersonalData.PASSWORD_SHORT, "Пароль слишком короткий"},
        };
    }

    @Test(dataProvider = "personData")
    public void signInTest(String email, String password, String error){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        signInHelper.findEmailField().sendKeys(email);
        signInHelper.findPasswordField().sendKeys(password);
        signInHelper.findSignInButton().click();
/*        WebDriverWait wait = new WebDriverWait(driver, 5L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorFieldNickname));*/
        if (error != null && password.length() != 0) {
            signInHelper.findSignInButton().isDisplayed();
            softAssert.assertEquals(signInHelper.findTextInputError().getText().toString(), error, "Incorrect text error for user");
        } else {
            softAssert.assertEquals(personInfoHelper.findPersonFieldEmail().getText().toString(), email, "Incorrect email after sign in");
            personInfoHelper.findPersonSaveButton().isDisplayed();
            personInfoHelper.findPersonLogOutButton().click();
        }
        softAssert.assertAll();
    }
}
