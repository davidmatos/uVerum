package pt.inescid.microVerum.adminConsole.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.inescid.microVerum.adminConsole.MicroVerumAdminConsole;
import pt.inescid.microVerum.adminConsole.domain.Ping;
import pt.inescid.microVerum.adminConsole.domain.RegisterResponse;

@RestController
public class AdminController {

	@RequestMapping("/admin/ping")
	public Ping ping(@RequestParam(value = "name", defaultValue = "World") String name) {

		return new Ping(name);
	}

	@RequestMapping("/admin/registerAgent")
	public RegisterResponse register(
			@RequestParam(value = "serviceName", defaultValue = "serviceName") String serviceName,
			@RequestParam(value = "serviceHostName", defaultValue = "serviceHostName") String serviceHostName,
			@RequestParam(value = "servicePort", defaultValue = "80") int servicePort) {

		MicroVerumAdminConsole.registerMicroVerumAgent(serviceName, serviceHostName, servicePort);
		MicroVerumAdminConsole.getLogger().info("Successfully registered this agent: serviceName=" + serviceName + " serviceHostName="
				+ serviceHostName + " servicePort=" + servicePort);

		return new RegisterResponse("Ok", "Successfully registered this agent: serviceName=\" + serviceName + \" serviceHostName=\"\n" + 
				"				+ serviceHostName + \" servicePort=\" + servicePort");
	}

}
