package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.AccountUtil;
import common.CommonConstant.Component;
import common.CommonConstant.Sleep;
import common.CommonConstant.Wait;

public class ServicePage {

	WebDriver driver;
	
	@FindBy(how=How.ID, using="service-actions-dropdown-btn")
	WebElement service_actions_dropdown_btn;
	@FindBy(how=How.CSS, using="ul.pull-right.dropdown-menu")
	WebElement dropdown_menu;
	@FindBy(how=How.CSS, using="button.btn.btn-success")
	WebElement btn_success;
	
	// mapreduce2
	@FindBy(how=How.CLASS_NAME, using="label_for_historyserver")
	List<WebElement> label_for_historyserver;
	@FindBy(how=How.CLASS_NAME, using="value_for_historyserver")
	List<WebElement> value_for_historyserver;
	
	// yarn
	@FindBy(how=How.CLASS_NAME, using="label_for_app_timeline_server")
	List<WebElement> label_for_app_timeline_server;
	@FindBy(how=How.CLASS_NAME, using="value_for_app_timeline_server")
	List<WebElement> value_for_app_timeline_server;
	@FindBy(how=How.CLASS_NAME, using="label_for_resourcemanager")
	List<WebElement> label_for_resourcemanager;
	@FindBy(how=How.CLASS_NAME, using="value_for_resourcemanager")
	List<WebElement> value_for_resourcemanager;
	
	// zookeeper
	@FindBy(how=How.CLASS_NAME, using="label_for_zookeeper_server")
	List<WebElement> label_for_zookeeper_server;
	@FindBy(how=How.CLASS_NAME, using="value_for_zookeeper_server")
	List<WebElement> value_for_zookeeper_server;
	
	// spark
	@FindBy(how=How.CLASS_NAME, using="label_for_spark_jobhistoryserver")
	List<WebElement> label_for_spark_jobhistoryserver;
	@FindBy(how=How.CLASS_NAME, using="value_for_spark_jobhistoryserver")
	List<WebElement> value_for_spark_jobhistoryserver;
	
	//livy
	@FindBy(how=How.CLASS_NAME, using="label_for_livy_sparkrestserver")
	List<WebElement> label_for_livy_sparkrestserver;
	@FindBy(how=How.CLASS_NAME, using="value_for_livy_sparkrestserver")
	List<WebElement> value_for_livy_sparkrestserver;
	
	//cassandra
	@FindBy(how=How.CLASS_NAME, using="label_for_cassandraseednode")
	List<WebElement> label_for_cassandraseednode;
	@FindBy(how=How.CLASS_NAME, using="value_for_cassandraseednode")
	List<WebElement> value_for_cassandraseednode;
	@FindBy(how=How.CLASS_NAME, using="label_for_prometheus")
	List<WebElement> label_for_prometheus;
	@FindBy(how=How.CLASS_NAME, using="value_for_prometheus")
	List<WebElement> value_for_prometheus;
	
	//ambaric metrics collector
	@FindBy(how=How.CLASS_NAME, using="label_for_metrics_collector")
	List<WebElement> label_for_metrics_collector;
	@FindBy(how=How.CLASS_NAME, using="value_for_metrics_collector")
	List<WebElement> value_for_metrics_collector;
	
	//kafka
	@FindBy(how=How.CLASS_NAME, using="label_for_kafka_broker")
	List<WebElement> label_for_kafka_broker;
	@FindBy(how=How.CLASS_NAME, using="value_for_kafka_broker")
	List<WebElement> value_for_kafka_broker;
	
	//elasticsearch
	@FindBy(how=How.CLASS_NAME, using="label_for_master_data_node")
	List<WebElement> label_for_master_data_node;
	@FindBy(how=How.CLASS_NAME, using="value_for_master_data_node")
	List<WebElement> value_for_master_data_node;
	
