package JiraIssue;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.AccountUtil;
import common.CommonConstant.Service;
import PageObject.ServicePage;
import PageObject.YARNPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ECPS_491 {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	ServicePage service;
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
		service = PageFactory.initElements(driver, ServicePage.class);
	}
	
	@Test
	public void ECPS_491() throws Exception {
		
		service.movePage(Service.YARN);
		
		YARNPage yarn= PageFactory.initElements(driver, YARNPage.class);
		yarn.pageLinkClick();

		List<WebElement> items = yarn.readPageLinkMenu();
		assertEquals("리소스매니저 UI", items.get(0).getText());
		assertEquals("리소스매니저 로그", items.get(1).getText());
		assertEquals("리소스매니저 JMX", items.get(2).getText());
		assertEquals("쓰레드 스택", items.get(3).getText());
		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

