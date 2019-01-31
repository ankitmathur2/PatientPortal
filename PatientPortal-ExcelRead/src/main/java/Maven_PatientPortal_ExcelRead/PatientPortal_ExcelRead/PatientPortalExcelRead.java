package Maven_PatientPortal_ExcelRead.PatientPortal_ExcelRead;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PatientPortalExcelRead extends ReadFromExcel
{
	//Setting up invoking for browser			
	@BeforeTest
	public void invokeBrowser()
	{
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().fullscreen();
	}
			
	//Running the test		
	@Test(dataProvider="DP")
	public void LoginRun(String url, String username, String password, String location, String firstname, String middlename, String lastname, String gender, String date, String month, String year, String address1, String address2, String city, String state, String country, String zipcode, String phoneNumber, String relationType, String relationName) throws InterruptedException
	{
		Login(url, username, password, location);
		Registration(firstname, middlename, lastname, gender, date, month, year, address1, address2, city, state, country, zipcode, phoneNumber, relationType, relationName);
		Logout();
	}
}

