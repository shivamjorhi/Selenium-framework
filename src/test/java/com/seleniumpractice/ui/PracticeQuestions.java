package com.seleniumpractice.ui;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PracticeQuestions extends BrowserSmokeTest {
    @Test
    public void loginTest() throws IOException {
        WebDriver driver = getDriver();
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
        WebElement emailInput = driver.findElement(By.id("userEmail"));
        WebElement passwordInput = driver.findElement(By.id("userPassword"));
        WebElement loginButton = driver.findElement(By.id("login"));

        emailInput.sendKeys("anshika@gmail.com");
        passwordInput.sendKeys("Iamking@000");
        loginButton.click();

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String destinationPath = System.getProperty("user.dir") + "/target/extent-reports/screenshots/loginTest.png";
        System.out.println("Screenshot saved at: " + destinationPath);
        File destFile = new File(destinationPath);
        FileUtils.copyFile(srcFile, destFile);
    }
}
