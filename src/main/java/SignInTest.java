import com.sun.javafx.PlatformUtil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseClass {

	WebDriver driver = new ChromeDriver();

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		setDriverPath();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.cleartrip.com/");

		driver.findElement(By.linkText("Your trips")).click();
		driver.findElement(By.id("SignIn")).click();

		driver.switchTo().frame("modal_window");

		waitElementToBeVisible("#signInButton", "css");

		driver.findElement(By.id("signInButton")).click();

		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
		driver.quit();
	}

}
