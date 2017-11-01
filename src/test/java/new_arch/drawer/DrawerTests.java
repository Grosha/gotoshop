package new_arch.drawer;

import new_arch.SetUpAppium;
import new_arch.category_shops.CategoryShopHelper;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

/**
 * Created by groshkka on 22.10.17.
 */
public class DrawerTests extends SetUpAppium {
    private DrawerHelper drawerHelper;
    private CategoryShopHelper categoryShopHelper;
    private SoftAssert softAssert = new SoftAssert();

    public void initDriver() {
        drawerHelper = new DrawerHelper(driver);
        categoryShopHelper = new CategoryShopHelper(driver);
    }

    @Test
    public void openDrawerTests(){
        drawerHelper.findDrawer().click();
        softAssert.assertTrue(drawerHelper.findDrawerImage().isDisplayed(), "Drawer image wasn't presented");
        softAssert.assertTrue(drawerHelper.findDrawerCategory(CategoryName.CITY_RU.category()).isDisplayed(), "Category ГОРОД is not presented");
        softAssert.assertTrue(drawerHelper.findDrawerCategory(CategoryName.AUTHORIZATION_RU.category()).isDisplayed(), "Category Связаться с нами is not presented");
        softAssert.assertTrue(drawerHelper.findDrawerCategory(CategoryName.SHOP_RU.category()).isDisplayed(), "Category Магазины is not presented");
        softAssert.assertTrue(drawerHelper.findDrawerCategory(CategoryName.CATEGORY_RU.category()).isDisplayed(), "Category Категории is not presented");
        softAssert.assertTrue(drawerHelper.findDrawerCategory(CategoryName.NOTIFICATION_RU.category()).isDisplayed(), "Category Уведомления is not presented");
        softAssert.assertTrue(drawerHelper.findDrawerCategory(CategoryName.FAVORITE_RU.category()).isDisplayed(), "Category Избранное is not presented");
        softAssert.assertTrue(drawerHelper.findDrawerCategory(CategoryName.CHAT_US_RU.category()).isDisplayed(), "Category Связаться с нами is not presented");
        softAssert.assertTrue(drawerHelper.findDrawerCategory(CategoryName.SETTINGS_RU.category()).isDisplayed(), "Category Настройки is not presented");
        softAssert.assertTrue(drawerHelper.findDrawerCategory(CategoryName.SALES_RU.category()).isDisplayed(), "Category Акции is not presented");
        softAssert.assertAll();
    }

    @Test
    public void closeDrawer(){
        drawerHelper.findDrawer().click();
        driver.navigate().back();
        assertTrue(categoryShopHelper.findShopInputField().isEnabled(), "Shop input field was bot active");
    }

    public void openAndCloseDrawerBySwipe(){

    }
}
