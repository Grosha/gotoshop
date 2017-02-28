package com;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by groshkka on 02.12.2016.
 */
public class SetUpAppium implements Variable, Locators {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "5.1");

        capabilities.setCapability("appPackage", packageGoToShop);
        capabilities.setCapability("appWaitPackage", packageGoToShop);
        capabilities.setCapability("appActivity", mainActivity);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }
}
