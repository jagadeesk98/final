package family;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class First


{
	@Test
	public void allages_one() throws IOException, InterruptedException
	{
		   // ✅ Launch headful Chrome browser
    	WebDriver dr = new FirefoxDriver();
        dr.manage().window().maximize();
        dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // ✅ Navigate to reservation page
        dr.get("https://reservation.frontdesksuite.ca/rcfs/richcraftkanata/Home/Index?Culture=en"
                + "&PageId=b3b9b36f-8401-466d-b4c4-19eb5547b43a"
                + "&ShouldStartReserveTimeFlow=False"
                + "&ButtonId=00000000-0000-0000-0000-000000000000");

        // ✅ Scroll down to slot area
        JavascriptExecutor js = (JavascriptExecutor) dr;
        js.executeScript("window.scrollBy(0,1500)");

        // ✅ Wait until 6:00 PM
        while (LocalTime.now().isBefore(LocalTime.of(14, 0))) {
            Thread.sleep(10);
        }
		
		dr.findElement(By.xpath("//div[11]//div[3]//a[1]")).click();  //badmintion family	
		
		WebElement reservation=dr.findElement(By.xpath("//input[@id='reservationCount']"));
		reservation.clear();
		reservation.sendKeys("2");	
		WebElement confirmButton = dr.findElement(By.className("mdc-button__ripple"));
		confirmButton.click();   //confirm button
		
		
		
		dr.findElement(By.xpath("//span[@class='header-text']")).click(); 			// date tab
		dr.findElement(By.xpath("(//div[@class='mdc-button__ripple'])[1]")).click();     // first time slot
		
		dr.findElement(By.xpath("//input[@id='telephone']")).sendKeys("5199809052");
		dr.findElement(By.xpath("//input[@id='email']")).sendKeys("jagak9052@gmail.com");
		dr.findElement(By.xpath("//input[@id='field2021']")).sendKeys("Jagadeesh");
		Thread.sleep(800);
		dr.findElement(By.className("mdc-button__ripple")).click();	
							       
		}
	}
