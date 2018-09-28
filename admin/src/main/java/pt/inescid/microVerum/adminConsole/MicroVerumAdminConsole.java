package pt.inescid.microVerum.adminConsole;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MicroVerumAdminConsole {

	public static ArrayList<MicroVerumAgent> registeredAgents = new ArrayList<>();
	
	
	public static void registerMicroVerumAgent(String serviceName, String hostname, int port) {
		MicroVerumAgent agent = new MicroVerumAgent(serviceName, hostname, port);
		registeredAgents.add(agent);
	}
	
	
	public static Logger getLogger() {
		return Logger.getLogger("MicroVerumAdminConsole");
	}
	
	
}
