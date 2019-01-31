package Maven_PatientPortal_ExcelRead.PatientPortal_ExcelRead;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadFromExcel 
{
	WebDriver driver;
	
	@DataProvider(name="DP")
	public String[][] readXls() throws BiffException, IOException
	{
		File f = new File("PatientPortalData.xls");
		Workbook wb = Workbook.getWorkbook(f);		
		Sheet sheet = wb.getSheet("Data"); //sheetname
		int rows = sheet.getRows();
		int cols = sheet.getColumns();
		String data[][] = new String [rows][cols];
		
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{
				Cell cell = sheet.getCell(j,i);
				data[i][j] = cell.getContents();
			}
		}
		return data;
	}

	//Method definitions for operations	
	public void Login(String url, String username, String password, String location)
	{	
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(password);
		driver.findElement(By.id(location)).click();
		driver.findElement(By.xpath(".//*[@id='loginButton']")).click();
	}
	
	public void Registration(String firstname, String middlename, String lastname, String gender, String date, String month, String year, String address1, String address2, String city, String state, String country, String zipcode, String phoneNumber, String relationType, String relationName) throws InterruptedException
	{
		//Name
		driver.findElement(By.xpath(".//*[@id='referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension']")).click();  
		driver.findElement(By.name("givenName")).sendKeys(firstname);
		driver.findElement(By.name("middleName")).sendKeys(middlename);
		driver.findElement(By.name("familyName")).sendKeys(lastname);
		
		//Gender
		driver.findElement(By.id("genderLabel")).click();
		driver.findElement(By.id("genderLabel")).click();
		
		if(gender.equals("Male"))
		{
			driver.findElement(By.xpath(".//*[@id='gender-field']/option[1]")).click();
		}
		else
		{
			driver.findElement(By.xpath(".//*[@id='gender-field']/option[2]")).click();
		}
		
		//Birthday
		driver.findElement(By.xpath(".//*[@id='formBreadcrumb']/li[1]/ul/li[3]")).click();
		driver.findElement(By.xpath(".//*[@id='formBreadcrumb']/li[1]/ul/li[3]")).click();
		driver.findElement(By.id("birthdateDay-field")).sendKeys(date);		
		driver.findElement(By.id("birthdateMonth-field")).click();
		driver.findElement(By.id("birthdateMonth-field")).click();	
		Select dropdown = new Select(driver.findElement(By.id("birthdateMonth-field")));
		dropdown.selectByVisibleText(month);		
		driver.findElement(By.id("birthdateYear-field")).click();	
		driver.findElement(By.id("birthdateYear-field")).sendKeys(year);
		
		//Address
		driver.findElement(By.xpath("//span[contains(text(),'Address')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Address')]")).click();		
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys(address1);
		driver.findElement(By.xpath("//input[@id='address2']")).sendKeys(address2);
		driver.findElement(By.xpath("//input[@id='cityVillage']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@id='stateProvince']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@id='country']")).sendKeys(country);
		driver.findElement(By.xpath("//input[@id='postalCode']")).sendKeys(zipcode);
		
		//Phone No.
		driver.findElement(By.xpath("//span[contains(text(),'Phone Number')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Phone Number')]")).click();
		driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
		
		//Relatives info
		driver.findElement(By.xpath("//span[contains(text(),'Relatives')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Relatives')]")).click();
		driver.findElement(By.xpath("//select[@id='relationship_type']")).click();
		Select dropdown2 = new Select(driver.findElement(By.xpath("//select[@id='relationship_type']")));
		dropdown2.selectByVisibleText(relationType);
		driver.findElement(By.xpath("//input[@placeholder='Person Name']")).sendKeys(relationName);
		
		//Confirm registration
		driver.findElement(By.xpath("//span[@id='confirmation_label']")).click();
		driver.findElement(By.xpath("//span[@id='confirmation_label']")).click();
		driver.findElement(By.xpath("//input[@id='submit']")).click();
		}
	
	public void Logout()
	{
		driver.findElement(By.xpath("html/body/header/ul/li[3]/a")).click();
	}
}
