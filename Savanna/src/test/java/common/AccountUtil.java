package common;

import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonConstant.Wait;

public class AccountUtil {

	public static WebDriver login(String userid, String password) throws Exception {
		
		WebDriver driver = new FirefoxDriver();
		driver.get(getSavannaManagerUrl() + "/#/login");
		
		WebElement id = driver.findElement(By.className("login-user-name"));
		id.sendKeys(userid);
		
		WebElement pwd = driver.findElement(By.className("login-user-password"));
		pwd.sendKeys(password);
		
		WebElement btn = driver.findElement(By.className("login-btn"));
		btn.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Wait.FiveSecond);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("nav-services"))); 
		
		return driver;
	}
	
	static ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
	
	public static String getUserId() {
		return rb.getString("TEST_ACCOUNT_USER_ID");
	}
	
	public static String getUserPwd() {
		return rb.getString("TEST_ACCOUNT_USER_PW");
	}
	
	public static String getSavannaManagerUrl() {
		return rb.getString("SAVANNA_MANAGER_URL");
	}
	
}
