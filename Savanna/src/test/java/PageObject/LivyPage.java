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

public class LivyPage {

	WebDriver driver;
	
	@FindBy(how=How.ID, using="service-actions-dropdown-btn")
	WebElement serviceAction;
	@FindBy(how=How.CLASS_NAME, using="label_for_livy_sparkrestserver")
	List<WebElement> label_for_livy_sparkrestserver;
	@FindBy(how=How.CLASS_NAME, using="value_for_livy_sparkrestserver")
	WebElement value_for_livy_sparkrestserver;
	@FindBy(how=How.CSS, using="ul.pull-right.dropdown-menu")
	WebElement menu;
	@FindBy(how=How.CSS, using="button.btn.btn-success")
	WebElement confirm;
	
	public LivyPage(WebDriver driver) {
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
		
		confirm.click();
		
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
		
	public void checkLivySparkRESTServerStatus(final String expectedStatus, WebDriver driver) throws Exception {
		(new WebDriverWait(driver, Wait.Short)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				String currentStatus = value_for_livy_sparkrestserver.getText();
				if(currentStatus.equals(expectedStatus)){
					System.out.println("리비 스파크 REST 서버 " + value_for_livy_sparkrestserver.getText());
					return true;
				}
				return false;
			}
		});
	}

}
