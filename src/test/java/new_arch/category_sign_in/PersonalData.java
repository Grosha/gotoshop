package new_arch.category_sign_in;

/**
 * Created by groshkka on 23.10.17.
 */
public enum PersonalData {
    EMAIL("testgrosha23@gmail.com"),
    EMAIL_WITHOUT_DOMEN("testgrosha23@"),
    EMAIL_WITHOUT_NICK("@gmail.com"),
    EMAIL_WITHOUT_AT("testgrosha23gmail.com"),
    PASSWORD("gotoshop"),
    PASSWORD_SHORT("goto"),
    PASSWORD_6_SYMBOLS("gotosh"),
    PASSWORD_WITH_NUMBERS("goto00"),
    PASSWORD_NUMBERS("123456"),
    PASSWORD_WITH_UPP_LETTERS("GOTOSHOP"),
    PASSWORD_WITH_UPP_LOW_LETTERS("GoToShOp"),
    PASSWORD_WITH_UPP_LOW_LETTERS_NUMBERS("GoTo18Op"),
    PASSWORD_WITH_UPP_LETTERS_NUMBERS("12TOSHOP"),
    PASSWORD_WITH_SYMBOLS("/!@$%&!@#$%^&*()_+"),
    PASSWORD_WITH_SYMBOLA_NUMBERS_LETTERS("_O9O^h1&"),
    PASSWORD_WITH_SPASE("Go  sh p"),
    PASSWORD_WITH_SPASE_START(" o  sh p"),
    PASSWORD_WITH_SPASE_FINISH("Go  sho "),
    ;

    private String personalData;

    PersonalData(String personalData) {
        this.personalData = personalData;
    }

    public String data(){
        return personalData;
    }
}
