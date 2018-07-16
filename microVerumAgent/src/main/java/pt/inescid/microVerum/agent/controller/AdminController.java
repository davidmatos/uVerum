package pt.inescid.microVerum.agent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.inescid.microVerum.common.domain.Ping;

@RestController
public class AdminController {
	
	
	@RequestMapping("/admin/ping")
	public Ping ping(@RequestParam(value="name", defaultValue="World") String name) {
		
		return new Ping(name);
	}
	
	

}
