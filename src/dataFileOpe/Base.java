package dataFileOpe;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;



public class Base {
	
WebDriver driver;
	
	
	public WebDriver initializeDriver() throws IOException {
	
	
	String browserName = "Chrome";
	
	if(browserName.equalsIgnoreCase("Chrome")) {
		
		System.setProperty("webdriver.chrome.driver", "./BrowserUtils/chromedriver.exe");
		driver = new ChromeDriver();
		
	}else if(browserName.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver", "./BrowserUtils/gecodriver.exe");
		driver = new FirefoxDriver();
		
	}else if(browserName.equalsIgnoreCase("IE")) {
		System.setProperty("webdriver.ie.driver", "./BrowserUtils/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		
	}
	Reporter.log("Chrome Browser has been opened.");
	
	driver.manage().window().maximize();
	Reporter.log("Browser maximized");
	
	
	
	return driver;
		
}
	


}
