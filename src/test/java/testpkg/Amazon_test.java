package testpkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Amazon_page;

public class Amazon_test {

	WebDriver driver;
	
	@BeforeTest
	public void setup()
	{
		driver=new ChromeDriver();
	}	
	
	@BeforeMethod
	public void urlload()
	{		
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();		
	}
	
	@Test
	public void test() throws Exception
	{
		Amazon_page obj=new Amazon_page(driver);
		obj.titleverification();
		obj.contentverification();
		obj.linkvalidation();
		obj.logoverification();
		obj.screenshot();
		obj.addtocart();
	}
}


