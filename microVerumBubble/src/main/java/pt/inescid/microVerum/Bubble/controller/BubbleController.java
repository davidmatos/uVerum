package pt.inescid.microVerum.Bubble.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.inescid.microVerum.Bubble.domain.Ping;


@RestController
public class BubbleController {
	
	
	@RequestMapping("/ping")
	public Ping ping(@RequestParam(value="name", defaultValue="World") String name) {
		
		return new Ping(name);
	}
	
	@RequestMapping("/{controller}/{action}")
	public String request(@PathVariable String controller, @PathVariable String action) {
		
		return "You requested the action '" + action + "' from the controller '" + controller + "'."; 
	}
	

}
