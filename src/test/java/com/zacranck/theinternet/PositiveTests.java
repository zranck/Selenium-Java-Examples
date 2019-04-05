package com.zacranck.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

    @Test
    public void loginTest() {
        // Create Driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        sleep(3000); // for demo purposes only

        driver.manage().window().maximize();

        // Open Page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        sleep(3000); // for demo purposes only
        System.out.println("Page is open");

        // Enter Username
        driver.findElement(By.id("username")).sendKeys("tomsmith");

        // Enter Password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        // Push Log In Button
        driver.findElement(By.xpath("//*[@id=\"login\"]/button")).click();


        // Verifications
        // New URL
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);

        // Log Out Button Visible
        WebElement logOutButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a"));
        Assert.assertTrue(logOutButton.isDisplayed(),"Logout button is not visible");

        // Successful Log In Message
        WebElement successMessage = driver.findElement(By.id("flash"));
        String expectedSuccessMessage = "You logged into a secure area!";
        String actualSuccessMessage = successMessage.getText();
        Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),"success message does not contain expected\nActual Message: " + actualSuccessMessage);


        sleep(3000); // for demo purposes only

        // Close Browser
        driver.quit();
    }
    private void sleep(long millis) {
        // Purposely adding this implicit wait for demo purposes
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}