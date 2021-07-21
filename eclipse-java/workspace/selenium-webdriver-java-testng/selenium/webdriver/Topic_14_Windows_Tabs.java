package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Windows_Tabs {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	String projectPath= System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browerDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC_01_Windows_Tabs() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String parentID = driver.getWindowHandle();
		String parentTitle = driver.getTitle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Google");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Google");
		
		
		switchToWindowByTitle(parentTitle);
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		switchToWindowByTitle(parentTitle);
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		switchToWindowByTitle(parentTitle);
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("LAZADA Vietnam™ - Mua Hàng Trực Tuyến Giá Tốt");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "LAZADA Vietnam™ - Mua Hàng Trực Tuyến Giá Tốt");
		
		closeAllWindowChild(parentID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		Assert.assertEquals(driver.getTitle(), parentTitle);
		
	}

	@Test
	public void TC_02_Window_Tabs() {
		driver.get("https://kyna.vn/");
		String parentTitle = "Kyna.vn - Học online cùng chuyên gia";
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		switchToWindowByTitle("Đăng nhập Facebook");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		
		switchToWindowByTitle(parentTitle);
		
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
		switchToWindowByTitle("Kyna.vn - YouTube");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		
		switchToWindowByTitle(parentTitle);
		
		closeAllWindowChild(parentID);
		
		
	}

	@Test
	public void TC_03_Window_Tabs() {
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div//a[@class='link-compare']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div//a[@class='link-compare']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		String parentID = driver.getWindowHandle();
		sleepInSecond(3);
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		closeAllWindowChild(parentID);
		
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(3);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		alert.accept();
		
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The comparison list was cleared.");
		
	}
	
	public void closeAllWindowChild(String parentID)
	{
		System.out.println("Parent Title: " +parentID);
		Set<String> allWindows = driver.getWindowHandles();
		for(String window:allWindows)
		{
			if(!window.equals(parentID))
			{
				driver.switchTo().window(window);
				driver.close();
			}
		}
		
		driver.switchTo().window(parentID);
	}
	
	public void switchToWindowByTitle(String expected)
	{
		Set<String> allWindows = driver.getWindowHandles();
		for(String window:allWindows)
		{
			driver.switchTo().window(window);
			sleepInSecond(3);
			String windowTitle = driver.getTitle();
			System.out.println("Title: " +windowTitle);
			if(windowTitle.equals(expected))
			{
				break;
			}
		}
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