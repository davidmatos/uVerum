package pt.inescid.microVerum.Bubble.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.inescid.microVerum.Bubble.db.RequestRepository;
import pt.inescid.microVerum.Bubble.domain.Ping;
import pt.inescid.microVerum.Bubble.domain.Request;

@RestController
public class BubbleController {
	
	@Autowired
	private RequestRepository repository;
	
	@RequestMapping("/ping")
	public Ping ping(@RequestParam(value="name", defaultValue="World") String name) {
		
		return new Ping(name);
	}
	
	@RequestMapping("/{controller}/{action}")
	public String request(@PathVariable String controller, @PathVariable String action) {
		
		return "You requested the action '" + action + "' from the controller '" + controller + "'."; 
	}
	
	@RequestMapping("/**")
	public String requestVague(HttpServletRequest request) {
		repository.save(new Request(request.getRequestURI(), request.getQueryString(), request.getRemoteHost(), request.getLocalAddr()));
		return "Super vague"; 
	}
	

}
