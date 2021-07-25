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

/**
 * 공변성: 하위 타입 관계를 유지
 * 코틀린에서 제네릭 클래스가 타입 파라미터에 대해 공변적임을 표시하려면 앞에 out을 넣어야한다.
 * 타입 파라미터를 공변적으로 만들면 함수 정의에 사용한 파라미터 타입과 타입 인자이 타입이 정확히 일치하지 않더라도 그 클래스의 인스턴스를 함수 인자나 반환값으로 사용할 수 있다.
 */
interface Producer<out T> {
    fun produce(): T
}

open class Animal {
    fun feed() {}
}

class Cat: Animal() {
    fun cleanLitter() {}
}

/**
 * 무공변 컬렉션
 */
class Herd<T: Animal> : ArrayList<T>() {
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
    }
    // feedAll(cats) // 타입 불일치 오류
}

/**
 * 공변적 컬렉션
 */
class Herd2<out T: Animal>(animals: List<T>) {
    private val _animals = animals

    val size: Int get() = _animals.size
    operator fun get(i: Int): T {
        return _animals[i]
    }

//    fun add(animal: T) {} // T는 out 위치에서만 쓰일 수 있다.
}

fun feedAll(animals: Herd2<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

fun takeCareOfCats(cats: Herd2<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
    }
     feedAll(cats)
}

/**
 * 타입 파라미터에 붙은 out 키워드는 다음 두 가지를 함께 의미한다.
 * - 공변성: 하위 타입 관계가 유지된다. Producer<Cat>은 Producer<Animal>의 하위타입이다.
 * - 사용 제한: T를 아웃 위치에서만 사용할 수 있다.
 *
 * 변성은 코드에서 위험할 여지가 있는 메소드를 호출할 수 없게 만듦으로써
 * 제네릭 타입의 인스턴스 역할을 하는 클래스 인스턴스를 잘못 사용하는 일이 없게 방지하는 역할을 한다.
 *
 * 변성 규칙은 클래스 외부의 사용자가 클래스를 잘못 사용하는 일을 막기 위한 것으로
 * 클래스 내부 구현에는 적용되지 않는다.(private 메소드의 파라미터는 인도 아니고 아웃도 아니다)
 */

fun main() {
    printContents(listOf("abc", "bac"))

    val strings = mutableListOf("abc", "bac")
//    addAnswer(strings) // 코틀린에서는 리스트의 변경 가능성에 따라 적절한 인터페이스를 선택하면 안전하지 못한 함수 호출을 막을 수 있다.
//    println(strings.maxBy { it.length })
}