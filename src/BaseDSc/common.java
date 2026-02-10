package BaseDSc;
import java.time.Duration;
import java.util.Map;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
public class common {
	
	

	    protected WebDriver driver;
	    protected WebDriverWait wait;
	    protected JavascriptExecutor js;
	    private boolean emailVerifiedOnce = false;

	    // =========================
	    // Setup browser + camera/mic
	    // =========================
	    public void setupChrome() {
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--use-fake-ui-for-media-stream");
	        options.addArguments("--disable-notifications");

	        driver = new ChromeDriver(options);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        js = (JavascriptExecutor) driver;
	        driver.manage().window().maximize();
	    }
	    

	    // This method waits until the given element becomes visible on the page and then returns that WebElement.

	    public WebElement visible(By by) {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	    }

	    // This method waits until the given element becomes clickable on the page and then returns that WebElement.

	    public WebElement clickable(By by) {
	        return wait.until(ExpectedConditions.elementToBeClickable(by));
	    }

	    // This method waits for the element to become visible, clears any existing text, and then types the given value into it.

	    public void type(By by, String value) {
	        WebElement el = visible(by);
	        el.clear();
	        el.sendKeys(value);
	    }

	    // This method waits until the element becomes clickable and then performs a click action on it.
	    public void click(By by) {
	        clickable(by).click();
	    }
	    
	    // This method waits for the dropdown to be clickable and then selects an option using the given value.

	    public void selectByValue(By by, String value) {
	        WebElement el = clickable(by);
	        new Select(el).selectByValue(value);
	    }

	    //This method opens the browser and navigates to the given URL.

	    public void openBrowser(String url) {
	        driver.get(url);
	    }
    
	    // This method scrolldown the bottom page 

	    public void scrollToBottom() {
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    }
	    
	    
	    // =========================
	    // DSC PAGE (values bahar se aayengi)
	    // =========================
	
