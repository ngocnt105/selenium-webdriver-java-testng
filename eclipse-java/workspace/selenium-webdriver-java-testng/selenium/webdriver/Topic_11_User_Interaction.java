package webdriver;


import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction {
	WebDriver driver;
	Actions action;
	Alert alert;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String jsHelper = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browerDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Hover_To_Element() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

	
	public void TC_02_Hover_To_Element() {
		driver.get("http://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName']")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		
	}

	
	public void TC_03_Hover_To_Element() {
		driver.get("https://hn.telio.vn/");
		
	}
	
	
	public void TC_04_Click_and_Hover_To_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangle = driver.findElements(By.cssSelector(".ui-selectable>li"));
		
		action.clickAndHold(rectangle.get(0)).moveToElement(rectangle.get(3)).release().perform();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectable>li.ui-selected")).size(), 4);
	}
	
	
	public void TC_05_Click_and_Select_To_Element_Random()
	{
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangle = driver.findElements(By.cssSelector(".ui-selectable>li"));
		
		action.keyDown(Keys.CONTROL).perform();
		action.click(rectangle.get(0)).click(rectangle.get(2)).click(rectangle.get(5)).click(rectangle.get(10)).
		perform();
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectable>li.ui-selected")).size(), 4);
	}
	
	
	public void TC_06_Double_Click()
	{
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//scroll to Element for firefox
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']"))); //need to scroll to element to ex for Firefox //no need for chrome
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
	}
	
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(5);
		
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
		
		action.click(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		
		driver.switchTo().alert().accept();
		sleepInSecond(5);
		
	}
	
	
	public void TC_08_Drag_and_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement smallCircle = driver.findElement(By.id("draggable"));
		WebElement bigCircle = driver.findElement(By.id("droptarget"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex(), "#03a9f4");
	
	}
	

	public void TC_09_Drag_and_Drop_HTML5_JS_JQuery_Css() throws IOException{//chi lam viec voi CSS
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		 jsHelper = readFile(jsHelper)+ "$('#column-a').simulateDragDrop({ dropTarget: '#column-b'});";
				
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
		
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		
	}
	
	
	@Test
	public void TC_10_Drag_and_Drop_HTML5_JS_Robot_Xpath_Css(){
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		 jsHelper = readFile(jsHelper)+ "$('#column-a').simulateDragDrop({ dropTarget: '#column-b'});";
				
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
		
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		
	}
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
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