package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateIncident {

	public static void main(String[] args) throws InterruptedException {
	
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver =new ChromeDriver(); 

		driver.get("https://dev103117.service-now.com");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.switchTo().frame("gsft_main");
		

		driver.findElement(By.id("user_name")).sendKeys("admin");

		driver.findElement(By.id("user_password")).sendKeys("India@123");

		driver.findElement(By.xpath("//button[text()='Log in']")).click();

		Thread.sleep(5000);

		WebElement incident=driver.findElement(By.id("filter"));
		incident.sendKeys("Knowledge");
		Thread.sleep(2000);
		incident.sendKeys(Keys.ENTER);

		driver.switchTo().frame("gsft_main");
		Thread.sleep(2000);
		
		driver.findElement(By.tagName("button")).click();
		
		String attrValue=driver.findElement(By.id("sys_readonly.kb_knowledge.number")).getAttribute("value");
		System.out.println(attrValue);
		
		driver.findElement(By.xpath("//button[@data-for='sys_display.kb_knowledge.kb_knowledge_base']//span[1]")).click();
		
		Set<String> newWin=driver.getWindowHandles();
		List<String> allWindow=new ArrayList<String>(newWin);

		String newWindow=allWindow.get(1);

		driver.switchTo().window(newWindow);
		
		driver.findElement(By.linkText("IT")).click();

		driver.switchTo().window(allWindow.get(0));	
		
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		
		driver.findElement(By.id("lookup.kb_knowledge.kb_category")).click();

		driver.findElement(By.xpath("(//div[@class='list-item arrow']/following-sibling::div)[3]")).click();
		
		driver.findElement(By.xpath("//span[text()='Java']")).click();
		
		driver.findElement(By.id("ok_button")).click();

		driver.findElement(By.id("kb_knowledge.short_description")).sendKeys("Automation Basics");
		Thread.sleep(2000);
			
		driver.findElement(By.id("sysverb_insert")).click();
		
		System.out.println("Article  Updated");
		
		driver.close();
	}

}
