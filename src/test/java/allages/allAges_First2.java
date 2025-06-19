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

public class allAges_First2


{
	@Test
	public void allages_one2() throws IOException, InterruptedException
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
        // Booking process
        dr.findElement(By.xpath("//div[11]//div[2]//a[1]")).click();
        WebElement reservation = dr.findElement(By.id("reservationCount"));
        reservation.clear();
        reservation.sendKeys("2");
        
        dr.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
     // Get day of week +2
        java.time.DayOfWeek targetDay = java.time.LocalDate.now().plusDays(2).getDayOfWeek();
        String dayName = targetDay.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH);

        // Build XPath dynamically
        String dayXpath = String.format("//span[contains(normalize-space(), '%s')]", dayName);
        dr.findElement(By.xpath(dayXpath)).click();

        dr.findElement(By.xpath("(//div[@class='mdc-button__ripple'])[1]")).click();
		dr.findElement(By.xpath("//input[@id='telephone']")).sendKeys("4389269052");
		dr.findElement(By.xpath("//input[@id='email']")).sendKeys("Chandukotipalli30@gmail.com");
		dr.findElement(By.xpath("//input[@id='field2021']")).sendKeys("Chandu");
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
