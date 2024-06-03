package Amazone.Amazone;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class amazon {
	
	static WebDriver driver;
	 static WebElement Element;
	 static String value="";

	public static void main(String[] args) {
		
		browserOpen();
		SearchProduct();
		
	}
	
	public static void browserOpen() {
		
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();		
			
		try {
		driver.get("https://www.amazon.in/");
		
		Element = driver.findElement(By.id("nav-logo-sprites"));
		value=Element.getAttribute("aria-label");
		
		System.out.println(value+ " successfully open!");
		}
		catch(Exception e) {
			String G = driver.findElement(By.tagName("Body")).getText();
			System.out.println(G + e.getMessage());
		}
	}
	
	
	public static void SearchProduct() {
		
		try {		
		Element = driver.findElement(By.id("twotabsearchtextbox"));
		
		//Enter the product you want to search
		value= "iphone";
		Element.sendKeys(value);
		Element=driver.findElement(By.id("nav-search-submit-button"));
		Element.submit();
		
		List<WebElement> listRecords = driver.findElements(By.cssSelector("div.s-main-slot div.s-result-item h2 a span"));
		
		for (WebElement result : listRecords) {
            String title = result.getText();
            System.out.println("Title: " + title);
            
            if (title.toLowerCase().contains(value.toLowerCase())) {
                System.out.println("Matching title found: " + title);
                
                
                result.findElement(By.xpath("./ancestor::a")).click();
                Thread.sleep(5000);
                break;
            	}
			}
		}
		catch(Exception ex) {
			String G = driver.findElement(By.tagName("Body")).getText();
			System.out.println(G + ex.getMessage());
		}
		
		driver.quit();
            
		}	   

}

