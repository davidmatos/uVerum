package pt.inescid.microVerum.Bubble.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletInputStream;
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
	public Ping ping(@RequestParam(value = "name", defaultValue = "World") String name) {

		return new Ping(name);
	}

	@RequestMapping("/{controller}/{action}")
	public String request(@PathVariable String controller, @PathVariable String action) {

		return "You requested the action '" + action + "' from the controller '" + controller + "'.";
	}

	@RequestMapping("/**")
	public String requestVague(HttpServletRequest request) {

		// String httpHeader = "";
		// while(request.getHeaderNames().hasMoreElements()) {
		// String name=request.getHeaderNames().nextElement();
		//
		// httpHeader += request.getHeader(name)
		// }

		try {
			ServletInputStream is = request.getInputStream();
			String body = "";
			byte[] line = new byte[1000];
			while (is.readLine(line, 0, 1000)>0) {
				body += new String(line) + "\n";
			}

			repository.save(new Request(request.getRequestURI(), body, request.getRemoteHost(), request.getLocalAddr(),
					"1", request.getMethod(), request.getParameterMap()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Super vague";
	}

}
