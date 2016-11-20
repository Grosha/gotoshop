import org.testng.annotations.Test;

/**
 * Created by groshkka on 20.11.2016.
 */
public class FavoritesSalesTest extends BaseTest {

    @Test
    public void openFavoriteSalesLogOutTest() throws InterruptedException {
        //open drawer
        openDrawer();
        //save number love sales
        String numberSales = driver.findElement(locatorDrawerNumberSales).getText();
        //enter to the feature from drawer
        driver.findElement(locatorDrawerFavorite).click();
        timeOut(10L);
        //check title
        assertEqualsText(locatorToolbarTitle, toolbarFavorite);
        //check result
        assertEqualsText(locatorTextEmpty, resultNothingFind);
    }

    @Test
    public void acceptDeleteSalesFromFavoriteTest() throws InterruptedException {
        signIn();
        //add sale to the favorite and open feature favorite
        addSaleToTheFavorite(getCountSales());
        driver.findElement(locatorDeleteAllSales).click();

        timeOut(1L);
        //accept delete sales
        driver.findElement(locatorAcceptDeleteSales).click();
        //check result
        assertEqualsText(locatorTextEmpty, resultNothingFind);
        checkCountSalesDrawer(0);
        //log out
        logOut();
    }

    @Test
    public void dismissDeleteSalesFromFavoriteTest() throws InterruptedException {
        signIn();
        int numberSales = getCountSales();
        int newNumberSales = ++numberSales;
        //add sale to the favorite and open feature favorite
        addSaleToTheFavorite(numberSales);
        driver.findElement(locatorDeleteAllSales).click();

        timeOut(1L);
        //accept delete sales
        driver.findElement(locatorDismisDeleteSales).click();
        //check result
        checkCountSalesDrawer(newNumberSales);
        //log out
        logOut();
    }

    @Test
    public void deleteSaleFromFavoriteTest() throws InterruptedException {
        signIn();
        //add sale to the favorite and open feature favorite
        addSaleToTheFavorite(getCountSales());
        driver.findElement(locatorStar).click();
        //check result
        assertEqualsText(locatorTextEmpty, resultNothingFind);
        checkCountSalesDrawer(0);
        //log out
        logOut();
    }

    private void addSaleToTheFavorite(int numberSales) throws InterruptedException {
        //enter to the feature from drawer
        enterToTheFeatureFromDrawer(locatorDrawerSales, drawerSaleRU);
        //find sale name
        String saleName = driver.findElement(locatorSaleTitle).getText();
        System.out.println(saleName);
        //click on star
        driver.findElement(locatorStar).click();
        checkCountSalesDrawer(numberSales);

        enterToTheFeatureFromDrawer(locatorDrawerFavorite, toolbarFavorite);
    }
}
