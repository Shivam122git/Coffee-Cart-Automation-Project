package com.coffeecart.tests;

import com.coffeecart.managers.DriverManager;
import com.coffeecart.pages.MenuPage;
import com.coffeecart.pages.CartPage;
import com.coffeecart.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.*;

public class CartTests {
    private MenuPage menuPage;
    private CartPage cartPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        DriverManager.setDriver(browser);
        menuPage = new MenuPage(DriverManager.getDriver());
        cartPage = new CartPage(DriverManager.getDriver());
        DriverManager.getDriver().get(ConfigReader.getUrl());
    }

    @Test(priority = 1, description = "Verify cart icon updates with item count")
    public void testCartItemCount() {
        menuPage.addEspresso();
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart count not updated");

        menuPage.addCappuccino();
        Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart count not updated for multiple items");
    }

    @Test(priority = 2, description = "Verify removing items from cart")
    public void testRemoveItemFromCart() {
        menuPage.addEspresso();
        menuPage.addCappuccino();

        int initialCount = cartPage.getCartItemCount();
        cartPage.removeFirstItem();

        Assert.assertTrue(cartPage.getCartItemCount() < initialCount,
                "Item was not removed from cart");
    }

    @Test(priority = 3, description = "Verify cart total calculation")
    public void testCartTotalCalculation() {
        menuPage.addEspresso();
        menuPage.addCappuccino();

        String total = cartPage.getTotalAmount();
        Assert.assertNotNull(total, "Total amount not displayed");
        Assert.assertFalse(total.isEmpty(), "Total amount is empty");
    }

    @Test(priority = 4, description = "Verify checkout button functionality")
    public void testCheckoutButton() {
        menuPage.addEspresso();
        Assert.assertTrue(cartPage.isCheckoutButtonEnabled(),
                "Checkout button should be enabled with items in cart");
    }

    @Test(priority = 5, description = "Verify empty cart behavior")
    public void testEmptyCartBehavior() {
        Assert.assertEquals(cartPage.getCartItemCount(), 0,
                "Cart should be empty initially");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}