package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath= System.getProperty("user.dir");
	String loginPageUrl;
	String emailID, userID, passwordID, customerID;
	String cusNameTextBox, dobTextBox, addressTextBox, cityTextBox, stateTextBox, pinTextBox, phoneTextBox, emailTextBox;
	String customerName, dob, address, city, state, pin, phone, email;
	String newAddress, newCity, newState, newPin, newPhone, newEmail;
	
	By cusNameBy = By.name("name");
	By dobBy = By.name("dob");
	By addressBy = By.name("addr");
	By cityBy = By.name("city");
	By stateBy = By.name("state");
	By pinBy = By.name("pinno");
	By phoneBy = By.name("telephoneno");
	By emailBy = By.name("emailid");
	By gender = By.name("gender");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browerDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		emailID="dam@gmail.com";
		customerName = "Selenium Online";
		dob = "2000-10-01";
		address = "123 Address";
		city = "Ho Chi Minh";
		state = "Thu Duc";
		pin = "123456";
		phone = "0987654321";
		email = "Selenium" + generateEmail();
	}

	@Test
	public void TC_01_Javascript_Executor() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		sleepInSecond(3);
		String homeDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(homeDomain, "live.demoguru99.com");
		
		String urlPage = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(urlPage, "http://live.demoguru99.com/");
		
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		highlightElement("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div//span[text()='Add to Cart']");
		clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div//span[text()='Add to Cart']");
		
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		String customerServiceTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		scrollToElement("//span[text()='Newsletter']");
		
		sendkeyToElementByJS("//input[@id='newsletter']", "ngoc.nguyenthi@gmail.com");
		
		highlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String domainPage = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(domainPage, "live.demoguru99.com");
		
	}

	@Test
	public void TC_02_Verify_HTML5_validation_message() {
		driver.get("https://automationfc.github.io/html5/index.html");
		
		String validationMessage;
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		validationMessage=getElementValidationMessage("//input[@id='fname']");
		System.out.println(validationMessage);
		//Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("ngoc");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		validationMessage=getElementValidationMessage("//input[@id='pass']");
		System.out.println(validationMessage);
		Assert.assertEquals(validationMessage, "Vui lòng điền vào trường này.");
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		validationMessage=getElementValidationMessage("//input[@id='em']");
		System.out.println(validationMessage);
		Assert.assertEquals(validationMessage, "Vui lòng điền vào trường này.");
		
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("ngoc@123");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		validationMessage=getElementValidationMessage("//input[@id='em']");
		System.out.println(validationMessage);
		//Assert.assertEquals(validationMessage, "Please enter an email address.");
		
		driver.findElement(By.xpath("//input[@id='em']")).clear();
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("ngoc#123");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		validationMessage=getElementValidationMessage("//input[@id='em']");
		System.out.println(validationMessage);
		//Assert.assertEquals(validationMessage, "Please match the requested format");
		
		driver.findElement(By.xpath("//input[@id='em']")).clear();
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("ngoc123@gmail.com");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		
		System.out.println(validationMessage);
		
		//Assert.assertEquals(validationMessage, "Please select an item in the list.");
		
	}

	@Test
s	public void TC_03_Remove_Attribute() {
		//register
		driver.get("http://demo.guru99.com/v4");
		loginPageUrl=driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();;
		
		driver.findElement(By.name("emailid")).sendKeys(emailID);
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
		passwordID = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
		
		//login
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(passwordID);
		driver.findElement(By.name("btnLogin")).click();
		
		//new user
		driver.findElement(By.linkText("New Customer")).click();
		
		driver.findElement(cusNameBy).sendKeys(customerName);
		driver.findElement(dobBy).sendKeys(dob);
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(phoneBy).sendKeys(phone);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(passwordID);
		removeAttributeInDOM("//input[@name='dob']", "type");
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(), email);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
	}
	
	@Test
	public void TC_04_Creat_An_Account() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		clickToElementByJS("//div[@class='block-title']/following-sibling::ul//a[text()='My Account']");
		
		clickToElementByJS("//span[text()='Create an Account']");
		
		sendkeyToElementByJS("//input[@id='firstname']", "ngoc");
		sendkeyToElementByJS("//input[@id='lastname']", "nguyen");
		sendkeyToElementByJS("//input[@id='email_address']", "ngoc" +generateEmail());
		sendkeyToElementByJS("//input[@id='password']", "123456");
		sendkeyToElementByJS("//input[@id='confirmation']", "123456");
		
		clickToElementByJS("//button[@title='Register']");
		sleepInSecond(3);
		
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for registering with Main Website Store"));
		
		clickToElementByJS("//a[text()='Log Out']");
		
		
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@gmail.com";
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout *1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}