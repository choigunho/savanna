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
import common.CommonConstant.ErrorMessages;
import common.CommonConstant.Service;
import common.CommonConstant.ServiceStatus;
import PageObject.ServicePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AmbariMetrics {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	ServicePage service;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
		service = PageFactory.initElements(driver, ServicePage.class);
	}
	
	@Test
	public void case1_ServiceStop() throws Exception {
		
		// 서비스 중지  
		service.stop(Service.AmbariMetrics);
		
		// 요약 페이지 문구 변경 확인
		service.checkStatus(Component.AmbariMetricsCollector, ServiceStatus.Stoped, driver);
		
		// 프로세스 kill 확인
		List<String> hosts = service.getHost(Component.AmbariMetricsCollector);
		String host = TestEnv.getIP(hosts.get(0));
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep ambari-metrics-collector";
		boolean bCheckExitCode = false;
		
		String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		assertTrue(ErrorMessages.ProcessStillAlive, !result.contains(TestVar.AMBARI_METRICS_COLLECTOR_1));
		assertTrue(ErrorMessages.ProcessStillAlive, !result.contains(TestVar.AMBARI_METRICS_COLLECTOR_2));
		
		// 매트릭 모니터 프로세스 kill 확인
		hosts = service.getHost(Component.AmbariMetricsMonitor);
		command = "ps -ef | grep `cat /var/run/ambari-metrics-monitor/ambari-metrics-monitor.pid`";
		for(int i=0; i<hosts.size(); i++) {
			host = TestEnv.getIP(hosts.get(i));
			
			result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(ErrorMessages.ProcessStillAlive + " at " + hosts.get(i), !result.contains(TestVar.AMBARI_METRICS_MONITOR));
		}
	}
	
	@Test
	public void case2_ServiceStart() throws Exception {

		// 서비스 시작  
		service.start(Service.AmbariMetrics);
		
		// 요약 페이지 문구 변경 확인
		service.checkStatus(Component.AmbariMetricsCollector, ServiceStatus.Started, driver);
				
		// 프로세스 running 확인
		List<String> hosts = service.getHost(Component.AmbariMetricsCollector);
		String host = TestEnv.getIP(hosts.get(0));
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep ambari-metrics-collector";
		boolean bCheckExitCode = false;
		
		String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		assertTrue(ErrorMessages.ProcessNotStarted, result.contains(TestVar.AMBARI_METRICS_COLLECTOR_1));
		assertTrue(ErrorMessages.ProcessNotStarted, result.contains(TestVar.AMBARI_METRICS_COLLECTOR_2));
		
		// 매트릭 모니터 프로세스 running 확인
		hosts = service.getHost(Component.AmbariMetricsMonitor);
		command = "ps -ef | grep `cat /var/run/ambari-metrics-monitor/ambari-metrics-monitor.pid`";
		for(int i=0; i<hosts.size(); i++) {
			host = TestEnv.getIP(hosts.get(i));
			
			result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(ErrorMessages.ProcessNotStarted + " at " + hosts.get(i), result.contains(TestVar.AMBARI_METRICS_MONITOR));
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
