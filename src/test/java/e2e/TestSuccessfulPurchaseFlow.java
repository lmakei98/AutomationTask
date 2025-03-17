package e2e;

import constants.ProductItems;
import elements.ShoppingBagDrawer;
import org.testng.annotations.Test;
import pages.ProductPage;
import pages.PurchasePage;
import pages.ShopPage;
import testcomponents.BaseTest;
import utils.Money;

import java.math.BigDecimal;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestSuccessfulPurchaseFlow extends BaseTest {

    /**
     * Test Successful PurchaseFlow
     * <p>
     * Steps:
     * 1. Navigate to the shop page.
     * 2. Retrieve the discount code from the sticky bar.
     * 3. Open the product page for muffins and add 4 muffins to the shopping bag.
     * 4. Proceed to check out and verify the product item name and price.
     * 5. Apply the discount code and verify the discount value.
     * 6. Select the shipping destination and address.
     * 7. Verify the total due amount.
     * 8. Enter contact information and place the order.
     * 9. Verify the success message is displayed.
     */
    @Test
    public void testSuccessfulPurchaseFlow() {
        String muffinProductName = ProductItems.FRESHLY_BAKED_MUFFINS.getName();
        Money muffinProductPrice = ProductItems.FRESHLY_BAKED_MUFFINS.getPrice();

        ShopPage shopPage = homePage.navigateToShop();

        String discountCode = shopPage.getDiscountCodeFromStickyBar();

        ProductPage maffinProductPage = shopPage.openProductByName(muffinProductName);
        maffinProductPage.enterQuantityAmount("4");

        ShoppingBagDrawer shoppingBagDrawer = maffinProductPage.addToBag();

        PurchasePage purchasePage = shoppingBagDrawer.proceedToCheckout();
        List<String> productItemNames = purchasePage.getProductItemNames();

        Money muffinsTotalPrice = muffinProductPrice.multiply(BigDecimal.valueOf(4));

        assertEquals(productItemNames.size(), 1);
        assertEquals(muffinProductName, productItemNames.get(0));
        assertEquals(purchasePage.getProductItemTotalPriceByName(muffinProductName),
                String.format("%s\n%s each", muffinsTotalPrice, muffinProductPrice));
        assertEquals(purchasePage.getSubtotal(), muffinsTotalPrice);

        purchasePage.applyDiscountCode(discountCode);

        Money discountValue = muffinsTotalPrice.multiply(new BigDecimal(0.1));

        assertEquals(purchasePage.getDiscountValue(),
                String.format("-%s", discountValue));

        purchasePage.selectShippingDestination("Lithuania");

        purchasePage.selectShippingAddress("Alytaus ORLEN paštomatas, Kauno g. 73");

        Money totalPrice = muffinsTotalPrice
                .subtract(discountValue)
                .add(purchasePage.getShippingPrice());

        assertEquals(purchasePage.getTotalDue(), totalPrice);
        assertEquals(purchasePage.getTotalPrice(), totalPrice);

        purchasePage.clickContinueOnShippingInformation();

        purchasePage.enterEmail("test@test.com");
        purchasePage.enterName("José Test Sr. Name");
        purchasePage.enterPhoneNumber("62533827");
        purchasePage.enterComment("nuts allergy");

        purchasePage.clickContinueOnContactInformation();
        purchasePage.placeAnOrder();

        assertTrue(homePage.getSuccessMessage().isDisplayed());

        homePage.clickGotItButton();
    }
}