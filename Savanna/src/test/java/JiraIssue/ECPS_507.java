package JiraIssue;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.AccountUtil;

import PageObject.DashboardPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ECPS_507 {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
	}
	
	@Test
	public void ECPS_507() throws Exception {
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		
		dashboardPage.backgroundOperationCountBtnClick();
		String result = dashboardPage.readModarHeader();
		
		assertEquals("0 백그라운드 오퍼레이션 동작중", result);
		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
