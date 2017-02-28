package com.portret;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SaleTest extends BaseTest {
    @Test
    public void openSaleTest() throws InterruptedException {
        timeOut(start);
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //open sale
        openSale();
    }

    @Test
    public void likeLogOutTest() throws InterruptedException {
        timeOut(start);
        //check authorization
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        //sign in
        try {
            $(locatorButtonEnter);
        }catch (NoSuchElementException e){
            $(locatorLogOut).click();
            $(locatorButtonEnter);
        }
        //enter to the feature from drawer
        driver.navigate().back();
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);

        //try ty increment likes
        managerLikes(locatorCountLikes, false, true);
    }

    @Test
    public void dislikeLogOutTest() throws InterruptedException {
        timeOut(start);
        //check authorization
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        //sign in
        try {
            $(locatorButtonEnter);
        }catch (NoSuchElementException e){
            $(locatorLogOut).click();
            $(locatorButtonEnter);
        }
        //enter to the feature from drawer
        driver.navigate().back();
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //check title
        assertEquals($(locatorToolbarTitle).getText(),drawerSaleRU);
        //try ty decrement likes
        managerLikes(locatorCountDislike, false, false);
    }

    @Test
    public void likeSignInTest() throws InterruptedException {
        timeOut(start);
        //sign in
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        try {
            $(locatorLogOut);
        }catch (NoSuchElementException e){
            signIn();

        }
        //enter to the feature from drawer
        driver.navigate().back();
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //try ty increment likes
        openSale();
        managerLikes(locatorCountLikes, true, true);
        //log out
        logOut();
    }

    @Test
    public void dislikeSignInTest() throws InterruptedException {
        timeOut(start);
        // передумати логіку лайків
        //sign in
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        try {
            $(locatorLogOut);
        }catch (NoSuchElementException e){
            signIn();
        }
        //enter to the feature from drawer
        driver.navigate().back();
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //try ty decrement likes
        openSale();
        managerLikes(locatorCountDislike, true, true);
        //log out
        logOut();
    }

    @Test
    public void addSaleToFavoriteLogOutTest() throws InterruptedException {
        int numberSales = getCountSales();
        //enter to the feature from drawer
        $(locatorDrawerSales).click();
        timeOut(10L);
        //check title
        assertEquals($(locatorToolbarTitle).getText(),drawerSaleRU);
        //click on star
        $(locatorStar).click();
        checkCountSalesDrawer(numberSales);
    }

    @Test
    public void addSaleToFavoriteSignInTest() throws InterruptedException {
        timeOut(start);
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        try {
            $(locatorLogOut);
        }catch (NoSuchElementException e){
            signIn();
        }
        int numberSales = getCountSales();
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //find sale name
        String saleName = $(locatorSaleTitle).getText();
        System.out.println(saleName);
        //click on star
        $(locatorStar).click();
        enterToTheFeatureFromDrawer(locatorDrawerFavorite, toolbarFavorite);
        driver.runAppInBackground(2);
        checkCountSalesDrawer(++numberSales);
        //
        logOut();
    }

    @Test
    public void openMapFromSaleTest() throws InterruptedException {
        timeOut(start);
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //open sale
        openSale();
        //open map
        //scroll to the comment
        driver.scrollTo(comment);
        $(locatorIconMap).click();
        //wait map view
        waitElement(locatorMapView);
    }

    @Test
    public void addCommentLogOutTest() throws InterruptedException {
        timeOut(start);
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //open sale
        openSale();
        //scroll to the comment
        driver.scrollTo(comment);
        //save number comment
        String numberComment = $(locatorNumComments).getText();
        //enter comment
        enterComment();
        //check number comments
        assertEquals($(locatorNumComments).getText(),numberComment);
    }

    @Test
    public void addCommentSignInTest() throws InterruptedException {
        timeOut(start);
        //sign in
        enterToTheFeatureFromDrawer(locatorDrawerAutorization, drawerMyAccountRU);
        try {
            $(locatorLogOut);
        }catch (NoSuchElementException e){
            signIn();
        }
        //enter to the feature from drawer
        openDrawer();
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //open sale
        openSale();
        //save number comment
        int numberComment = Integer.parseInt($(locatorNumComments).getText());
        //scroll to the comment
        driver.scrollTo(comment);
        //enter comment
        enterComment();
        driver.scrollTo("ОСТАВИТЬ ОТЗЫВ");
        $(locatorSendComment).click();
        //wait comment
        waitElement(locatorFindComment);
        //check number comments
        assertEqualsInt(locatorNumComments, ++numberComment);
    }

    private void openSale() {
        //find sale
        String saleName = $(locatorSaleTitle).getText();
        System.out.println(saleName);
        //open sale
        $(locatorSaleImage).click();
        //check toolbar name
        assertEquals($(locatorToolbarTitle).getText(),drawerSaleRU);
        //check saleName
        assertEquals($(locatorSaleTitle).getText(),saleName);
    }

    private void enterComment() {
        //enter comment
        $(locatorMyComment).sendKeys(testComment);
        //close keyboard
        driver.navigate().back();
        //send comment
        $(locatorSendComment).click();
        //check number comments
    }

    private void managerLikes(By likesChanger, boolean signIn, boolean increment) {
        driver.scrollTo(comment);
        //get count likes
        int countLikesBefore = Integer.parseInt($(likesChanger).getText());
        //click on likes
        $(likesChanger).click();
        //get count likes
        int countLikesAfter = Integer.parseInt($(likesChanger).getText());
        //compare likes
        if (signIn == true && increment == true) {
            assertEquals(countLikesAfter, ++countLikesBefore);
        } else {
            assertEquals(countLikesAfter, countLikesBefore);
        }
        driver.navigate().back();
    }

}
