package utils;

import org.openqa.selenium.*;

/*
 * UploadUtil:
 * Handles file uploads using sendKeys
 * Reusable for all document uploads
 */

public class UploadUtil {
	
	// Uploads file using input type=file
    public static void uploadFile(WebDriver driver, By locator, String filePath) {
        driver.findElement(locator).sendKeys(filePath);
    }

}
