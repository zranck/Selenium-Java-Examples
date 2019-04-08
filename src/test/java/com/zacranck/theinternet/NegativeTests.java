package com.zacranck.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class NegativeTests {
    WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod
    protected void setUp(@Optional("chrome") String browser) {
        // Create Driver
        System.out.println("Create driver: " + browser);

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            default:
                System.out.println("Do not know hot to start: " + browser + " starting chrome instead.");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }


        sleep(3000); // for demo purposes only

        driver.manage().window().maximize();
    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test()
    public void negativeTest(String username, String password, String expectedErrorMessage) {
        System.out.println("Starting Negative Test");


        // Open Page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        sleep(3000); // for demo purposes only
        System.out.println("Page is open");

        // Enter Incorrect Username
        driver.findElement(By.id("username")).sendKeys(username);

        // Enter Password
        driver.findElement(By.id("password")).sendKeys(password);

        // Push Log In Button
        driver.findElement(By.xpath("//*[@id=\"login\"]/button")).click();

        // Successful Log In Message
        WebElement errorMessage = driver.findElement(By.id("flash"));

        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Error message is not displayed\nActual Message: " + actualErrorMessage);


    }

    @AfterMethod
    protected void tearDown() {
        sleep(3000);
        System.out.println("Close driver");
        // Close driver
        driver.quit();
    }

    private void sleep(long millis) {
        // Purposely adding this implicit wait for demo purposes
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
