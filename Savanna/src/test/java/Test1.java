import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.AccountUtil;
import common.RemoteShellUtil;
import common.CommonConstant.Service;

import PageObject.DashboardPage;
import PageObject.KafkaPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test1 {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
	}
	
	@Test
	public void Test() throws Exception {
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