	    public void dscPage(String userType, String certType, String tokenType, String assistedType) {


	        new Select(wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//select[@formcontrolname='userType']")))).selectByValue(userType);

	        new Select(wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//select[@formcontrolname='certificateType']")))).selectByValue(certType);

	        new Select(wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//select[@formcontrolname='tokenType']")))).selectByValue(tokenType);

	        new Select(wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//select[@formcontrolname='assistedType']")))).selectByValue(assistedType);

	        js.executeScript("window.scrollTo(0, document.body.scrollHeight*0.2);");

	        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button//span[contains(normalize-space(),'Proceed To Next')]")));
	        js.executeScript("arguments[0].click();", nextBtn);
	    }
	    
	    

	    // =========================
	    // // This method waits for the OTP input boxes to load
	    // =========================
	    
	    public void waitForOtp() {
	    	

	        // WebDriverWait waits until exactly 6 OTP input fields
	        // are present on the page
	        wait.until(ExpectedConditions.numberOfElementsToBe( By.cssSelector("input.otp-input"), 6));
	           
	    }
	 // =========================
	    // // This method Login Page 
	    // ========================= 
	    
	    /*
	    public void loginWithMobile() throws InterruptedException {

	        driver.findElement(By.xpath("//input[@placeholder='Mobile Number']")).sendKeys("9999999999");
	        Thread.sleep(500);
	        driver.findElement(By.xpath("//input[@placeholder='Enter Captcha']")).sendKeys("00000");
	        click(By.xpath("//span[normalize-space()='Login']"));
	        waitForOtp();
	        
	     // Loop runs 6 times because OTP has 6 digits

	        for (int i = 0; i < 6; i++) {
	        	// // Finds all OTP input fields
	            // Enters a digit into each field one by one
	            driver.findElements(By.cssSelector("input.otp-input"))
	                  .get(i)
	                  // Finds all OTP input fields
	                  // Enters a digit into each field one by one
	                  .sendKeys(String.valueOf(i + 1));
	        }

	        driver.findElement(By.xpath("//button[.//span[normalize-space()='Proceed']]")).click();
	        
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//button[.//span[text()='Proceed To Next ']]")).click();
	    }
   */
	    
	    public void login() throws InterruptedException {

	        // 🔹 Check which login field is visible
	        if (driver.findElements(By.xpath("//input[@placeholder='Mobile Number']")).size() > 0) {

	            // ✅ Mobile login
	            type(By.xpath("//input[@placeholder='Mobile Number']"), "9999999999");
	            Thread.sleep(3000); 
	            type(By.xpath("//input[@placeholder='Enter Captcha']"), "00000");

	        } else if (driver.findElements(By.cssSelector("input[formcontrolname='emailAddress']")).size() > 0) {

	            // ✅ Email login (Foreign Organization)
	            type(By.cssSelector("input[formcontrolname='emailAddress']"), "Shristi.kumari@rannlab.com");
	            type(By.xpath("//input[@placeholder='Enter Captcha']"), "00000");

	        } else {
	            throw new RuntimeException("❌ No Mobile or Email login field found");
	        }

	        // 🔹 Click Login
	        click(By.xpath("//span[normalize-space()='Login']"));

	        // 🔹 Wait for OTP inputs
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.cssSelector("input.otp-input")
	        ));

	        // 🔹 Enter OTP (123456)
	        for (int i = 0; i < 6; i++) {
	            driver.findElements(By.cssSelector("input.otp-input"))
	                  .get(i)
	                  .sendKeys(String.valueOf(i + 1));
	        }

	        // 🔹 Proceed
	        click(By.xpath("//button[.//span[normalize-space()='Proceed']]"));

	        // 🔹 Proceed To Next (dashboard)
	        wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[.//span[contains(normalize-space(),'Proceed To Next')]]")
	        )).click();
	    }

	    
	    
	    
	    
	    
	    
	    
	    
	    // =========================
	    // PAN PAGE
	    // =========================
	    public void panPage() {
	        type(By.xpath("//input[@formcontrolname='panNo']"), "GMDPK3207G");
	        type(By.xpath("//input[@placeholder='Name as per PAN']"), "Shristi Kumari");
	        type(By.xpath("//input[@placeholder='dd-mm-yyyy']"), "08-09-1999");
	        type(By.xpath("//input[@formcontrolname='eKycPin']"), "1A345a");
	        click(By.xpath("//button[text()='Verify & Next']"));
	        
	     
	    }

	   
	    // =========================
	    // GstModule
	    // =========================
	    public void gstModule(String flowType) {

	        click(By.id("chkIsGstAvailable"));

	        // Common fields (DGFT + ORG dono me)
	        type(By.xpath("//input[@formcontrolname='tradeName']"), "Shristiiiii");
	        type(By.xpath("//input[@placeholder='Enter Department Name']"), "Audit Department");

	        // DGFT me extra fields
	        if (flowType.equalsIgnoreCase("DGFT")) {
	            type(By.xpath("//input[@placeholder='IEC Code']"), "1234567890");
	            type(By.xpath("//input[@placeholder='Branch Code']"), "123");
	        }

	        // Common dropdown
	        selectByValue(By.xpath("//select[@formcontrolname='organizationType']"), "6");

	        click(By.xpath("//button[text()=' Confirm & Next']"));
	    }

	    // =========================
	    // Email Verification
	    // =========================
	    public void emailVerification() throws InterruptedException {

	     
	   WebElement emailInput = driver.findElement(By.cssSelector("input[formcontrolname='email']"));

	          
	 // Enter an email ID
        emailInput.sendKeys("nisha106@gmail.com");
        Thread.sleep(3000); 
        
        
        driver.findElement(By.xpath("//button[text()='Verify']")).click();

        Thread.sleep(3000);
        
        driver.findElements(By.cssSelector("input.otp-input")).get(0).sendKeys("1");
        driver.findElements(By.cssSelector("input.otp-input")).get(1).sendKeys("2");
        driver.findElements(By.cssSelector("input.otp-input")).get(2).sendKeys("3");
        driver.findElements(By.cssSelector("input.otp-input")).get(3).sendKeys("4");
        driver.findElements(By.cssSelector("input.otp-input")).get(4).sendKeys("5");
        driver.findElements(By.cssSelector("input.otp-input")).get(5).sendKeys("6");
        
        Thread.sleep(500);
       
        driver.findElement(By.xpath("//button[.//span[text()='Proceed ']]")).click();
        Thread.sleep(5000);
        
	    }
	    
	    // =========================
	    //Basic details 
	    // =========================
	  
	    public void addressPage() throws InterruptedException  {
	    		
	    	    //selectByValue(By.xpath("//select[@formcontrolname='country']"), "US");
	    		//type(By.xpath("//input[@placeholder='Zip code']"), "10001");
	    		 
	    		type(By.xpath("//input[contains(@placeholder,'Pin')]"), "277001");
	    		 Thread.sleep(6000);
	    	    type(By.xpath("//input[@formcontrolname='bpCode']"), "PRO000013");
	    	    type(By.xpath("//textarea[@formcontrolname='address']"), "Deepmala maam house");
	    	   
	    	    click(By.xpath("//button[text()='Confirm & Next']"));
	    	   // click(By.xpath("//button[text()='OK']"));
	    	    //click(By.xpath("//button[text()='Confirm & Next']"));
	    	}

	  /*  
	    // =========================
	    // Upload Documents
	    // =========================
	  
	    public void uploadDocumentsUntilDone(

	    	        List<String> attachments
	    	        //String applicantPhotoPath
	    	) throws InterruptedException {

	    	    // 🔹 Wait until at least one upload field appears
	    	    visible(By.xpath("//input[@type='file']"));

	    	    int index = 0;

	    	    while (index < attachments.size()) {

	    	        // 🔑 Always pick first AVAILABLE (empty) file input
	    	        List<WebElement> inputs = driver.findElements(
	    	            By.xpath("//input[@type='file' and not(@disabled)]")
	    	        );

	    	        if (inputs.isEmpty()) {
	    	            System.out.println("No more attachment fields available");
	    	            break;
	    	        }

	    	        WebElement fileInput = inputs.get(0); // always fresh element
	    	        fileInput.sendKeys(attachments.get(index));

	    	        index++;

	    	        // 🔽 Scroll down before Confirm & Next
			        ((JavascriptExecutor) driver)
			            .executeScript("window.scrollTo(0, document.body.scrollHeight);");

			       
	    	        
	    	        
	    	        // ⏳ wait for Angular DOM refresh
	    	        Thread.sleep(3000);
	    	    }

	    	    //Applicantphoto
			    WebElement Applicantphoto = driver.findElement(By.xpath("//lib-file-upload-resize[@validationlabel='Applicant Photo']//input[@type='file']"));
			    Applicantphoto.sendKeys("C:\\Users\\shris\\Downloads\\applicantPhoto.jpeg");
			    Thread.sleep(000);
			    
			           WebElement saveButton = driver.findElement(
			    	    By.xpath("//div[contains(@class,'modal-footer')]//button[.//text()[contains(.,'Save')]]")
			    	);

			    	saveButton.click(); 
			    	
			    	
			    	Thread.sleep(2000);

			    	scrollToBottom();
			        Thread.sleep(3000);
			        
			    	
			    	 driver.findElement(By.xpath("//button[text()='Confirm & Next']")).click();  
					    Thread.sleep(2000);
					
	    	}

	    */
	    
	    public void uploadDocuments(List<String> files) {
	    	 
	        // 1️⃣ wait till at least one upload box is visible
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//input[@type='file']")
	        ));
	     
