package org.example.pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class CustomСalculatorСarTest {

    private WebDriver driver;
    private СustomСalculatorCarPage calculatorCarPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/vladyslavbeziazychnyi/Desktop/chromedriver-mac-arm64/chromedriver"); // TODO: Need to update it so that the links are not directly involved in the code
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        calculatorCarPage = new СustomСalculatorCarPage(driver);
        driver.get("https://auto.ria.com/uk/rastamozhka-avto/calculator"); // TODO: Need to update it so that the links are not directly involved in the code
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    // Calculate the cost of a car
    public void leaveReview() {
        calculatorCarPage.calculatePriceOfCar("бензин", "ЄС", "2 роки", "3500", "1500");
        calculatorCarPage.checkThatPriceIsDisplayedCorrectly();
    }
}
