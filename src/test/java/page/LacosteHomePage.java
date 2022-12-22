package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaitUtils;

public class LacosteHomePage extends AbstractPage {
    public static String LACOSTE_HOME_PAGE_URL = "https://www.lacoste.pl/en/";
    private final String searchInputXpath = "//*[@id=\"header--search-input js-list-search-input\"]/html/body/div[4]/div[1]/header/div[1]/div[2]/div[2]/div/form/input";
    private final String cookiesXpath = "//button[@class=\"cookie-consent-accept js-cookie-consent-accept\"]";

    @FindBy(xpath = searchInputXpath)
    private WebElement searchInput;
    @FindBy(xpath = cookiesXpath)
    private WebElement cookies;

    public LacosteHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public LacosteHomePage closeCookies() {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(cookiesXpath, driver);
        cookies.click();
        return this;
    }

    public LacosteSearchResultPage searchForTerms(String searchQuery) {
        WaitUtils.waitForPresenceOfAllElementsLocatedByXpath(searchInputXpath, driver);
        searchInput.sendKeys(searchQuery);
        return new LacosteSearchResultPage(driver);
    }

    @Override
    public LacosteHomePage openPage() {
        driver.get(LACOSTE_HOME_PAGE_URL);
        return this;
    }
}
