package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import utils.WaitUtil;

public class DSCSelectionPage {
	private WebDriver driver;
    private WaitUtil wait;

    // Page locators
    private By userType = By.xpath("//select[@formcontrolname='userType']");
    private By certificateType = By.xpath("//select[@formcontrolname='certificateType']");
    private By proceedBtn = By.xpath("//span[contains(text(),'Proceed')]");

    // Constructor
    public DSCSelectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtil(driver);
    }

    // Select user type
    public void selectUserType(String value) {
        new Select(wait.waitForClickable(userType)).selectByValue(value);
    }

    // Select certificate type
    public void selectCertificateType(String value) {
        new Select(wait.waitForClickable(certificateType)).selectByValue(value);
    }

    // Click Proceed button
    public void clickProceed() {
        wait.click(proceedBtn);
    }

}
