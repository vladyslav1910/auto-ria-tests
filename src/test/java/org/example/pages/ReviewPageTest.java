package org.example.pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class ReviewPageTest {

    private WebDriver driver;
    private ReviewPage reviewPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/vladyslavbeziazychnyi/Desktop/chromedriver-mac-arm64/chromedriver"); // TODO: Need to update it so that the links are not directly involved in the code
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        reviewPage = new ReviewPage(driver);
        driver.get("https://auto.ria.com/uk/reviews/add.html"); // TODO: Need to update it so that the links are not directly involved in the code
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    // Leave a successful review
    public void leaveReview() {
        reviewPage.publishReview();
        reviewPage.checkThatReviewSuccessfullyAdded();
    }
}
