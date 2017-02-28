package com;

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
    By locatorCountDislike = By.id("textDislikes");
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
    By locatorCalendar = By.id("action_calendar");
    By locatorPickerCalendar = By.id("datePickerCaller");
    By locatorCalendarPreMonth = By.id("prev");
    By locatorButtonSaveDate = By.id("saveButton");
    By locatorCalendarDay = By.xpath("//android.view.View[@index='13']");

    //drawer menu
    By locatorDrawerCity = By.xpath("//android.widget.CheckedTextView[@text='Город']");
    By locatorDrawerAutorization = By.xpath("//android.widget.CheckedTextView[@text='Мой профиль']");
    By locatorDrawerShops = By.xpath("//android.widget.CheckedTextView[@text='Магазины']");
    By locatorDrawerCategory = By.xpath("//android.widget.CheckedTextView[@text='Категории']");
    By locatorDrawerSales = By.xpath("//android.widget.CheckedTextView[@text='Акции']");
    By locatorDrawerFavorite = By.xpath("//android.widget.CheckedTextView[@text='Избранное']");
    By locatorDrawerSettings = By.xpath("//android.widget.CheckedTextView[@text='Настройки']");
    By locatorDrawerCallUs = By.xpath("//android.widget.CheckedTextView[@text='Город']");

    //
    By locatorClosestShopsBlock = By.xpath("//android.widget.RelativeLayout[descendant::android.widget.TextView[@text='Ближайшие']]");
    By locatorProductShopsBlock = By.xpath("//android.widget.RelativeLayout[descendant::android.widget.TextView[@text='Продукты']]");
    By locatorTextTurnOnLocation = By.id("info");
    By locatorDistanceValue = By.id("tv_distance");
    By locatorFirstShopsBlockName = By.xpath("//android.widget.RelativeLayout[@index=0]//android.widget.TextView[contains(@resource-id,'title')]");
    By locatorLocationStatus = By.xpath("//android.view.ViewGroup[@index=1]");
    By locatorLocationStatus_ = By.xpath("//android.view.ViewGroup[descendant::android.widget.TextView[@text='Location']]");
    By locatorSearchSettings = By.xpath("//android.widget.Button[@resource-id,'search']");
    By locatorSearchFieldSettings = By.xpath("//android.widget.EditText[@resource-id,'search_src_text']");
    By locatorLocationSettings = By.xpath("//android.widget.TextView[@text='Location']");
    By locatorLocationSitcher = By.xpath("//android.widget.Switchm[@resource-id,'switch_widget']");
    By locatorButtonOKInPopup = By.xpath("//android.widget.Button[@text='OK']");
    By locatorButtonCancelInPopup = By.xpath("//android.widget.Button[@text='Cancel']");
    By locatorTextShowClosestShopsRU_ = By.xpath("//android.widget.Switch[preceding::android.widget.TextView[@text='Показывать ближайшие магазины']]");
    By locatorTextDeleteOldSalesRU = By.xpath("//android.widget.Switch[preceding::android.widget.TextView[@text='Удалять прошедшие акции']]");

}
