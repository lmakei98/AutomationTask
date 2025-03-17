package stepdefinitions;

import constants.ProductItems;
import elements.ShoppingBagDrawer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import pages.ProductPage;
import pages.PurchasePage;
import pages.ShopPage;
import testcomponents.BaseTest;

import java.io.IOException;

import static constants.ErrorMessages.*;
import static org.testng.Assert.assertEquals;

public class PurchaseStepDefinitionImpl extends BaseTest {
    private PurchasePage purchasePage;

    @Given("Product purchase is initiated")
    public void Product_purchase_is_initiated() throws IOException {
        launchApplication();
        ShopPage shopPage = homePage.navigateToShop();

        ProductPage maffinProductPage = shopPage.openProductByName(ProductItems.FRESHLY_BAKED_MUFFINS.getName());

        ShoppingBagDrawer shoppingBagDrawer = maffinProductPage.addToBag();
        purchasePage = shoppingBagDrawer.proceedToCheckout();
    }

    @When("User enters invalid discount code in the Discount code field and clicks Apply")
    public void User_enters_in_the_discount_code_field_and_clicks_apply() {
        purchasePage.applyDiscountCode("INVALID");
    }

    @Then("Validate Discount code error message is displayed")
    public void Validation_discount_code_error_message_is_displayed() {
        assertEquals(purchasePage.getDiscountCodeMessage(), INVALID_DISCOUNT_CODE);
    }

    @When("^User enters (.+) in the Email field$")
    public void User_enters_in_the_email_field(String emailAddress) {
        purchasePage.enterEmail(emailAddress);
        actions.sendKeys(Keys.TAB).perform();
    }

    @Then("Validate Email address error message is displayed")
    public void Validate_email_address_error_message_is_displayed() {
        assertEquals(purchasePage.getEmailErrorMessage(), INVALID_EMAIL);
    }

    @When("^User enters (.+) in the Phone number field$")
    public void User_enters_in_the_phone_number_field(String phoneNumber) {
        purchasePage.enterPhoneNumber(phoneNumber);
        actions.sendKeys(Keys.TAB).perform();
    }

    @Then("Validate Phone number error message is displayed")
    public void Validate_phone_number_error_message_is_displayed() {
        assertEquals(purchasePage.getPhoneNumberErrorMessage(), INVALID_PHONE_NUMBER);
    }

    @When("User clicks Continue button in Shipping Information form")
    public void User_clicks_continue_button_in_shipping_information_form() {
        purchasePage.clickContinueOnShippingInformation();
    }

    @Then("Validate Shipping destination required error message is displayed")
    public void Validate_shipping_destination_required_error_message_is_displayed() {
        assertEquals(purchasePage.getShippingDestinationErrorMessage(), SHIPPING_DESTINATION_REQUIRED);
    }

    @Then("Validate Shipping option required error message is displayed")
    public void Validate_shipping_option_required_error_message_is_displayed() {
        assertEquals(purchasePage.getShippingOptionsErrorMessage(), PARCEL_ADDRESS_REQUIRED);
    }

    @When("User selects Shipping destination")
    public void User_selects_shipping_destination() {
        purchasePage.selectShippingDestination("Lithuania");
    }

    @When("User leaves Shipping destination field empty")
    public void User_leaves_shipping_destination_field_empty() {
        purchasePage.clearShippingDestination();
    }

    @Then("Validate Phone Number required error message")
    public void Validate_Phone_Number_required_error_message() {
        assertEquals(purchasePage.getPhoneNumberErrorMessage(), PHONE_NUMBER_REQUIRED);
    }

    @Then("Validate Full Name required error message")
    public void Validate_Full_Name_required_error_message() {
        assertEquals(purchasePage.getFullNameErrorMessage(), FULL_NAME_REQUIRED);
    }

    @Then("Validate Special requests required error message")
    public void Validate_Comment_required_error_message() {
        assertEquals(purchasePage.getCommentErrorMessage(), SPECIAL_REQUESTS_REQUIRED);
    }

    @When("User clicks Continue button in Contact Information")
    public void User_clicks_continue_button_in_contact_information() {
        purchasePage.clickContinueOnContactInformation();
    }

    @Given("Contact Information form is displayed")
    public void Contact_information_form_is_displayed() {
        purchasePage.selectShippingDestination("Lithuania");
        purchasePage.waitForElementToBeVisible(purchasePage.getShippingOptions());
        purchasePage.selectShippingAddress("Alytaus ORLEN paštomatas, Kauno g. 73");
        purchasePage.clickContinueOnShippingInformation();
    }
}