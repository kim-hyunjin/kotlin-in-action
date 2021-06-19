package function

import function.strings.join
import function.strings.joinToString
import function.strings.joinToStringExtension
import function.strings.lastCharProperty
import java.lang.StringBuilder
import function.strings.lastChar as last // as 키워드를 사용하면 임포트한 클래스나 함수를 다른 이름으로 부를 수 있다.

/* varargs
*   코틀린의 가변 길이 인자(varargs)도 자바와 비슷하다. 타입 뒤에 ...을 붙이는 대신 파라미터 앞에 varargs 변경자를 붙인다.
*   fun listOf<T>(varargs values: T): List<T> {...}
*
*   배열에 들어있는 원소를 가변 길이 인자로 넘길 때
*   자바에서는 배열을 그냥 넘기면 되지만
*   코틀린에서는 명시적으로 풀어서 각 원소가 인자로 전달되게 해야 한다.
*   스프레드 연산자(*)를 사용하면 된다.
*
* */
fun main(args: Array<String>) {
    val argsList = listOf("args", *args)
    println(argsList)
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three") // 여기서 to는 특별한 키워드가 아니라 일반함수다.
    println(set.javaClass) // getClass()에 해당하는 코틀린 코드다.
    println(list.javaClass)
    println(map.javaClass)
    // 코틀린은 자신만의 컬렉션 기능을 제공하지 않는다. - 기존 자바의 컬렉션을 활용
    // 표준 자바 컬렉션을 활용하면 자바 코드와 상호작용하기가 훨씬 더 쉽기 때문이다.

    println(joinToString(list, "; ", "(", ")"))

    // 코틀린은 인자에 이름을 붙일 수 있다.
    // 인자 중 하나라도 이름을 병시하고 나면 그 뒤에 오는 모든 인자는 이름을 꼭 명시해야 한다.
    // 불행히도 자바로 작성한 코드를 호출할 때는 이름 붙인 인자를 사용할 수 없다.
    println(joinToString(list, separator = " ", prefix = " ", postfix = "."))
    println(joinToString(list))
    println(joinToString(list, prefix = "#"))

    println("Kotlin".last())
    println(list.joinToStringExtension(separator = "; ", prefix = "(", postfix = ")")) // joinToString을 마치 클래스의 멤버인 것처럼 호출할 수 있다.
    println(listOf("one", "two", "three").join(" "))
    val sb = StringBuilder("Kotlin?")
    sb.lastCharProperty = '!'
    println(sb)
}

