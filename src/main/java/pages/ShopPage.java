package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.abstractpage.AbstractPage;

/**
 * This class represents the Shop Page and provides methods to interact with various elements on the page.
 */
public class ShopPage extends AbstractPage {
    private final By stickyBarPromotionLocator = By.xpath("//div[@class='text-box block-sticky-bar__element']//p[@class='body-small']");

    /**
     * Constructor for the ShopPage class.
     *
     * @param driver the WebDriver instance to interact with the web page.
     */
    public ShopPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Opens the product page for a given product name.
     *
     * @param productName the name of the product to open.
     * @return a ProductPage instance representing the product page.
     */
    public ProductPage openProductByName(String productName) {
        By locator = By.xpath(String.format("//h6[@data-qa='product-list-section-item-title' and contains(text(), '%s')]", productName));
        waitForElementToAppear(locator);

        WebElement product = driver.findElement(locator);
        waitForElementToBeClickable(product);
        product.click();
        return new ProductPage(driver);
    }

    /**
     * Retrieves the discount code from the sticky bar.
     *
     * @return the discount code as a string.
     */
    public String getDiscountCodeFromStickyBar() {
        WebElement stickyBarPromotion = driver.findElement(stickyBarPromotionLocator);
        waitTextToAppear(stickyBarPromotion);
        return stickyBarPromotion.getText().split("CODE:")[1].trim();
    }
}