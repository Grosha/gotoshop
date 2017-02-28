package com.portret;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FavoritesSalesTest extends BaseTest {

    @Test
    public void openFavoriteSalesLogOutTest() throws InterruptedException {
        //open drawer
        openDrawer();
        //save number love sales
        String numberSales = $(locatorDrawerNumberSales).getText();
        //enter to the feature from drawer
        $(locatorDrawerFavorite).click();
        timeOut(10L);
        //check title
        assertEquals($(locatorToolbarTitle).getText(),toolbarFavorite);
        //check result
        assertEquals($(locatorTextEmpty).getText(),resultNothingFind);
    }

    @Test
    public void acceptDeleteSalesFromFavoriteTest() throws InterruptedException {
        timeOut(start);
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        try {
            $(locatorLogOut);
        }catch (NoSuchElementException e){
            signIn();
        }
        //add sale to the favorite and open feature favorite
        addSaleToTheFavorite(getCountSales(),ShopComfy);
        driver.findElement(locatorDeleteAllSales).click();

        timeOut(1L);
        //accept delete sales
        $(locatorAcceptDeleteSales).click();
        //check result
        assertEquals($(locatorTextEmpty).getText(),resultNothingFind);
        driver.runAppInBackground(2);
        checkCountSalesDrawer(0);
        //log out
        logOut();
    }

    @Test
    public void dismissDeleteSalesFromFavoriteTest() throws InterruptedException {
        timeOut(start);
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        try {
            $(locatorLogOut);
        }catch (NoSuchElementException e){
            signIn();
        }
        int numberSales = getCountSales();
        int newNumberSales = numberSales+1;
        System.out.println("newNumberSales - " + newNumberSales);
        //add sale to the favorite and open feature favorite
        addSaleToTheFavorite(numberSales, ShopBILLA);
        $(locatorDeleteAllSales).click();

        timeOut(1L);
        //accept delete sales
        $(locatorDismisDeleteSales).click();
        //check result
        checkCountSalesDrawer(newNumberSales);
        //log out
        logOut();
    }

    @Test
    public void deleteSaleFromFavoriteTest() throws InterruptedException {
        timeOut(start);
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        try {
            $(locatorLogOut);
        }catch (NoSuchElementException e){
            signIn();
        }
        //add sale to the favorite and open feature favorite
        addSaleToTheFavorite(getCountSales(), ShopATB);
        $(locatorStar).click();
        //check result
        assertEquals($(locatorTextEmpty).getText(),resultNothingFind);
        checkCountSalesDrawer(0);
        //log out
        logOut();
    }

    private void addSaleToTheFavorite(int numberSales, String shopName) throws InterruptedException {
        //enter to the feature from drawer
        //enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        enterToTheFeatureFromDrawer(locatorDrawerShops, drawerShopRU);
        openShop(shopName);
        //find sale name
        String saleName = $(locatorSaleTitle).getText();
        System.out.println(saleName);
        //click on starnumberSales
        $(locatorStar).click();
        openDrawer();
        System.out.println("numberSales -" +  numberSales);
        driver.runAppInBackground(2);
        checkCountSalesDrawer(++numberSales);

        enterToTheFeatureFromDrawer(locatorDrawerFavorite, toolbarFavorite);
    }


}
