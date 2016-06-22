package BasicFunctionTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import common.TestEnv;
import common.TestVar;
import common.CommonConstant.Component;
import common.CommonConstant.Service;
import common.CommonConstant.ServiceStatus;
import PageObject.DashboardPage;
import PageObject.ServicePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Elasticsearch {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
	}
	
	@Test
	public void case1_ServiceStop() throws Exception {
		
		// 페이지 이동
		DashboardPage dashboard = PageFactory.initElements(driver, DashboardPage.class);
		dashboard.serviceClick(Service.Elasticsearch);
		
		// 서비스 중지  
		ServicePage service = PageFactory.initElements(driver, ServicePage.class);
		service.stop();
		
		// 요약 페이지 문구 변경 확인
		dashboard.checkStatus(Component.Elasticsearch_MasterDataNode, ServiceStatus.Stoped, driver);
		
		// 프로세스 kill 확인
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep elastic";
		boolean bCheckExitCode = true;
		
		List<String> hosts = dashboard.getHost(Component.Elasticsearch_MasterDataNode);
		for(int i=0; i<hosts.size(); i++) {
			String host = TestEnv.getHOST_IP(hosts.get(i));
			
			String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(!result.contains(TestVar.MASTER_DATA_NODE_PROCESS_CMD));
		}
		
	}
	
	@Test
	public void case2_ServiceStart() throws Exception {

		// 페이지 이동
		DashboardPage dashboard = PageFactory.initElements(driver, DashboardPage.class);
		dashboard.serviceClick(Service.Elasticsearch);
		
		// 서비스 시작  
		ServicePage service = PageFactory.initElements(driver, ServicePage.class);
		service.start();
		
		// 요약 페이지 문구 변경 확인
		dashboard.checkStatus(Component.Elasticsearch_MasterDataNode, ServiceStatus.Started, driver);
		
		// 프로세스 running 확인
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep elastic";
		boolean bCheckExitCode = true;

		List<String> hosts = dashboard.getHost(Component.Elasticsearch_MasterDataNode);
		for(int i=0; i<hosts.size(); i++) {
			String host = TestEnv.getHOST_IP(hosts.get(i));
			
			String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(result.contains(TestVar.MASTER_DATA_NODE_PROCESS_CMD));
		}
		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
}
