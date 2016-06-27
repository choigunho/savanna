package common;

public class TestVar {

	// mapreduce2
	public static final String HISTORY_SERVER="JobHistoryServer";
	// yarn
	public static final String APP_TIMELINE_SERVER="org.apache.hadoop.yarn.server.applicationhistoryservice.ApplicationHistoryServer";
	public static final String RESOURCE_MANAGER="org.apache.hadoop.yarn.server.resourcemanager.ResourceManager";
	// ambari metrics
	public static final String AMBARI_METRICS_COLLECTOR_1="org.apache.hadoop.hbase.master.HMaster start";
	public static final String AMBARI_METRICS_COLLECTOR_2="org.apache.hadoop.yarn.server.applicationhistoryservice.ApplicationHistoryServer";
	public static final String AMBARI_METRICS_MONITOR="/usr/lib/python2.6/site-packages/resource_monitoring/main.py start";
	
	// etc
	public static final String PROCESS_RUNNING="Running";
	public static final String PROCESS_NOT_RUNNING="Not Running";
	
}
