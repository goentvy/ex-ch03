package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public interface Car { public String getColor(); }

    public class Sonata implements Car {
        public Sonata() {
            log.info("=== 출고 === Sonata");
        }

        @Override
        public String getColor() {
            return "=== 색상:RED === Sonata";
        }
    }

    public class K5 implements Car {
        public K5() {
            log.info("=== 출고 === K5");
        }

        @Override
        public String getColor() {
            return "=== 색상:Blue === K5";
        }
    }
    // 3-2-4 다형성
    public String page75() {
        Car car1 = new Sonata();
        String re1 = car1.getColor();
        Car car2 = new K5();
        String re2 = car2.getColor();

        String res = re1 + "<br>" + re2 + "<br>" + "차 두대를 출고하였음";
        return res;
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

        for (int i = 0; i < list.size(); i++) {
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
	
	// 3-3-4 스트림 API
	public String page90() {
		Integer[] integerArray = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		List<Integer> list = Arrays.asList(integerArray);
		
		List evenList = list.stream()
				.filter(value -> value % 2 == 0).collect(Collectors.toList());
		
		evenList.stream().forEach(value -> log.info(value.toString()));
		return evenList.toString();
	}
	
	// 3-3-5 forEach() - 반복시 하나씩 순회
	public String page91() {
		Integer[] integerArray = new Integer[] {1,2,3,4,5};
		List<Integer> list = Arrays.asList(integerArray);
		list.stream().forEach(value -> log.info(value.toString()));
		return list.toString();
	}
	
	// 3-3-6 filter() - 조건에 맞는 요소만 출력
	public String page92() {
		Integer[] integerArray = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		List<Integer> list = Arrays.asList(integerArray);
		List evenList = list.stream()
				.filter(value -> value % 3 == 0).collect(Collectors.toList());
		evenList.stream().forEach(value -> log.info(value.toString()));
		return evenList.toString();
	}
	
	// 3-3-7 distinct() - 중복제거
	public String page93() {
		Integer[] integerArray = new Integer[] {1,1,1,1,2,2,2,3,3,4};
		List<Integer> list = Arrays.asList(integerArray);
		List<Integer> distinctList = list.stream().distinct().toList();
		distinctList.stream().forEach(value -> log.info(value.toString()));
		return distinctList.toString();
	}

	// 3-3-8 map() - 컬렉션의 요소들에 특정 연산을 적용한 새로운 스트림을 만듬.
	public String page94() {
		String[] lowercaseArray = new String[] {"public", "static", "void"};
		List<String> lowercaseList = Arrays.asList(lowercaseArray);
		List<String> uppercaseList = lowercaseList.stream()
				.map(value -> value.toUpperCase()).toList();
		uppercaseList.stream().forEach(value -> System.out.println(value));
		return uppercaseList.toString();
	}
		
	// 3-3-12 값을 포함한 Optional 반환
	private static Optional<String> getSomeString() {
		return Optional.ofNullable("public static void");
	}
	
	public Optional<String> page96() {
		Optional<String> isThisNull = getSomeString();
		
		isThisNull.ifPresent(str -> System.out.println(str.toUpperCase()));
		return isThisNull;
	}
		
	// 3-3-13 안티패턴 - 비효율적이거나 생산적이지 않은 패턴
	// 코드 가독성을 떨어뜨리거나 성능상 심각한 손실 유발하는 코드패턴
	public Optional<String> page97() {
		Optional<String> str = getSomeString();
		
		if(str.isPresent()) {
			System.out.println(str.get().toUpperCase());
		}
		return str;
	}
		
	// 3-3-14 안티 패턴 해결
	public Optional<String> page98() {
        Optional<String> str = getSomeString();

        str.ifPresent((string) -> System.out.println(string.toUpperCase()));

        return str;
    }
}
