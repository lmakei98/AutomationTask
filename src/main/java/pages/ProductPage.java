package pages;

import elements.ShoppingBagDrawer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.abstractpage.AbstractPage;

/**
 * This class represents the Product Page and provides methods to interact with various elements on the page.
 */
public class ProductPage extends AbstractPage {
    private final By quantityAmountLocator = By.xpath("//input[@data-qa='productpage-text-qty']");
    private final By addToBagButtonLocator = By.xpath("//button[@data-qa='productsection-btn-addtobag']");

    /**
     * Constructor for the ProductPage class.
     *
     * @param driver the WebDriver instance to interact with the web page.
     */
    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Enters the specified quantity amount in the quantity input field.
     *
     * @param value the quantity amount to enter.
     */
    public void enterQuantityAmount(String value) {
        waitForElementToAppear(quantityAmountLocator);
        WebElement quantityElement = driver.findElement(quantityAmountLocator);
        quantityElement.clear();
        quantityElement.sendKeys(value);
    }

    /**
     * Adds the product to the shopping bag.
     *
     * @return a ShoppingBagDrawer instance representing the shopping bag drawer.
     */
    public ShoppingBagDrawer addToBag() {
        waitForElementToAppear(addToBagButtonLocator);
        WebElement element = driver.findElement(addToBagButtonLocator);
        waitForElementToBeClickable(element);
        element.click();
        return new ShoppingBagDrawer(driver);
    }
}