import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by groshkka on 12.11.2016.
 */
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
    public void likeLogOut() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //try ty increment likes
        managerLikes(locatorLike, false, true);
    }

    @Test
    public void dislikeLogOut() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //try ty decrement likes
        managerLikes(locatorDislike, false, false);
    }

    @Test
    public void likeSignIn() throws InterruptedException {
        //треба додумати логіку
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //try ty increment likes
        managerLikes(locatorLike, true, true);
    }

    @Test
    public void dislikeSignIn() throws InterruptedException {
        //треба додумати логіку
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //try ty decrement likes
        managerLikes(locatorDislike, true, false);
    }

    @Test
    public void loveSale() throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales);
        //check title
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //find sale name
        String saleName = driver.findElement(locatorSaleTitle).getText();
        System.out.println(saleName);
        //open sale
        driver.findElement(locatorStar).click();
        //check toolbar name
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //find sale name
        String saleNaame = driver.findElement(locatorSaleTitle).getText();
        System.out.println(saleName);
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
