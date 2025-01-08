package org.swapcard.qa.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() throws InterruptedException {
        String browserName = System.getProperty("browser", "chrome");

        if ("chrome".equals(browserName)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        }
        else if ("firefox".equals(browserName)) {
            driver = new FirefoxDriver();
        }

        driver.get("https://www.amazon.com");

        // if you face errors here you may need to enter captcha manually. It's intermittent.
        driver.findElement(By.cssSelector("[placeholder=\"Search Amazon\"]"))
                .sendKeys("Ariano Suassuna" + Keys.ENTER);
        Thread.sleep(5000);
        driver.findElement(By.partialLinkText("Auto da Compadecida")).click();
        driver.findElement(By.id("add-to-cart-button-ubb")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void addAndRemoveProductWillResultInLessThan100Bucks() throws InterruptedException {
        driver.findElement(By.cssSelector("[placeholder=\"Search Amazon\"]"))
                .sendKeys("When: The Scientific Secrets of Perfect Timing" + Keys.ENTER);
        driver.findElement(By.cssSelector("[data-cy=\"title-recipe\"] a")).click();
        new Select(driver.findElement(By.cssSelector("[name=\"quantity\"]"))).selectByValue("2");
        driver.findElement(By.id("add-to-cart-button")).click();
        Thread.sleep(5000);
        driver.findElement(By.partialLinkText("Go to Cart")).click();
        driver.findElement(By.cssSelector("[data-a-selector=\"decrement\"]")).click();
        Thread.sleep(1000);
        String subTotal = driver.findElement(By.cssSelector("[id='sc-subtotal-amount-activecart'] span")).getText();
        float subTotalFloat = Float.parseFloat(subTotal.replaceAll("[^\\d.]", ""));

        assert(subTotalFloat < 100): "Subtotal should be less than 100 USD";
    }
}
