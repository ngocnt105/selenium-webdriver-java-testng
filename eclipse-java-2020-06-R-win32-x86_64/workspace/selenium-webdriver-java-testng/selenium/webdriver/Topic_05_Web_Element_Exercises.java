package webdriver;

import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Exercises {
	WebDriver driver;
	String firstName, lastName, emailAddress, fullName, password;
	By emailTextBox = By.id("email");
	By educationTextBox = By.id("edu");
	By ageUnder18 = By.id("under_18");
	By job1 = By.id("job1");
	By job2 = By.id("job2");
	By job3 = By.id("job3");
	By development = By.xpath("//label[@for='development']");
	By slider01 = By.id("slider-1");
	By silder02 = By.id("slider-2");
	By passWord = By.xpath("//input[@name='user_pass']");
	By radioButton = By.id("radio-disabled");
	By biography = By.id("bio");
	By Checkbox = By.id("check-disbaled");
	By javaCheckBox = By.id("java");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		
		firstName="ngoc";
		lastName="nguyen";
		emailAddress ="ngocngoc" +generateEmail();
		fullName=firstName+" " +lastName;
		password="123456";
		
		
	}

	@Test
	public void TC_01_Creat_New_Account() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Contact Information')]//parent::div/following-sibling::div/p[contains(string(),'" + fullName +"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Contact Information')]//parent::div/following-sibling::div/p[contains(string(),'" + emailAddress +"')]")).isDisplayed());
		//button[@title="Register"]
		
		
		driver.findElement(By.cssSelector(".skip-account")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	//	Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/");
		
		
	}

	@Test
	public void TC_02_Login() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);
		
		driver.findElement(By.name("send")).click();
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText(),"Hello, "+ fullName+ "!");
		
		Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Contact Information')]//parent::div/following-sibling::div/p")).getText().contains(fullName));
		Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Contact Information')]//parent::div/following-sibling::div/p")).getText().contains(emailAddress));
	}

	@Test
	public void TC_03_isDisplay_Newbie() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if(driver.findElement(By.id("email")).isDisplayed())
		{
			driver.findElement(By.id("email")).sendKeys("ngocngoc@gmail.com");
			System.out.println("Mail textbox is display");
			
		} 
		else {
			System.out.println("Mail textbox is not display");
		}
		
		if(driver.findElement(By.id("under_18")).isDisplayed())
		{
			System.out.println("Age under 18 is display");
			
		} 
		else {
			System.out.println("Age under 18 is not display");
		}
		
		if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed())
		{
			System.out.println("Name: User5 is display");
			
		} 
		else {
			System.out.println("Name: User5 is not display");
		}
		
		
		if(driver.findElement(By.id("edu")).isDisplayed())
		{
			driver.findElement(By.id("edu")).sendKeys("Autonation FC");
			System.out.println("Education textbox is display");
			
		} 
		else {
			System.out.println("Education textbox is not display");
		}
	}

	@Test
	public void TC_03_isDisplay_function() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(isElementDisplayed(emailTextBox))
		{
			sendKeytoElement(emailTextBox,"Automation testing");
		}
		if(isElementDisplayed(educationTextBox))
		{
			sendKeytoElement(educationTextBox,"Automation testing");
		}
		if(isElementDisplayed(ageUnder18))
		{
			clickToElement(ageUnder18);
		}
		
	
	}
	
	@Test
	public void TC_04_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		

		if(driver.findElement(By.id("email")).isEnabled())
		{
			System.out.println("email is enable");
		}
		else
		{
			System.out.println("email is not enable");
		}
		Assert.assertTrue(isElementEnabled(emailTextBox));
		sleepInSecond(3);
		Assert.assertTrue(isElementEnabled(educationTextBox));
		Assert.assertTrue(isElementEnabled(ageUnder18));
		Assert.assertTrue(isElementEnabled(job1));
		Assert.assertTrue(isElementEnabled(job2));
		Assert.assertTrue(isElementEnabled(development));
		Assert.assertTrue(isElementEnabled(slider01));
		
		Assert.assertFalse(isElementEnabled(passWord));
		Assert.assertFalse(isElementEnabled(radioButton));
		Assert.assertFalse(isElementEnabled(biography));
		Assert.assertFalse(isElementEnabled(job3));
		Assert.assertFalse(isElementEnabled(Checkbox));
		Assert.assertFalse(isElementEnabled(silder02));
		
	}
	
	@Test
	public void TC_05_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickToElement(ageUnder18);
		clickToElement(javaCheckBox);
		
		Assert.assertTrue(isElementSelected(ageUnder18));
		Assert.assertTrue(isElementSelected(javaCheckBox));
		
		clickToElement(javaCheckBox);
		
		Assert.assertFalse(isElementSelected(javaCheckBox));
	}
	
	@Test
	public void TC_06_Register_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("ngocngoc@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("ngocngoc");
		
		By passwordTextBox = By.id("new_password");
		By lowerCaseComplete = By.cssSelector(".lowercase-char.completed");
		By upperCaseComplete = By.cssSelector(".uppercase-char.completed");
		By numberComplete = By.cssSelector(".number-char.completed");
		By specialComplete = By.cssSelector(".special-char.completed");
		By char8Complete = By.xpath("//li[@class='8-char completed']");
		By signUpButton = By.id("create-account");
		By marketingButton = By.id("marketing_newsletter");
		
		driver.findElement(passwordTextBox).sendKeys("a");
		Assert.assertTrue(isElementDisplayed(lowerCaseComplete));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("A");
		Assert.assertTrue(isElementDisplayed(upperCaseComplete));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("1");
		Assert.assertTrue(isElementDisplayed(numberComplete));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("@");
		Assert.assertTrue(isElementDisplayed(specialComplete));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("aaaaaaaa");
		Assert.assertTrue(isElementDisplayed(char8Complete));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("Ngoc@1005");
		Assert.assertFalse(isElementDisplayed(lowerCaseComplete));
		Assert.assertFalse(isElementDisplayed(upperCaseComplete));
		Assert.assertFalse(isElementDisplayed(numberComplete));
		Assert.assertFalse(isElementDisplayed(specialComplete));
		Assert.assertFalse(isElementDisplayed(char8Complete));
		Assert.assertTrue(isElementEnabled(signUpButton));
		
		clickToElement(marketingButton);
		Assert.assertTrue(isElementSelected(marketingButton));
		
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected())
		{
			System.out.println(by + " is selected");
			return true;
		}
		else
		{
			System.out.println(by + " is de-selected");
			return false;
		}
	}
	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled())
		{
			System.out.println(by + " is enabled");
			return true;
		}
		else
		{
			System.out.println(by + " is disabled");
			return false;
		}
	}
	
	public void clickToElement(By by) {
		driver.findElement(by).click();
	}
	
	public boolean isElementDisplayed(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println(by + " is display");
			return true;
		}
		else
		{
			System.out.println(by + " is not display");
			return false;
		}
			
	}
	
	public void sendKeytoElement(By by, String value)
	{
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
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