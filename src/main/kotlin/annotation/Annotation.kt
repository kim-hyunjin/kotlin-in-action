package annotation

/**
 * 애노테이션과 리플렉션을 사용하면 미리 알지 못하는 임의의 클래스를 다룰 수 있다.
 * 애노테이션을 사용하면 라이브러리가 요구하는 의미를 클래스에게 부여할 수 있고,
 * 리플렉션을 사용하먄 실행 시점에 컴파일러 내부 구조를 분석할 수 있다.
 *
 * 코틀린의 애노테이션 문법
 * - 클래스를 애노테이션 인자로 지정할 때는 @MyAnnotation(MyClass::class) 처럼 ::class를 클래스 이름 뒤에 넣어야 한다.
 * - 다른 애노테이션을 인자로 지정할 때는 인자로 들어가는 애노테이션 앞에 @룰 넣지 않는다.
 * - 배열을 인자로 지정하려면 @RequestMapping(path=arrayOf("/foo", "/bar")) 처럼 arrayOf 함수를 사용한다.
 *
 * 애노테이션 인자를 컴파일 시점에 알 수 있어야 한다. 따라서 임의의 프로퍼티를 인자로 지정할 수는 없다.
 * 프로퍼티를 애노테이션 인자로 사용하려면 그 앞에 const 변경자를 붙여야 한다.
 * 컴파일러는 const가 붙은 프로퍼티를 컴파일 시점 상수로 취급한다.
 * const val TEST_TIMEOUT = 100L
 * @Test(timeout=TEST_TIMEOUT) fun testMethod() {}
 *
 * const 가 붙은 프로퍼티는 파일의 맨 위나 object 안에 선언해야 하며,
 * 원시 타입이나 String 으로 초기화해야만 한다.
 */