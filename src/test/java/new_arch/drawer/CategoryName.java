package new_arch.drawer;

/**
 * Created by groshkka on 22.10.17.
 */
public enum CategoryName {
    CITY_RU("Город"),
    AUTHORIZATION_RU("Мой профиль"),
    SHOP_RU("Магазины"),
    CATEGORY_RU("Категории"),
    SALES_RU("Акции"),
    NOTIFICATION_RU("Уведомления"),
    FAVORITE_RU("Избранное"),
    CHAT_US_RU("Связаться с нами"),
    SETTINGS_RU("Настройки");

    private String categoryName;

    CategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String category(){
        return categoryName;
    }
}
