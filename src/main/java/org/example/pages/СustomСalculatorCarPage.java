package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class СustomСalculatorCarPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(ReviewPage.class.getName());

    private final By fuelInput = By.xpath("//*[@id='leftFilterFuel']");
    private final By countryOfOriginInput = By.xpath("//*[@id='leftFilterOrigin']");
    private final By filterAgeInput = By.xpath("//*[@id='leftFilterAge']");

    public СustomСalculatorCarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // TODO: Need to update
    public void selectOption(By selector, String optionText) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Thread was interrupted", e);
        }

        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(selector));
        select.sendKeys(optionText);
    }

    public void calculatePriceOfCar(String fuel, String countryOfOrigin, String filterAge, String cost, String engine) {
        selectOption(fuelInput, fuel);
        selectOption(countryOfOriginInput, countryOfOrigin);
        selectOption(filterAgeInput, filterAge);
        enterTextIntoCostOfCarAbroadField(cost);
        enterTextIntoEngineField(engine);
        clickOnCalculateButton();
    }

    public void checkThatPriceIsDisplayedCorrectly() {
        driver.findElement(By.xpath("//*[@class='item'][contains(.,'4611 €')]"));
    }


    public void clickOnCalculateButton() {
        WebElement buttonTypeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='calc-btn button unlink']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonTypeElement);
        buttonTypeElement.click();
    }

    public void enterTextIntoCostOfCarAbroadField(String cost) {
        enterTextIntoField(By.xpath("//*[@name='price']"), cost);
    }

    public void enterTextIntoEngineField(String engine) {
        enterTextIntoField(By.xpath("//*[@name='engine']"), engine);
    }

    private void enterTextIntoField(By locator, String text) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.sendKeys(text);
    }
}
