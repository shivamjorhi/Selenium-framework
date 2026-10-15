package com.seleniumpractice.ui;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import java.io.File;

import com.seleniumpractice.ui.BrowserSmokeTest;

public class Screenshot extends BrowserSmokeTest {
    @Test
   public void takeScreenshot() throws IOException, InterruptedException{
        WebDriver driver = getDriver();
        // Implement screenshot logic here
        driver.get("https://www.google.com");
        Thread.sleep(3000);
        driver.findElement(By.name("q")).sendKeys("facebook");
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshot.png");
        FileUtils.copyFile(srcFile, destFile);
        System.out.println("Screenshot taken and saved as screenshot.png");
        System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());

    }
}
