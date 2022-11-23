package dataFileOpe;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Cart {
	WebDriver driver;
	
	public Cart(WebDriver driver)
	{
		this.driver=driver;
	}
	public void AddToCart() {
		
		//WINDOW handle
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                
                //add first watch into the cart
                driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
                Reporter.log("1st watch added into the cart");
                
                //add 2nd watch into the cart
                
                driver.findElement(By.xpath("//span[@id='a-autoid-3']//input[@name='submit.addToCart']")).click();
                Reporter.log("2nd watch added into the cart");
                
                //View cart
                driver.findElement(By.xpath("//a[@href='/gp/cart/view.html?ref_=sw_gtc']")).click();
                Reporter.log("Click on cart to varify the products");
                
                //remove one item from cart
                driver.findElement(By.id("a-autoid-0-announce")).click();
                
                driver.findElement(By.id("quantity_0")).click();
                Reporter.log("One product is removed from cart successfully");
                
                //proceed to checkout
                driver.findElement(By.name("proceedToRetailCheckout")).click();
                Reporter.log("Proceed to checkout");
           
                //select existing address
                driver.findElement(By.xpath("//span[contains(text(),'Banesh Dighe')]")).click();
                Reporter.log("One Of the Existing address is selected For Delivery");
                
                driver.findElement(By.xpath("//input[@data-testid='Address_selectShipToThisAddress']")).click();
                Reporter.log("Nevigated to payment Page successfully");
               
                
            }
        }driver.quit();
        Reporter.log("Browser is closed successfully");
	}

}
