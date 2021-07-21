package webdriver;

import java.util.List;
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

public class Topic_12_Popup {
	WebDriver driver;
	String projectPath= System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browerDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		driver.findElement(By.cssSelector("button.login_")).click();
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("ngoc.nguyenthi");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("ngocnguyenuet");
		
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		
		Assert.assertFalse(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());
	}

	@Test
	public void TC_02_Random_Popup_In_DOM() {
		driver.get("https://blog.testproject.io/");
		
		WebElement popup = driver.findElement(By.cssSelector("div.mailch-wrap"));
		
		if(popup.isDisplayed())
		{
			driver.findElement(By.cssSelector("div#close-mailch")).click();;
			System.out.println("Popup is display");
			
		}
		else
			System.out.println("Popup is not display ");
		
		driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("Selenium");
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		sleepInSecond(5);
		
		List<WebElement> articicleTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		for(WebElement articicle:articicleTitle)
		{
			Assert.assertTrue(articicle.getText().contains("Selenium"));
		}
	}

	@Test
	public void TC_03_Random_Popup_Not_In_DOM() {
		driver.get("https://shopee.vn/");
		
		List<WebElement> popups = driver.findElements(By.cssSelector("div#modal img"));
		if(popups.size()>0 && popups.get(0).isDisplayed())
		{
			driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
			System.out.println("Popup is display");
		}
		else
			System.out.println("Popup is not display");
		
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Macbook Pro");
		driver.findElement(By.cssSelector("button.btn-solid-primary")).click();
		
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