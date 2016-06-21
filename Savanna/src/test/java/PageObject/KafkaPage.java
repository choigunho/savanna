package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.CommonConstant.Sleep;

public class KafkaPage {

	WebDriver driver;
	
	@FindBy(how=How.ID, using="service-actions-dropdown-btn")
	WebElement serviceAction;
	
	@FindBy(how=How.CLASS_NAME, using="label_for_kafka_broker")
	List<WebElement> label_for_kafka_broker;
	
	@FindBy(how=How.CLASS_NAME, using="value_for_kafka_broker")
	List<WebElement> value;
	@FindBy(how=How.CSS, using="ul.pull-right.dropdown-menu")
	WebElement menu;
	@FindBy(how=How.CSS, using="button.btn.btn-success")
	WebElement confirm;
	
	public KafkaPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void start() throws Exception {
		
		// 서비스 동작 버튼 클릭
		serviceAction.click();
		Thread.sleep(Sleep.HalfASecond);
		
		// 시작 선택
		List<WebElement> elements = menu.findElements(By.cssSelector("li"));
		elements.get(0).click();
		Thread.sleep(Sleep.HalfASecond);
		
		// 최종 확인
		WebElement ok = confirm;
		ok.click();
		
	}
	
	public void stop() throws Exception {
		
		// 서비스 동작 버튼 클릭
		serviceAction.click();
		Thread.sleep(Sleep.HalfASecond);
		
		// 중지 선택
		List<WebElement> elements = menu.findElements(By.cssSelector("li"));
		elements.get(1).click();
		Thread.sleep(Sleep.HalfASecond);
		
		// 최종 확인
		WebElement ok = confirm;
		ok.click();
		
	}
	
}
