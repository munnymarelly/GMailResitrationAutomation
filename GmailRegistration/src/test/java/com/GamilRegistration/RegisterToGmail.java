package com.GamilRegistration;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v129.performance.Performance;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Utility.JavaUtility;
import com.Utility.PropertyFileUtility;

public class RegisterToGmail 
{
	public static WebDriver driver;
	
	public static String browser;
	public static String url;
	public static String firstName;
	public static String lastName;
	public static String month;
	public static String day;
	public static String year;
	public static String gender;
	public static String password;
	public static String phone;
	public static String emailname;
	
	
	public static void main(String[] args) throws Exception
	{
		PropertyFileUtility property = new PropertyFileUtility();
		JavaUtility java = new JavaUtility();
		
		browser = property.getData("browser");
		url = property.getData("url");
		firstName = property.getData("firstName");
		lastName = property.getData("lastName");
		month = property.getData("month");
		day = property.getData("day");
		year = property.getData("year");
		gender = property.getData("gender");
		password = property.getData("password");
		phone = property.getData("phone");
		emailname = property.getData("emailname");
		
		switch (browser.toLowerCase()) 
		{
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		}
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		
		driver.get(url);
	
		String parentWindow = driver.getWindowHandle();
		
		Actions action = new Actions(driver);
		
		//Scrolling till Try Google WorkSpace to make the Create Account Button Visible
		action.scrollToElement(driver.findElement(By.xpath("//a[contains(@href,'https://workspace.google.com/business/signup/welcome?uj=hc-new-gmail')]")))
			  .perform();
		
		//Clicking on Create an Account button
		action.click(driver.findElement(By.xpath("//a[contains(text(),'Create an account ')]")))
			  .perform();
		
		//Fetching the Parent Window Handle
		Set<String> allWindows = driver.getWindowHandles();
		
		//Switching to the new Page
		for (String pagehandle : allWindows) 
		{	
			if (pagehandle.equals(parentWindow)) 
			{
				continue;
			}
			else {
				driver.switchTo().window(pagehandle);
				break;
			}
		}

		//Clicking on CreateAccount button
		driver.findElement(By.xpath("//button[@aria-haspopup='menu']")).click();
		
		//Clicking on "For My Personal use"
		driver.findElement(By.xpath("//span[contains(text(),'For my personal use')]")).click();
		
		//Writing the Firstname
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
		
		//Writing the LastName
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
		
		//Clicking on Next Button
		driver.findElement(By.xpath("//button[@jsname='LgbsSe']")).click();
		
		//Selecting Birth Month
		WebElement monthDropDown = driver.findElement(By.cssSelector("select[id='month']"));		
		Select selectMonth = new Select(monthDropDown);		
		selectMonth.selectByVisibleText(month);
		
		//Write BirthDay		
		driver.findElement(By.cssSelector("input[name='day']")).sendKeys(day);
		
		//Write Birth Year
		driver.findElement(By.name("year")).sendKeys(year);
		
		//Select Gender
		WebElement genderDropDown = driver.findElement(By.cssSelector("select[id='gender']"));
		Select selectGender = new Select(genderDropDown);
		selectGender.selectByVisibleText(gender);
		
		//Clicking on the NEXT Button
		action.click(driver.findElement(By.xpath("//button[@jsname='LgbsSe']")))
			  .perform();
		
		//selecting the Custom Mail
		driver.findElement(By.xpath("(//div[contains(@class,'QTJzre zVkt0c')])[3]")).click();
		
		//Writing the CustomMail
		String mailName = emailname+java.createRandomNumber();
		action.sendKeys(driver.findElement(By.xpath("//input[@name='Username']")),mailName)
			  .perform();
		
		//Clicking on next Button
		action.click(driver.findElement(By.xpath("(//button[@jsname='LgbsSe'])[1]"))).perform();
		
		//entering password
		driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys(password);
		
		//re-entering password
		driver.findElement(By.xpath("//input[@name='PasswdAgain']")).sendKeys(password);
		
		//clicking on next button
		action.click(driver.findElement(By.xpath("//button[@jsname='LgbsSe']"))).perform();
		
		//Writing Mobile Number
		driver.findElement(By.xpath("//input[@id='phoneNumberId']")).sendKeys(phone);
		
		//Giving 10second for manually write the Verification code
		Thread.sleep(10000);
		
		//Clicking on the Skip Button
		driver.findElement(By.xpath("(//button[@jsname='LgbsSe'])[1]")).click();
		
		//Clicking on the Next Button
		driver.findElement(By.xpath("//button[@jsname='LgbsSe']")).click();
		
		//Scrolling till I AGree button nd clicking on it
		action.scrollToElement(driver.findElement(By.xpath("(//button[@jsname='LgbsSe'])[2]")))
		 	  .perform();
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("(//button[@jsname='LgbsSe'])[2]")).click();
		
		
			
			  
	}
}
