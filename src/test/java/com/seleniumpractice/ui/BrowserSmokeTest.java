package com.seleniumpractice.ui;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.seleniumpractice.listeners.ExtentListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.URL;
import java.net.HttpURLConnection;

@Listeners(ExtentListener.class)
public class BrowserSmokeTest {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
         WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "chrome":
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                webDriver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.set(webDriver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
       WebDriver webDriver = driver.get();

       if ((result.getStatus() == ITestResult.SUCCESS || result.getStatus() == ITestResult.FAILURE) && webDriver != null) {
           try {
               File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
               String reportDir = System.getProperty("user.dir") + "/target/extent-reports/screenshots";
               File folder = new File(reportDir);
               folder.mkdirs();
               String screenshotPath = reportDir + "/" + result.getName() + "_" + System.currentTimeMillis() + ".png";
               FileUtils.copyFile(screenshot, new File(screenshotPath));

               ExtentTest currentTest = ExtentListener.getCurrentTest();
               if (currentTest != null) {
                   currentTest.addScreenCaptureFromPath(screenshotPath);
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

        if (webDriver != null) {
            webDriver.quit();
        }
        driver.remove();
    }
}
