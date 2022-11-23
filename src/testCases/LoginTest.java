package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import dataFileOpe.Base;
import dataFileOpe.Cart;
import dataFileOpe.HomePage;
import dataFileOpe.ReadCredExel;


public class LoginTest extends Base{
	
	WebDriver driver;
	

	@Test(priority=1)
	public void Test1() throws IOException {
		driver=initializeDriver();
		ReadCredExel readFile= new ReadCredExel(driver);
		String filePath="C:\\Users\\Akash\\Downloads\\";
		readFile.readExel(filePath, "ExportXL.xlsx", "Sheet1");
		
		
	}
	@Test(priority=2)
	public void selectApprProduct() {
		
		HomePage HP = new HomePage(driver);
		HP.SortProduct();
		
		
	}
	@Test(priority=3)
	public void AddProdToCart() {
		
		Cart cart = new Cart(driver);
		cart.AddToCart();
	}
}
