package com.seleniumpractice.ui;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Set;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageMultipleWindows extends BrowserSmokeTest {
    @Test
    public void manageMultipleWindows() throws IOException, InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://www.hyrtutorials.com/p/window-handles-practice.html");
        String originalWindow = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement newTabBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("newTabBtn")));
        newTabBtn.click();

        Set<String> windowHandles = driver.getWindowHandles();
        for(String handle : windowHandles) {
            if(!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                System.out.println("Switched to new window with title: " + driver.getTitle());
                driver.close();
            }
        }

        driver.switchTo().window(originalWindow);
        System.out.println("Switched back to original window with title: " + driver.getTitle());
    }
}
