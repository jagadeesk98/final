package family;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Second

{@Test
	public void allages_two() throws IOException
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
		reservation.sendKeys("2");	
		dr.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();   //confirm button
		
		
		
		dr.findElement(By.xpath("//span[@class='header-text']")).click(); 			// date tab
		dr.findElement(By.xpath("(//div[@class='mdc-button__ripple'])[2]")).click();     // second time slot
		
		dr.findElement(By.xpath("//input[@id='telephone']")).sendKeys("5199809052");
		dr.findElement(By.xpath("//input[@id='email']")).sendKeys("jagak9052@gmail.com");
		dr.findElement(By.xpath("//input[@id='field2021']")).sendKeys("Jagadeesh");
		for (int i = 1; i <= 5; i++) {
		    try {
		        WebElement confirmButton = dr.findElement(By.xpath("//span[@class='mdc-button__ripple']"));
		        confirmButton.click();
		        System.out.println("[✓] Attempt " + i + ": Clicked Confirm Button");			
		        // Check if the next page loaded by looking for the "Verification code" field or message
		        if (dr.getPageSource().toLowerCase().contains("verification code") ||
		            dr.getPageSource().toLowerCase().contains("enter the verification code")) {
		            System.out.println("[✓] Verification page detected. Exiting loop.");
		            break;
		        }

		    } catch (Exception e) {
		        System.out.println("[!] Error on attempt " + i + ": " + e.getMessage());
		    }
		}
		
	}
	
	
}
