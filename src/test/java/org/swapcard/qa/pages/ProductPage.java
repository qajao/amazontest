package org.swapcard.qa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "quantity")
    private WebElement quantityDropdown;

    @FindBy(id = "a-autoid-2-announce")
    private WebElement dropdownButton;

    @FindBy(xpath = "//input[@id='add-to-cart-button' or @id='add-to-cart-button-ubb']")
    private WebElement addToCartButton;

    @FindBy(id = "acrCustomerReviewLink")
    private WebElement reviewsLink;

    @FindBy(xpath = "//ul[@id='histogramTable']//li[1]//div[@role='progressbar']")
    private WebElement fiveStar;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Select  quantity by clicking on dropdown
     */
    public void selectQuantity(String quantity) {
        // 1) Wait for dropdown button and click it
        waitForClickable(dropdownButton);
        scrollIntoView(dropdownButton);
        dropdownButton.click();

        // 2) "quantity_0" => "1", "quantity_1" => "2", etc.
        int index = Integer.parseInt(quantity) - 1;
        String optionId = "quantity_" + index;

        // 3) Wait for the dynamic option element (driver.findElement, because it's dynamic)
        WebElement dropdownOption = wait.until(ExpectedConditions.elementToBeClickable(By.id(optionId)));
        dropdownOption.click();
    }

    /**
     * Click on "Add to Cart" button
     */
    public void addToCart() {
        // Wait for the add-to-cart button to be clickable
        waitForClickable(addToCartButton);
        scrollIntoView(addToCartButton);
        addToCartButton.click();
    }

    /**
     * Get the 5 star rating percentage.
     */
    public int getFiveStarRatingPercentage() {
        waitForVisibility(fiveStar);
        String percentage = fiveStar.getAttribute("aria-valuenow");
        return Integer.parseInt(percentage);
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150);");
    }

    private void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
