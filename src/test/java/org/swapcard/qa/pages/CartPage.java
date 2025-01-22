package org.swapcard.qa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "input[value='Delete']")
    private WebElement deleteButton;

    @FindBy(id = "sc-subtotal-amount-activecart")
    private WebElement subTotalAmount;

    @FindBy(partialLinkText = "Go to Cart")
    private WebElement goToCartLink;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        // Adjust timeout as needed; here we use 10 seconds for consistency.
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the cart view by clicking the "Go to Cart" link (if available).
     */
    public void navigateToCart() {
        waitForClickable(goToCartLink).click();
    }

    /**
     * Removes an item from the cart by clicking the delete button.
     */
    public void removeItem() {
        waitForClickable(deleteButton).click();
    }

    /**
     * Retrieves the current cart subtotal by parsing the text of the subtotal element.
     */
    public float getSubTotal() {
        waitForVisibility(subTotalAmount);
        String subTotalText = subTotalAmount.getText().replaceAll("[^\\d.]", "");
        return Float.parseFloat(subTotalText);
    }

    /**
     * Waits until the given element is clickable, then returns it.
     */
    private WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the given element is visible in the DOM, then returns it.
     */
    private WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}