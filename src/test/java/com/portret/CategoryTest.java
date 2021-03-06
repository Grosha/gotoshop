package com.portret;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

/**
 * Created by groshkka on 12.11.2016.
 */
public class CategoryTest extends com.portret.BaseTest {

    @Test
    public void openCategoryShopsTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerCategory, drawerCategoryRU);
        //open category
        openCategory();
    }

    @Test
    public void openShopFromCategoryTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerCategory, drawerCategoryRU);
        //open category
        openCategory();
        //open shop
        openShop(openShopCategory, false);
    }

    @Test
    public void openShopThroughSearchFromCategoryTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerCategory, drawerCategoryRU);
        //open category
        openCategory();
        //open shop
        openShop(openShopCategory, true);
    }

    //@Test
    public void checkCountSalesTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerCategory, drawerCategoryRU);
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
        $(By.name(category)).click();
        //check title
        assertEquals($(locatorToolbarTitle).getText(),category);
    }

    private void getCountSales(String count) {
        int d = Integer.parseInt(count.substring(0,count.indexOf(" ")));
        System.out.println(d);
    }
}
