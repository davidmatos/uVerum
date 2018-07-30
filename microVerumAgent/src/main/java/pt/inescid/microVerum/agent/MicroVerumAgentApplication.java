package pt.inescid.microVerum.agent;

import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

//@SpringBootApplication(scanBasePackages={
//		"pt.inescid.microVerum.common.db.RequestRepository"})
@SpringBootApplication
public class MicroVerumAgentApplication {

	public static void main(String[] args) {

		for (int i = 0; i < args.length; i++) {
			System.out.println("args[" + i + "]" + args[i]);
		}

		if (args.length < 2) {
			System.out.println("args.length= " + args.length);
			printUsage();
//			System.exit(-1);
		}
		MicroVerumAgent.serviceHostName = args[0];
		try {
			MicroVerumAgent.servicePort = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			printUsage();
//			System.exit(-1);
		}

//		
//		
//		
//		
//		
//		 UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://microverum-admin-console/admin/registerAgent");
//		
//		 
//		 uriComponentsBuilder.queryParam("serviceName", MicroVerumAgent.serviceHostName);
//		 uriComponentsBuilder.queryParam("serviceHostName", MicroVerumAgent.serviceHostName);
//		 uriComponentsBuilder.queryParam("servicePort", MicroVerumAgent.servicePort);
//		
//		 
//
//         
//		 URI uri = uriComponentsBuilder.build().toUri();
//		 System.out.println(new RestTemplate().getForObject(uri, String.class));

		MicroVerumAgent.logger.info("MicroVerum agent for service'" + MicroVerumAgent.serviceHostName + "' started.");
		SpringApplication.run(MicroVerumAgentApplication.class, args);

	}

	private static void printUsage() {
		System.err.println("Usage: ");
		System.err.println("microverumagent <serviceHostname> <servicePort>");
	}

}
