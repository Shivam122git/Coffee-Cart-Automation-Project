package com.coffeecart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class MenuPage extends BasePage {

    @FindBy(css = "[data-test='Espresso']")
    private WebElement espressoButton;

    @FindBy(css = "[data-test='Espresso_Macchiato']")
    private WebElement espressoMacchiatoButton;

    @FindBy(css = "[data-test='Cappuccino']")
    private WebElement cappuccinoButton;

    @FindBy(css = "[data-test='Mocha']")
    private WebElement mochaButton;

    @FindBy(css = "[data-test='Americano']")
    private WebElement americanoButton;

    @FindBy(css = "[data-test='Flat_White']")
    private WebElement flatWhiteButton;

    @FindBy(css = ".cup")
    private List<WebElement> coffeeItems;

    @FindBy(css = ".cup .price, .cup div")
    private List<WebElement> coffeePrices;

    public MenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getCoffeeItemsCount() {
        try {
            Thread.sleep(2000);
            return coffeeItems.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void addItemToCart(int index) {
        try {
            Thread.sleep(1000);
            if (index < coffeeItems.size()) {
                click(coffeeItems.get(index));
                Thread.sleep(1000);
            }
        } catch (Exception e) {

        }
    }

    public void addEspresso() {
        try {
            click(espressoButton);
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    public void addCappuccino() {
        try {
            click(cappuccinoButton);
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    public void addMocha() {
        try {
            click(mochaButton);
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    public void addAmericano() {
        try {
            click(americanoButton);
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    public void addFlatWhite() {
        try {
            click(flatWhiteButton);
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    public void addEspressoMacchiato() {
        try {
            click(espressoMacchiatoButton);
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    public boolean areItemsDisplayed() {
        return coffeeItems.size() > 0;
    }

    public boolean arePricesDisplayed() {
        return coffeePrices.size() > 0;
    }
}