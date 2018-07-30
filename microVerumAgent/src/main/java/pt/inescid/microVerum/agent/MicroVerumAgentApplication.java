package pt.inescid.microVerum.agent;

import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;



//@SpringBootApplication(scanBasePackages={
//		"pt.inescid.microVerum.common.db.RequestRepository"})
@SpringBootApplication
public class MicroVerumAgentApplication{


	
	
	public static void main(String[] args) {
		
		if(args.length < 3){
			printUsage();
			System.exit(-1);
		}
		MicroVerumAgent.serviceHostName = args[1];
		try {
			MicroVerumAgent.servicePort = Integer.parseInt(args[2]);			
		}catch (Exception e) {
			System.err.println(e.getMessage());
			printUsage();
			System.exit(-1);
		}
		
		
		SpringApplication.run(MicroVerumAgentApplication.class, args);
		
		
		
		
		 UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("microverum-admin-console/admin/registerAgent");
		
		 
		 uriComponentsBuilder.queryParam("serviceName", MicroVerumAgent.serviceHostName);
		 uriComponentsBuilder.queryParam("serviceHostName", MicroVerumAgent.serviceHostName);
		 uriComponentsBuilder.queryParam("servicePort", MicroVerumAgent.servicePort);
		
		 
		 
         
		 URI uri = uriComponentsBuilder.build().toUri();
		 System.out.println(new RestTemplate().getForObject(uri, String.class));

	}


	
	private static void printUsage() {
		System.err.println("Usage: ");
		System.err.println("microverumagent <serviceHostname> <servicePort>");
	}
	
	
}
