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
		MicroVerumAgent.logger.info("Local port = " + localPort);
		
		
		String serviceHostName = args[1];
		
		MicroVerumAgent.logger.info("Service hostname = " + serviceHostName);
		
		int servicePort = 0;
		try {
			servicePort = Integer.parseInt(args[2]);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			printUsage();
			System.exit(-1);
		}
		MicroVerumAgent.logger.info("Service port = " + servicePort);

		
		String microVerumLogAddress = args[3];
		
		MicroVerumAgent.logger.info("MicroVerumLogAddress = " + microVerumLogAddress);
		
		int microVerumLogPort = 0;
		try {
			microVerumLogPort = Integer.parseInt(args[4]);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			printUsage();
			System.exit(-1);
		}
		
		MicroVerumAgent.logger.info("MicroVerumLogPort = " + microVerumLogPort);

		boolean logMode = MicroVerumAgentConstants.LOG_ASYNC;
		if(args.length > 5) {
			if(args[5].equalsIgnoreCase("sync")) {
				logMode = MicroVerumAgentConstants.LOG_SYNC;
			}
			MicroVerumAgent.logger.info("LogMode = " + args[5] );
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
