package utils;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * OTPUtil:
 * Enters OTP into multiple OTP input boxes
 * Used in all login flows
 */
public class OTPUtil {
	
	
	// Static method so it can be called directly
    public static void enterOtp(WebDriver driver, String otp) {

        // Find all OTP input fields
        List<WebElement> otpInputs =
                driver.findElements(By.cssSelector("input.otp-input"));

        // Fill each OTP box with one digit
        for (int i = 0; i < otpInputs.size() && i < otp.length(); i++) {
            otpInputs.get(i).clear();
            otpInputs.get(i).sendKeys(String.valueOf(otp.charAt(i)));
        }
    }

}
