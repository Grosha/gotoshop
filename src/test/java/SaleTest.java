import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SaleTest extends BaseTest {
    @Test
    public void openSaleTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //open sale
        openSale();
    }

    @Test
    public void likeLogOutTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //try ty increment likes
        managerLikes(locatorLike, false, true);
    }

    @Test
    public void dislikeLogOutTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //try ty decrement likes
        managerLikes(locatorDislike, false, false);
    }

    @Test
    public void likeSignInTest() throws InterruptedException {
        //треба додумати логіку
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //try ty increment likes
        managerLikes(locatorLike, true, true);
    }

    @Test
    public void dislikeSignInestT() throws InterruptedException {
        //треба додумати логіку
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //try ty decrement likes
        managerLikes(locatorDislike, true, false);
    }

    @Test
    public void addSaleToFavoriteLogOutestT() throws InterruptedException {
        //open drawer
        openDrawer();
        //save number love sales
        String numberSales = driver.findElement(locatorNumberSales).getText();
        //enter to the feature from drawer
        driver.findElement(locatorDrawerSales).click();
        timeOut(10L);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //find sale name
        String saleName = driver.findElement(locatorSaleTitle).getText();
        System.out.println(saleName);
        //click on star
        driver.findElement(locatorStar).click();
        //open drawer
        openDrawer();
        //compare count sales
        assertEqualsText(locatorNumberSales, numberSales);
    }

    @Test
    public void addSaleToFavoriteSignInTest() throws InterruptedException {
        //open drawer
        openDrawer();
        //save number love sales
        int numberSales = Integer.parseInt(driver.findElement(locatorNumberSales).getText());
        //enter to the feature from drawer
        driver.findElement(locatorDrawerAutorization).click();
        timeOut(10L);
        //sign in
        enterUserData(email, password, false);
        //check account's data
        checkAccountData(nickName, email);
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //find sale name
        String saleName = driver.findElement(locatorSaleTitle).getText();
        System.out.println(saleName);
        //click on star
        driver.findElement(locatorStar).click();
        //open drawer
        openDrawer();
        //compare count sales
        assertEqualsInt(locatorNumberSales, ++numberSales);
    }

    @Test
    public void openMapFromSaleTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //open sale
        openSale();
        //open map
        driver.findElement(locatorIconMap).click();
        //wait map view
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorMapView));
    }

    @Test
    public void addCommentLogOutTest() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //open sale
        openSale();
        //save number comment
        String numberComment = driver.findElement(locatorNumComments).getText();
        //scroll to the comment
        Actions dragger = new Actions(driver);
        WebElement comment = driver.findElement(locatorMyComment);
        dragger.moveToElement(comment);
        comment.click();
        //enter comment
        comment.sendKeys(testComment);
        //send comment
        driver.findElement(locatorSendComment);
        //check number comments
        assertEqualsText(locatorNumComments, numberComment);
    }

    @Test
    public void addCommentSignInTest() throws InterruptedException {
        //sign in
        signIn();
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //open sale
        openSale();
        //save number comment
        int numberComment = Integer.parseInt(driver.findElement(locatorNumComments).getText());
        //scroll to the comment
        Actions dragger = new Actions(driver);
        WebElement comment = driver.findElement(locatorMyComment);
        dragger.moveToElement(comment);
        comment.click();
        //enter comment
        comment.sendKeys(testComment);
        //send comment
        driver.findElement(locatorSendComment);
        //wait comment
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorFindComment));
        //check number comments
        assertEqualsInt(locatorNumComments, ++numberComment);
    }

    private void openSale() {
        //find sale
        String saleName = driver.findElement(locatorSaleTitle).getText();
        System.out.println(saleName);
        //open sale
        driver.findElement(locatorSaleImage).click();
        //check toolbar name
        assertEqualsText(toolbarTitle, drawerSaleRU);
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
