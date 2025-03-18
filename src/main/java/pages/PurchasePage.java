package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import pages.abstractpage.AbstractPage;
import utils.Money;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.Keys.TAB;

/**
 * This class represents the Purchase Page and provides methods to interact with various elements on the page.
 */
public class PurchasePage extends AbstractPage {
    private final By productItemNamesLocator = By.xpath("//div[contains(@data-qa, 'checkout-cartsummary-itemname')]");
    private final By totalPriceLocator = By.xpath("//div[contains(@class, 'total-price')]");
    private final By subtotalLocator = By.xpath("//div[@data-qa='checkout-cartsummary-subtotalprice-value']");
    private final By totalDueLocator = By.xpath("//div[@data-qa='checkout-cartsummary-totalprice-value']");
    private final By addDiscountButtonLocator = By.xpath("//button[@data-qa='checkout-cartsummary-button-adddiscountcode']");
    private final By discountCodeInputLocator = By.id("discountCode");
    private final By applyDiscountButtonLocator = By.xpath("//button[@data-qa='checkout-cartsummary-button-discountapply']");
    private final By discountValueLocator = By.xpath("//span[@data-qa='checkout-cartsummary-discount-value']");
    private final By shippingDestinationLocator = By.xpath("//div[@data-qa='checkout-shippingdestination-select']//input");
    private final By shippingOptionsLocator = By.xpath("//div[@data-qa='checkout-shippingdetails-option-table']");
    private final By continueOnShippingInformationLocator = By.xpath("//button[@data-qa='checkout-shippingdetails-continue']");
    private final By continueOnContactInformationLocator = By.xpath("//button[@data-qa='checkout-contactinformation-continue']");
    private final By parcelAddressSelectLocator = By.xpath("//div[@data-qa='checkout-shippingoptions-parcelselect']/div/div[@role='combobox']");
    private final By emailTextBoxLocator = By.xpath("//div[@data-qa='checkout-contactinformation-email']//input");
    private final By nameTextBoxLocator = By.xpath("//div[@data-qa='checkout-contactinformation-name']//input");
    private final By phoneNumberTextBoxLocator = By.xpath("//div[@data-qa='checkout-contactinformation-phone']//input");
    private final By commentTextBoxLocator = By.xpath("//div[@data-qa='checkout-contactinformation-customfield']//input");
    private final By placeOrderButtonLocator = By.xpath("//button[@data-qa='checkout-paymentmethods-placeorder']");
    private final By discountCodeMessageLocator = By.id("discountCode-messages");
    private final By shippingDestinationErrorLocator = By.id("destination-messages");
    private final By shippingOptionsErrorLocator = By.className("v-messages__message");
    private final By emailErrorLocator = By.id("email-messages");
    private final By nameErrorLocator = By.id("name-messages");
    private final By phoneErrorLocator = By.xpath("//div[@id='phone']//following-sibling::div[contains(@class, 'error')]");
    private final By commentErrorLocator = By.id("customFieldValue-messages");

