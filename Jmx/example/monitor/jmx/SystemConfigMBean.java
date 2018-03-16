package example.monitor.jmx;

public interface SystemConfigMBean {
	
	public abstract void setThreadCount(int noofThreads);
	
	public abstract int getThreadCount();
	
	public abstract void setSchemaName(String schemaName);
	
	public abstract String getSchemaName();
	
	public abstract String doConfig();
	

}
