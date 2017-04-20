package com.portret;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by groshkka on 21.02.2017.
 */
public class SettingsTest extends BaseTest {

    @Test
    public void testBlockClosestShopsWhenLocationOff() throws InterruptedException, IOException {
        //turn off location
        timeOut(start);
        waitElement(locatorFirstShopsBlockName);
        String nameBlock = $(locatorFirstShopsBlockName).getText();
        if (nameBlock.equals(BlockNameClosestRU)) {
            conditionForTextTurnOn();
        } else if (nameBlock.equals(BlockNameProductsRU)) {
            switcherFeatureInSettings(OFF);
            conditionForTextTurnOn();
        }
    }

    @Test
    public void testBlockClosestShopsWhenLocationOn() throws InterruptedException, IOException {
        //turn on location
        timeOut(start);
        waitElement(locatorFirstShopsBlockName);
        String nameBlock = $(locatorFirstShopsBlockName).getText();
        if (nameBlock.equals(BlockNameClosestRU)) {
            clickOnTextTurnOnLocation();
        } else if (nameBlock.equals(BlockNameProductsRU)) {
            switcherFeatureInSettings(OFF);
            clickOnTextTurnOnLocation();
        }
    }

    @Test
    public void testTurnOffFeatureClosestShops() throws InterruptedException, IOException {
        //turn off location
        timeOut(start);
        waitElement(locatorFirstShopsBlockName);
        String nameBlock = $(locatorFirstShopsBlockName).getText();
        try {
            assertNotNull($(locatorDistanceValue));
            //turnOffLocation();
        } catch (NoSuchElementException exception) {
            //everything is ok
        }
        System.out.println(nameBlock);
        if (nameBlock.equals(BlockNameClosestRU)) {
            System.out.println(1);
            switcherFeatureInSettings(ON);
            checkBlockClosestShop();
        } else if (nameBlock.equals(BlockNameProductsRU)) {
            System.out.println(2);
            switcherFeatureInSettings(OFF);
            checkBlockClosestShop();
            //check enable text for turn on location
            assertEquals($(locatorTextTurnOnLocation).getText(), TextTurnOnLocation);
            switcherFeatureInSettings(ON);
            checkBlockClosestShop();
        }

    }

    @Test
    public void testTurnOnFeatureClosestShops() throws InterruptedException, IOException {
        //turn off location
        timeOut(start);
        waitElement(locatorFirstShopsBlockName);
        String nameBlock = $(locatorFirstShopsBlockName).getText();
        try {
            assertNotNull($(locatorDistanceValue));
            //turnOffLocation();
        } catch (NoSuchElementException exception) {
            //everything is ok
        }

        System.out.println(nameBlock);
        if (nameBlock.equals(BlockNameProductsRU)) {
            System.out.println(1);
            switcherFeatureInSettings(OFF);
            checkBlockClosestShop();
        } else if (nameBlock.equals(BlockNameClosestRU)) {
            System.out.println(2);
            switcherFeatureInSettings(ON);
            checkBlockClosestShop();
            switcherFeatureInSettings(OFF);
            checkBlockClosestShop();
        }
    }

    @Test
    public void testDeleteOldSales() throws InterruptedException, IOException {
        timeOut(start);
        //check that user is authorized, if no - sign in
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        try {
            $(locatorLogOut);
        } catch (NoSuchElementException e) {
            signIn();
        }
        //turn off feature 'delete old sales'
        driver.navigate().back();
        enterToTheFeatureFromDrawer(locatorDrawerSettings, drawerSettingsRU);
        assertNotNull($(locatorTextDeleteOldSalesRU));
        if ($(locatorTextDeleteOldSalesRU).getText().equals(OFF)) {
            //turn on feature closest shops
            $(locatorTextDeleteOldSalesRU).click();
        }
        driver.navigate().back();

        waitElement(locatorFirstShopsBlockName);
        assertEquals($(locatorToolbarTitle).getText(), drawerShopRU, "Wrong toolbar title");
        //set last day
        openShop(ShopBILLA);
        chooseDayInCalendar();
        //add old sale
        $(locatorStar).click();
        int numberSales = getCountSales();
        //turn on feature 'delete old sales'
        enterToTheFeatureFromDrawer(locatorDrawerSettings, drawerSettingsRU);
        assertNotNull($(locatorTextDeleteOldSalesRU));
        if ($(locatorTextDeleteOldSalesRU).getText().equals(OFF)) {
            //turn on feature closest shops
            $(locatorTextDeleteOldSalesRU).click();
        }
        //check number favorite sales
        driver.navigate().back();
        driver.runAppInBackground(2);
        checkCountSalesDrawer(--numberSales);
    }

