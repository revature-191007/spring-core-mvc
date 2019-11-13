package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.models.CustomResponse;
import com.revature.services.HelloService;

/*
 * Stereotype Annotations:
 * 	@Controller
 * 	@Service
 * 	@Repository
 * 	@RestController (@Controller + @ResponseBody)
 * 
 * Context Mapping Annotations:
 * 	@RequestMapping - General mapping
 *  @GetMapping
 *  @PostMapping
 *  @PutMapping
 *  @PatchMapping
 *  @DeleteMapping
 *  etc...
 *  
 * Dynamic Mapping
 * 	@PathVariable - Maps parts of URI to variables
 * 	@PathParam - Maps query parameters (?key=value) to variables
 *  @ExceptionHandler - Maps unhandled exception to handler
 *  @RequestBody - Maps body of HTTP request to a variable
 *  
 * Response Configuring
 * 	@ResponseBody - Designates that return value of controller
 * 		method is the body of the response.
 *	@ResponseStatus - Indicates the status code of the response
 */

@RestController
// Delegates all contexts with an initial 'hello' to this controller
@RequestMapping("hello")
public class HelloController {

	HelloService helloService;

	// Maps this method as a handler for GET requests with the
	// provided context
	@GetMapping("")
	// Indicates that the return value of the method is the body
	// of the response
//	@ResponseBody
	public String sayHello() {
		System.out.println("Request received");
		return helloService.getHello();
	}

	@GetMapping("/{id:[0-9]+}")
	public CustomResponse getResponse(@PathVariable int id) {
		return helloService.getResponseById(id);
	}
	
	@GetMapping("/{firstName}/{lastName}")
	public String sayHelloTo(@PathVariable String firstName, @PathVariable String lastName) {
		return "Hello " + firstName + " " + lastName + "!";
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomResponse createCustomResponse(
			@RequestBody CustomResponse customResponse) {
		return helloService.create(customResponse);
	}

	@Autowired
	public HelloController(HelloService helloService) {
		super();
		this.helloService = helloService;
	}
	
	/*
	 * @ExceptionHandler will bind this method as a handler for
	 * exceptions of the type specified in the annotation.
	 * ResponseEntity represents a full response abstraction
	 * which allows you to specify status codes, a body, etc.
	 */
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> 
		handleHttpClientError(HttpClientErrorException e) {
		return ResponseEntity
				.status(e.getStatusCode())
				.body(e.getMessage());
	}
}

