package com.rishavdas.restfullapi.restfullapi.HelloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	
	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String helloWorld()
	{
		return "HELLO WORLD";
		
	}
	
	@GetMapping(path = "/helloworld")
	public HelloWorld helloWorldBean()
	{
		return new HelloWorld("Hello World");
	}
	
	@GetMapping(path = "/hello/{name}")
	public HelloWorld helloWorldName(@PathVariable String name)
	{
		return new HelloWorld("Hello "+name);
	}
}
