package com.coffeecart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class  CheckoutPage extends BasePage {

    @FindBy(css = "input[name='name']")
    private WebElement nameField;

    @FindBy(css = "input[name='email']")
    private WebElement emailField;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    @FindBy(css = "input[name='promotion']")
    private WebElement promoField;

    @FindBy(css = ".snackbar")
    private WebElement successMessage;

    @FindBy(css = ".order-summary")
    private WebElement orderSummary;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillCustomerInfo(String name, String email) {
        sendKeys(nameField, name);
        sendKeys(emailField, email);
    }

    public void applyPromoCode(String promoCode) {
        sendKeys(promoField, promoCode);
    }

    public void submitOrder() {
        click(submitButton);
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            Thread.sleep(2000);
            return isDisplayed(successMessage);
        } catch (Exception e) {
            return false;
        }
    }

    public String getOrderSummary() {
        return getText(orderSummary);
    }

    public boolean isNameFieldVisible() {
        return isDisplayed(nameField);
    }

    public boolean isEmailFieldVisible() {
        return isDisplayed(emailField);
    }
}