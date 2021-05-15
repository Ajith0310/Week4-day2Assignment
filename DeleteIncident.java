package week4.day2;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteIncident {

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
		
		driver.findElement(By.xpath("//div[text()='Create New']")).click();

		driver.switchTo().frame("gsft_main");
		
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default')]")).click();
		
		WebElement forTextNum = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']")); 
		
		new Select(forTextNum).selectByIndex(1);

		WebElement searchBox=driver.findElement(By.xpath("//input[@placeholder='Search']"));
		
		searchBox.sendKeys("INC0010965");
		
		searchBox.sendKeys(Keys.ENTER);
	
		
		driver.findElement(By.linkText("INC0010966")).click();
		
		driver.findElement(By.id("sysverb_delete")).click();
		
		driver.findElement(By.id("ok_button")).click();
		
		String recTo = driver.findElement(By.xpath("//td[text()='No records to display']")).getText();
		
		if(recTo.equals("No records to display"))
		{
			System.out.println("Deleted Successfully");
		}
		
		driver.close();
	}

}
