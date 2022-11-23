package dataFileOpe;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	public void SortProduct() {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		//search for mens wrist watches
				WebElement Search=driver.findElement(By.id("twotabsearchtextbox"));
				Reporter.log("Clicked on Search Bar");
				
				Search.sendKeys("Mens Wrist Watches");
				Reporter.log("Searched for Mens Wrist Watches");
				
				driver.findElement(By.id("nav-search-submit-button")).click();
				
				//filter price high to low
				driver.findElement(By.id("a-autoid-0")).click();
				
				
				driver.findElement(By.id("s-result-sort-select_2")).click();
				Reporter.log("Price has been Filterd By High To Low");
				
				//select sonata brand checkbox
				driver.findElement(By.xpath("//div[@id='brandsRefinements']//span[@class='a-size-base a-color-base'][normalize-space()='Sonata']")).click();
				Reporter.log("Sorted By Sonata Brand");
				
				//select price range 
				driver.findElement(By.xpath("//span[contains(text(),'₹2,000 - ₹5,000')]")).click();
				Reporter.log("Price Range is sorted between 2k to 5k");
				
				//select one product
				driver.findElement(By.xpath("//span[normalize-space()='4,149']")).click();
				Reporter.log("Click on a Wrist watch for add this into cart");
	}

}
