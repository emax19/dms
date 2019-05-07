package com.dms;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyAdviser {

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<String> handle(Exception ex) {
		return ResponseEntity.badRequest().body("oops");
	}

}
