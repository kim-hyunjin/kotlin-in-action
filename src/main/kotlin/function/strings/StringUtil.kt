package function.strings

import java.lang.StringBuilder

/*
* 기존 자바 API를 재작성하지 않고 코틀린이 제공하는 여러 편리한 기능을 사용할 수 있게 해주는 것이 확장함수다.
* 확장함수는 어떤 클래스의 멤버 메소드인 것처럼 호출할 수 있지만 그 클래스의 밖에 선언된 함수다.
* 함수 이름 앞에 그 함수가 확장할 클래스의 이름을 덧붙이기만 하면 된다.
*
* 클래스 이름을 수신 객체 타입(receiver type)이라 부르며, 호출되는 대상이 되는 값(객체)을 수신 객체(receiver object)라고 부른다.
*
* 자바, 코틀린, 그루비 등 다른 JVM 언어로 작성된 클래스라면 확장할 수 있다.
*
* 확장함수가 캠슐화를 깨지는 않는다. private, protected 멤버는 사용할 수 없다.
*
* 호출하는 쪽에서는 확장함수와 멤버 메소드를 구분할 수 없다. 호출하는 쪽에서 그 구분이 중요한 경우도 거의 없다.
* */
fun String.lastChar(): Char = this[this.length - 1] // this가 수신객체.

/*
* 내부적으로 확장 함수는 수신 객체를 첫 번째 인자로 받는 정적 메소드다.
*
* 자바에서는 다음과 같이 호출할 수 있다. (파일명이 StringUtil.kt인 경우)
* char c = StringUtilKt.lastChar("Java")
* */


fun <T> Collection<T>.joinToStringExtension(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) { // "this"는 수신객체. 여기서는 T 타입의 원소로 이뤄진 컬렉션이다.
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 확장 함수는 단지 정적 메소드 호출에 대한 문법적인 편의(syntatic sugar)일 뿐이다.
fun Collection<String>.join(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToString(separator, prefix, postfix)