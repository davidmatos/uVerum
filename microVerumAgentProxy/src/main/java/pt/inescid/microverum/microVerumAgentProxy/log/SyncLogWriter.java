package pt.inescid.microverum.microVerumAgentProxy.log;

import pt.inescid.microVerum.common.domain.Request;

public class SyncLogWriter implements LogWriter {

	static SyncLogWriter instance = null;

	public static SyncLogWriter getInstance() {
		if (instance == null) {
			instance = new SyncLogWriter();
		}

		return instance;
	}

	public void logRequest(String requestString) {

		Request request = new Request("globalId", "from", "to", requestString);

		MicroVerumMongoClient.getInstance().writeRequest(request);

	}

}
