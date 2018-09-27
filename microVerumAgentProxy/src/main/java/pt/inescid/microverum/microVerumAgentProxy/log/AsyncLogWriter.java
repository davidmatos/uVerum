package pt.inescid.microverum.microVerumAgentProxy.log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import pt.inescid.microVerum.common.domain.Request;

public class AsyncLogWriter implements LogWriter {
	
	/**
	 * 
	 */
	public long period; 

	static AsyncLogWriter instance = null;

	private ConcurrentLinkedQueue<String> queue;

	public static AsyncLogWriter getInstance() {
		if (instance == null) {
			instance = new AsyncLogWriter();
		}

		return instance;
	}

	public AsyncLogWriter() {
		period = 3000;
		queue = new ConcurrentLinkedQueue<String>();
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				writeToLog();
				
			}
		};
		
		Timer timer =  new Timer();
		timer.scheduleAtFixedRate(timerTask, period, period);
	}

	
	
	
	public void logRequest(String requestString) {
		queue.add(requestString);
	}

	/**
	 * periodic taskt responsible for storing the queued log entries to the log
	 */
	public void writeToLog() {
		if (queue.isEmpty()) {
			return;
		}
		// System.out.println(requestString);

		while(!queue.isEmpty()) {
			
			Request request = new Request("globalId", "from", "to", queue.poll());
			// System.out.println("=======");
			// System.out.println(request.toString());
			// System.out.println("=======");
			
			MicroVerumMongoClient.getInstance().writeRequest(request);
		}
		
	}
	
	
	
	
	

}
