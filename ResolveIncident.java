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

public class ResolveIncident {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
		
		driver.get("https://dev103117.service-now.com/");
		
		driver.switchTo().frame("gsft_main");
		
		driver.findElement(By.id("user_name")).sendKeys("admin");
		
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		
		driver.findElement(By.id("sysverb_login")).click();
		
		WebElement inFilter = driver.findElement(By.id("filter"));
		
		inFilter.sendKeys("Invest");
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//div[text() = 'Homepage'])[1]")));
		
		inFilter.sendKeys(Keys.ENTER);
		
		
		driver.findElement(By.xpath("//div[text() = 'Open']")).click();
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("gsft_main"));
		
		WebElement inField = driver.findElement(By.xpath("//input[@class = 'form-control']"));
		
		String IncidentNumber = "INC0010922";
		
		inField.sendKeys(IncidentNumber);
		
		inField.sendKeys(Keys.ENTER);
		
		
		WebElement Search = driver.findElement(By.xpath("//a[@class = 'linked formlink']"));
		
		wait.until(ExpectedConditions.textToBePresentInElement(Search, IncidentNumber));
		
		Search.click();
		
		WebElement state = driver.findElement(By.id("incident.state"));
		
		Select dd1 = new Select(state);
		
		dd1.selectByVisibleText("Resolved");
		
		driver.findElement(By.xpath("//span[text() = 'Resolution Information']")).click();
		
		WebElement closeCode = driver.findElement(By.id("incident.close_code"));
		
		Select reCode = new Select(closeCode);
		
		reCode.selectByVisibleText("Solved (Permanently)");
		
		driver.findElement(By.id("incident.close_notes")).sendKeys("Incident Resolved");
		
		driver.findElement(By.id("resolve_incident")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class = 'linked formlink']")));
		String inState = driver.findElement(By.xpath("(//tr[@class = 'list_row list_odd']//td)[8]")).getText();		
		if(inState.contains("Resolved")) {
			System.out.println("Incident is resolved");
		}
		else {
			System.out.println("Incident is not resolved");
		}
		driver.close();

	}

}
