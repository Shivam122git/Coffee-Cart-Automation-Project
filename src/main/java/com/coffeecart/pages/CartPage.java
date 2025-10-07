package com.coffeecart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "[aria-label='Cart page']")
    private WebElement cartIcon;

    @FindBy(css = ".list-item")
    private List<WebElement> cartItems;

    @FindBy(css = ".pay")
    private WebElement cartBadge;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutButton;

    @FindBy(css = ".pay")
    private WebElement totalAmount;

    @FindBy(css = "button[aria-label*='Remove']")
    private List<WebElement> removeButtons;

    @FindBy(css = ".empty-state")
    private WebElement emptyCartMessage;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openCart() {
        try {
            click(cartIcon);
            Thread.sleep(1000);
        } catch (Exception e) {

        }
    }

    public int getCartItemCount() {
        try {
            return cartItems.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isItemInCart(int index) {
        try {
            Thread.sleep(1000);
            return getCartItemCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isItemInCartByName(String itemName) {
        return cartItems.stream()
                .anyMatch(item -> item.getText().contains(itemName));
    }

    public String getItemPrice(String itemName) {
        return cartItems.stream()
                .filter(item -> item.getText().contains(itemName))
                .findFirst()
                .map(item -> item.findElement(org.openqa.selenium.By.cssSelector(".price")).getText())
                .orElse("");
    }

    public void removeFirstItem() {
        try {
            Thread.sleep(1000);
            if (!removeButtons.isEmpty()) {
                click(removeButtons.get(0));
            }
        } catch (Exception e) {
        }
    }

    public String getTotalAmount() {
        return getText(totalAmount);
    }

    public void proceedToCheckout() {
        click(checkoutButton);
    }

    public boolean isCheckoutButtonEnabled() {
        return checkoutButton.isEnabled();
    }

    public boolean isCartEmpty() {
        return isDisplayed(emptyCartMessage);
    }
}