import org.testng.annotations.Test;

/**
 * Created by groshkka on 08.11.2016.
 */
public class ShopsTest extends BaseTest {

    @Test
    public void openShopTest() throws InterruptedException {
        Thread.sleep(5000);
        //open shop
        openShop(openShop, false);
    }

    @Test
    public void findShopThroughSearchTest() throws InterruptedException {
        Thread.sleep(5000);
        //open shop
        openShop(openShop, true);
    }

    @Test
    public void openSaleFromShopTest() throws InterruptedException {
        Thread.sleep(5000);
        //open shop
        openShop(openShop, false);

        //find sale name
        String saleName = driver.findElement(locatorSaleTitle).getText();
        System.out.println(saleName);

        //open sale
        driver.findElement(locatorSaleImage).click();

        //check toolbar name
        assertEqualsText(toolbarTitle, drawerSaleRU);
        //check saleName
        assertEqualsText(locatorSaleTitle, saleName);
    }

}
