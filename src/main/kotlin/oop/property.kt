package oop

/**
 * 코틀린에서는 인터페이스에 추상 프로퍼티 선언을 넣을 수 있다.
 *
 * 아래는 User3 인터페이스를 구현하는 클래스가 nickname의 값을 얻을 수 있는 방법을 제공해야 한다는 뜻이다.
 */
interface User3 {
    val nickname: String
}

class PrivateUser(override val nickname: String): User3 // 주 생성자에서 User3의 추상 프로퍼티 구현

class SubscribingUser(val email: String): User3 {
    override val nickname: String
        get() = email.substringBefore('@') // 커스텀 게터로 프로퍼티 설정 필드에 값을 저장X 매번 계산해서 반환
}

class FacebookUser(val accountId: Int): User3 {
    override val nickname = getFacebookName(accountId) // 프로퍼티 초기화식
}

fun getFacebookName(accountId: Int): String {
    return "nicknameFindBy$accountId"
}

/**
 * 추상 프로퍼티뿐 아니라 게터와 세터가 있는 프로퍼티를 선언할 수 있다.
 * 아래의 경우 하위 클래스는 추상 프로퍼티인 email을 반드시 오버라이드해야 한다.
 * 반면 nickname은 오버리아드하지 않고 상속할 수 있다.
 */
interface User4 {
    val email: String
    val nickname: String
        get() = email.substringBefore('@')
}

fun main() {
    println(PrivateUser("test@kotlinlang.org").nickname)
    println(SubscribingUser("test@kotlinlang.org").nickname)
}