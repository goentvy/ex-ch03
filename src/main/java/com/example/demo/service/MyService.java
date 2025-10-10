package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {
	
	private static final Logger log = LoggerFactory.getLogger(MyService.class);
	
	public String sayHello(String hi) {
		String msg = hi + " 좋은날";
		
		System.out.println(msg);
		log.info(msg);
		return msg;
	}
	
	// 3-2-1 조건문 if else
	public String page70() {
		String result = "";
		
		int number = 1;
		if(number == 1) {
			result = "if 블록입니다.";
		} else if (number == 2) {
			result = "else if 블록입니다.";
		} else {
			result = "else 블록입니다.";
		}
		log.info(result);
		String msg = "ex70 결과는: " + result;
		return msg;
	}
	
	// 3-2-2 for문
	public int[] page72() {
		int[] array = {1,2,3,4,5};
		
		for(int i = 0; i < array.length; i++) {
			log.info("i = " + array[i]);
		}
		return array;
	}
	
	// 3-2-3 while문
	public int[] page73() {
		int [] array = {1,2,3,4,5};
		int i = 0;
		
		while(i < array.length) {
			log.info("i = " + array[i]);
			i++;
		}
		return array;
	}
	
	// 3-2-5 List 컬렉션
	public Object page76() {
		List list = new ArrayList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		
		list.get(1);
		
		return list.get(1);
	}
	
	// 3-2-6 ArrayList 출력
	public String page77() {
		List list = new ArrayList<String>();
		
		list.add("public");
		list.add("static");
		list.add("void");
		
		for(int i = 0; i < list.size(); i++) {
			log.info(list.get(i).toString()); // 순환하여 확인
		}
		log.info(list.toString()); // arrayList 확인
		
		list.remove(1); // arrayList 1번 index 제거
		int voidIndex = list.indexOf("void"); // void index 찾기 ( type : int )
		
		String result = "void의 index = " + voidIndex;
		
		return result;
	}
	
	// 3-2-7 동일성 비교
	public String page79() {
		String str1 = new String("is same?");
		String str2 = new String("is same?");
		boolean result = (str1 == str2);
		
		log.info(String.valueOf(result));
		
		return String.valueOf(result);
	}
	
	// 3-2-8 동등성 비교
	public String page79_2() {
		String str1 = new String("is same?");
		String str2 = new String("is same?");
		boolean result = str1.equals(str2);
		
		log.info("str1.equals(str2) = " + String.valueOf(result));
		
		return String.valueOf(result);
	}
	
	// 3-3-1 람다표현식
	public void page85() {
		List list = new ArrayList<String>();
		
		list.add("public");
		list.add("static");
		list.add("void");
		
		// 익명 클래스 코드
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});
		
		// 람다 표현식 코드
		list.sort((Comparator<String>) (str1, str2) -> str1.compareTo(str2));
		
//		파라미터 1개, 명령문 1개인 경우
//		var1 -> System.out.println(var1);
		
//		파라미터 2개, 명령문 2개 이상인 경우
//		var1 -> {
//			var1 = var1 + 1;
//			System.out.println(var1);
//			retrun var1;
//		}

//		파라미터 2개이상, 명령문 1개인 경우
//		(var1, var2) -> System.out.println(var1 + var2);
		
//		파라미터 2개이상, 명령문 2개 이상인 경우
//		(var1, var2) -> {
//			System.out.println(var1);
//			System.out.println(var2);
//		}
	}
	
	// 3-3-2 스트림 API
	public void page88() {
		List list = new ArrayList<String>();
		
		list.add("public");
		list.add("static");
		list.add("void");
		
		list.stream().forEach(str -> System.out.println(str));
	}
	
	// 3-3-3 list for문
	public String page89() {
		Integer[] integerArray = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		List<Integer> list = Arrays.asList(integerArray);
		
		List evenList = new ArrayList<Integer>();
		
		for(int i=0; i < list.size(); i++) {
			Integer number = list.get(i);
			if(number % 2 == 0) {
				evenList.add(number);
			}
		}
		
		for(int i = 0; i < evenList.size(); i++) {
			log.info(evenList.get(i).toString());
		}
		
		return evenList.toString();
	}
}
