
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.javafx.PlatformUtil;

public class BaseClass {
	public WebDriver driver = null;

	public void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

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

	public void waitElementToBeVisible(String e, String locator) {
		if (locator == "css") {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(e)));
		}
		if (locator == "xpath") {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(e)));
		}
	}

	public void selectDate() {

		Date currentDate = new Date();

		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		String day = new SimpleDateFormat("dd").format(currentDate);

		waitElementToBeVisible("//a[text()='" + day + "']", "xpath");
		driver.findElement(By.xpath("//a[text()='" + day + "']")).click();

	}
}
