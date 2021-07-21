package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class Topic_07_DropDown_List {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsexecutor;
	String email, password, day, month, year;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsexecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register");
		email = "Selenium" + generateEmail();
		password = "123456";
		day = "1";
		month = "May";
		year = "1980";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.className("ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys("ngoc");
		driver.findElement(By.id("LastName")).sendKeys("nguyen");
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys("password");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("password");;
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getOptions().size(), 112);
		
		clickByJS(By.id("register-button"));
		//driver.findElement(By.id("register-button")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
	}

	@Test
	public void TC_02_Verify() {
		driver.findElement(By.className("ico-account")).click();
		sleepInSecond(3);
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@gmail.com";
	}
	
	public void clickByJS(By by) {
		jsexecutor.executeScript("arguments[0].click();", driver.findElement(by)) ;
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