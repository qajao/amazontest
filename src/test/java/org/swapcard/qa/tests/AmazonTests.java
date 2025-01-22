package org.swapcard.qa.tests;

import org.swapcard.qa.pages.AmazonHomePage;
import org.swapcard.qa.pages.ProductPage;
import org.swapcard.qa.pages.CartPage;
import org.testng.annotations.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

public class AmazonTests extends BaseTest {
    private AmazonHomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void initPages() {
        homePage = new AmazonHomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @DataProvider(name = "CartData")
    public Object[][] provideCartData() {
        return new Object[][] {
                { "Ariano Suassuna", "Auto da Compadecida", "Essential Scrum: A Practical Guide to the Most Popular Agile Process (Addison-Wesley Signature Series (Cohn))" }
        };
    }

    @DataProvider(name = "BookData")
    public Object[][] provideBookData() {
        return new Object[][] {
                { "Jorge Amado", "Dona Flor and Her Two Husbands" },
                { "Nick page", "Christmas: Tradition, Truth and Total Baubles" }
        };
    }

    @Test(dataProvider = "CartData")
    public void addAndRemoveProductWillResultInLessThan100Bucks(
            String author,
            String firstTitle,
            String secondTitle
    ) {
        // Search and add fist product
        homePage.searchProduct(author);
        homePage.clickOnProduct(firstTitle);
        productPage.addToCart();

        // Search and add second product
        homePage.searchProduct(secondTitle);
        homePage.clickOnProduct(secondTitle);
        productPage.selectQuantity("2");
        productPage.addToCart();

        // Navigate to cart and remove one of the items
        cartPage.navigateToCart();
        cartPage.removeItem();

        // Check if subtotal is less than 100 USD
        float subTotal = cartPage.getSubTotal();
        assertThat("Expected the cart subtotal to be less than 100 USD, but it was " + subTotal,
                subTotal, lessThan(100.0f));
    }

    @Test(dataProvider = "BookData")
    public void validateFiveStarRating(String searchKeyword, String title) {
        // Filter products by the 'Books' category
        homePage.filterByCategory("Books");
        // Search for products 'Jorge Amado'
        homePage.searchProduct(searchKeyword);
        // Click on the product titled
        homePage.clickOnProduct(title);

        // Get five start rating percentage
        int fiveStarPercentage = productPage.getFiveStarRatingPercentage();

        // Assert that at least 70% of the reviews are 5 star ratings
        assertThat("Percentage of 5 star reviews is less than 70%",
                fiveStarPercentage, greaterThanOrEqualTo(70));
    }
}