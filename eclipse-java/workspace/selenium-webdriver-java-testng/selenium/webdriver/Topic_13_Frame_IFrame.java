package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_IFrame {
	WebDriver driver;
	Select select;
	String projectPath= System.getProperty("user.dir");
	String name= "ngoc.nguyenthi";
	String phone = "0987654321";
	String message = "Java BootCamp";
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browerDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_IFrame() {
		driver.get("https://kyna.vn/");
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "168K lượt thích");
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
		driver.findElement(By.cssSelector("div.button_bar")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys(name);
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys(phone);
		sleepInSecond(5);
		
		select = new Select(driver.findElement(By.id("serviceSelect")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("textarea.input")).sendKeys(message);
		sleepInSecond(5);
	
		driver.findElement(By.cssSelector("input.submit")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.floater_inner label.logged_in_name")).getText(), name);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.floater_inner label.logged_in_phone")).getText(), phone);
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button img")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content h4"));
		
		Assert.assertEquals(courseName.size(), 9);
		
		for(WebElement course:courseName)
		{
			Assert.assertTrue(course.getText().contains("Excel"));
		}
		
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame(driver.findElement(By.name("login_page")));
		
		driver.findElement(By.className("input_password")).sendKeys("ngoc");
		driver.findElement(By.cssSelector("table.lForm img")).click();
		
		Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.name("footer")));
		
		driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
		
		
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