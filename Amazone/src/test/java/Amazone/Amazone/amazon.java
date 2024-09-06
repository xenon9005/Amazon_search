package Amazone.Amazone;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
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
	 static String Sample="";
	 static WebDriverWait wait;
	 static Properties properties = new Properties();
	 
	 static {
	        try (InputStream input = new FileInputStream("PageData.properties")) {
	            properties.load(input);
	        } catch (IOException e) {
	            System.out.println("Failed to load properties file: PageData.properties");
	            e.printStackTrace();
	        }
	    }     
	 
	public static void main(String[] args) {
		
		browserOpen();
		SearchProduct();
		
	}
	
	public static String BigData(String variable) {
		String data=properties.getProperty(variable);
		return data;
	}
	
	
	public static void browserOpen() {
		
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();	
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			
		try {
			//url open
			Sample =BigData("url");
			driver.get(Sample);
		
			//Logo print 			
					
			Sample =BigData("title");		
			Element = wait.until((ExpectedConditions.visibilityOfElementLocated(By.id(Sample))));
			Sample =BigData("t1");
			value=Element.getAttribute(Sample);
		
			System.out.println(value+ " successfully open!");
		}
		catch(Exception e) {
			String G = wait.until((ExpectedConditions.visibilityOfElementLocated(By.tagName("Body")))).getText();
			System.out.println(G + e.getMessage());
		}
	}
	
	
	public static void SearchProduct() {
		
		try {		
			Sample=BigData("searchbox");
			Element = wait.until((ExpectedConditions.visibilityOfElementLocated(By.id(Sample))));
		
			//Enter the product you want to search
			value=BigData("Userinput");
			Element.sendKeys(value);
			//click on button
			Sample=BigData("button");
			Element=wait.until((ExpectedConditions.visibilityOfElementLocated(By.id(Sample))));
			Element.submit();
		
			//list all results 
			Sample=BigData("listresult");
			List<WebElement> listRecords = driver.findElements(By.cssSelector(Sample));
		
			for (WebElement result : listRecords) {
				String title = result.getText();
				System.out.println("Title: " + title);
            
				if (title.toLowerCase().contains(value.toLowerCase())) {
					System.out.println("Matching title found: " + title);
                
					Sample=BigData("ancestor");
					result.findElement(By.xpath(Sample)).click();
					// Thread.sleep(5000);
					break;
            	}
			}
		}
		catch(Exception ex) {
			String G = wait.until((ExpectedConditions.visibilityOfElementLocated(By.tagName("Body")))).getText();
			System.out.println(G + ex.getMessage());
		}
		
		driver.quit();
            
		}	   

}
