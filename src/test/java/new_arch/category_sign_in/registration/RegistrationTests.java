package new_arch.category_sign_in.registration;

import new_arch.SetUpAppium;
import new_arch.category_sign_in.PersonalData;
import new_arch.category_sign_in.SignInHelper;
import new_arch.category_sign_in.person_info.PersonInfoHelper;
import new_arch.drawer.CategoryName;
import new_arch.drawer.DrawerHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Date;

import static org.testng.Assert.assertTrue;

/**
 * Created by groshkka on 26.10.17.
 */
public class RegistrationTests extends SetUpAppium {
    private SignInHelper signInHelper;
    private RegistrationHelper registrationHelper;
    private DrawerHelper drawerHelper;
    private SoftAssert softAssert = new SoftAssert();
    private PersonInfoHelper personInfoHelper;

    public void initDriver() {
        signInHelper = new SignInHelper(driver);
        registrationHelper = new RegistrationHelper(driver);
        drawerHelper = new DrawerHelper(driver);
        personInfoHelper = new PersonInfoHelper(driver);
    }

    @DataProvider(name = "registrationData")
    public static Object[][] registration() {
        return new Object[][]{
                {"", "", "", ""},
                {"Test", "", "", ""},
                {"Test", "Test@test.com", "", ""},
                {"Test", "Test@test.com", PersonalData.PASSWORD_NUMBERS.data(), ""},
                {"Test", "Test@test.com", "", PersonalData.PASSWORD_NUMBERS.data()},
                {"Test", "", PersonalData.PASSWORD_NUMBERS.data(), PersonalData.PASSWORD_NUMBERS.data()},
                {"", "Test@test.com", PersonalData.PASSWORD_NUMBERS.data(), PersonalData.PASSWORD_NUMBERS.data()},
                {"", "", PersonalData.PASSWORD_NUMBERS.data(), PersonalData.PASSWORD_NUMBERS.data()},
                {"", "", "", PersonalData.PASSWORD_NUMBERS.data()},
                {"", "", PersonalData.PASSWORD_NUMBERS.data(), ""},
                {"Test", "Test@test.com", "123654", PersonalData.PASSWORD_NUMBERS.data()},
                {"Test", "Test@test.com", "123", PersonalData.PASSWORD_NUMBERS.data()},
                {"Test", "Test@test.com", PersonalData.PASSWORD_NUMBERS.data(), "1234567"},
                {"Test", "Testtest.com", PersonalData.PASSWORD_NUMBERS.data(), PersonalData.PASSWORD_NUMBERS.data()},
                {"Test", "Test@testcom", PersonalData.PASSWORD_NUMBERS.data(), PersonalData.PASSWORD_NUMBERS.data()},
                {"Test", "Test@test.com", PersonalData.PASSWORD_SHORT.data(), PersonalData.PASSWORD_SHORT.data()},
                {"Test", "test1@test.com", PersonalData.PASSWORD.data(), PersonalData.PASSWORD.data()},
        };
    }

    @Test(dataProvider = "registrationData")
    public void registrationFalseTests(String name, String email, String password, String repeatPassword){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        signInHelper.findRegistrationButton().click();
        registrationHelper.findRegistrationNameField().sendKeys(name);
        registrationHelper.findRegistrationEmailField().sendKeys(email);
        registrationHelper.findRegistrationPasswordField().sendKeys(password);
        registrationHelper.findRegistrationRepeatPasswordField().sendKeys(repeatPassword);
        registrationHelper.findRegistrationSignInButton().click();

        registrationHelper.waitRegistrationSignInButton();
    }

    @Test
    public void registrationElementTests(){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        signInHelper.findRegistrationButton().click();
        softAssert.assertTrue(registrationHelper.findRegistrationNameField().isEnabled(), "Registration field name was't shown");
        softAssert.assertTrue(registrationHelper.findRegistrationEmailField().isEnabled(), "Registration field email was't shown");
        softAssert.assertTrue(registrationHelper.findRegistrationPasswordField().isEnabled(), "Registration field password was't shown");
        softAssert.assertTrue(registrationHelper.findRegistrationRepeatPasswordField().isEnabled(), "Registration field repeat password was't shown");
        softAssert.assertTrue(registrationHelper.findRegistrationSignInButton().isEnabled(), "Registration button was't shown");
        softAssert.assertEquals(registrationHelper.findRegistrationSignInButton().getText().toString(), "Зарегистрироваться", "Registration button was't shown");
        softAssert.assertAll();
    }

    @DataProvider(name = "password")
    public static Object[][] passwordData() {
        return new Object[][]{
                {PersonalData.PASSWORD.data()},
                {PersonalData.PASSWORD_NUMBERS.data()},
                {PersonalData.PASSWORD_6_SYMBOLS.data()},
                {PersonalData.PASSWORD_WITH_NUMBERS.data()},
                {PersonalData.PASSWORD_WITH_SPASE.data()},
                {PersonalData.PASSWORD_WITH_SPASE_FINISH.data()},
                {PersonalData.PASSWORD_WITH_SPASE_START.data()},
                {PersonalData.PASSWORD_WITH_SYMBOLA_NUMBERS_LETTERS.data()},
                {PersonalData.PASSWORD_WITH_SYMBOLS.data()},
                {PersonalData.PASSWORD_WITH_UPP_LOW_LETTERS.data()},
                {PersonalData.PASSWORD_WITH_UPP_LOW_LETTERS_NUMBERS.data()}
        };
    }

    @Test(dataProvider = "password")
    public void registrationTests(String password){
        String temp = String.valueOf(new Date().getTime());
        String name = temp + "Test";
        String email = temp + "@test.com";

        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        signInHelper.findRegistrationButton().click();
        registrationHelper.findRegistrationNameField().sendKeys(name);
        registrationHelper.findRegistrationEmailField().sendKeys(email);
        registrationHelper.findRegistrationPasswordField().sendKeys(password);
        registrationHelper.findRegistrationRepeatPasswordField().sendKeys(password);
        registrationHelper.findRegistrationSignInButton().click();

        signInHelper.waitRegistrationButton();
        signInHelper.findEmailField().sendKeys(email);
        signInHelper.findPasswordField().sendKeys(PersonalData.PASSWORD.data());
        signInHelper.findSignInButton().click();

        softAssert.assertEquals(personInfoHelper.findPersonFieldEmail().getText().toString(), email, "Incorrect email after sign in");
        personInfoHelper.findPersonSaveButton().isDisplayed();
        personInfoHelper.findPersonLogOutButton().click();
    }

    @Test
    public void returnToTheSignInPage(){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        signInHelper.findRegistrationButton().click();
        driver.navigate().back();

        assertTrue(signInHelper.findRegistrationButton().isDisplayed(), "SignIn page wasn't shown after recovery password");
    }

    @Test
    public void returnToTheSignInPageThroughToolbar(){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        signInHelper.findRegistrationButton().click();
        drawerHelper.findDrawer().click();

        assertTrue(signInHelper.findRegistrationButton().isDisplayed(), "SignIn page wasn't shown after recovery password");
    }
}
