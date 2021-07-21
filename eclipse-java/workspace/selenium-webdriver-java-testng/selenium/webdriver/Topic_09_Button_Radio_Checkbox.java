package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	boolean status;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.fahasa.com/customer/account/create");
	}

	
	public void TC_01_Button() {
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		Assert.assertFalse(driver.findElement(By.className("fhs-btn-login")).isEnabled());
		
		driver.findElement(By.id("login_username")).sendKeys("ngocngoc@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		Assert.assertTrue(driver.findElement(By.className("fhs-btn-login")).isEnabled());
		
		driver.navigate().refresh();
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(By.className("fhs-btn-login")));
		driver.findElement(By.className("fhs-btn-login")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.className("fhs-btn-login")).isEnabled());
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	}

	
	public void TC_02_Radio_Checkbox_Default() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		By luggage = By.xpath("//label[text()='Luggage compartment cover']//preceding-sibling::input");
		driver.findElement(luggage).click();
		Assert.assertTrue(driver.findElement(luggage).isSelected());
		
		driver.findElement(luggage).click();
		Assert.assertFalse(driver.findElement(luggage).isSelected() );
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		By Petrol = By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input");
		driver.findElement(Petrol).click();
		
		status = driver.findElement(Petrol).isSelected();
		if(!status)
		{
			driver.findElement(Petrol).click();
		}
	}

	public void TC_03_Radio_Checkbox_Customer() {
		driver.get("https://material.angular.io/components/radio/examples");
		By summer = By.xpath("//input[@value='Summer']/preceding-sibling::span[@class='mat-radio-outer-circle']");
		clickByJs(summer);
		sleepInSecond(3);
		status = driver.findElement(By.xpath("//input[@value='Summer']")).isSelected();
		Assert.assertTrue(status);
		if(!status)
		{
			clickByJs(summer);
		}
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		clickByJs(By.xpath("//span[text()='Checked']/preceding-sibling::span"));
		clickByJs(By.xpath("//span[text()='Indeterminate']/preceding-sibling::span"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input")).isSelected());
		
		clickByJs(By.xpath("//span[text()='Checked']/preceding-sibling::span"));
		clickByJs(By.xpath("//span[text()='Indeterminate']/preceding-sibling::span"));
		
		Assert.assertFalse(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input")).isSelected());
	}

	@Test
	public void TC_04_Radio_Checkbox_Customer()
	{
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		
		clickByJs(By.xpath("//div[@aria-label='Cần Thơ']"));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
	}
	

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void selectCheckbox(By by)
	{
		if(!driver.findElement(by).isSelected())
		{
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}
		
	}
	
	public void unSelectCheckbox(By by)
	{
		if(driver.findElement(by).isSelected())
		{
			System.out.println("The checkbox isSelected");
			sleepInSecond(2);
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}
	}
	
	public void clickByJs(By by)
	{
		if(!driver.findElement(by).isSelected())
		{
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
			//driver.findElement(by).click();
		}
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