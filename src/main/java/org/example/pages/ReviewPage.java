package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReviewPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(ReviewPage.class.getName());

    private final By typeOfTransportInput = By.xpath("//*[@id='categoryId']");
    private final By driveInput = By.xpath("//*[@id='driveIdList']");
    private final By bodyInput = By.xpath("//*[@id='bodyId']");
    private final By yearOfReleaseInput = By.id("year");
    private final By fuelInput = By.id("fuelIdList");
    private final By gearInput = By.id("gearIdList");
    private final By timeDrivingInput = By.id("timeDriving");
    private final By drivingExperienceInput = By.id("drivingExperience");
    private final By usingTypeInput = By.id("usingType");

    public ReviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // TODO: Need to update
    public void selectOption(By selector, String optionText) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Thread was interrupted", e);
        }

        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(selector));
        select.sendKeys(optionText);
    }

    public void publishReview() {
        selectOption(typeOfTransportInput, "Вантажівки");
        setValueForMark();
        setValueForModel();
        selectOption(yearOfReleaseInput, "2015");
        selectOption(bodyInput, "Фургон");
        selectOption(fuelInput, "Бензин");
        selectOption(driveInput, "Повний");
        selectOption(gearInput, "Автомат");
        enterNumberIntoRaceField(999);
        enterNumberIntoFuelConsumptionField(999);
        enterTextIntoNameField("FullName");
        selectOption(timeDrivingInput, "1-2 роки");
        selectOption(drivingExperienceInput, "2-3 роки");
        selectOption(usingTypeInput, "Подорожі");
        enterTextIntoNoteField("AutoTest 123 AutoTest 123 AutoTest 123 AutoTest 123 AutoTest 123 AutoTest 123");
        clickOnFirstPlusElement();
        clickOnEstimateElement();
        clickOnLeaveReview();
    }

    public void checkThatReviewSuccessfullyAdded() {
        driver.findElement(By.xpath("//a[@class='reviews-cars_name-link'][contains(.,'Dacia Dokker  2015')]"));
    }

    public void clickOnFirstPlusElement() {
        clickWithScroll(By.cssSelector("[data-gaq='korobka-peredach_plus']"));
    }

    public void clickOnEstimateElement() {
        clickWithScroll(By.cssSelector("[for='type1-val1']"));
        clickWithScroll(By.cssSelector("[for='type2-val1']"));
        clickWithScroll(By.cssSelector("[for='type3-val1']"));
        clickWithScroll(By.cssSelector("[for='type4-val1']"));
        clickWithScroll(By.cssSelector("[for='type5-val1']"));
    }

    private void clickWithScroll(By selector) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selector));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void clickOnLeaveReview() {
        WebElement buttonTypeElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonTypeElement);
        buttonTypeElement.click();
    }

    public void enterTextIntoNameField(String name) {
        enterTextIntoField(By.id("userName"), name);
    }

    public void enterTextIntoNoteField(String note) {
        enterTextIntoField(By.id("review"), note);
    }

    private void enterTextIntoField(By locator, String text) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.sendKeys(text);
    }

    public void enterNumberIntoRaceField(int number) {
        enterNumberIntoField(By.id("race"), String.valueOf(number));
    }

    public void enterNumberIntoFuelConsumptionField(int number) {
        enterNumberIntoField(By.id("fuelConsumption"), String.valueOf(number));
    }

    private void enterNumberIntoField(By locator, String number) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.sendKeys(number);
    }

    public void setValueForMark() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[for='markaId']")));
        element.click();

        WebElement markBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='item '][contains(.,'Dacia')]")));
        markBtn.click();
    }

    public void setValueForModel() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[for='modelId']")));
        element.click();

        WebElement markBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='item '][contains(.,'Dokker')]")));
        markBtn.click();
    }
}
