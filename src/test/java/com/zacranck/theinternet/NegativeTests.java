package com.zacranck.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

    @Test(priority = 1,groups = { "smokeTests"})
    public void incorrectUsernameTest(){
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

        // Enter Incorrect Username
        driver.findElement(By.id("username")).sendKeys("wrongUser");

        // Enter Password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        // Push Log In Button
        driver.findElement(By.xpath("//*[@id=\"login\"]/button")).click();

        // Successful Log In Message
        WebElement errorMessage = driver.findElement(By.id("flash"));
        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Error message is not displayed\nActual Message: " + actualErrorMessage);

        sleep(3000);

        driver.quit();
    }

    @Test(priority = 2,enabled = true,groups = { "smokeTests", "negativeTests" })
    public void incorrectPasswordTest(){
        // Create Driver
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        sleep(3000); // for demo purposes only

        driver.manage().window().maximize();

        // Open Page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        sleep(3000); // for demo purposes only
        System.out.println("Page is open");

        // Enter Incorrect Username
        driver.findElement(By.id("username")).sendKeys("tomsmith");

        // Enter Password
        driver.findElement(By.id("password")).sendKeys("wrongPassword");

        // Push Log In Button
        driver.findElement(By.xpath("//*[@id=\"login\"]/button")).click();

        // Password Error Message
        WebElement errorMessage = driver.findElement(By.id("flash"));
        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Error message is not displayed\nActual Message: " + actualErrorMessage);

        sleep(3000);

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
