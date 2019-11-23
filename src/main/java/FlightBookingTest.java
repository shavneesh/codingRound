import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Extended baseclass to increase the usability

public class FlightBookingTest extends BaseClass {

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		setDriverPath();

		// Added implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("https://www.cleartrip.com/");

		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		// Wait for the auto complete options to appear for the origin
		// Added explicit wait for the auto suggestions
		waitElementToBeVisible("#ui-id-1 li a", "css");

		List<WebElement> originOptions = driver.findElements(By.cssSelector("#ui-id-1 li a"));
		
		/*
		 * Below statement check null exception for the list
		 * Click operation will only form when list is not empty
		 */
		if (!originOptions.isEmpty()) {
			originOptions.get(0).click();
		} else {
			Assert.assertFalse(true, "List of auto suggestion not element for the originOptions field ");
		}

		driver.findElement(By.id("ToTag")).clear();
		driver.findElement(By.id("ToTag")).sendKeys("Delhi");


		/*
		 * Wait for the auto complete options to appear for the destination
			Added explicit wait for the auto suggestions
		 */
		waitElementToBeVisible("#ui-id-2 li a", "css");
		
		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("a"));
		
		/*
		 * Below statement check null exception for the list
		 * Click operation will only form when list is not empty
		 */
		if(!destinationOptions.isEmpty()) {
			destinationOptions.get(0).click();
		}else {
			Assert.assertFalse(true, "List of auto suggestion not element for the destinationOptions field ");
		}
		

		// Driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();
		
		// This method will select the current date from the calendar
		selectDate();

		// All fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

		waitElementToBeVisible(".searchSummary", "css");
		// Verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

		// Close the browser
		driver.quit();

	}

}
