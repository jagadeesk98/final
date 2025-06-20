package allages;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class allAges_singleslot_First


{
	@Test
	public void allages_one() throws IOException, InterruptedException
	{
		   // ✅ Launch headful Chrome browser
    	WebDriver dr = new ChromeDriver();
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
        while (LocalTime.now().isBefore(LocalTime.of(18, 0))) {
            Thread.sleep(10);
        }
		
		dr.findElement(By.xpath("//div[11]//div[2]//a[1]")).click();  //badmintion all ages	
		
		WebElement reservation=dr.findElement(By.xpath("//input[@id='reservationCount']"));
		reservation.clear();
		reservation.sendKeys("1");	
		dr.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();   //confirm button
		
		
		
		dr.findElement(By.xpath("//span[@class='header-text']")).click(); 			// date tab
		dr.findElement(By.xpath("(//div[@class='mdc-button__ripple'])[1]")).click();     // first time slot
		
		// Fill fields
		dr.findElement(By.xpath("//input[@id='telephone']")).sendKeys("5199809052");
		dr.findElement(By.xpath("//input[@id='email']")).sendKeys("jagak9052@gmail.com");
		dr.findElement(By.xpath("//input[@id='field2021']")).sendKeys("Jagadeesh");

		// Read back values
		String phone = dr.findElement(By.xpath("//input[@id='telephone']")).getAttribute("value");
		String email = dr.findElement(By.xpath("//input[@id='email']")).getAttribute("value");
		String name = dr.findElement(By.xpath("//input[@id='field2021']")).getAttribute("value");

		// Confirm all fields are filled correctly
		if (phone.equals("5199809052") && email.equals("jagak9052@gmail.com") && name.equals("Jagadeesh")) {
		    System.out.println("[✅] All inputs verified. Proceeding to click the final button.");

		    // Now click final confirm button
		    dr.findElement(By.className("mdc-button__ripple")).click();
		} else {
		    System.out.println("[❌] One or more inputs are incorrect. Aborting final click.");
		}
	}
	
	
}
