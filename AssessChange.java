package week4.day2;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssessChange {

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
		
		
		
		WebElement searchBox = driver.findElement(By.id("filter"));
		
		searchBox.sendKeys("Change");
		
		searchBox.sendKeys(Keys.ENTER);
		

		driver.findElement(By.xpath("(//div[text() = 'Open'])[3]")).click();
		

		driver.switchTo().frame("gsft_main");
		
		WebElement searchChange = driver.findElement(By.xpath("//input[@class = 'form-control']"));
		
		String ReqID = "CHG0030432";
		
		searchChange.sendKeys(ReqID);
		
		searchChange.sendKeys(Keys.ENTER);
		
	
		driver.findElement(By.xpath("//a[@class = 'linked formlink']")).click();
		
	
		WebElement state = driver.findElement(By.id("change_request.state"));
		
		Select changeReqState = new Select(state);
		
		changeReqState.selectByVisibleText("Assess");
		
		
		WebElement assignmentGroup = driver.findElement(By.id("sys_display.change_request.assignment_group"));
		
		assignmentGroup.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text() = 'Recent selections']/following::span[text() = 'Software']"))).click();
				
		WebElement assigned = driver.findElement(By.id("sys_display.change_request.assigned_to"));
		
		assigned.click();
		
	
		driver.findElement(By.id("sysverb_update")).click();
		
	
		String stateText = driver.findElement(By.xpath("(//tr[@class ='list_row list_odd']//td)[6]")).getText();
		
		String Changed = driver.findElement(By.xpath("(//a[@class = 'linked'])[2]")).getText();
		
		if(stateText.contains("Assess") && Changed.contains("ITIL User")){
			System.out.println("Changed");
		}
		else {
			System.out.println("Not Changed");
		}
		driver.close();
	}

}
