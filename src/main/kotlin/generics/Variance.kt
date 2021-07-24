package generics

/**
 * 변성: 제네릭과 하위 타입
 */
fun printContents(list: List<Any>) {
    println(list.joinToString())
}

fun addAnswer(list: MutableList<Any>) {
    list.add(42)
}

fun main() {
    printContents(listOf("abc", "bac"))

    val strings = mutableListOf("abc", "bac")
//    addAnswer(strings) // 코틀린에서는 리스트의 변경 가능성에 따라 적절한 인터페이스를 선택하면 안전하지 못한 함수 호출을 막을 수 있다.
//    println(strings.maxBy { it.length })
}