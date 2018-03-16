package example.monitor.jmx;

public class SystemConfig implements SystemConfigMBean {

	private int threadCount;
	private String schemaName;
	
	@Override
	public void setThreadCount(int noofThreads) {
		this.threadCount = noofThreads;
	}

	@Override
	public int getThreadCount() {
		return threadCount;
	}

	@Override
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Override
	public String getSchemaName() {
		return schemaName;
	}

	@Override
	public String doConfig() {
		return "No of Threads = " + this.threadCount + " and DB schema Name = " + this.schemaName;
	}

	public SystemConfig(int threadCount, String schemaName) {
		super();
		this.threadCount = threadCount;
		this.schemaName = schemaName;
	}

}
