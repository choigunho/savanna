package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonConstant.Sleep;
import common.CommonConstant.Wait;

public class ElasticsearchPage {

	WebDriver driver;
	
	@FindBy(how=How.ID, using="service-actions-dropdown-btn")
	WebElement serviceAction;
	@FindBy(how=How.CLASS_NAME, using="label_for_master_data_node")
	List<WebElement> label_for_master_data_node;
	@FindBy(how=How.CLASS_NAME, using="value_for_master_data_node")
	List<WebElement> value_for_master_data_node;
	@FindBy(how=How.CSS, using="ul.pull-right.dropdown-menu")
	WebElement menu;
	@FindBy(how=How.CSS, using="button.btn.btn-success")
	WebElement confirm;
	
	public ElasticsearchPage(WebDriver driver) {
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
		
		confirm.click();;
		
	}
	
	public void stop() throws Exception {
		
		// 서비스 동작 버튼 클릭
		serviceAction.click();
		Thread.sleep(Sleep.HalfASecond);
		
		// 중지 선택
		List<WebElement> elements = menu.findElements(By.cssSelector("li"));
		elements.get(1).click();
		Thread.sleep(Sleep.HalfASecond);
		
		confirm.click();
		
	}
		
	// 모든 노드가 기대값(상태)으로 변경되는 것을 확인
	public void checkNodeStatus(final String expectedServiceStatus, WebDriver driver) throws Exception {

		final List<WebElement> webElements = value_for_master_data_node;
		
		(new WebDriverWait(driver, Wait.Short)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				int index = 0;
				
				for(WebElement we : webElements) {
					//System.out.println(we.getText());
					if(we.getText().equals(expectedServiceStatus)) {
						index++;
					}
				}
				
				if(index == webElements.size()) {
					for(int i=0; i<webElements.size(); i++) {
						System.out.println("마스터+데이터 노드 " + webElements.get(i).getText());
					}
					return true;
				}
				
				return false;
			}
		});
	}

	
}
