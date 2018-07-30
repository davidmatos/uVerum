package pt.inescid.microVerum.agent;

import java.net.URI;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pt.inescid.microVerum.common.db.RequestRepository;
import pt.inescid.microVerum.common.domain.Ping;
import pt.inescid.microVerum.common.domain.Request;

@EnableMongoRepositories(basePackages = { "pt.inescid.microVerum.common.db" })
@RestController
public class AgentController {

	private static AtomicLong atomicLong = new AtomicLong();

	@Autowired
	private RequestRepository repository;

	@RequestMapping("/ping")
	public Ping ping(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("/ping");
		return new Ping(name);
	}

	// @RequestMapping("/{controller}/{action}")
	// public String request(@PathVariable String controller, @PathVariable String
	// action) {
	// System.out.println("/{controller}/{action}");
	// return "You requested the action '" + action + "' from the controller '" +
	// controller + "'.";
	// }

	@RequestMapping("/**")
	public String requestVague(HttpServletRequest request) {
		System.out.println("/**");

		repository.save(new Request(request.getRequestURI(), request.getRequestURI(), request.getRemoteAddr(),
				request.getLocalAddr(), atomicLong.incrementAndGet() + "", request.getMethod(),
				request.getParameterMap()));

		List<Request> requests = repository.findAll();

		String result = "";
		for (Request r : requests) {

			result += r.toString() + "\n";
		}

		
		
		System.out.println("Received a request  that will be forwarded");
		System.out.println(result);
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		
		 UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURI().replaceAll(MicroVerumAgent.serviceHostName, "real-" + MicroVerumAgent.serviceHostName));
		 Enumeration<String> paramsKeys = request.getParameterNames();
		 while(paramsKeys.hasMoreElements()) {
			 String paramKey = paramsKeys.nextElement();
			 uriComponentsBuilder.queryParam(paramKey, request.getParameterValues(paramKey));
		 }
		 
         
		 URI uri = uriComponentsBuilder.build().toUri();
     return restTemplate.getForObject(uri, String.class);
//
//		return result;
		//
		//
		// try {
		// ServletInputStream is = request.getInputStream();
		// String body = "";
		// byte[] line = new byte[1000];
		// while (is.readLine(line, 0, 1000)>0) {
		// body += new String(line) + "\n";
		// }
		//
		// repository.save(new Request(request.getRequestURI(), body,
		// request.getRemoteHost(), request.getLocalAddr(),
		// "1", request.getMethod(), request.getParameterMap()));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// return "Super vague";
	}

}
