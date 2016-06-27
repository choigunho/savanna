package common;

public class TestVar {

	// hdfs
	public static final String HDFS_NAME_NODE="org.apache.hadoop.hdfs.server.namenode.NameNode";
	public static final String HDFS_SECONDARY_NAME_NODE="org.apache.hadoop.hdfs.server.namenode.SecondaryNameNode";
	public static final String HDFS_DATA_NODE="org.apache.hadoop.hdfs.server.datanode.DataNode";
	// mapreduce2
	public static final String HISTORY_SERVER="JobHistoryServer";
	// yarn
	public static final String APP_TIMELINE_SERVER="org.apache.hadoop.yarn.server.applicationhistoryservice.ApplicationHistoryServer";
	public static final String RESOURCE_MANAGER="org.apache.hadoop.yarn.server.resourcemanager.ResourceManager";
	// zookeeper
	// flume
	// ambari metrics
	public static final String AMBARI_METRICS_COLLECTOR_1="org.apache.hadoop.hbase.master.HMaster start";
	public static final String AMBARI_METRICS_COLLECTOR_2="org.apache.hadoop.yarn.server.applicationhistoryservice.ApplicationHistoryServer";
	public static final String AMBARI_METRICS_MONITOR="/usr/lib/python2.6/site-packages/resource_monitoring/main.py start";
	// cassandra
	// elasticsearch
	public static final String MASTER_DATA_NODE="elasticsearch";
	// kafka
	public static final String KAFKA_BROKER="ambari-metrics-kafka-sink.jar";
	// livy
	public static final String LIVY_SPARK_REST_SERVER="postgresql.jar com.cloudera.hue.livy.server.Main";
	// spark
	public static final String SPARK_HISTORY_SERVER="org.apache.spark.deploy.history.HistoryServer";
	
	// etc
	public static final String PROCESS_RUNNING="Running";
	public static final String PROCESS_NOT_RUNNING="Not Running";
	
}
