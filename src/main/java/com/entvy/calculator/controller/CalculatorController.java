package com.entvy.calculator.controller;

import org.springframework.web.bind.annotation.RestController;

import com.entvy.calculator.dto.CalculationRequest;
import com.entvy.calculator.dto.CalculationResponse;
import com.entvy.calculator.service.CalculatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("calculator")
public class CalculatorController {
	
//	사용법1: Autowired 어노테이션을 사용하는 방법 - SpringBoot (필드주입방식 가독성 떨어짐 Mock 주입 어려움)
	@Autowired
//	CalculatorService calculatorService;
	
//	사용법2: new 생성자 사용하는 방법 - Java (의존성 관리, 테스트, 유지보수 힘듬)
//	CalculatorService calculatorService = new CalculatorService();
	
//	권장되는 생성자 주입 방식 (mock 객체 주입 쉬움 - 테스트 용이)
	CalculatorService calculatorService;
	
	public CalculatorController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}
	
	@PostMapping("/calculate")
	public CalculationResponse calculate(@RequestBody CalculationRequest request) {
		String result = calculatorService.calculate(
				request.getNum1(),
				request.getNum2(),
				request.getOperation());
				
		return new CalculationResponse(
				request.getNum1(),
				request.getNum2(),
				request.getOperation(),
				result);
	}
	
	
	@GetMapping("/add")
	public Record add(@RequestParam double num1, @RequestParam double num2) {
		String result = calculatorService.add(num1, num2);
		return new CalculationResponse(num1, num2, "ADD", result);
	}
	@GetMapping("/subtract")
	public Record subtract(@RequestParam double num1, @RequestParam double num2) {
		String result = calculatorService.subtract(num1, num2);
		return new CalculationResponse(num1, num2, "SUBTRACT", result);
	}
	@GetMapping("/multiply")
	public Record multiply(@RequestParam double num1, @RequestParam double num2) {
		String result = calculatorService.multiply(num1, num2);
		return new CalculationResponse(num1, num2, "MULTIPLY", result);
	}
	@GetMapping("/divide")
	public Record divide(@RequestParam double num1, @RequestParam double num2) {
		String result = calculatorService.divide(num1, num2);
		return new CalculationResponse(num1, num2, "DIVIDE", result);
	}
}
