package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.abstractpage.AbstractPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class represents the Home Page and provides methods to interact with various elements on the page.
 */
public class HomePage extends AbstractPage {
    private final By successMessageLocator = By.xpath("//div[@data-qa='ecommerce-modal-checkout-success-order']");
    private final By gotItButtonLocator = By.xpath("//div[@data-qa='ecommerce-modal-checkout-success-order']//button");
    private final By shopButtonLocator = By.xpath("//div[contains(@class,'block-header-layout-desktop')]//a[@data-qa='navigationblock-page-shop']");

    /**
     * Constructor for the HomePage class.
     *
     * @param driver the WebDriver instance to interact with the web page.
     */
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Retrieves the success message element.
     *
     * @return the WebElement representing the success message.
     */
    public WebElement getSuccessMessage() {
        waitForElementToAppear(successMessageLocator);
        return driver.findElement(successMessageLocator);
    }

    /**
     * Clicks the "Got It" button on the success message modal.
     */
    public void clickGotItButton() {
        driver.findElement(gotItButtonLocator).click();
    }

    /**
     * Opens the home page using the URL specified in the properties file.
     *
     * @throws IOException if there is an error reading the properties file.
     */
    public void open() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src/main//resources//project.properties");
        prop.load(fis);
        driver.get(prop.getProperty("URL"));
        waitForElementToBeVisible(driver.findElement(shopButtonLocator));
    }

    /**
     * Navigates to the shop page.
     *
     * @return a ShopPage instance representing the shop page.
     */
    public ShopPage navigateToShop() {
        driver.findElement(shopButtonLocator).click();
        return new ShopPage(driver);
    }
}