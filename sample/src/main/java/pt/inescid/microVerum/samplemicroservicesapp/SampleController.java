package pt.inescid.microVerum.samplemicroservicesapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@RequestMapping("/sample/ping")
	public Ping ping(@RequestParam(value = "name", defaultValue = "World") String name) {

		return new Ping(name);
	}

}
