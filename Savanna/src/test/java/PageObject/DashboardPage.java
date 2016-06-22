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

import common.CommonConstant.Component;
import common.CommonConstant.Sleep;
import common.CommonConstant.Wait;

public class DashboardPage {

	WebDriver driver;
	
	@FindBy(how=How.ID, using="span-bg-operation-count")
	WebElement backgroundOperationCount;
	@FindBy(how=How.ID, using="modal-label")
	WebElement modarHeader;
	@FindBy(how=How.CLASS_NAME, using="nav-services")
	WebElement navServices;
	@FindBy(how=How.CLASS_NAME, using="navbar-inner")
	WebElement navbarInner;
	@FindBy(how=How.ID, using="about")
	WebElement about;
	
	@FindBy(how=How.CSS, using="button.btn.dropdown-toggle")
	WebElement btn_dropdownMenu;
	@FindBy(how=How.CSS, using="ul.dropdown-menu")
	WebElement dropdownMenu;
	
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
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	public void backgroundOperationCountBtnClick() {
		backgroundOperationCount.click();
	}
	
	public String readModarHeader() {
		return modarHeader.getText();
	}
	
	public void userBtnClick() throws Exception {
		btn_dropdownMenu.click();
		Thread.sleep(Sleep.OneSecond);
	}
	
	public void userMenuSelect(int menu) throws Exception {
		List<WebElement> items = dropdownMenu.findElements(By.cssSelector("li"));
			switch(menu) {
				case 0: items.get(0).click(); break;
				case 1: items.get(1).click(); break;
				case 2: items.get(2).click(); break;
				case 3: items.get(3).click(); break;
			}
		Thread.sleep(Sleep.OneSecond);
	}
	
	public String readContents() {
		return driver.findElement(By.className("modal-body")).getText();
	}
	
	public void serviceClick(String serviceName) throws Exception {
		
		List<WebElement> elements = navServices.findElements(By.cssSelector("li"));
		for(WebElement element : elements) {
			if(element.getText().trim().startsWith(serviceName)) {
				//System.out.println("[log] " + element.getText().split("\n")[0].trim() +  " service click");
				element.click();
				break;
			}
		}
		Thread.sleep(Sleep.OneSecond);
	}
	
	public List<String> getHost(String component) throws Exception {
		List<String> hosts = new ArrayList<String>();
		List<WebElement> label = null ;
		
		WebDriverWait wait = new WebDriverWait(driver, Wait.FiveSecond);
		
		switch(component) {
			case Component.HDFS_NameNode: label = label_for_namenode; break;
			case Component.HDFS_SecondaryNamenode: label = label_for_secondary_namenode; break;
			case Component.HDFS_DataNode: 
				// todo 
				driver.findElement(By.cssSelector("tr.component.DATANODE")).findElement(By.tagName("td")).findElement(By.tagName("a")).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("active-filter")));
				label = getDataNodeHosts(component); 
				break;
			case Component.MapReduce2_HistoryServer: 
				wait.until(ExpectedConditions.visibilityOfAllElements(label_for_historyserver));
				label = label_for_historyserver; break;
			case Component.YARN_AppTimelineServer: 
				label = label_for_app_timeline_server; break;
			case Component.YARN_ResourceManager: label = label_for_resourcemanager; break;
			case Component.ZooKeeperServer: label = label_for_zookeeper_server; break;
			case Component.Flume:
				driver.findElement(By.id("flume-summary")).findElement(By.tagName("a")).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("active-filter")));
				Thread.sleep(Sleep.TwoSecond);
				label = getFlumeHosts(component); 
				break;
			case Component.AmbariMetricsCollector: label = label_for_metrics_collector; break;
			case Component.Cassandra_SeedNode: 
				wait.until(ExpectedConditions.visibilityOfAllElements(label_for_cassandraseednode));
				label = label_for_cassandraseednode; break;
			case Component.Cassandra_Prometheus: 
				wait.until(ExpectedConditions.visibilityOfAllElements(label_for_prometheus));
				label = label_for_prometheus; break;
			case Component.Elasticsearch_MasterDataNode:
				wait.until(ExpectedConditions.visibilityOfAllElements(label_for_master_data_node));
				label = label_for_master_data_node; break;
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
	
	public void checkStatus(final String target, final String expectedStatus, WebDriver driver) {
		
		(new WebDriverWait(driver, Wait.Long)).until(new ExpectedCondition<Boolean>() {
			List<WebElement> label = null;
			List<WebElement> value = null;
			
			public Boolean apply(WebDriver d) {
				switch(target) {
					case Component.HDFS_NameNode: 
						label = d.findElements(By.className("label_for_namenode"));
						value = d.findElements(By.className("value_for_namenode"));
						break;
					case Component.HDFS_SecondaryNamenode: 
						label = label_for_secondary_namenode; 
						value = value_for_secondary_namenode; 
						break;
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
				if(!value.isEmpty() && !label.isEmpty()) {
					for(WebElement we : value) {
						//System.out.println(we.getText());
						if(we.getText().equals(expectedStatus)) {
							index++;
						}
//						System.out.println(label.get(0).getText() + " " + value.get(0).getText());
					}
				}
				

				if(index == value.size()) {
					System.out.println(label.get(0).getText() + " " + value.get(0).getText());
					return true;
				}
				return false;
			}
		});
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