package com.portret;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by groshkka on 08.11.2016.
 */
public class ShopsTest extends com.portret.BaseTest {

    @Test
    public void openShopTest() throws InterruptedException {
        Thread.sleep(5000);
        //open shop
        openShop(openShop, false);
    }

    @Test
    public void findShopThroughSearchTest() throws InterruptedException {
        wait(5000);
        //open shop
        openShop(openShop, true);
    }

    @Test
    public void openSaleFromShopTest() throws InterruptedException {
        Thread.sleep(5000);
        //open shop
        openShop(openShop, false);

        //find sale name
        String saleName = $(locatorSaleTitle).getText();
        System.out.println(saleName);

        //open sale
        $(locatorSaleImage).click();

        //check toolbar name
        assertEquals($(locatorToolbarTitle).getText(),drawerSaleRU);
        //check saleName
        assertEquals($(locatorSaleTitle).getText(),saleName);
    }

}
