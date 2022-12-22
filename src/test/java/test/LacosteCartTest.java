package test;

import model.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import page.LacosteCartPage;
import page.LacosteItemPage;
import service.ProductCreator;
import service.TestDataReader;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LacosteCartTest extends CommonConditions {
    public final String APPROVAL_REMOVAL_MESSAGE = TestDataReader.getTestData("testdata.approval_removal_message");
    public final String DOUBLED_PRODUCT_PRICE  = TestDataReader.getTestData("testdata.product.doubled.price");
    public final String INVALID_PROMO_CODE = TestDataReader.getTestData("testdata.invalid_promo_code");
    public final String INVALID_PROMO_CODE_MESSAGE = TestDataReader.getTestData("testdata.invalid_promo_code_message");

    @BeforeMethod(onlyForGroups = {"addedItemToCartPreconditionIsNeeded"})
    public void addItemToCart() {
        new LacosteItemPage(driver)
                .openPage()
                .closeCookies()
                .addItemToCart();
    }

    @Test
    public void addItemToCartTest() {
        Product testProduct = ProductCreator.withCredentialsFromProperty();

        new LacosteItemPage(driver)
                .openPage()
                .closeCookies()
                .clickSizeButton()
                .addItemToCart();

        LacosteCartPage lacosteCartPage = new LacosteCartPage(driver);
        String addedProductName = lacosteCartPage
                .openPage()
                .getProductName();
        String addedProductPrice = lacosteCartPage
                .getProductPrice();

        assertThat(addedProductName, is(equalTo(testProduct.getName())));
        assertThat(addedProductPrice, is(equalTo(testProduct.getPrice())));
    }

    @Test
    public void addItemToCartWithoutSizeTest() {
        String messageAfterSelectingNoSize = new LacosteItemPage(driver)
                .openPage()
                .closeCookies()
                .tryGetErrorMessage();
        assertThat(messageAfterSelectingNoSize, is(equalTo(APPROVAL_REMOVAL_MESSAGE)));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void priceChangeAfterAddingSecondSameItem() {
        String doubledPrice = new LacosteCartPage(driver)
                .openPage()
                .closeCookies()
                .addTheSameItem()
                .getProductPrice();
        assertThat(doubledPrice, is(equalTo(DOUBLED_PRODUCT_PRICE)));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void removeItemFromCartTest() {
        String messageAfterProductRemoval = new LacosteCartPage(driver)
                .openPage()
                .closeCookies()
                .removeItemFromCart()
                .getApprovalOfProductRemovalMessage();
        assertThat(messageAfterProductRemoval, is(equalTo(APPROVAL_REMOVAL_MESSAGE)));
    }

    @Test
    public void addItemsWithDifferentSizesToCartTest() {
        Product testProduct = ProductCreator.withCredentialsFromProperty();

        new LacosteItemPage(driver)
                .openPage()
                .closeCookies()
                .clickSizeButton()
                .addItemToCart()
                .closePopOutMenu()
                .clickSizeDiffButton()
                .addItemToCart();
        LacosteCartPage lacosteCartPage = new LacosteCartPage(driver);
        String addedProductName = lacosteCartPage
                .openPage()
                .closeCookies()
                .getProductName();
        String addedProductPrice = lacosteCartPage
                .getProductPrice();

        assertThat(addedProductName, is(equalTo(testProduct.getName())));
        assertThat(addedProductPrice, is(equalTo(testProduct.getPrice())));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void restoreItemToCartTest() {
        Product testProduct = ProductCreator.withCredentialsFromProperty();

        LacosteCartPage bigGeekCartPage = new LacosteCartPage(driver)
                .openPage()
                .removeItemFromCart()
                .restoreItemToCart();
        String restoredProductName = bigGeekCartPage.getProductName();
        String restoredProductPrice = bigGeekCartPage.getProductPrice();

        assertThat(restoredProductName, is(equalTo(testProduct.getName())));
        assertThat(restoredProductPrice, is(equalTo(testProduct.getPrice())));
    }

    @Test(groups = {"addedItemToCartPreconditionIsNeeded"})
    public void useInvalidPromoCodeTest() {
        String promoCodeStatusMessage = new LacosteCartPage(driver)
                .openPage()
                .enterPromoCode(INVALID_PROMO_CODE)
                .getPromoCodeStatusMessage();
        Assert.assertTrue(promoCodeStatusMessage.contains(INVALID_PROMO_CODE_MESSAGE),
                "The error message of using invalid promo code was not shown!");
    }
}
