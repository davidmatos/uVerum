package pt.inescid.microverum.microVerumAgentProxy;

import pt.inescid.microverum.microVerumAgentProxy.config.MicroVerumAgentConstants;

public class MicroVerumAgentApplication {

	
	
	public static void main(String[] args) {

		System.out.println("Args length = " + args.length);
		
		if (args.length < 5) {
			printUsage();
			System.exit(-1);
		}
		
		
		int localPort = 0;
		try {
			localPort = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			printUsage();
			System.exit(-1);
		}

		
		
		String serviceHostName = args[1];
		
		int servicePort = 0;
		try {
			servicePort = Integer.parseInt(args[2]);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			printUsage();
			System.exit(-1);
		}

		
		String microVerumLogAddress = args[3];
		
		int microVerumLogPort = 0;
		try {
			microVerumLogPort = Integer.parseInt(args[4]);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			printUsage();
			System.exit(-1);
		}

		boolean logMode = MicroVerumAgentConstants.LOG_ASYNC;
		if(args.length > 5) {
			if(args[5].equalsIgnoreCase("sync")) {
				logMode = MicroVerumAgentConstants.LOG_SYNC;
			}
		}
		
		MicroVerumAgent agent = new MicroVerumAgent(localPort, serviceHostName, servicePort, microVerumLogAddress, microVerumLogPort);
		agent.setLogMode(logMode);
		agent.startProxy();
		
	}

	private static void printUsage() {
		System.err.println("Usage: ");
		System.err.println("microverumagent <localPort> <serviceHostname> <servicePort> <microVerumLogAddress> <microVerumLogPort> [<logWriteMode=sync/asyn>]");
	}

}
