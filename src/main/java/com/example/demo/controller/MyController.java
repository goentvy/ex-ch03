package com.example.demo.controller;

import com.example.demo.service.MyService;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MyService;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class MyController {
	
	private final MyService myService;
	
	public MyController(MyService myService) {
		this.myService = myService;
	}
	
//	@GetMapping("/")
//	public String getMethodName() {
//		return myService.sayHello("안녕");
//	}
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
    @GetMapping("/page75")
    public String page75() { return myService.page75(); }
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
	@GetMapping("/page90")
	public String page90() {
		return myService.page90();
	}
	@GetMapping("/page91")
	public String page91() {
		return myService.page91();
	}
	@GetMapping("/page92")
	public String page92() {
		return myService.page92();
	}
	@GetMapping("/page93")
	public String page93() {
		return myService.page93();
	}
	@GetMapping("/page94")
	public String page94() {
		return myService.page94();
	}
	@GetMapping("/page96")
	public  Optional<String> page96() {
		return myService.page96();
	}
	@GetMapping("/page97")
	public  Optional<String> page97() {
		return myService.page97();
	}
	@GetMapping("/page98")
	public  Optional<String> page98() {
		return myService.page98();
	}
}
