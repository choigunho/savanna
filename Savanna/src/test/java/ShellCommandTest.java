import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.AccountUtil;
import common.RemoteShellUtil;

import PageObject.DashboardPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShellCommandTest {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
	}
	
//	@Test
	public void ShellCommandTest() throws Exception {
		
		String host = "172.21.80.146";
		int port = 22;
		String user = "root";
		String passwd = "!dptmzhdj00";
		String command = "ps -ef | grep spark";
		boolean bCheckExitCode = true;
		
		String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		System.out.println("result:");
		System.out.println(result);
		
		assertTrue(result.contains("org.apache.spark.deploy.history.HistoryServer"));
		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
