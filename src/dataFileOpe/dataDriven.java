package dataFileOpe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;




public class dataDriven {
	 
	
	public void readExel(String filePath,String fileName,String sheetName) throws IOException 
	{

		File file=new File(filePath+"\\"+fileName);
		FileInputStream fis = new FileInputStream(file);
		
		Workbook loginWorkbook=null;
		
		String filExtension=fileName.substring(fileName.indexOf("."));
		if(filExtension.equals(".xlsx"))
		{
			loginWorkbook = new XSSFWorkbook(fis);
		}
		else if(filExtension.equals(".xls"))
		{
			loginWorkbook = new HSSFWorkbook(fis);
		}
		
		
		Sheet loginsheet = loginWorkbook.getSheet(sheetName);
		
		int rowCount=loginsheet.getLastRowNum()-loginsheet.getFirstRowNum();
		for(int i=1;i<rowCount+1;i++)
		{
			Row row=loginsheet.getRow(i);
			String username=row.getCell(0).getStringCellValue();
			String password=row.getCell(1).getStringCellValue();
			test(username,password);
		}
		
	}
	public void test(String username,String password)
	{
		
		//set Driver properties
		System.setProperty("webdriver.chrome.driver", "./BrowserUtils/chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);
		String baseURL="https://www.amazon.in/";
		driver.get(baseURL);
		

		//click on sign in
		driver.findElement(By.xpath("//a[@id='nav-link-accountList']")).click();
		
		//provide credentials 
		driver.findElement(By.id("ap_email")).sendKeys(username);
		driver.findElement(By.id("continue")).click();
		
		driver.findElement(By.id("ap_password")).sendKeys(password);
		driver.findElement(By.id("signInSubmit")).submit();
		
		//search for mens wrist watches
		WebElement Search=driver.findElement(By.id("twotabsearchtextbox"));
		
		Search.sendKeys("Mens Wrist Watches");
		
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		//filter price high to low
		driver.findElement(By.id("a-autoid-0")).click();
		
		driver.findElement(By.id("s-result-sort-select_2")).click();
		
		
		//select sonata brand checkbox
		driver.findElement(By.xpath("//div[@id='brandsRefinements']//span[@class='a-size-base a-color-base'][normalize-space()='Sonata']")).click();
		
		//select price range 
		driver.findElement(By.xpath("//span[contains(text(),'₹2,000 - ₹5,000')]")).click();
		
		//select one product
		driver.findElement(By.xpath("//span[normalize-space()='4,149']")).click();
		
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
                
                //add 2nd watch into the cart
                
                driver.findElement(By.xpath("//span[@id='a-autoid-3']//input[@name='submit.addToCart']")).click();
                
                
                //View cart
                driver.findElement(By.xpath("//a[@href='/gp/cart/view.html?ref_=sw_gtc']")).click();
                
                //remove one item from cart
                driver.findElement(By.id("a-autoid-0-announce")).click();
                
                driver.findElement(By.id("quantity_0")).click();
                
                
                //proceed to checkout
                driver.findElement(By.name("proceedToRetailCheckout")).click();
                
           
                //select existing address
                driver.findElement(By.xpath("//span[contains(text(),'Banesh Dighe')]")).click();
                
                driver.findElement(By.xpath("//input[@data-testid='Address_selectShipToThisAddress']")).click();
                
               
               
               
               
               
                
                
                
                
            }
        }driver.quit();
		
	}
	
	
	
//	public static void main(String[] args) throws IOException {
//		
//		dataDriven readFile= new dataDriven();
//		
//		String filePath="C:\\Users\\Akash\\Downloads\\";
//		readFile.readExel(filePath, "ExportXL.xlsx", "Sheet1");
//		
//	}
	
	@Test
	public void testcases1() throws IOException {
		
		dataDriven readFile= new dataDriven();
		String filePath="C:\\Users\\Akash\\Downloads\\";
		readFile.readExel(filePath, "ExportXL.xlsx", "Sheet1");
	}

}