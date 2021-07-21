package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Run_Brower {
	WebDriver driver;
	String projectPath= System.getProperty("user.dir");
	

	
	public void TC_01_Run_On_Firefox() {
		//old firefox 
		driver = new FirefoxDriver(); 
		
		
		//new firefox (>=48)
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browerDriver\\geckodriver.exe");
		driver = new FirefoxDriver(); 
	}

	
	public void TC_02_Run_On_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browerDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}

	@Test
	public void TC_03_Run_On_Edge_Chromium() {
		
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