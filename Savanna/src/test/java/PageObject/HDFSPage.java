package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonConstant.Wait;

public class HDFSPage {

	WebDriver driver;
	
	@FindBy(how=How.ID, using="service-actions-dropdown-btn")
	WebElement serviceAction;
	@FindBy(how=How.CSS, using="ul.pull-right.dropdown-menu")
	WebElement menu;
	@FindBy(how=How.CSS, using="button.btn.btn-success")
	WebElement confirm;
	@FindBy(how=How.CLASS_NAME, using="datanode-count")  
	WebElement datanodecount;
	
	
	public HDFSPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void start() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		// 서비스 동작 버튼 클릭
		serviceAction.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("service-actions-dropdown-btn")));
		
		// 시작 선택
		List<WebElement> elements = menu.findElements(By.cssSelector("li"));
		wait.until(ExpectedConditions.attributeToBe(elements.get(0).findElement(By.tagName("i")), "class", "icon-play enabled"));
		elements.get(0).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-success"))); 
		confirm.click();
		
	}
	
	public void stop() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Wait.FiveSecond);
		
		// 서비스 동작 버튼 클릭
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("service-actions-dropdown-btn")));
		serviceAction.click();
		
		// 중지 선택
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.pull-right.dropdown-menu")));
		List<WebElement> elements = menu.findElements(By.cssSelector("li"));
		elements.get(1).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-success")));
		confirm.click();
		
	}
		
}