	//hdfs
	@FindBy(how=How.CLASS_NAME, using="label_for_namenode")
	List<WebElement> label_for_namenode;
	@FindBy(how=How.CLASS_NAME, using="value_for_namenode")
	List<WebElement> value_for_namenode;
	@FindBy(how=How.CLASS_NAME, using="label_for_secondary_namenode")
	List<WebElement> label_for_secondary_namenode;
	@FindBy(how=How.CLASS_NAME, using="value_for_secondary_namenode")
	List<WebElement> value_for_secondary_namenode;
	@FindBy(how=How.CSS, using="tr.component.DATANODE")
	WebElement tr_component_DATANODE;
	
	
	// flume
	@FindBy(how=How.ID, using="flume-summary")
	WebElement flume_summary;

	WebDriverWait wait;
	
	public ServicePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Wait.ThirtySecond);
	}
	
	@FindBy(how=How.CLASS_NAME, using="nav-services")
	WebElement nav_services;
	@FindBy(how=How.ID, using="modal")
	WebElement modal;
	
	public void stop(String serviceName) {

		// 서비스 이동
		movePage(serviceName);
		
		// 서비스 동작 버튼 클릭
		wait.until(ExpectedConditions.elementToBeClickable(service_actions_dropdown_btn)).click();
		
		// 시작 선택
		WebElement we = dropdown_menu.findElements(By.cssSelector("li")).get(1);
		wait.until(ExpectedConditions.attributeToBe(we.findElement(By.tagName("i")), "class", "icon-stop enabled"));
		we.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_success)).click(); 
		
		if(ExpectedConditions.visibilityOf(modal) != null) {
			wait.until(ExpectedConditions.elementToBeClickable(btn_success)).click(); 
		}
	}
	
	public void start(String serviceName) {

		// 서비스 이동
		movePage(serviceName);
		
		// 서비스 동작 버튼 클릭
		wait.until(ExpectedConditions.elementToBeClickable(service_actions_dropdown_btn)).click();
		
		// 시작 선택
		WebElement we = dropdown_menu.findElements(By.cssSelector("li")).get(0);
		wait.until(ExpectedConditions.attributeToBe(we.findElement(By.tagName("i")), "class", "icon-play enabled"));
		we.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_success)).click();
		
		if(ExpectedConditions.visibilityOf(modal) != null) {
			wait.until(ExpectedConditions.elementToBeClickable(btn_success)).click(); 
		}
	}
	
	public void movePage(String serviceName) {
		List<WebElement> elements = nav_services.findElements(By.cssSelector("li"));
		for(WebElement element : elements) {
			if(element.getText().trim().startsWith(serviceName)) {
				element.click();
				break;
			}
		}
	}
	
	public void checkStatus(final String componentName, final String expectedStatus, WebDriver driver) {
		
		(new WebDriverWait(driver, Wait.Long)).until(new ExpectedCondition<Boolean>() {
			List<WebElement> label = null;
			List<WebElement> value = null;
			
			public Boolean apply(WebDriver d) {
				switch(componentName) {
					case Component.HDFS_NameNode: label = label_for_namenode; value = value_for_namenode; break;
					case Component.HDFS_SecondaryNamenode: label = label_for_secondary_namenode; value = value_for_secondary_namenode; break;
					case Component.MapReduce2_HistoryServer: label = label_for_historyserver; value = value_for_historyserver; break;
					case Component.YARN_AppTimelineServer: label = label_for_app_timeline_server; value = value_for_app_timeline_server; break;
					case Component.YARN_ResourceManager: label = label_for_resourcemanager; value = value_for_resourcemanager; break;
					case Component.ZooKeeperServer: label = label_for_zookeeper_server; value = value_for_zookeeper_server; break;
					case Component.AmbariMetricsCollector: label = label_for_metrics_collector; value = value_for_metrics_collector; break;
					case Component.Cassandra_SeedNode: label = label_for_cassandraseednode; value = value_for_cassandraseednode; break;
					case Component.Cassandra_Prometheus: label = label_for_prometheus; value = value_for_prometheus; break;
					case Component.Elasticsearch_MasterDataNode: label = label_for_master_data_node; value = value_for_master_data_node; break;
					case Component.KafkaBroker: label = label_for_kafka_broker; value = value_for_kafka_broker; break;
					case Component.Livy_SparkRestServer: label = label_for_livy_sparkrestserver; value = value_for_livy_sparkrestserver; break;
					case Component.SparkHistoryServer: label = label_for_spark_jobhistoryserver; value = value_for_spark_jobhistoryserver; break;
				}

				int index = 0;
				try{
					for(WebElement we : value) {
						if(we.getText().equals(expectedStatus)) {
							index++;
						}
					}
					System.out.println(label.get(0).getText() + " " + value.get(0).getText());
				} catch(Exception e){}
				
				if(index == value.size()) {
					return true;
				}
				return false;
				
			}
		});
	}
	
	public List<String> getHost(String component) throws Exception {
		
		// 서비스 페이지에서 호스트 정보를 알아올 때 title 속성값이 없는 경우가 있음.(title 속성값은 시스템에 접근할 때 사용한다)
		// 그런데 다른 페이지를 갔다 돌아오면 title 속성값이 채워져 있음.
		// 해결책) 호스트 페이지 로드 후 다시 대시보드 페이지 복귀
		WebDriverWait wait = new WebDriverWait(driver, Wait.TenSecond);
		driver.navigate().to(AccountUtil.getSavannaManagerUrl() + "/#/main/hosts"); 
		driver.navigate().to(AccountUtil.getSavannaManagerUrl() + "/#/main/services"); 
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("nav-services")));
//		Thread.sleep(3000);
				
		List<String> hosts = new ArrayList<String>();
		List<WebElement> label = null ;
		
		switch(component) {
			case Component.HDFS_NameNode: label = label_for_namenode; break;
			case Component.HDFS_SecondaryNamenode: label = label_for_secondary_namenode; break;
			case Component.HDFS_DataNode: 
				tr_component_DATANODE.findElement(By.tagName("td")).findElement(By.tagName("a")).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("active-filter")));
				Thread.sleep(Sleep.TwoSecond);
				label = getDataNodeHosts(component); 
				break;
			case Component.MapReduce2_HistoryServer: label = label_for_historyserver; break;
			case Component.YARN_AppTimelineServer: label = label_for_app_timeline_server; break;
			case Component.YARN_ResourceManager: label = label_for_resourcemanager; break;
			case Component.ZooKeeperServer: label = label_for_zookeeper_server; break;
			case Component.Flume:
				flume_summary.findElement(By.tagName("a")).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("active-filter")));
				Thread.sleep(Sleep.TwoSecond);
				label = getFlumeHosts(component); 
				break;
			case Component.AmbariMetricsCollector: label = label_for_metrics_collector; break;
			case Component.Cassandra_SeedNode: label = label_for_cassandraseednode; break; 
			case Component.Cassandra_Prometheus: label = label_for_prometheus; break;
			case Component.Elasticsearch_MasterDataNode: label = label_for_master_data_node; break;
			case Component.KafkaBroker: label = label_for_kafka_broker; break;
			case Component.Livy_SparkRestServer: label = label_for_livy_sparkrestserver; break;
			case Component.SparkHistoryServer: label = label_for_spark_jobhistoryserver; break;
		}
		
		if(component.equals(Component.Flume) || component.equals(Component.HDFS_DataNode)) {
			for(int i=0; i<label.size(); i++) {
				hosts.add(label.get(i).findElement(By.tagName("a")).getAttribute("data-original-title"));
			}	
		} else {
			for(int i=0; i<label.size(); i++) {
				hosts.add(label.get(i).findElement(By.tagName("a")).getAttribute("title"));
			}
		}
		
		return hosts;
	}
	
	@FindBy(how=How.CLASS_NAME, using="trim_hostname")
	List<WebElement> trim_hostname;
	
	public List<WebElement> getFlumeHosts(String component) {
		List<WebElement> hosts = trim_hostname;
		return hosts;
	}
	
	public List<WebElement> getDataNodeHosts(String component) {
		List<WebElement> hosts = trim_hostname;
		return hosts;
	}
}
