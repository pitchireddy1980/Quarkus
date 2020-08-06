package org.acme.quarkus.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/spring-hello")
	public String hello() {
		return "hello spring web";
	}

}
