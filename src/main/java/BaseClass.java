
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.javafx.PlatformUtil;

/*
 *This class contains all the method that are used or might be use in test classes 
 */
public class BaseClass {
	public WebDriver driver = null;

	/**
	 * This method is for the static wait
	 * 
	 * @param durationInMilliSeconds
	 *            - need to provide int value for time
	 */
	public void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	/**
	 * This method will verify weather an element is present in the DOM or not It
	 * return true if element is present and false if element is not present
	 * 
	 * @param by
	 *            - need to pass the locator with locator type
	 * @return
	 */
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage() + " element is not found");
			return false;
		}
	}

	/**
	 * This method will check which operating system is use for the test Set the
	 * path of chormedriver
	 * 
	 * @return - this method will return the reference of chormedriver
	 */
	public WebDriver setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
		driver = new ChromeDriver();
		return driver;
	}

	/**
	 * According to the locator type appropriate explicit wait will be selected This
	 * method will wait for 20 second for a element to be visible on web page
	 * 
	 * @param locator-
	 *            Pass locator as a string
	 * @param locatorType
	 *            - type of locator use for locating the element
	 */
	public void waitElementToBeVisible(String locator, String locatorType) {
		if (locatorType == "css") {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locator)));
		} else if (locatorType == "xpath") {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
		}
	}

	/**
	 * This method will select the current date from the calendar. 
	 * This remove the static reference to the calendar date
	 */
	public void selectDate() {

		Date currentDate = new Date();

		String day = new SimpleDateFormat("dd").format(currentDate);

		waitElementToBeVisible("//a[text()='" + day + "']", "xpath");
		driver.findElement(By.xpath("//a[text()='" + day + "']")).click();

	}
}
