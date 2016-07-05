package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.AccountUtil;
import common.CommonConstant.Component;
import common.CommonConstant.Wait;

public class HostPage {
	
	WebDriver driver;
	
	@FindBy(how=How.CLASS_NAME, using="component-label")
	List<WebElement> component_label;
	@FindBy(how=How.CLASS_NAME, using="host-components")
	WebElement host_components;
	
	WebDriverWait wait;
	
	public HostPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Wait.ThirtySecond);
	}
	
	/**
	 * 특정 호스트 페이지를 로딩하여 원하는 컴포넌트를 정지, 시작한다.  
	 *  
	 */
	@FindBy(how=How.CSS, using="button.btn.btn-success")
	WebElement btn_success;
	@FindBy(how=How.ID, using="modal")
	WebElement modal;
	
	public void componentAction(String host, String componentName, String action) throws Exception {
		
		// 호스트 페이지 이동
		String url = AccountUtil.getSavannaManagerUrl() + "/#/main/hosts/" + host + "/sumnnary";
		driver.navigate().to(url);
		
		Thread.sleep(1000);
				
		String label = null;
		List<WebElement> menu = null;
		
		switch(componentName) {
			case Component.KafkaBroker: label = "Kafka";
		}
		
		WebElement components = host_components;
		List<WebElement> rows = components.findElements(By.className("row-fluid")); 
		
		try {
			for(WebElement row: rows) {
				if(row.findElement(By.className("component-label")).getText().contains(label)) {
					row.findElement(By.className("dropdown-toggle")).click();
					
					Thread.sleep(1000);
					
					menu = row.findElement(By.className("dropdown-menu")).findElements(By.tagName("li"));
					
				}
			}	
		}catch(Exception e) {
		}
		
		// 드롭다운 메뉴에서 동작 선택
		for(WebElement item: menu) {
			if(item.getText().equals(action)) {
				item.click();
				wait.until(ExpectedConditions.elementToBeClickable(btn_success)).click();
				break;
			}
		}
		
		if(ExpectedConditions.visibilityOf(modal) != null) {
			wait.until(ExpectedConditions.elementToBeClickable(btn_success)).click(); 
		}
		
	}
	
	/**
	 * 특정 호스트 페이지를 로딩하여 원하는 컴포넌트를 추가한다(현재는 kafka broker만 추가할 수 있음).  
	 *  
	 */
	@FindBy(how=How.ID, using="add_component")
	WebElement add_component;
	@FindBy(how=How.CSS, using="div.btn-group.pull-right.open")
	WebElement div;
	
	public void addComponent(String host, String component) {
		
		// 호스트 페이지 이동
		String url = AccountUtil.getSavannaManagerUrl() + "/#/main/hosts/" + host + "/sumnnary";
		driver.navigate().to(url);
		
		wait.until(ExpectedConditions.elementToBeClickable(add_component)).click(); 
		
		WebElement menu = div.findElement(By.className("dropdown-menu"));
		List<WebElement> list = menu.findElements(By.tagName("li"));
		
		for(WebElement we: list) {
			// to do bug fix
			// System.out.println(">>> " + we.getText());
			if(we.getText().equals("카프카 브로커")) {
				//System.out.println("name: " + we.getText());
				we.click();
				wait.until(ExpectedConditions.elementToBeClickable(btn_success)).click();
				break;
			}
		}
		
	}
	
}
