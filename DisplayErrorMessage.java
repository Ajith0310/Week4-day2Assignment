package week4.day2;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DisplayErrorMessage {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver =new ChromeDriver(); 

		driver.get("https://dev103117.service-now.com");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
		
		driver.switchTo().frame("gsft_main");

		driver.findElement(By.id("user_name")).sendKeys("admin");

		driver.findElement(By.id("user_password")).sendKeys("India@123");

		driver.findElement(By.xpath("//button[text()='Log in']")).click();

		WebElement searchIncident=driver.findElement(By.id("filter"));
		
		searchIncident.sendKeys("Invest");
		
		searchIncident.sendKeys(Keys.ENTER);
		
		driver.switchTo().frame("gsft_main");
		
		driver.findElement(By.id("sysverb_new")).click();
		
		driver.findElement(By.id("sysverb_insert")).click();


		String ErrorMess = driver.findElement(By.xpath("//span[text()='The following mandatory fields are not filled in: Short description']")).getText();
		
		System.out.println(ErrorMess);
		
		if(ErrorMess.equalsIgnoreCase("The following mandatory fields are not filled in: Short description")) 
		{
			System.out.println("Error Message Verified");
		}

		driver.close();


	}

}
