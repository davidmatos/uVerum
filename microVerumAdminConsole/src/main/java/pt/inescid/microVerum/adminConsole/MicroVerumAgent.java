package pt.inescid.microVerum.adminConsole;

public class MicroVerumAgent {
	
	public final static boolean STATUS_ONLINE = true;
	public final static boolean STATUS_OFFLINE = false;
	
	
	
	public String serviceName;
	public String serviceHostName;
	public int servicePort;
	public boolean status;
	
	
	public MicroVerumAgent(String serviceName, String serviceHostName, int servicePort) {
		super();
		this.serviceName = serviceName;
		this.serviceHostName = serviceHostName;
		this.servicePort = servicePort;
		this.status = STATUS_ONLINE;
	}

	
	
	
	
}
