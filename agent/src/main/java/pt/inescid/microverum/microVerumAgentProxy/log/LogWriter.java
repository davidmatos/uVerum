package pt.inescid.microverum.microVerumAgentProxy.log;

import pt.inescid.microverum.microVerumAgentProxy.MicroVerumAgent;
import pt.inescid.microverum.microVerumAgentProxy.config.MicroVerumAgentConstants;

public interface LogWriter {

	public static LogWriter getInstance() {
		if (MicroVerumAgent.getInstance().logMode == MicroVerumAgentConstants.LOG_ASYNC) {
			return AsyncLogWriter.getInstance();
		} else {
			return SyncLogWriter.getInstance();
		}

	}

	public void logRequest(String request);

}