    private void turnOffLocation() {
        driver.openNotifications();
        $(locatorLocationStatus_).click();
        driver.navigate().back();
    }

    private boolean getStatusTextTurnOn() {
        try {
            if ($(locatorTextTurnOnLocation).getText().equals(TextTurnOnLocation)) return true;
            else return false;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    private void conditionForTextTurnOn() throws InterruptedException {
        if (getStatusTextTurnOn() == false) {
            turnOffLocation();
            //enter to settings
            enterToTheFeatureFromDrawer(locatorDrawerSettings, drawerSettingsRU);
            //back
            driver.navigate().back();
            checkBlockProductShop();
        } else checkBlockProductShop();
    }

    private void checkBlockClosestShop() {
        //check enable block ProductsShops
        assertNotNull($(locatorProductShopsBlock));
        //check block name ProductsShops
        assertEquals($(locatorFirstShopsBlockName).getText(), BlockNameProductsRU, "Wrong product block's name");
    }

    private void checkBlockProductShop() {
        //check enable block ClosestShops
        assertNotNull($(locatorClosestShopsBlock));
        //check block name ClosestShops
        assertEquals($(locatorFirstShopsBlockName).getText(), BlockNameClosestRU, "Wrong closest shop block's name");
    }

    private void turnOnLocationVIAPopup() {
        //click on text turn on location
        timeOut(5L);
        $(locatorTextTurnOnLocation).click();
        //
        timeOut(5L);
        $(locatorButtonOKInPopup).click();
        //check distance for one shop for block ClosestShops
        assertNotNull($(locatorDistanceValue));
    }

    private void switcherFeatureInSettings(String status) throws InterruptedException {
        enterToTheFeatureFromDrawer(locatorDrawerSettings, drawerSettingsRU);
        driver.scrollTo("Показывать ближайшие магазины");
        assertNotNull($(locatorTextShowClosestShopsRU_));
        //check that feature closest shops is turned on
        //boolean statusClosestShop = false;
        String statusClosestShop = $(locatorTextShowClosestShopsRU_).getText();
        if (statusClosestShop.equals(status)) {
            //turn on feature closest shops
            $(locatorTextShowClosestShopsRU_).click();
            driver.navigate().back();
            assertEquals($(locatorToolbarTitle).getText(), drawerShopRU, "Wrong toolbar title");
        } else driver.navigate().back();
    }

    private void clickOnTextTurnOnLocation() throws InterruptedException {
        if (getStatusTextTurnOn() == true) {
            //click on text turn on location
            turnOnLocationVIAPopup();
        } else {
            turnOffLocation();
            //enter to settings
            enterToTheFeatureFromDrawer(locatorDrawerSettings, drawerSettingsRU);
            //back
            driver.navigate().back();
            //turn on location
            turnOnLocationVIAPopup();
        }
    }

    private void returnMonth(int month) {
        //enter shop's name
        for (int i = 0; i < month; i++) {
            $(locatorCalendarPreMonth).click();
        }

    }

    private void chooseDayInCalendar() {
        //open filter by date
        $(locatorCalendar).click();
        //check title
        assertEquals($(locatorToolbarTitle).getText(), filtreByDateRU, "Wrong toolbar title with calendar");
        //open calendar
        $(locatorPickerCalendar).click();
        //return some month
        waitElement(locatorCalendarPreMonth);
        returnMonth(4);
        //choose day
        $(locatorCalendarDay).click();
        //approve day
        $(locatorButtonOKInPopup).click();
        //save date
        $(locatorButtonSaveDate).click();
    }

}
