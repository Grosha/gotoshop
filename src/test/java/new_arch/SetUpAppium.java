package new_arch;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by groshkka on 22.10.17.
 */
public abstract class SetUpAppium {
    public AndroidDriver driver;
    private String packageGoToShop = "com.laitauril.gotoshop";
    private String mainActivity = ".app.activity.SplashActivity";

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        //capabilities.setCapability("platformVersion", "5.1");

        capabilities.setCapability("appPackage", packageGoToShop);
        capabilities.setCapability("appWaitPackage", packageGoToShop);
        capabilities.setCapability("appActivity", mainActivity);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        initDriver();
    }

    @AfterClass
    public void setDown() {
        driver.quit();
    }

    public abstract void initDriver();
}
