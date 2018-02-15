package vTiger_PracticeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Homepage extends Loginpage {
	
	//***********************************************************************************************************
					//USED Select CLASS FOR DROPDOWN
					//USED @Parameters
	
	@Parameters({"usname","pawd"})
	@Test(priority=4)
	
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
		Assert.assertTrue(true,"Lead Not created successfully");
		System.out.println("Lead created successfully");
	

	}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	

