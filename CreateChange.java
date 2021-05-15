package week4.day2;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateChange {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();	
		
		driver.get("https://dev103117.service-now.com");
		
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));	
		
		driver.switchTo().frame("gsft_main");
				
		driver.findElement(By.id("user_name")).sendKeys("admin");
		
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		
		driver.findElement(By.id("sysverb_login")).click();
		
		WebElement filter = driver.findElement(By.id("filter"));
		
		filter.sendKeys("Change");
		
		filter.sendKeys(Keys.ENTER);
		
	
		driver.findElement(By.xpath("(//div[text() = 'Create New'])[3]")).click();
		
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("gsft_main"));
		
		driver.findElement(By.xpath("//span[text() = 'Normal']")).click();
		
	
		String ReqID = driver.findElement(By.id("change_request.number")).getAttribute("Invest");
		
	
		driver.findElement(By.id("sysverb_insert")).click();
		
		
		driver.findElement(By.xpath("(//div[text() = 'Open'])[3]")).click();
		
	
		driver.switchTo().frame("gsft_main");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class = 'form-control']")));
		
		WebElement searchField = driver.findElement(By.xpath("//input[@class = 'form-control']"));
	
		searchField.sendKeys(Keys.ENTER);
		

		String chID = driver.findElement(By.xpath("//a[@class = 'linked formlink']")).getText();
		
		
		if(ReqID.equals(chID)) {
			System.out.println("Change Request is created");
		}
			else
			{
				System.out.println("Change Request is not created");
			}
		driver.close();
	}

}
