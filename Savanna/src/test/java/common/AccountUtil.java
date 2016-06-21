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
		
		// 서비스 페이지에서 호스트 정보를 알아올 때 title 속성값이 없는 경우가 있음.(title 속성값은 시스템에 접근할 때 사용한다)
		// 그런데 다른 페이지를 갔다 돌아오면 title 속성값이 채워져 있음.
		// 해결책) 로그인 후, 호스트 페이지 로드, 다시 대시보드 페이지 로드
		driver.navigate().to(getSavannaManagerUrl() + "/#/main/hosts"); 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("page-bar")));
		driver.navigate().to(getSavannaManagerUrl() + "/#/main/dashboard/metrics"); 
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
