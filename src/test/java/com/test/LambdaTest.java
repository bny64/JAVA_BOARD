package com.test;

import java.util.function.IntBinaryOperator;

//클래스
class Calculator_class {
	Calculator_class(){};//생성자
	public int calc(int n) {
		return n + 1;
	}
}

//인터페이스
interface Calculator_interface{
	//람다식에서 생성되는 "익명구현객체는 기반이 되는 interface의 타입을 갖는다" 이것을 타켓타입이라고 한다.
	//람다식으로 구현할 calc는 Calculator_interface의 추상메서드이므로 람다식의 타겟타입은 Calcuator_interface
	//람다식 사용 인터페이스는 2개 이상의 추상 메서드를 가지면 안된다.
	//@FunctionInterface는 이것을 명시적으로 선언하고 강제하는 애노테이션이다. 이런식으로 선언된 인터페이스를 람다식의 "함수적 인터페이스"라 한다.
	//람다식의 매개변수는 final 키워드가 붙지 않더라도 상수로 취급.
	int calc(int n);
}

class MyMath {
	public int myMax(int x, int y) {return x > y ? x : y;};
}

public class LambdaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int n = 2;
//		Calculator_class cal = new Calculator_class();
//		
//		System.out.println(cal.calc(n));
		
		int n = 2;
		Calculator_interface cal = (m) -> m + 1;
		System.out.println(cal.calc(n));
		
		//3가지 메소드 레퍼런스
		//1. Math.max()는 정적(static) 메소드 참조방식이 된다. 만약 인스턴스 메소드일 경우 인스턴스를 생성한 후 인스턴스::메소드명을 사용할 수 있다. 아래 참고
		MyMath myMath = new MyMath();
		IntBinaryOperator op = myMath::myMax;
		
		System.out.println(op.applyAsInt(3, 7));
		//2. 매개변수의 메소드 참조 방식
		
	}

}
