package Base;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

/*
 * WaitUtil class
 * Used to wait for elements instead of Thread.sleep
 */

public class DriverFactory {
	
	
	private WebDriverWait wait;

    public static void main(String[] args) {

        // Start browser
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();

        try {
            // Run Individual flow
            new IndividualFlow(driver).run();
        } finally {
            // Close browser
            DriverFactory.quitDriver();
        }
}
