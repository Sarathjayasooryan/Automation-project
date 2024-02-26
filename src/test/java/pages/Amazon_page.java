package pages;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class Amazon_page {
	
	WebDriver driver;
	
	By logo=By.xpath("//*[@id=\"nav-logo-sprites\"]");
	By search=By.xpath("//*[@id=\"twotabsearchtextbox\"]");
	By product=By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[4]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/h2/a/span");
	By addtocart=By.xpath("//*[@id=\"mbc-buybutton-addtocart-1\"]/span/input");
	By viewcart=By.xpath("//*[@id=\"nav-cart-count\"]");
	// constructor
	public Amazon_page(WebDriver driver)
	{
		this.driver=driver;
	}
	
	//title verification
	public void titleverification()
	{
		String actualtitle=driver.getTitle();
		System.out.println(actualtitle);
		String expecttitle="Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		
		if(actualtitle.equals(expecttitle))
		{
			System.out.println("Title Verified");
		}
		else
		{
			System.out.println("Title Not Verified");
		}
	}
		
	//content verification	
	public void contentverification()
	{
		String content=driver.getPageSource();
		
		if(content.contains("Amazon miniTV"))
		{
			System.out.println("Content Verified");
		}
		else
		{
			System.out.println("Content Not Verified");
		}
	}
	
	//link validation
	public void linkvalidation() throws Exception
	{
		String baseurl="https://www.amazon.in";
		driver.get(baseurl);
		URL ob=new URL(baseurl);
		HttpURLConnection con=(HttpURLConnection)ob.openConnection();
		con.connect();
		int b = con.getResponseCode();
		System.out.println("Response code : " +b);
		
		if(con.getResponseCode()==200)	
		{
			System.out.println("Valid url : " +baseurl);
		}
		else
		{
			System.out.println("Invalid url : " +baseurl);
		}
	}
	
	
	// logo is present or not 
	public void logoverification()
	{
		WebElement logos= driver.findElement(logo);
		if(logos.isDisplayed())
		{
			System.out.println("Logo is present");
		}
		else
		{
			System.out.println("Logo is not present");
		}
	}
	
	// screenshot
	public void screenshot() throws Exception
	{				
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
		FileHandler.copy(src,new File("./screenshot//amazonhome.png"));
		
	}
	public void addtocart() throws Exception
	{
		Thread.sleep(7000);
		driver.findElement(search).sendKeys("mobilephones",Keys.ENTER);
		
		String parentwindow=driver.getWindowHandle();
		Thread.sleep(3000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		driver.findElement(product).click();
						
		Set<String> allwindowhandles=driver.getWindowHandles();
		for(String handle:allwindowhandles)
		{
			if(!handle.equalsIgnoreCase(parentwindow))
			{
				driver.switchTo().window(handle);
			
				js.executeScript("window.scrollBy(0,500)", "");
				Thread.sleep(5000);
				driver.findElement(addtocart).click();
				driver.navigate().refresh();
				Thread.sleep(3000);
				driver.findElement(viewcart).click();			
			}			
		}
	}
}




