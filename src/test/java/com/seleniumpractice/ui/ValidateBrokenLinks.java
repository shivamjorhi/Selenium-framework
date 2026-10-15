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

public class ValidateBrokenLinks extends BrowserSmokeTest {
    @Test
    public void verifyExamplePageTitle() throws IOException, InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://practice-automation.com/broken-links/");
        Assert.assertEquals(driver.getTitle(), "Broken Links | Practice Automation");
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        System.out.println("Total links found: " + allLinks.size());

        for(WebElement links : allLinks) {
            String url = links.getAttribute("href");
            if(url == null || url.isEmpty() || !url.startsWith("http")) {
                continue; // Skip invalid or non-http links
            }

            try {
                URL link = new URL(url);
                HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
                httpConn.setConnectTimeout(3000);
                httpConn.connect();
                int responseCode = httpConn.getResponseCode();

                if(responseCode >= 400) {
                    System.out.println(url + " is a broken link.");
                } else {
                    System.out.println(url + " is a valid link.");
                }
            } catch (IOException e) {
                System.out.println(url + " is a broken link.");
            }
        }
    }
}
