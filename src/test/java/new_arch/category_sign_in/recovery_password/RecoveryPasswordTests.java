package new_arch.category_sign_in.recovery_password;

import new_arch.SetUpAppium;
import new_arch.category_sign_in.SignInHelper;
import new_arch.drawer.CategoryName;
import new_arch.drawer.DrawerHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

/**
 * Created by groshkka on 26.10.17.
 */
public class RecoveryPasswordTests extends SetUpAppium {
    private SignInHelper signInHelper;
    private DrawerHelper drawerHelper;
    private SoftAssert softAssert = new SoftAssert();
    private RecoveryPasswordHelper recoveryPasswordHelper;

    public void initDriver() {
        signInHelper = new SignInHelper(driver);
        drawerHelper = new DrawerHelper(driver);
        recoveryPasswordHelper = new RecoveryPasswordHelper(driver);
    }

    @DataProvider(name = "email")
    public static Object[][] recoveryData() {
        return new Object[][]{
                {"test1@test.com", true},
                {"kjfhksf@lflk.sf", false},
                {null, false}
        };
    }

    @Test
    public void recoveryPasswordElementsTests(){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        signInHelper.findRecoveryPasswordButton().click();

        softAssert.assertTrue(recoveryPasswordHelper.findRecoveryEmailFiled().isEnabled(), "Recovery password field email was't shown");
        softAssert.assertTrue(recoveryPasswordHelper.findRecoverySendButton().isEnabled(), "Recovery send button was't shown");
        softAssert.assertEquals(recoveryPasswordHelper.findRecoverySendButton().getText().toString(),"Отправить", "Recovery send button name is incorrect");
        softAssert.assertEquals(recoveryPasswordHelper.findRecoveryTextInfo().getText().toString(),RecoveryPasswordHelper.recoverytextInfo, "Recovery password field email was't shown");
        softAssert.assertAll();
    }

    @Test(dataProvider = "email")
    public void recoveryPasswordTests(String email, boolean emailExist){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        signInHelper.findRecoveryPasswordButton().click();

        recoveryPasswordHelper.findRecoveryEmailFiled().sendKeys(email);
        recoveryPasswordHelper.findRecoverySendButton().click();

        if (emailExist == true) {
            softAssert.assertTrue(signInHelper.findRegistrationButton().isDisplayed(), "SignIn page wasn't shown after recovery password");
        } else if (emailExist == false && email != null) {
            softAssert.assertEquals(recoveryPasswordHelper.findRecoveryEmailFiled().getText().toString(), email,"Email was deleted after try recovery password with not exist email");
            softAssert.assertTrue(recoveryPasswordHelper.findRecoverySendButton().isEnabled(), "Recovery send button was't shown");
            softAssert.assertEquals(recoveryPasswordHelper.findRecoverySendButton().getText().toString(),"Отправить", "Recovery send button name is incorrect");
            softAssert.assertEquals(recoveryPasswordHelper.findRecoveryTextInfo().getText().toString(),RecoveryPasswordHelper.recoverytextInfo, "Recovery password field email was't shown");
        }  else if (emailExist == false && email == null) {
            softAssert.assertTrue(recoveryPasswordHelper.findRecoveryEmailFiled().isEnabled(), "Recovery password field email was't shown");
            softAssert.assertTrue(recoveryPasswordHelper.findRecoverySendButton().isEnabled(), "Recovery send button was't shown");
            softAssert.assertEquals(recoveryPasswordHelper.findRecoverySendButton().getText().toString(),"Отправить", "Recovery send button name is incorrect");
            softAssert.assertEquals(recoveryPasswordHelper.findRecoveryTextInfo().getText().toString(),RecoveryPasswordHelper.recoverytextInfo, "Recovery password field email was't shown");
        }
        softAssert.assertAll();
    }

    @Test
    public void returnToTheSignInPage(){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        signInHelper.findRecoveryPasswordButton().click();
        driver.navigate().back();

        assertTrue(signInHelper.findRegistrationButton().isDisplayed(), "SignIn page wasn't shown after recovery password");
    }

    @Test
    public void returnToTheSignInPageThroughToolbar(){
        drawerHelper.findDrawer().click();
        drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).click();
        signInHelper.findRecoveryPasswordButton().click();
        drawerHelper.findDrawer().click();

        assertTrue(signInHelper.findRegistrationButton().isDisplayed(), "SignIn page wasn't shown after recovery password");
    }
}
