package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_TextBox_TextArea {
	WebDriver driver;
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
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		emailID="dam@gmail.com";
		customerName = "Selenium Online";
		dob = "2000-10-01";
		address = "123 Address";
		city = "Ho Chi Minh";
		state = "Thu Duc";
		pin = "123456";
		phone = "0987654321";
		email = "Selenium" + generateEmail();
		
		newAddress= "456 Address"; 
		newCity = "Ha Noi";
		newState = "Cau Giay";
		newPin = "678901";
		newPhone = "0123456789";
		newEmail = "Automation" + generateEmail();
	}

	@Test
	public void TC_01_Register() {
		loginPageUrl=driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();;
		
		driver.findElement(By.name("emailid")).sendKeys(emailID);
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
		passwordID = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(passwordID);
		driver.findElement(By.name("btnLogin")).click();
	}
	
	@Test
	public void TC_03_New_User()
	{
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
	public void TC_04_Edit_User() {
		driver.findElement(By.linkText("Edit Customer")).click();
		
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertFalse(driver.findElement(cusNameBy).isEnabled());
		Assert.assertFalse(driver.findElement(gender).isEnabled());
		Assert.assertFalse(driver.findElement(dobBy).isEnabled());
		
		driver.findElement(addressBy).clear();
		driver.findElement(addressBy).sendKeys(newAddress);
		
		driver.findElement(cityBy).clear();
		driver.findElement(cityBy).sendKeys(newCity);
		
		driver.findElement(stateBy).clear();
		driver.findElement(stateBy).sendKeys(newState);
		
		driver.findElement(pinBy).clear();
		driver.findElement(pinBy).sendKeys(newPin);
		
		driver.findElement(phoneBy).clear();
		driver.findElement(phoneBy).sendKeys(newPhone);
		
		driver.findElement(emailBy).clear();
		driver.findElement(emailBy).sendKeys(newEmail);
		
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), newAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), newCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(), newState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), newPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(), newPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(), newEmail);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@gmail.com";
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