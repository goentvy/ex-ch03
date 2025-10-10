package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class MyController {
	
	private final MyService myService;
	
	public MyController(MyService myService) {
		this.myService = myService;
	}
	
	@GetMapping("/")
	public String getMethodName() {
		return myService.sayHello("안녕");
	}
	
	@GetMapping("/page70")
	public String page70() {
		return myService.page70();
	}
	
	@GetMapping("/page72")
	public int[] page72() {
		return myService.page72();
	}
	
	@GetMapping("/page73")
	public int[] page73() {
		return myService.page73();
	}
	
	@GetMapping("/page76")
	public Object page76() {
		return myService.page76();
	}
	
	@GetMapping("/page77")
	public String page77() {
		return myService.page77();
	}
	
	@GetMapping("/page79")
	public String page79() {
		return myService.page79();
	}
	@GetMapping("/page79_2")
	public String page79_2() {
		return myService.page79_2();
	}
	@GetMapping("/page89")
	public String page89() {
		return myService.page89();
	}
}
