package com.dms;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRest {

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("hello world! !");
	}

	@GetMapping("/bad-hello")
	public ResponseEntity<String> badHello() {
		throw new RuntimeException();
	}

}
