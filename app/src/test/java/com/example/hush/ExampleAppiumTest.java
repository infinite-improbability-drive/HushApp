package com.example.hush;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ExampleAppiumTest {

        WebDriver driver;

        @Before
        public void setUp() throws MalformedURLException {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "XT1562");
            capabilities.setCapability(CapabilityType.VERSION, "6.0.1");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appPackage", "com.example.hush");
            capabilities.setCapability("appActivity", "com.example.hush.MainActivity");
            capabilities.setCapability("autoGrantPermissions", "true");
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }

        @Test
        public void testApp() {
            driver.findElements(By.xpath("//android.widget.Button")).get(0).click();
        }

        @After
        public void End() {
            driver.quit();
        }
    }
