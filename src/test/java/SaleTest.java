import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SaleTest extends BaseTest {
    @Test
    public void openSaleTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //open sale
        openSale();
    }

    @Test
    public void likeLogOutTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //try ty increment likes
        managerLikes(locatorLike, false, true);
    }

    @Test
    public void dislikeLogOutTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //check title
        assertEqualsText(locatorToolbarTitle, drawerSaleRU);
        //try ty decrement likes
        managerLikes(locatorDislike, false, false);
    }

    @Test
    public void likeSignInTest() throws InterruptedException {
        //sign in
        signIn();
        //enter to the feature from drawer
        openDrawer();
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //try ty increment likes
        managerLikes(locatorLike, true, true);
        //log out
        logOut();
    }

    @Test
    public void dislikeSignInTest() throws InterruptedException {
        // передумати логіку лайків
        //sign in
        signIn();
        //enter to the feature from drawer
        openDrawer();
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //try ty decrement likes
        managerLikes(locatorDislike, true, false);
        //log out
        logOut();
    }

    @Test
    public void addSaleToFavoriteLogOutTest() throws InterruptedException {
        int numberSales = getCountSales();
        //enter to the feature from drawer
        driver.findElement(locatorDrawerSales).click();
        timeOut(10L);
        //check title
        assertEqualsText(locatorToolbarTitle, drawerSaleRU);
        //click on star
        driver.findElement(locatorStar).click();
        checkCountSalesDrawer(numberSales);
    }

    @Test
    public void addSaleToFavoriteSignInTest() throws InterruptedException {
        /*//open drawer
        openDrawer();
        //save number love sales
        Thread.sleep(500);
        int numberSales = Integer.parseInt(driver.findElement(locatorNumberSales).getText());
        System.out.println(numberSales);
        //enter to the feature from drawer
        driver.findElement(locatorDrawerAutorization).click();
        timeOut(10L);
        //sign in
        enterUserData(email, password, false);
        //check account's data
        checkAccountData(nickName, email);*/
        signIn();
        int numberSales = getCountSales();
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //find sale name
        String saleName = driver.findElement(locatorSaleTitle).getText();
        System.out.println(saleName);
        //click on star
        driver.findElement(locatorStar).click();
        checkCountSalesDrawer(++numberSales);
        //
        logOut();
    }

    @Test
    public void openMapFromSaleTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //open sale
        openSale();
        //open map
        //scroll to the comment
        driver.scrollTo(comment);
        driver.findElement(locatorIconMap).click();
        //wait map view
        waitElement(locatorMapView);
    }

    @Test
    public void addCommentLogOutTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //open sale
        openSale();
        //scroll to the comment
        driver.scrollTo(comment);
        //save number comment
        String numberComment = driver.findElement(locatorNumComments).getText();
        //enter comment
        driver.findElement(locatorMyComment).sendKeys(testComment);
        //close keyboard
        driver.navigate().back();
        //send comment
        driver.findElement(locatorSendComment).click();
        //check number comments
        assertEqualsText(locatorNumComments, numberComment);
    }

    @Test
    public void addCommentSignInTest() throws InterruptedException {
        //sign in
        signIn();
        //enter to the feature from drawer
        openDrawer();
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //open sale
        openSale();
        //save number comment
        int numberComment = Integer.parseInt(driver.findElement(locatorNumComments).getText());
        //scroll to the comment
        driver.scrollTo(comment);
        //enter comment
        driver.findElement(locatorMyComment).sendKeys(testComment);
        //close keyboard
        driver.navigate().back();
        //send comment
        driver.findElement(locatorSendComment).click();
        //wait comment
        waitElement(locatorFindComment);
        //check number comments
        assertEqualsInt(locatorNumComments, ++numberComment);
    }

    /*public void addCoTest() throws InterruptedException {
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(locatorToolbarTitle, drawerSaleRU);
        //open sale
        //find sale
        driver.swipe(280, 1100, 280, 245, 2000);
        WebElement d = driver.findElement(locatorSaleImage);

        String saleName = d.getText();
        System.out.println(saleName);
        //open sale
        d.click();
        //check toolbar name
        assertEqualsText(locatorToolbarTitle, drawerSaleRU);
        //check saleName
        //assertEqualsText(locatorSaleTitle, saleName);
    }*/

    private void openSale() {
        //find sale
        String saleName = driver.findElement(locatorSaleTitle).getText();
        System.out.println(saleName);
        //open sale
        driver.findElement(locatorSaleImage).click();
        //check toolbar name
        assertEqualsText(locatorToolbarTitle, drawerSaleRU);
        //check saleName
        assertEqualsText(locatorSaleTitle, saleName);
    }

    private void managerLikes(By likesChanger, boolean signIn, boolean increment) {
        //get count likes
        int countLikesBefore = Integer.parseInt(driver.findElement(locatorCountLikes).getText());
        //click on likes
        driver.findElement(likesChanger).click();
        //get count likes
        int countLikesAfter = Integer.parseInt(driver.findElement(locatorCountLikes).getText());
        //compare likes
        if (signIn == true && increment == true) {
            assertEquals(countLikesAfter, ++countLikesBefore);
        } else if (signIn == true && increment == false) {
            assertEquals(countLikesAfter, --countLikesBefore);
        } else assertEquals(countLikesAfter, countLikesBefore);
    }

}