    /**
     * Constructor for the PurchasePage class.
     *
     * @param driver the WebDriver instance to interact with the web page.
     */
    public PurchasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        waitForElementToAppear(shippingDestinationLocator);
        waitForElementToLoad(driver.findElement(shippingDestinationLocator));
    }

    /**
     * Retrieves the names of the product items in the cart.
     *
     * @return a list of product item names.
     */
    public List<String> getProductItemNames() {
        return driver.findElements(productItemNamesLocator).stream()
                .map(p -> p.getText().trim()).collect(Collectors.toList());
    }

    /**
     * Retrieves the total price of the items in the cart.
     *
     * @return the total price as a Money object.
     */
    public Money getTotalPrice() {
        String priceText = driver.findElement(totalPriceLocator).getText();
        return new Money(new BigDecimal(priceText.replace("€", "").trim()));
    }

    /**
     * Retrieves the subtotal price of the items in the cart.
     *
     * @return the subtotal price as a Money object.
     */
    public Money getSubtotal() {
        String subtotalText = driver.findElement(subtotalLocator).getText();
        return new Money(new BigDecimal(subtotalText.replace("€", "").trim()));
    }

    /**
     * Retrieves the total due amount.
     *
     * @return the total due amount as a Money object.
     */
    public Money getTotalDue() {
        String totalDueText = driver.findElement(totalDueLocator).getText();
        return new Money(new BigDecimal(totalDueText.replace("€", "").trim()));
    }

    /**
     * Retrieves the discount value applied to the cart.
     *
     * @return the discount value as a string.
     */
    public String getDiscountValue() {
        return driver.findElement(discountValueLocator).getText();
    }

    /**
     * Retrieves the shipping options element.
     *
     * @return the WebElement representing the shipping options.
     */
    public WebElement getShippingOptions() {
        waitForElementToAppear(shippingOptionsLocator);
        return driver.findElement(shippingOptionsLocator);
    }

    /**
     * Clicks the continue button on the shipping information form.
     */
    public void clickContinueOnShippingInformation() {
        WebElement element = driver.findElement(continueOnShippingInformationLocator);
        waitForElementToBeClickable(element);
        element.click();


    }

    /**
     * Clicks the continue button on the contact information form.
     */
    public void clickContinueOnContactInformation() {
        WebElement element = driver.findElement(continueOnContactInformationLocator);
        waitForElementToBeClickable(element);
        element.click();
    }

    /**
     * Retrieves the discount code message.
     *
     * @return the discount code message as a string.
     */
    public String getDiscountCodeMessage() {
        WebElement element = driver.findElement(discountCodeMessageLocator);
        waitTextToAppear(element);
        return element.getText();
    }

    /**
     * Retrieves the email error message.
     *
     * @return the email error message as a string.
     */
    public String getEmailErrorMessage() {
        WebElement element = driver.findElement(emailErrorLocator);
        waitTextToAppear(element);
        return element.getText();
    }

    /**
     * Retrieves the phone number error message.
     *
     * @return the phone number error message as a string.
     */
    public String getPhoneNumberErrorMessage() {
        WebElement element = driver.findElement(phoneErrorLocator);
        waitTextToAppear(element);
        return element.getText();
    }

    /**
     * Retrieves the full name error message.
     *
     * @return the full name error message as a string.
     */
    public String getFullNameErrorMessage() {
        WebElement element = driver.findElement(nameErrorLocator);
        waitTextToAppear(element);
        return element.getText();
    }

    /**
     * Retrieves the shipping destination error message.
     *
     * @return the shipping destination error message as a string.
     */
    public String getShippingDestinationErrorMessage() {
        WebElement element = driver.findElement(shippingDestinationErrorLocator);
        waitTextToAppear(element);
        return element.getText();
    }

    /**
     * Retrieves the shipping options error message.
     *
     * @return the shipping options error message as a string.
     */
    public String getShippingOptionsErrorMessage() {
        WebElement element = driver.findElement(shippingOptionsErrorLocator);
        waitTextToAppear(element);
        return element.getText();
    }

    /**
     * Retrieves the comment error message.
     *
     * @return the comment error message as a string.
     */
    public String getCommentErrorMessage() {
        WebElement element = driver.findElement(commentErrorLocator);
        waitTextToAppear(element);
        return element.getText();
    }

    /**
     * Retrieves the total price of a product item by its name.
     *
     * @param productName the name of the product.
     * @return the total price of the product item as a string.
     */
    public String getProductItemTotalPriceByName(String productName) {
        return driver.findElement(
                By.xpath(String.format("//div[contains(@data-qa, 'checkout-cartsummary-itemname') " +
                                "and contains(text(), '%s')]" +
                                "/following-sibling::div[contains(@data-qa, 'checkout-cartsummary-itemprice')]",
                        productName))).getText().trim();
    }

    /**
     * Applies a discount code to the cart.
     *
     * @param discountCode the discount code to apply.
     */
    public void applyDiscountCode(String discountCode) {
        driver.findElement(addDiscountButtonLocator).click();

        WebElement discountCodeInput = driver.findElement(discountCodeInputLocator);
        waitForElementToBeVisible(discountCodeInput);
        discountCodeInput.sendKeys(discountCode);

        driver.findElement(applyDiscountButtonLocator).click();
        waitForElementToLoad(driver.findElement(applyDiscountButtonLocator));
    }

    /**
     * Selects a shipping destination.
     *
     * @param destination the shipping destination to select.
     */
    public void selectShippingDestination(String destination) {
        driver.findElement(shippingDestinationLocator).click();
        By locator = By.xpath(String.format("//div[@class='v-list-item-title' and text()='%s']", destination));
        waitForElementToAppear(locator);
        driver.findElement(locator).click();

        waitForElementToBeVisible(driver.findElement(shippingOptionsLocator));
    }

    /**
     * Selects a shipping address by partial name.
     *
     * @param shippingAddress the partial name of the shipping address to select.
     */
    public void selectShippingAddress(String shippingAddress) {
        driver.findElement(parcelAddressSelectLocator).click();

        WebElement address = driver.findElements(By.xpath("//div[@class='v-list-item-title']"))
                .stream().filter(e -> e.getText().equals(shippingAddress)).findFirst().orElseThrow();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", address);
        address.click();
    }

    /**
     * Retrieves the shipping price.
     *
     * @return the shipping price as a Money object.
     */
    public Money getShippingPrice() {
        String priceString = driver.findElement(By.xpath("//div[@data-qa='checkout-cartsummary-shippingprice']"))
                .getText();
        return new Money(new BigDecimal(priceString.split("€")[1]));
    }

    /**
     * Enters an email address in the email text box.
     *
     * @param email the email address to enter.
     */
    public void enterEmail(String email) {
        driver.findElement(emailTextBoxLocator).sendKeys(email);
    }

    /**
     * Enters a name in the name text box.
     *
     * @param name the name to enter.
     */
    public void enterName(String name) {
        driver.findElement(nameTextBoxLocator).sendKeys(name);
    }

    /**
     * Enters a phone number in the phone number text box.
     *
     * @param phoneNumber the phone number to enter.
     */
    public void enterPhoneNumber(String phoneNumber) {
        WebElement element = driver.findElement(phoneNumberTextBoxLocator);
        element.sendKeys(phoneNumber);

        if (phoneNumber.length() == 8) {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);

            wait.until(driver -> {
                String value = element.getAttribute("value");
                return value.startsWith("+370");
            });
        }
    }

    /**
     * Enters a comment in the comment text box.
     *
     * @param comment the comment to enter.
     */
    public void enterComment(String comment) {
        driver.findElement(commentTextBoxLocator).sendKeys(comment);
    }

    /**
     * Places an order by clicking the place order button.
     */
    public void placeAnOrder() {
        waitForElementToAppear(placeOrderButtonLocator);
        WebElement element = driver.findElement(placeOrderButtonLocator);
        waitForElementToBeClickable(element);
        waitForElementToBeClickable(element);
        element.click();
    }

    /**
     * Clears the shipping destination field.
     */
    public void clearShippingDestination() {
        actions.moveToElement(driver.findElement(shippingDestinationLocator))
                .doubleClick()
                .sendKeys(Keys.DELETE)
                .sendKeys(TAB)
                .perform();
    }
}