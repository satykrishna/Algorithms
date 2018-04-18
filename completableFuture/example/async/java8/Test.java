package example.async.java8;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;



public class Test {

	private static PropertiesConfiguration configuration;
	private static File file;
	private static Logger logger = Logger.getLogger(Test.class);

	
	public static void main(String[] args) {
		
		Test t = Test.getInstance();
		
		t.doesExists("412191392", "B", "C");
	}
	
	private Test() {
		try {
			String sysconfig = "/home/satya/Desktop/index.html";
			file = new File(sysconfig);
			if (file == null) {
				return;
			}
			configuration = new PropertiesConfiguration(file);
			configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (Exception e) {
			file = null;
			logger.info("Unable to read the file edi.biz.properties, please check if edi.biz.properties file path is properly configured");
			logger.info(e);
		}
		
	}
	
	private static Test instance = null;

	public static Test getInstance() {
		if (instance == null) {
			instance = new Test();
		}
		return instance;
	}
	
	private static boolean isFileValid() {
		return file != null;
	}

	public boolean doesExists(final String key, String senderAlias, String senderQualifier) {
		if (!isFileValid()) {
			return false;
		}
		
		String check = senderAlias+'#'+senderQualifier;
		
		Object obj = configuration.getProperty(key);
		if (obj instanceof String) {
			String str = (String) obj;
			return str.equalsIgnoreCase(check);
		}
		
		List<String> senderDetails = (ArrayList<String>) configuration.getProperty(key);
		for(String s: senderDetails) {
			if(s==null ||s.isEmpty()||s.trim().length()==0) {
				senderDetails.remove(s);
			}
		}
		return senderDetails.contains(check);
	}
}

