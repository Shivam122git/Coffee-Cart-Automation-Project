package com.coffeecart.tests;

import com.coffeecart.data.TestDataProvider;
import com.coffeecart.managers.DriverManager;
import com.coffeecart.pages.CheckoutPage;
import com.coffeecart.pages.MenuPage;
import com.coffeecart.pages.CartPage;
import com.coffeecart.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckoutTests {
    private MenuPage menuPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        DriverManager.setDriver(browser);
        menuPage = new MenuPage(DriverManager.getDriver());
        cartPage = new CartPage(DriverManager.getDriver());
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        DriverManager.getDriver().get(ConfigReader.getUrl());
    }

    @Test(priority = 1, description = "Verify checkout process with valid data")
    public void testCompleteCheckoutProcess() {
        menuPage.addEspresso();
        cartPage.proceedToCheckout();

        checkoutPage.fillCustomerInfo("John Doe", "john.doe@example.com");
        checkoutPage.submitOrder();

        Assert.assertTrue(checkoutPage.isSuccessMessageDisplayed(),
                "Success message should be displayed after checkout");
    }

    @Test(priority = 2, description = "Verify customer information form validation")
    public void testFormValidation() {
        menuPage.addEspresso();
        cartPage.proceedToCheckout();

        Assert.assertTrue(checkoutPage.isNameFieldVisible(),
                "Name field should be visible");
        Assert.assertTrue(checkoutPage.isEmailFieldVisible(),
                "Email field should be visible");
    }

    @Test(priority = 3, description = "Verify checkout with empty name field")
    public void testCheckoutWithEmptyName() {
        menuPage.addEspresso();
        cartPage.proceedToCheckout();

        checkoutPage.fillCustomerInfo("", "john.doe@example.com");
        checkoutPage.submitOrder();

        Assert.assertFalse(checkoutPage.isSuccessMessageDisplayed(),
                "Checkout should not succeed with empty name");
    }

    @Test(priority = 4, dataProvider = "invalidEmails", dataProviderClass = TestDataProvider.class,
            description = "Verify checkout with invalid email")
    public void testCheckoutWithInvalidEmail(String name, String email) {
        menuPage.addEspresso();
        cartPage.proceedToCheckout();

        checkoutPage.fillCustomerInfo(name, email);
        checkoutPage.submitOrder();

        Assert.assertFalse(checkoutPage.isSuccessMessageDisplayed(),
                "Checkout should not succeed with invalid email: " + email);
    }

    @Test(priority = 5, dataProvider = "promoCodes", dataProviderClass = TestDataProvider.class,
            description = "Verify promo code application")
    public void testPromoCodeApplication(String promoCode, String expectedDiscount) {
        menuPage.addEspresso();
        cartPage.proceedToCheckout();

        checkoutPage.applyPromoCode(promoCode);
        checkoutPage.fillCustomerInfo("John Doe", "john.doe@example.com");
        checkoutPage.submitOrder();

    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}