package com.coffeecart.tests;

import com.coffeecart.managers.DriverManager;
import com.coffeecart.pages.MenuPage;
import com.coffeecart.pages.CartPage;
import com.coffeecart.utils.ConfigReader;
import com.coffeecart.data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.*;

public class MenuTests {
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

    @Test(priority = 1, description = "Verify menu items are displayed")
    public void testMenuItemsDisplay() {
        int itemCount = menuPage.getCoffeeItemsCount();
        Assert.assertTrue(itemCount > 0, "No coffee items found on menu");
        Assert.assertTrue(menuPage.areItemsDisplayed(), "Coffee items not displayed");
        Assert.assertTrue(menuPage.arePricesDisplayed(), "Coffee prices not displayed");
    }

    @Test(priority = 2, description = "Verify adding items to cart")
    public void testAddItemToCart() {
        menuPage.addItemToCart(0);
        cartPage.openCart();
        Assert.assertTrue(cartPage.isItemInCart(0), "Item was not added to cart");
    }

    @Test(priority = 3, description = "Add Espresso to cart")
    public void testAddEspresso() {
        menuPage.addEspresso();
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Espresso not added to cart");
    }

    @Test(priority = 4, description = "Add Cappuccino to cart")
    public void testAddCappuccino() {
        menuPage.addCappuccino();
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cappuccino not added to cart");
    }

    @Test(priority = 5, description = "Add multiple items to cart")
    public void testAddMultipleItems() {
        menuPage.addEspresso();
        menuPage.addCappuccino();
        menuPage.addMocha();
        Assert.assertEquals(cartPage.getCartItemCount(), 3, "Multiple items not added correctly");
    }

    @Test(priority = 6, dataProvider = "coffeeItems", dataProviderClass = TestDataProvider.class)
    public void testAddMultipleItemsToCart(String itemName, String quantity, String price) {
        switch (itemName.toLowerCase()) {
            case "espresso":
                menuPage.addEspresso();
                break;
            case "cappuccino":
                menuPage.addCappuccino();
                break;
            case "mocha":
                menuPage.addMocha();
                break;
            case "americano":
                menuPage.addAmericano();
                break;
            default:
                menuPage.addEspresso();
        }

        cartPage.openCart();
        Assert.assertTrue(cartPage.isItemInCartByName(itemName),
                itemName + " was not added to cart");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}