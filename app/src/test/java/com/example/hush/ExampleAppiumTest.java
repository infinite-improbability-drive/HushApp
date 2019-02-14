package com.example.hush;

import java.net.MalformedURLException;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



    public class ExampleAppiumTest {

        WebDriver driver;

        @Before
        public void setUp() throws MalformedURLException {
            // Created object of DesiredCapabilities class.
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // Set android deviceName desired capability. Set your device name.
            capabilities.setCapability("deviceName", "XT1562");

            // Set BROWSER_NAME desired capability. It's Android in our case here.
            // capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

            // Set android VERSION desired capability. Set your mobile device's OS version.
            capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

            // Set android platformName desired capability. It's Android in our case here.
            capabilities.setCapability("platformName", "Android");

            // Set android appPackage desired capability. It is
            // com.android.calculator2 for calculator application.
            // Set your application's appPackage if you are using any other app.
            capabilities.setCapability("appPackage", "com.example.hush");

            // Set android appActivity desired capability. It is
            // com.android.calculator2.Calculator for calculator application.
            // Set your application's appPackage if you are using any other app.
            capabilities.setCapability("appActivity", "com.example.hush.MainActivity");

            // Created object of RemoteWebDriver will all set capabilities.
            // Set appium server address and port number in URL string.
            // It will launch calculator app in android device.
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


        }

//        @Test
//        public void testFirstCalculator() {
//
//            // Click on DELETE/CLR button to clear result text box before running test.
//            driver.findElements(By.xpath("//android.widget.Button")).get(0).click();
//
//            // Click on number 2 button.
//            driver.findElement(By.name("7")).click();
//
//            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
//        }

        @After
        public void End() {
            driver.quit();
        }
    }
