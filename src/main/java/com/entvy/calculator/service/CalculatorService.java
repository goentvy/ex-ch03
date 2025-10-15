package com.entvy.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
	
	public String calculate(double num1, double num2, String operation) {
		return switch(operation.toUpperCase()) {
			case "ADD" -> add(num1, num2);
			case "SUBTRACT" -> subtract(num1, num2);
			case "MULTIPLY" -> multiply(num1, num2);
			case "DIVIDE" -> divide(num1, num2);
			default -> throw new IllegalArgumentException("명시하지않은 연산자 사용입니다.");
		};
	}
	public String add(double num1, double num2) {
		String result = String.valueOf(num1 + num2);
		return result;
	}
	
	public String subtract(double num1, double num2) {
		String result = String.valueOf(num1 - num2);
		return result;
	}
	
	public String multiply(double num1, double num2) {
		String result = String.valueOf(num1 * num2);
		return result;
	}
	
	public String divide(double num1, double num2) {
		if(num2 == 0) throw new ArithmeticException("0으로 나눌수 없음");
		String result = String.valueOf(num1 / num2);
		return result;
	}
}
