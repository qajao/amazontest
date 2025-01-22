package org.swapcard.qa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class AmazonHomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(id = "searchDropdownBox")
    private WebElement categoryDropdown;

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Searches for a product using the Amazon search box
     */
    public void searchProduct(String productName) {
        waitForVisibility(searchBox).sendKeys(productName);
        waitForClickable(searchButton).click();
    }

    /**
     * Clicks on a product link whose title contains the given partial text
     */
    public void clickOnProduct(String productName) {
        By productLinkLocator = By.partialLinkText(productName);
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(productLinkLocator));
        productLink.click();
    }

    /**
     * Filters the search by selecting a category from the dropdown
     */
    public void filterByCategory(String category) {
        try {
            Select select = new Select(categoryDropdown);
            select.selectByVisibleText(category);
        } catch (ElementNotInteractableException e) {
            // fallback via JS
            final String script =
                    "var sel = arguments[0];" +
                            "for (var i = 0; i < sel.options.length; i++) {" +
                            "    if (sel.options[i].text.trim() === arguments[1].trim()) {" +
                            "        sel.selectedIndex = i;" +
                            "        sel.dispatchEvent(new Event('change')); " +
                            "        break;" +
                            "    }" +
                            "}";
            ((JavascriptExecutor) driver).executeScript(script, categoryDropdown, category);
        }
    }


    /**
     * Waits until the given element is visible before returning it.
     */
    private WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the given element is clickable before returning it.
     */
    private WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}