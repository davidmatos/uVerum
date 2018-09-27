package pt.inescid.microverum.microVerumAgentProxy.log;

public class SyncLogWriter implements LogWriter{
	
	static SyncLogWriter instance = null;
	
	
	
	
	public static SyncLogWriter getInstance() {
		if (instance == null) {
			instance = new SyncLogWriter();
		}
		
		return instance;
	}
	
	
	
	public void logRequest(String request) {
		
	}
	
	
	
	

}
