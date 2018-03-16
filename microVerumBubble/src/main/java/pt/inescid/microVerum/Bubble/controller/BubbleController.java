package pt.inescid.microVerum.Bubble.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BubbleController {
	
	
	@RequestMapping("/ping")
	public String ping(@RequestParam(value="name", defaultValue="World") String name) {
		
		return "Hello, " + name;
	}
	

}