	        // 2️⃣ loop through each file
	        for (String file : files) {
	     
	            // 3️⃣ find empty (not disabled) upload box
	            WebElement emptyBox =
	                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                    By.xpath("//input[@type='file' and not(@disabled)]")
	                )).get(0);
	     
	            // 4️⃣ upload file
	            emptyBox.sendKeys(file);
	        }
	    }
	    // =========================
	    // Video Upload
	    // =========================
	    public void videoUpload() throws InterruptedException {

	       
	    	click(By.xpath("//span[normalize-space()='Proceed']"));
	    	 Thread.sleep(500);
	    	click(By.xpath("//span[normalize-space()='Start']"));
	        Thread.sleep(30000);
	        driver.findElement(By.xpath("//span[text()='Submit']")).click();
	        Thread.sleep(3000);
	        driver.findElement(By.xpath("//span[normalize-space()='Proceed To Next']")).click();
		    
	    }
	    
	   // =========================
	    // Video Skip
	    // =========================
	    public void SkipVideo() throws InterruptedException {
		 // Skip the Video
	    
		  driver.findElement(By.xpath("//button[text()='Skip for now, record Later?']")).click();  
		   Thread.sleep(2000);
		    
		 
		   driver.findElement(By.xpath("//button[text()='OK']")).click();  
		   
		    Thread.sleep(3000);
	        driver.findElement(By.xpath("//span[normalize-space()='Proceed To Next']")).click();
		    
		    // Video recode for proceed click
	    
	    }
	    

	    // =========================
	    // Final Submit
	    // =========================
	    public void finalSubmitUsingPaylater() throws InterruptedException {

	        // 🔽 Scroll page bottom
	        scrollToBottom();
	        Thread.sleep(1000);

	        // 🔹 Enter Captcha
	        type(By.xpath("//input[@placeholder='Enter Captcha']"),"00000");
	        Thread.sleep(2000);
	        
	        scrollToBottom();
	        // 🔹 Accept Terms & Conditions
	        click(By.id("chkTermAndConditions"));

	        // 🔹 Proceed
	        click(By.xpath("//span[normalize-space()='Proceed']"));
	        Thread.sleep(2000);

	        // 🔹 Select Payment Method
	        click(By.xpath("//div[normalize-space()='PayLater']"));
	        Thread.sleep(2000);

	        // 🔹 Final Submit (JS click for safety)
	        js.executeScript(
	            "arguments[0].click();",
	            driver.findElement(By.xpath("//span[normalize-space()='Submit']"))
	        );

	        // =========================
	        // 🔹 Fetch PID & eKYC ID
	        // =========================
	        String pid = visible(
	            By.xpath("//td[contains(text(),'PID')]/following-sibling::td")
	        ).getText().trim();

	        String ekycId = visible(
	            By.xpath("//td[contains(text(),'eKYC ID')]/following-sibling::td")
	        ).getText().trim();

	        System.out.println("PID: " + pid);
	        System.out.println("eKYC ID: " + ekycId);
	    }

	}

	
	
	
	
	
	
	
	
	
	

