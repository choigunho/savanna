package common;

public class CommonConstant {


	public class ServiceStatus {
		public static final String Stoped = "정지";
		public static final String Started = "시작됨";
	}
	
	public class Wait {
		public static final int OneSecond = 1;
		public static final int FiveSecond = 5;
		public static final int TenSecond = 10;
		
		public static final int Short = 100;
		public static final int Long = 200;
	}
	
	public class Sleep {
		public static final int HalfASecond = 500;
		public static final int OneSecond = 1000;
		public static final int TwoSecond = 2000;
		public static final int TenSecond = 10 * 1000;
		public static final int ThirtySecond = 30 * 1000;
		public static final int SixtySecond = 60 * 1000;
	}
	
	public class Service {
		public static final String HDFS = "HDFS";
		public static final String MapReduce2 = "MapReduce2";
		public static final String YARN = "YARN";
		public static final String ZooKeeper = "ZooKeeper";
		public static final String Flume = "Flume";
		public static final String AmbariMetrics = "Ambari Metrics";
		public static final String Cassandra = "Cassandra";
		public static final String Elasticsearch = "Elasticsearch";
		public static final String Kafka = "Kafka";
		public static final String Livy = "Livy";
		public static final String Spark = "Spark";
		
	}
	
	public class Component {
			
		public static final String HDFS_NameNode = "NameNode";
		public static final String HDFS_SecondaryNamenode = "SNameNode";
		public static final String HDFS_DataNode = "DataNode";
		public static final String MapReduce2_HistoryServer = "MapReduce2HistoryServer";
		public static final String YARN_AppTimelineServer = "AppTimelineServer";
		public static final String YARN_ResourceManager = "ResourceManager";
		public static final String ZooKeeperServer = "ZooKeeperServer";
		public static final String Flume = "Flume";
		public static final String AmbariMetricsCollector = "AmbariMetricsCollector";
		public static final String Cassandra_SeedNode = "CassandraSeedNode";
		public static final String Cassandra_Prometheus = "CassandraPrometheus";
		public static final String Elasticsearch_MasterDataNode = "MasterDataNode";
		public static final String KafkaBroker = "KafkaBroker";
		public static final String Livy_SparkRestServer = "SparkRestServer";
		public static final String SparkHistoryServer = "SparkHistoryServer";
	}
}
