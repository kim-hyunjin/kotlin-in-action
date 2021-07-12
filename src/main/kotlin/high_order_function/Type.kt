package high_order_function

import java.lang.StringBuilder

/**
 * 고차 함수는 다른 함수를 인자로 받거나 함수를 반환하는 함수다.
 * 코틀린에서는 람다나 함수 참조를 사용해 함수를 값으로 표현할 수 있다.
 * 따라서 고차 함수는 람다나 함수 참조를 인자로 넘길 수 있거나
 * 람다나 함수 참조를 반환하는 함수다.
 *
 * list.filter { x > 0 }
 */

/**
 * 함수 타입
 */
val sum: (Int, Int) -> Int = {x: Int, y: Int -> x + y}
val action: () -> Unit = { println(42) } // Unit 타입은 의미 있는 값을 반환하지 않는 함수 반환 타입에 쓰는 특별한 타입
var canReturnNull: (Int, Int) -> Unit? = {x, y -> null}
var funOrNull: ((Int, Int) -> Int)? = null

/**
 * 인자로 받은 함수 호출
 */
fun twoAndThree(operation: (Int, Int) -> Int) {
    val result = operation(2, 3)
    println("The Result is $result")
}

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) sb.append(element)
    }
    return sb.toString()
}

fun main() {
    twoAndThree { a, b -> a + b}
    twoAndThree {a, b -> a * b}
}

/**
 * 자바에서 코틀린 함수 타입 사용
 * 컴파일된 코드 안에서 함수 타입은 일반 인터페이스로 바뀐다.
 * 즉 함수 타입의 변수는 FunctionN 인터페이스를 구현하는 객체를 저장한다.
 * 코틀린 표준 라이브러리는 함수 인자의 개수에 따라
 *      Function0<R> : 인자가 없는 함수
 *      Function1<P1, R> : 인자가 하나인 함수
 * 등의 인터페이스를 제공한다.
 * 각 인터페이스에는 invoke 메소드 정의가 하나 들어 있다.
 *
 * 함수 타입의 변수는 인자 개수에 따라 적당한 FunctionN 인터페이스를 구현하는 클래스 인스턴스를 저장하며,
 * invoke 메소드 본문에는 람다의 본문이 들어간다.
 *
 * // 코틀린선언
 * fun processTheAnswer(f: (Int) -> Int) {
 *      println(42)
 * }
 *
 * // 자바에서 사용
 * processTheAnswer(number -> number + 1);
 *
 * // 자바8 이전
 * processTheAnswer(
 *      new Function1<Integer, Integer>() {
 *          @override
 *          public Integer invoke(Integer number) {
 *              System.out.println(number);
 *              return number + 1;
 *          }
 *      });
 *
 */