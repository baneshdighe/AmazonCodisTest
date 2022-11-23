package dataFileOpe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

public class ReadCredExel {
	
	WebDriver driver;
	
	public ReadCredExel(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void readExel(String filePath,String fileName,String sheetName) throws IOException 
	{

		File file=new File(filePath+fileName);
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
			
			driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);
			String baseURL="https://www.amazon.in/";
			
			driver.get(baseURL);
			Reporter.log("Nevigated to the Landing Page of The given URL");
			
			//click on sign in
			driver.findElement(By.xpath("//a[@id='nav-link-accountList']")).click();
			Reporter.log("Click on Sign In button Successfully");
			
			//provide credentials 
			driver.findElement(By.id("ap_email")).sendKeys(username);
			Reporter.log("Email has been entered in the text field successfully");
			
			driver.findElement(By.id("continue")).click();
			Reporter.log("Clicked on Continue button");
			
			driver.findElement(By.id("ap_password")).sendKeys(password);
			Reporter.log("Password has been Entered Successfully");
			
			driver.findElement(By.id("signInSubmit")).submit();
			Reporter.log("Clicked on Sign In button");
			
	}

}
