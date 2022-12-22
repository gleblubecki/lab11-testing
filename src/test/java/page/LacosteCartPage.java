package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtils;

public class LacosteCartPage extends AbstractPage {
    public static String LACOSTE_CART_PAGE_URL = "https://www.lacoste.pl/baskets/basket/";
    private final String cookiesXpath = "//class[@class=\"cookie-consent-accept js-cookie-consent-accept\"]";
    private final String productNameXpath = "//div[@class=\"name\"]";
    private final String productPriceXpath = "//div[@class=\"price\"]";
    private final String removeItemButtonXpath = "//a[@class=\"js-delete\"]";
    private final String approvalOfProductRemovalMessageXpath = "//div[@class=\"basket__items--empty__title\"]";
    private final String restoreItemButtonXpath = "//button[@class=\"c-button c-button--secondary c-button--animation\"]";
    private final String promoCodeInputId = "\"voucher_code\"";
    private final String promoCodeSubmitButtonXpath = "//*[@class=\"js-voucher-form voucher-form\"]/html/body/div[4]/div[1]/div[2]/div/div[3]/div[2]/div[1]/div[2]/form/button";
    private final String promoCodeStatusMessageXpath = "//*[@class=\"error js-error-non_field_errors\"]";

    @FindBy(xpath = cookiesXpath)
    private WebElement cookies;

    @FindBy(xpath = productNameXpath)
    private WebElement productName;

    @FindBy(xpath = productPriceXpath)
    private WebElement productPrice;

    @FindBy(xpath = removeItemButtonXpath)
    private WebElement removeItemButton;

    @FindBy(xpath = approvalOfProductRemovalMessageXpath)
    private WebElement approvalOfProductRemovalMessage;

    @FindBy(xpath = restoreItemButtonXpath)
    private WebElement restoreItemButton;

    @FindBy(xpath = promoCodeInputId)
    private WebElement promoCodeInput;

    @FindBy(xpath = promoCodeSubmitButtonXpath)
    private WebElement promoCodeSubmitButton;

    @FindBy(xpath = promoCodeStatusMessageXpath)
    private WebElement promoCodeStatusMessage;

    public LacosteCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getProductName() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(productNameXpath, driver);
        return productName.getText();
    }

    public String getProductPrice() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(productPriceXpath, driver);
        return productPrice.getText().substring(0, productPrice.getText().length() - 1);
    }

    public LacosteCartPage closeCookies() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(cookiesXpath , driver);
        cookies.click();
        return this;
    }

    public LacosteCartPage removeItemFromCart() {
        removeItemButton.click();
        return this;
    }

    public String getApprovalOfProductRemovalMessage() {
        return approvalOfProductRemovalMessage.getText();
    }

    public LacosteCartPage restoreItemToCart() {
        restoreItemButton.click();
        return this;
    }

    public LacosteCartPage enterPromoCode(String promoCode) {
        promoCodeInput.sendKeys(promoCode);
        promoCodeSubmitButton.click();
        return this;
    }

    public LacosteCartPage addTheSameItem() {
        restoreItemButton.click();
        return this;
    }

    public String getPromoCodeStatusMessage() {
        return promoCodeStatusMessage.getText();
    }

    @Override
    public LacosteCartPage openPage() {
        driver.get(LACOSTE_CART_PAGE_URL);
        return this;
    }

}
