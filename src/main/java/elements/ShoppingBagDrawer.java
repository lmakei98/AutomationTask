package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import pages.PurchasePage;
import pages.abstractpage.AbstractPage;

import java.time.Duration;
import java.util.List;

public class ShoppingBagDrawer extends AbstractPage {
    private final By checkOutButtonLocator = By.xpath("//button[@data-qa='shoppingcart-btn-checkout']");

    public ShoppingBagDrawer(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PurchasePage proceedToCheckout() {
        WebElement element = driver.findElement(checkOutButtonLocator);
        waitForElementToBeClickable(element);
        element.click();
        PurchasePage purchasePage = new PurchasePage(driver);

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        wait.until(driver -> {
            List<String> productItemNames = purchasePage.getProductItemNames();
            return !productItemNames.isEmpty();
        });

        waitForElementToBeVisible(purchasePage.getShippingOptions());
        return purchasePage;
    }
}
