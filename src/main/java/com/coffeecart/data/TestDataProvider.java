package com.coffeecart.data;

import com.coffeecart.utils.ExcelReader;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "coffeeItems")
    public Object[][] getCoffeeItemsData() {
        return new Object[][] {
                {"Espresso", "1", "$10.00"},
                {"Cappuccino", "1", "$19.00"},
                {"Americano", "1", "$7.00"}
        };
    }

    @DataProvider(name = "checkoutData")
    public Object[][] getCheckoutData() {
        return new Object[][] {
                {"John Doe", "john@example.com", "true"}
        };
    }

    @DataProvider(name = "invalidEmails")
    public Object[][] getInvalidEmailData() {
        return new Object[][] {
                {"invalid-email", "password123"},
                {"@example.com", "password123"},
                {"test@", "password123"}
        };
    }

    @DataProvider(name = "promoCodes")
    public Object[][] getPromoCodeData() {
        return new Object[][] {
                {"DISCOUNT10", "10"}
        };
    }
}