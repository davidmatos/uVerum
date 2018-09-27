package pt.inescid.microverum.microVerumAgentProxy.log;

public class AsyncLogWriter implements LogWriter {
	
	static AsyncLogWriter instance = null;
	
	
	
	
	public static AsyncLogWriter getInstance() {
		if (instance == null) {
			instance = new AsyncLogWriter();
		}
		
		return instance;
	}
	
	
	
	public void logRequest(String request) {
		System.out.println(request);
	}
	
	
	
	

}
