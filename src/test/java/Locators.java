import org.openqa.selenium.By;
public interface Locators {
    //open drawer
    By locatorOpenDrawer = By.xpath("//android.widget.ImageButton");

    By openFirstShop = By.id("listView");
    By openShop = By.id("name");
    By openShopCategory = By.id("text");
    By locatorDrawerNumberSales = By.id("text");
    By openCategoryShop = By.id("textViewName");
    By locatorToolbarTitle = By.className("android.widget.TextView");
    By shopInput = By.id("shop_input");
    By locatorSaleTitle = By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'tTitle')]//android.widget.TextView[contains(@resource-id,'title')]");
    By locatorSaleImage = By.id("tImage");
    By locatorLoginGoogle = By.id("gp");
    By locatorGoogleAccount = By.id("account_profile_picture");
    By locatorEmailName = By.id("account_name");
    By locatorLoginFB = By.id("fb");
    By locatorLoginVK = By.id("vk");
    By locatorFieldEmail = By.id("email");
    By locatorFieldPassword = By.id("password");
    By locatorFieldRepeatPassword = By.id("password_again");
    By locatorFieldOldPassword = By.id("passwordOld");
    By locatorButtonEnter = By.id("email_sign_in_button");
    By locatorButtonSave = By.id("saveButton");
    By locatorFieldNickname = By.id("nickname");
    By locatorLogOut = By.xpath("//android.widget.TextView[@text='Выйти из аккаунта']");
    By locatorRegistration = By.xpath("//android.widget.TextView[@text='Регистрация']");
    By locatorRecoveryPassword = By.xpath("//android.widget.TextView[@text='Восстановление пароля']");
    By locatorButtonRegistration = By.id("email_sign_in_button");
    By locatorCheckboxRememberMe = By.id("checkBox");
    By locatorCountSalesCategory = By.id("textViewActions");
    By locatorCountSalesShop = By.id("textActions");
    By locatorCountLikes = By.id("textLikes");
    By locatorLike = By.id("iconLike");
    By locatorDislike = By.id("iconDislike");
    By locatorStar = By.id("star");
    By locatorMapView = By.id("mapView");
    By locatorIconMap = By.id("iconMap");
    By locatorNumComments = By.id("numComments");
    By locatorMyComment = By.id("myComment");
    By locatorSendComment = By.id("sendComment");
    By locatorNumberSales = By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'nav_favorite')]//android.widget.TextView[contains(@resource-id,'text')]");
    By locatorFindComment = By.xpath("//android.widget.TextView[@text,'Chorosha akcia']");
    By locatorCityDnepr = By.xpath("//android.widget.RelativeLayout//android.widget.TextView[@text,'Днепр']");
    By locatorSearchCity = By.id("search_city_input");
    By locatorTextEmpty = By.id("empty");
    By locatorDeleteAllSales = By.id("delete_all");
    By locatorDismisDeleteSales = By.id("button2");
    By locatorAcceptDeleteSales = By.id("button1");

    //drawer menu
    By locatorDrawerCity = By.xpath("//android.widget.CheckedTextView[@text='Город']");
    By locatorDrawerAutorization = By.xpath("//android.widget.CheckedTextView[@text='Мой профиль']");
    By locatorDrawerShops = By.xpath("//android.widget.CheckedTextView[@text='Город']");
    By locatorDrawerCategory = By.xpath("//android.widget.CheckedTextView[@text='Категории']");
    By locatorDrawerSales = By.xpath("//android.widget.CheckedTextView[@text='Акции']");
    By locatorDrawerFavorite = By.xpath("//android.widget.CheckedTextView[@text='Избранное']");
    By locatorDrawerSettings = By.xpath("//android.widget.CheckedTextView[@text='Город']");
    By locatorDrawerCallUs = By.xpath("//android.widget.CheckedTextView[@text='Город']");
}
