package vTiger_PracticeTests;

import java.awt.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;





import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Loginpage {
	
	public WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	
	
	@BeforeTest
	public void SetUp()
	{
		
		 
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\Browsers\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.navigate().to("http://localhost:100/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	
//*************************************************************************************************************
						//USED IF ELSE BLOCK
	
	@Test(priority=0,description="To verify the title of the login page")
	public void verifylogintitle()
	{
		
	 String Actualtitle=driver.getTitle();
	 String expectedtitle="vtiger CRM - Commercial Open Source CRM";
	 
	 if(Actualtitle.equals(expectedtitle))
	 {
	
		 System.out.println("verifylogintitle TestCase successfull");
	 }
	 else
	 {
		 System.out.println("verifylogintitle TestCase Not successfull"); 
		 
	 }
	 
	 	
	} 
	
	
//**************************************************************************************************************	
	
					//USED DATAPROVIDER ANNOTATION WITH DIFFERENT DATA SET
	
	@Test(priority=3,dataProvider="testdata",description="To verify login with valid credentials.")
	public void validcredentialslogin(String username,String password)
	{
	
	WebElement uname=driver.findElement(By.name("user_name"));
	uname.clear();
	uname.sendKeys(username);
	WebElement pwd=driver.findElement(By.name("user_password"));
	pwd.clear();
	pwd.sendKeys(password);
	driver.findElement(By.name("Login")).click();
	driver.findElement(By.xpath("//a[text()='Logout']")).click();
	System.out.println("validcredentials login testcase successfull ");
	
		
	}
	
	@DataProvider(name="testdata")
	
	public Object [][] credentials()
	{
		
		Object data[][]=new Object[3][2];
		
		
		data[0][0]="admin";
		data[0][1]="admin";
		
		data[1][0]="admin";
		data[1][1]="admin";
		
		
		data[2][0]="admin";
		data[2][1]="admin";
		
		
		return data;
	}
	
	
//***************************************************************************************************************	
	
					//USED QUERYSELECTOR INSTEAD OF SENDKEYS 
					//USED KEYBOARD BUTTON TO ENTER
					//USED CONTAINS IN XPATH
	
	
	@Test(priority=2,description="To verify login with invalid credentials.")
	public void invalidcredentials()
	{
		
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("document.querySelector(\"input[name='user_name']\").value='admin'");
		js.executeScript("document.querySelector(\"input[name='user_password']\").value='1234'");
		driver.findElement(By.name("Login")).sendKeys(Keys.ENTER);
		String actual=driver.findElement(By.xpath("//td[contains(text(),'You must specify a valid username and password.')]")).getText();
		String expected="You must specify a valid username and password.";
		if(actual.contains(actual))
		{
			System.out.println("invalidcredentials Error Message verified");
				
		}
		else
		{
			
			System.out.println("ValidCredentials Error Message verified");
			
		}
		
		
	}
	
//************************************************************************************************************	
	
	@Test(priority=1,description="To verify the logo of Vtiger Loginpage")
	public void verifylogo()
	{
		
	driver.findElement(By.xpath("//img[@src='include/images/vtiger-crm.gif']")).isDisplayed();
	
	Assert.assertTrue(true);
	
	System.out.println("Vtiger Logo displayed");
	
		
	}
	
	
	
//*****************************************************************************************************************	
	
	//HOME PAGE
	//USED Select CLASS FOR DROPDOWN
	//USED @Parameters
	
	
	
	@Test(priority=4,description="To adding the New contact")
	@Parameters({"usname","pawd"})
	
	public void createcontact(String usname,String pawd)
	{
		driver.findElement(By.name("user_name")).clear();
		driver.findElement(By.name("user_name")).sendKeys(usname);
		driver.findElement(By.name("user_password")).clear();
		driver.findElement(By.name("user_password")).sendKeys(pawd);
		driver.findElement(By.name("Login")).click();
		driver.findElement(By.xpath("//a[contains(text(),'New Contact')]")).click();
		Select oselect=new Select (driver.findElement(By.name("salutationtype")));
		oselect.selectByVisibleText("Dr.");
		driver.findElement(By.name("firstname")).sendKeys("Ashish");
		driver.findElement(By.name("lastname")).sendKeys("Gupta");
		Select leadsource=new Select(driver.findElement(By.name("leadsource")));
		leadsource.selectByVisibleText("Employee");
		driver.findElement(By.name("portal")).click();
		driver.findElement(By.name("support_end_date")).sendKeys("2020-01-15");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		driver.findElement(By.xpath("//input[@title='SendMail']")).isDisplayed();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Assert.assertTrue(true,"Lead Not created successfully");
		System.out.println("Contact created successfully");
		
		
		
	}
//****************************************************************************************************************	
	
	@Test(priority=5,description="To adding the New lead")
	public void createlead()
	{
		driver.findElement(By.name("user_name")).clear();
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).clear();
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.name("Login")).click();
		driver.findElement(By.xpath("//a[contains(text(),'New Lead')]")).click();
		Select Sselect=new Select(driver.findElement(By.name("salutationtype")));
		Sselect.selectByVisibleText("Mrs.");
		driver.findElement(By.name("lastname")).sendKeys("Raman");
		driver.findElement(By.name("company")).sendKeys("Tcs");
		driver.findElement(By.xpath("//input[@value='T']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		System.out.println("New Lead created successfully");
		
		
	}
	
//****************************************************************************************

	@Test(priority=6,description="To adding the New Potential")
	public void newaccount()
	{
		driver.findElement(By.name("user_name")).clear();
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).clear();
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.name("Login")).click();
		driver.findElement(By.xpath("//a[text()='New Account']")).click();
		driver.findElement(By.name("accountname")).sendKeys("JetBlue");
		Select admin=new Select(driver.findElement(By.name("accounttype")));
		admin.selectByVisibleText("Competitor");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		System.out.println("New potential added successfully");
		
	}
	
//**********************************************************************************************************	
	@Test(priority=7,description="To adding the New Ticket")
	public void newticket() throws InterruptedException
	{
		driver.findElement(By.name("user_name")).clear();
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).clear();
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.name("Login")).click();
		driver.findElement(By.xpath("//*[text()='New Ticket']")).click();
		String parent=driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@name='ticket_title']")).sendKeys("Bus Booking");
		driver.findElement(By.xpath("//input[@title='Change [Alt+G]']")).click();
		Set<String> allWindows=driver.getWindowHandles();
		int count = allWindows.size();
		for (String child:allWindows)
		{
			if(!parent.equalsIgnoreCase(child))
			{
				
				driver.switchTo().window(child);
				
				driver.close();
				
				
			}
			
		}
		
		Thread.sleep(3000);
		driver.switchTo().window(parent);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		System.out.println("New ticket added successfully");
	}
	
//************************************************************************************************************
	
	@Test(priority=8,description="To adding the New FAQ")
	public void newfaq()
	{
		
		driver.findElement(By.name("user_name")).clear();
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).clear();
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.name("Login")).click();
		driver.findElement(By.xpath("//*[text()='New FAQ']")).click();
		String parent=driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@value='Change']")).click();
		Set<String> allwindows=driver.getWindowHandles();
		int count=allwindows.size();
		for(String child:allwindows)
		{
			if(!parent.equalsIgnoreCase(child))
				
			{
				driver.switchTo().window(child);
				driver.close();
					
			}
			
		}
		
		driver.switchTo().window(parent);
		Select oselect=new Select(driver.findElement(By.xpath("//*[@name='faqstatus']")));
		oselect.selectByVisibleText("Published");
		driver.findElement(By.xpath("//*[@name='question']")).sendKeys("How are you?");
		driver.findElement(By.xpath("//*[@name='faq_answer']")).sendKeys("I am fine");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click(); 		System.out.println("New FAQ added successfully");	
	}
	
//**************************************************************************************************************	
	@AfterTest
	public void teardown()
	{  
		driver.quit();
			
	}
	

	

}
