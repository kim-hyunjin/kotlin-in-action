package type_system

import java.lang.IllegalArgumentException

/**
 * 널 가능성(nullability)은  NullPointerException 오류를 피할 수 있게 돕기 위한 코틀린 타입 시스템의 특성이다.
 * 코틀린을 비롯한 최신 언어에서 null에 대한 접근 방법은 가능한 한 이 문제를 실행 시점에서 컴파일 시점으로 옮기는 것이다.
 * 널이 뒬 수 있는지 여부를 타입 시스템에 추가함으로써 컴파일러가 여러 가지 오류를 컴파일 시 미리 감지해서 실행 시점에 발생할 수 있는 예외의 가능성을 줄일 수 있다.
 */

/**
 * 널이 될 수 있는 타입
 */
fun strLen(s: String) = s.length // null이 될 수 있는 인자를 넘기는 것이 금지. 널이 오면 컴파일 시 오류

// 어떤 타입이든 타입 이름 뒤에 물음표를 붙이면 그 타입의 변수나 프로퍼티에 null 참조를 저장할 수 있다는 뜻이다.
fun strLenSafe(s: String?) = s?.length

/**
 * 실행 시점에 널이 될 수 있는 타입이나 널이 될 수 없는 타입의 객체는 같다.
 * 널이 될 수 있는 타입은 널이 될 수 없는 타입을 감싼 래퍼 타입이 아니다.
 * 모든 검사는 컴파일 시점에 수행된다.
 * 따라서 코틀린에선 널이 될 수 있는 타입을 처리하는 데 별도의 실행 시점 부가 비용이 들지 않는다.
 */

/**
 * ?. 연산자
 * ?.은 null 겸사와 메소드 호출을 한번의 연산으로 수행한다.
 * s?.toUpperCase()는 if (s != null) s.toUpperCase() else null과 같다.
 *
 * ?. 호출의 결과 타입도 널이 될 수 있는 타입이라는 시살에 유의하라.
 */

class Employee(val name: String, val manager: Employee?)
fun managerName(employee: Employee): String? = employee.manager?.name

class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

// 안전한 호출 연산자를 연쇄해 사용.
// 엘비스 연산자(?:) - null 대신 사용할 디폴트 값을 지정할 때 사용할 수 있는 연산자
fun Person.countryName(): String = company?.address?.country ?: "Unknown"

fun printShippingLabel(person: Person) {
    val address = person.company?.address ?: throw IllegalArgumentException("No address")
    with (address) {
        println(streetAddress)
        println("$zipCode $city $country")
    }
}

/**
 * 널 아님 단언 !!
 * 느낌표를 이중으로 사용하면 어떤 값이든 널이 될 수 없는 타입으로 바꿀 수 있다.
 * null에 대해 !!를 적용하면 NPE가 발생한다.
 * !!를 널에 대해 사용해서 발생하는 예외의 stack trace에는
 * 어떤 파일의 몇 번째 줄인지에 대한 정보는 들어있지만
 * 어떤 식에서 예외가 발생했는지에 대한 정보는 들어있지 않다.
 * 따라서 !!단언문을 한 줄에 함께 쓰는 일을 피하라.
 */

/**
 * let 함수
 * let 함수를 안전한 호출 연산자와 함께 사용하면 원하는 식을 평가해
 * 결과가 널인지 검사한 다음 그 결과를 변수에 넣는 작업을 간단한 식을 사용해 한꺼번에 처리할 수 있다.
 *
 * let을 사용하는 가장 흔한 용례는 널이 될 수 있는 값을 널이 아닌 값만 인자로 받는 함수에 넘기는 경우다.
 */
fun sendEmailTo(email: String) {

}

fun main() {
    val ceo = Employee("Da Boss", null)
    val developer = Employee("Bob Smith", ceo)
    println(managerName(developer))
    println(managerName(ceo))

    val person = Person("Dmitry", null)
    println(person.countryName())

    val address = Address("Elsestr. 47", 80687, "Munich", "Germany")
    val jetbrains = Company("JetBrains", address)
    val person2 = Person("Dmitry", jetbrains)
    printShippingLabel(person2)

    var email: String? = "yole@example.com"
    email?.let { sendEmailTo(it) }
    email = null
    email?.let { sendEmailTo(it) } // email이 null이기 때문에 아무일도 일어나지 않는다.

}
