package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtils;

import java.util.*;

public class LacosteSearchResultPage extends AbstractPage {
    private final String filterAndSortXpath = "//*[@class=\"filter-title\"]//*[@id=\"product\"]/div[2]/div[2]/div[1]/div[9]/button";
    private final String cookiesXpath = "//class[@class=\"cookie-consent-accept js-cookie-consent-accept\"]";
    private final String productOfSpecificSearchResultXpath = "//div[@class=\"product-info--name\"]";
    private final String productsSearchResultListXpath = "//*[@id=\"product \"]/div[2]";
    private final String emptySearchResultMessageXpath = "//*[@id=\"show-on-search\"]/div[2]/div[1]/div[1]/span[2]";
    private final String obtainedProductNameXpath = "//*[@id=\"/en/bh2153/53s-1/\"]/div[2]/div[3]/div[1]/div/div[3]/div[2]/div[1]/a";
    private final String obtainedProductPriceXpath = "//div[@class=\"current-price\"]";
    private final String priceRangeFiltersXpath = "//*[@id=\"product\"]/div[2]/div[2]/div[1]/div[8]/div/label[2]/span";

    @FindBy(xpath = filterAndSortXpath)
    private WebElement filterAndSort;
    @FindBy(xpath = productsSearchResultListXpath)
    private List<WebElement> productsSearchResultList;

    @FindBy(xpath = cookiesXpath)
    private WebElement cookies;

    @FindBy(xpath = emptySearchResultMessageXpath)
    private WebElement emptySearchResultMessage;

    @FindBy(xpath = obtainedProductNameXpath)
    private WebElement obtainedProductName;

    @FindBy(xpath = obtainedProductPriceXpath)
    private WebElement obtainedProductPrice;

    @FindBy(xpath = priceRangeFiltersXpath)
    private List<WebElement> priceRangeFilters;

    public LacosteSearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public List<String> getSearchedItemsText() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(productsSearchResultListXpath, driver);
        ArrayList<String> itemTextList = new ArrayList<>();
        for (WebElement productItem : productsSearchResultList) {
            itemTextList.add(productItem.getText());
        }
        return itemTextList;
    }

    public LacosteSearchResultPage closeCookies() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(cookiesXpath , driver);
        cookies.click();
        return this;
    }

    public String getProductName() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(obtainedProductNameXpath, driver);
        return obtainedProductName.getText();
    }

    public String getProductPrice() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(obtainedProductPriceXpath, driver);
        return obtainedProductPrice.getText().substring(0, obtainedProductPrice.getText().length() - 1);
    }

    public String getEmptySearchResultMessage() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(emptySearchResultMessageXpath, driver);
        return emptySearchResultMessage.getText();
    }

    public LacosteSearchResultPage enterPriceRange(String sizeRange) {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(filterAndSortXpath, driver);
        for (WebElement priceRangeFilter : priceRangeFilters) {
            int numberOfDigitsToClean = 7;
            for (int i = 0; i < numberOfDigitsToClean; i++) {
                priceRangeFilter.sendKeys(Keys.BACK_SPACE);
            }
            priceRangeFilter.sendKeys(sizeRange);
            priceRangeFilter.sendKeys(Keys.ENTER);
        }

        WaitUtils.waitForNumberOfElementsLocatedByXpathToBe(productOfSpecificSearchResultXpath, 1, driver);
        return this;
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}

