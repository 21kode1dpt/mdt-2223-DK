package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@SpringBootApplication
@ComponentScan("com.example.demo")
@RestController
@RequestMapping("/api/old")
public class DemoApplication {

	private String privateProperty = "unset";

	// @RequestMapping("/")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String sayHello() {

		return "Test";
	}

	@GetMapping("/helloTo/{name}")
	public String sayHelloTo(@PathVariable String name) {

		privateProperty = name;
		return "Hello: " + name;
	}

	// @PostMapping("/somePath") equivalent to
	// @RequestMapping(value = "/somePath", method = RequestMethod.POST)

	@GetMapping("/helloAgain")
	public String sayHelloAgain() {

		return "Test again";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/setProperty")
	public String setSomething(@RequestParam String something) {

		this.privateProperty = something;

		return "";

	}

	@GetMapping("/property")
	public String getSomething() {

		return this.privateProperty;

	}

	@Operation(summary = "Set the current value of a property")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created the new value", content = { @Content })
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/property/{newProperty}")
	public String setSomethingNew(@PathVariable String newProperty) {

		this.privateProperty = newProperty;

		return newProperty;

	}

}