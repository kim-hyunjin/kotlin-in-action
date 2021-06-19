package function

import function.strings.joinToString


fun main() {
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
}

