import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

/**
 * Created by groshkka on 12.11.2016.
 */
public class CategoryTest extends BaseTest {

    @Test
    public void openCategoryShopsTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerCategory);
        //check title
        assertEqualsText(toolbarTitle, drawerCategoryRU);
        //open category
        openCategory();
    }

    @Test
    public void openShopFromCategoryTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerCategory);
        //check title
        assertEqualsText(toolbarTitle, drawerCategoryRU);
        //open category
        openCategory();
        //open shop
        openShop(openShopCategory, false);
    }

    @Test
    public void openShopThroughSearchFromCategoryTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerCategory);
        //check title
        assertEqualsText(toolbarTitle, drawerCategoryRU);
        //open category
        openCategory();
        //open shop
        openShop(openShopCategory, true);
    }

    //@Test
    public void checkCountSalesTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerCategory);
        //check title
        assertEqualsText(toolbarTitle, drawerCategoryRU);
        //open category
        openCategory();
        //open shop
        openShop(openShopCategory, true);
    }

    private void openCategory() {
        //create list with categories
        List<WebElement> categoryList = driver.findElements(openFirstShop);
        List<WebElement> categoryName = categoryList.get(0).findElements(openCategoryShop);
        //List<WebElement> countSales = categoryList.get(0).findElements(locatorCountSalesCategory);
        //get random category
        int random = new Random().nextInt(categoryName.size());
        String category = categoryName.get(random).getText();
        //String count = countSales.get(random).getText();
        //getCountSales(count);
        //find random category and enter
        driver.findElement(By.name(category)).click();
        //check title
        assertEqualsText(toolbarTitle, category);
    }

    private void getCountSales(String count) {
        int d = Integer.parseInt(count.substring(0,count.indexOf(" ")));
        System.out.println(d);
    }
}
