package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Customer_DropDown_PartI {
	WebDriver driver;
	
	WebDriverWait explicitWait;
	
	JavascriptExecutor jsExecutor;
	
	String projectPath= System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browerDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomerDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div" ,"19");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "19");
		
		
	}

	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomerDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Stevie Feliciano");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Stevie Feliciano']")).isDisplayed());
	}

	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomerDropdown("//span[@class='caret']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
	}
	
	//@Test
	public void TC_04_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomerDropdown("//span[@aria-owns='games_options']", "//li[@class='e-list-item']", "Football");
		
	}

	@Test
	public void TC_05_Editable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		enterAndSelectItem("//div[@id='default-place']/input", "//li[@class='es-visible']", "Audi");
		
	}
	
	//@Test
	public void TC_06_Multiple_Select() {
		driver.get("");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void enterAndSelectItem(String parentXpath, String childXpath, String expectedItem){
		driver.findElement(By.xpath(parentXpath));
		sleepInSecond(1);
		
		driver.findElement(By.xpath(childXpath)).sendKeys(expectedItem);
		sleepInSecond(1);
		
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		for(WebElement item:allItems)
		{
			if(item.getText().trim().equals(expectedItem))
			{
				jsExecutor.executeScript("arguments[0].scrollIntoView(true).", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	public void selectItemInCustomerDropdown(String parentXpath, String childXpath, String expectedItem)
	{
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(3);
		
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		for (WebElement item: allItems)
		{
			if(item.getText().trim().equals(expectedItem))
			{
			//	if(!item.isDisplayed())
			//	{
					jsExecutor.executeScript("arguments[0].scrollIntoView(true).", item);
					sleepInSecond(1);
				//}
				item.click();
				break;
			}
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