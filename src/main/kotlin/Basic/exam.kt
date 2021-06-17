package Basic

fun main(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin"
    // 문자열 템플릿
    // - StringBuilder를 사용해 효율적으로 연산한다.
    // 존재하지 않는 변수를 문자열 안에서 사용하면 컴파일 오류가 발생한다.
    // 복잡한 식도 ${} 를 통해 문자열 템플릿 안에 넣을 수 있다.
    // 변수만 사용하더라도 ${}안에 넣어 사용하는 것이 좋다.
    // - 정규식 등을 통해 일괄 변환할 때나, 코드를 사람이 읽을 때 등
    println("Hello, $name!")

    // 중괄호로 둘러싼 식 안에서 큰 따음표를 사용할 수도 있다.
    println("Hello, ${if (args.size > 0) args[0] else "someone"}!")
}

/*
* 코틀린에서 if는 식(expression)이지 문(statement)이 아니다. 식은 값을 만들어 내며
* 다른 식의 하위 요소로 계산에 참여할 수 있다.
* 반면, 문은 자신을 둘러싸고 있는 가장 안쪽 블록의 최상위 요소로 존재하며, 아무런 값을 만들어내지 못한다.
* 자바에서는 모든 제어 구조가 문인 반면,
* 코틀린에서는 루프를 제외한 대부분의 제어 구조가 식이다.
*
* 본문이 중괄호로 둘러싸인 함수를 블록이 분몬인 함수라 부른다. (block body)
* */
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

/*
* 등호와 식으로 이루어진 함수를 식이 본문인 함수로 부른다. (expression body)
* 식이 본문인 경우 컴파일러가 타입 추론을 해준다.
*
* 블록이 본문인 경우 반드시 반환 타입을 지정하고 return 문을 사용해 반환 값을 명시해야 한다.
* */
fun min(a: Int, b: Int) = if (a > b) b else a

// 식이 본문인 함수와 마찬가지로 컴파일러가 초기화 식을 분석해 타입을 지정해주므로 타입을 명시하지 않아도 된다.
val question = "삶, 우주, 그리고 모든 것에 대한 궁극적인 질문"

fun answer() {
    // 초기화 식이 없다면 변수에 저장될 값에 대해 아무 정보가 없기 때문에 컴파일러가 타입 추론을 할 수 없다.
    // 따라서 이 경우 타입을 반드시 지정해야 한다.
    val answer: Int
    answer = 42
}

/*
* 변경 가능한 변수와 변경 불가능한 변수
* 변수 선언 시 사용하는 키워드는 두가지가 있다.
* val - 값을 뜻하는 value에서 따옴. 변경 불가능한 참조를 저장하는 변수다. val로 선언된 변수는 일단 초기화되고 나면 재대입이 불가능하다.
*       자바로 치면 final 변수.
* var - 변수를 뜻하는 variable에서 따옴. 변경 가능한 참조다. 자바의 일반 변수.
*
* 기본적으로는 모든 변수를 val 키워드를 사용해 불변 변수로 선언하고,
* 나중에 꼭 필요할 때만 var로 변경하라.
* 변경 불가능한 참조와 변경 불가능한 객체를 부수 효과가 없는 함수와 조합해 사용하면 코드가 함수형 코드에 가까워진다.
* */
fun aboutValAndVar() {
    // val 변수는 정확히 한 번만 초기화돼야 한다. 컴파일러가 이를 확인할 수 있다면 조건에 따라 초기화할 수도 있다.
    val message: String
    if (true) {
        message = "Success"
    } else {
        message = "Failed"
    }

    // val 참조 자체는 불변일지라도 그 참조가 가리키는 객체의 내부 값은 변경될 수 있다.
    val languages = arrayListOf("Java")
    languages.add("Kotlin")

    // var 키워드를 사용하면 변수의 값을 변경할 수 있지만 타입은 고정돼 바뀌지 않는다.
    var answer = 42
    // 컴파일 에러
    // answer = "no answer"
}
