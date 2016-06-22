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
import PageObject.AmbariMetricsPage;
import PageObject.DashboardPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AmbariMetrics {

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
		
		// Ambari Metrics 페이지 이동
		DashboardPage dashboard = PageFactory.initElements(driver, DashboardPage.class);
		dashboard.serviceClick(Service.AmbariMetrics);
		
		// 서비스 중지  
		AmbariMetricsPage ambariMetrics = PageFactory.initElements(driver, AmbariMetricsPage.class);
		ambariMetrics.stop();
		
		// 요약 페이지 문구 변경 확인
		dashboard.checkStatus(Component.AmbariMetricsCollector, ServiceStatus.Stoped, driver);
		
		// 프로세스 kill 확인
		List<String> hosts = dashboard.getHost(Component.AmbariMetricsCollector);
		String host = TestEnv.getHOST_IP(hosts.get(0));
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep collector";
		boolean bCheckExitCode = true;
		
		String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		assertTrue(!result.contains(TestVar.AMBARI_METRICS_COLLECTOR_CMD));
		
	}
	
	@Test
	public void case2_ServiceStart() throws Exception {

		// Ambari Metrics 페이지 이동
		DashboardPage dashboard = PageFactory.initElements(driver, DashboardPage.class);
		dashboard.serviceClick(Service.AmbariMetrics);
		
		// 서비스 시작  
		AmbariMetricsPage ambariMetrics = PageFactory.initElements(driver, AmbariMetricsPage.class);
		ambariMetrics.start();
		
		// 요약 페이지 문구 변경 확인
		dashboard.checkStatus(Component.AmbariMetricsCollector, ServiceStatus.Started, driver);
				
		// 프로세스 running 확인
		List<String> hosts = dashboard.getHost(Component.AmbariMetricsCollector);
		String host = TestEnv.getHOST_IP(hosts.get(0));
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep collector";
		boolean bCheckExitCode = true;
		
		String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		assertTrue(result.contains(TestVar.AMBARI_METRICS_COLLECTOR_CMD));
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
