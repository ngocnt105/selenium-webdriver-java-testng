package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_PartI {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("firstname")).sendKeys("Ngoc");
		driver.findElement(By.id("search")).click();
		
	}

	@Test
	public void TC_02_Class() {
		driver.navigate().refresh();
		driver.findElement(By.id("search")).sendKeys("sony");
		driver.findElement(By.className("search-button")).click();
	}

	@Test
	public void Tc_03_Name() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		driver.findElement(By.name("email")).sendKeys("ngocnguyenuet@gmail.com");
		sleepInSecond(3);
		driver.findElement(By.name("is_subscribed")).click();
		sleepInSecond(3);
	}
	
	@Test
	public void TC_04_Tagname() {
		System.out.println("Link = "+ driver.findElements(By.tagName("a")).size());
		System.out.println("Input = " + driver.findElements(By.tagName("input")).size());
		
	}
	
	@Test
	public void TC_05_LinkText() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		//driver.findElement(By.linkText("Search Terms")).click();
		driver.findElement(By.linkText("Search Terms")).click();
		//sleepInSecond(3);
	}
	
	@Test
	public void TC_06_Partial_LinkText() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		driver.findElement(By.partialLinkText("Orders and Returns")).click();
		//sleepInSecond(3);
		driver.findElement(By.partialLinkText("Orders and")).click();
		//sleepInSecond(3);
		//driver.findElement(By.partialLinkText("and Returns")).click();
		//sleepInSecond(3);
	}
	
	@Test
	public void TC_07_Css() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		driver.findElement(By.cssSelector("input[id='firstname']")).sendKeys("Ngoc");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input[id='lastname']")).sendKeys("Nguyen");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("ngocnguyenuet@gmail.com");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input[class='checkbox']")).click();
		sleepInSecond(3);
	}
	
	@Test
	public void TC_08_Xpath() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Ngoc");
		sleepInSecond(3);
		
		
		
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