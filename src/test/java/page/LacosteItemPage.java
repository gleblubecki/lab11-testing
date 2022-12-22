package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtils;

public class LacosteItemPage extends AbstractPage{
    public static String LACOSTE_ITEM_PAGE_URL = "https://www.lacoste.pl/en/bh2153/53s-1/?integration_renk=53S&integration_beden";
    private final String buttonSizeXpath = "//a[@data-value='T56']";
    private final String closePopOutMenuXpath = "//*[@id=\"js-sidepopup-close\"]";
    private final String buttonSizeDiffXpath = "//a[@data-value='T54']";
    private final String selectOpenXpath = "//button[@class='lc-button lc-button--wide md-px-0 js-sidepopup-trigger' and @data-target='product-sizes']";
    private final String cookiesXpath = "//class[@class=\"cookie-consent-accept js-cookie-consent-accept\"]";
    private final String addToCartButtonXpath = "//a[@class=\"js-add-basket product-add-to-basket lc-button lc-button--primary product-button js-lac-basket-btn\"]";
    private final String errorMessageXpath = "//span[@class='basket-icon-badge js-total-quantity']";
    private final String  amountOfProductsInCartXpath = "//span[@class='basket-icon-badge js-total-quantity']";

    @FindBy(xpath = buttonSizeXpath)
    private WebElement buttonSize;

    @FindBy(xpath = closePopOutMenuXpath)
    private WebElement closePopOutMenu;

    @FindBy(xpath = buttonSizeDiffXpath)
    private WebElement buttonSizeDiff;

    @FindBy(xpath = selectOpenXpath)
    private WebElement selectOpen;

    @FindBy(xpath = cookiesXpath)
    private WebElement cookies;

    @FindBy(xpath = addToCartButtonXpath)
    private WebElement addToCartButton;

    @FindBy(xpath = errorMessageXpath)
    private WebElement errorMessage;

    @FindBy(xpath = amountOfProductsInCartXpath)
    private WebElement amountOfProductsInCart;

    public LacosteItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public LacosteItemPage closePopOutMenu() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(closePopOutMenuXpath, driver);
        closePopOutMenu.click();
        return this;
    }

    public LacosteItemPage closeCookies() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(cookiesXpath, driver);
        cookies.click();
        return this;
    }

    public LacosteItemPage clickSizeDiffButton() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(selectOpenXpath, driver);
        selectOpen.click();
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(buttonSizeDiffXpath, driver);
        buttonSize.click();
        return this;
    }

    public LacosteItemPage clickSizeButton() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(selectOpenXpath, driver);
        selectOpen.click();
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(buttonSizeXpath, driver);
        buttonSize.click();
        return this;
    }

    public LacosteItemPage addItemToCart() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(addToCartButtonXpath, driver);
        addToCartButton.click();
        return this;
    }

    public String tryGetErrorMessage() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(errorMessageXpath, driver);
        return errorMessage.getText();
    }

    public String getAmountOfProducts() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(amountOfProductsInCartXpath ,driver);
        WaitUtils.waitForNumberOfElementsLocatedByXpathToBe(amountOfProductsInCartXpath , 1 , driver );
        return amountOfProductsInCart.getText();
    }

    @Override
    public LacosteItemPage openPage() {
        driver.get(LACOSTE_ITEM_PAGE_URL);
        return this;
    }

}
