package common;

public class TestVar {

	// hdfs
	public static final String HDFS_NAME_NODE_CMD="org.apache.hadoop.hdfs.server.namenode.NameNode";
	public static final String HDFS_SECONDARY_NAME_NODE_CMD="org.apache.hadoop.hdfs.server.namenode.SecondaryNameNode";
	public static final String HDFS_DATA_NODE_CMD="org.apache.hadoop.hdfs.server.datanode.DataNode";
	// mapreduce2
	public static final String HISTORY_SERVER_PROCESS_CMD="JobHistoryServer";
	// yarn
	public static final String APP_TIMELINE_SERVER_PROCESS_CMD="org.apache.hadoop.yarn.server.applicationhistoryservice.ApplicationHistoryServer";
	public static final String RESOURCE_MANAGER_PROCESS_CMD="org.apache.hadoop.yarn.server.resourcemanager.ResourceManager";
	// zookeeper
	public static final String ZOOKEEPER_SERVER_PROCESS_CMD="org.apache.zookeeper.server.quorum.QuorumPeerMain";
	// flume
	public static final String FLUME_PROCESS_CMD="flume";
	// ambari metrics
	public static final String AMBARI_METRICS_COLLECTOR_CMD="ambari-metrics-collector";
	// cassandra
	public static final String CASSANDRA_SEED_NODE_CMD="org.apache.cassandra.service.CassandraDaemon";
	public static final String CASSANDRA_PROMETHEUS_CMD="org.apache.cassandra.service.CassandraDaemon";
	// elasticsearch
	public static final String MASTER_DATA_NODE_PROCESS_CMD="elasticsearch";
	// kafka
	public static final String KAFKA_BROKER_PROCESS_CMD="ambari-metrics-kafka-sink.jar";
	// livy
	public static final String LIVY_SPARK_REST_SERVER_PROCESS_CMD="com.cloudera.hue.livy.server.Main";
	// spark
	public static final String SPARK_HISTORY_SERVER_PROCESS_CMD="org.apache.spark.deploy.history.HistoryServer";
	
}
