package family;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Singleslot_First


{
	@Test
	public void allages_one() throws IOException, InterruptedException
	{
		WebDriver dr=new ChromeDriver();
		dr.get("https://reservation.frontdesksuite.ca/rcfs/richcraftkanata/Home/Index?Culture=en&PageId=b3b9b36f"
				+ "-8401-466d-b4c4-19eb5547b43a&ShouldStartReserveTimeFlow=False&ButtonId=00000000-0000-0000-0000-000000000000");
		dr.manage().window().maximize();
		
		JavascriptExecutor js=(JavascriptExecutor)dr;
		js.executeScript("window.scrollBy(0,1500)");
		while (java.time.LocalTime.now().isBefore(java.time.LocalTime.of(18, 0))) {
		    try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
		}
//		dr.findElement(By.xpath("//div[contains(text(),'Basketball - adult')]")).click();
		
		dr.findElement(By.xpath("//div[11]//div[3]//a[1]")).click();  //badmintion all ages	
		
		WebElement reservation=dr.findElement(By.xpath("//input[@id='reservationCount']"));
		reservation.clear();
		reservation.sendKeys("1");	
		dr.findElement(By.className("mdc-button__ripple")).click();
		  //confirm button
		
		
		
		dr.findElement(By.xpath("//span[@class='header-text']")).click(); 			// date tab
		dr.findElement(By.xpath("(//div[@class='mdc-button__ripple'])[1]")).click();     // first time slot
		
		dr.findElement(By.xpath("//input[@id='telephone']")).sendKeys("5199809052");
		dr.findElement(By.xpath("//input[@id='email']")).sendKeys("jagak9052@gmail.com");
		dr.findElement(By.xpath("//input[@id='field2021']")).sendKeys("Jagadeesh");
		Thread.sleep(500);
		WebDriverWait wait = new WebDriverWait(dr, Duration.ofSeconds(2));
		WebElement finalConfirm = wait.until(ExpectedConditions.elementToBeClickable(By.className("mdc-button__ripple")));
		finalConfirm.click();
		
	}
	
	
}
