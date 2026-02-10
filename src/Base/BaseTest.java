package Base;

import java.time.Duration;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

/*
 * WaitUtil:
 * Handles explicit waits in a reusable way
 * Makes tests stable and reliable
 */

public class BaseTest {
	


	private WebDriverWait wait;

    // ✅ Constructor name matches class name
    public  WaitUtil(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Wait until element becomes visible
    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait until element becomes clickable
    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Safe click operation
    public void click(By locator) {
        waitForClickable(locator).click();
    }

    // Safe text input
    public void type(By locator, String value) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

}
