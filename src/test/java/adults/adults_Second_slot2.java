package adults;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class adults_Second_slot2 {

    @Test
    public void adult_two2() throws InterruptedException, IOException {
            // ✅ Start Chrome in headful mode
    	WebDriver dr = new ChromeDriver();
            dr.manage().window().maximize();
            dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // ✅ Navigate to booking page
            dr.get("https://reservation.frontdesksuite.ca/rcfs/richcraftkanata/Home/Index?Culture=en"
                    + "&PageId=b3b9b36f-8401-466d-b4c4-19eb5547b43a"
                    + "&ShouldStartReserveTimeFlow=False"
                    + "&ButtonId=00000000-0000-0000-0000-000000000000");

            // ✅ Scroll to slot section
            JavascriptExecutor js = (JavascriptExecutor) dr;
            js.executeScript("window.scrollBy(0,1500)");

            // ✅ Wait until 6:00 PM
            while (LocalTime.now().isBefore(LocalTime.of(18, 0))) {
                Thread.sleep(100);
            }

            // ✅ Click 'Badminton Adults'
            dr.findElement(By.xpath("//div[11]//div[1]//a[1]")).click();

            // ✅ Set reservation count to 2
            WebElement reservation = dr.findElement(By.id("reservationCount"));
            reservation.clear();
            reservation.sendKeys("2");

            // ✅ Confirm
            dr.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();

            // ✅ Click dynamic date (today + 2)
            java.time.DayOfWeek targetDay = LocalDate.now().plusDays(2).getDayOfWeek();
            String dayName = targetDay.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String dayXpath = String.format("//span[contains(normalize-space(), '%s')]", dayName);
            dr.findElement(By.xpath(dayXpath)).click();

            // ✅ Select second time slot
            dr.findElement(By.xpath("(//div[@class='mdc-button__ripple'])[2]")).click();

            // ✅ Fill in personal details
            dr.findElement(By.id("telephone")).sendKeys("4389269052");
            dr.findElement(By.id("email")).sendKeys("Chandukotipalli30@gmail.com");
            dr.findElement(By.id("field2021")).sendKeys("Chandu");

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
