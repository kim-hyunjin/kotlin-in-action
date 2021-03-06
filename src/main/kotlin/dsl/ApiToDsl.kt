package dsl

import kotlinx.html.stream.createHTML
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.tr

/**
 * DSL의 궁극적인 목표는 코드의 가독성과 유지 보수성을 가장 좋게 유지하는 것이다.
 *
 * 목표 달성을 위해선 개별 클래스를 넘어 클래스 간 상호작용이 일어나는 지점을 살펴봐야 한다.
 * 즉, 클래스의 API를 살펴봐야 한다.
 * 라이브러리가 외부 사용자에게 프로그래밍 API를 지원하는 것처럼
 * 애플리케이션 안의 모든 클래스는 다른 클래스에게 자신과 상호작용할 수 있는 기능을 제공한다.
 * 이 상호작용을 이해하기 쉽고 명확하게 표현할 수 있게 만들어야 프로젝트를 계속 유지 보수할 수 있다.
 *
 * API가 깔끔하다는 뜻은?
 * - 코드를 읽는 독자들이 어떤 일이 벌어질지 명확하게 이해할 수 있어야 한다.(이름을 잘 붙이고 적절한 개념을 사용하는 것이 중요!)
 * - 코드가 간결해야 한다. 불필요한 구문, 번잡한 준비코드가 가능한 적어야 한다.
 *
 * 코틀린이 지원하는 간결한 구문
 * 일반 구문                    | 간결한 구문                | 사용한 언어 특성
 * StringUtil.capitalize(s)   | s.capitalize()          | 확장 함수
 * 1.to("one")                | 1 to "one"              | 중위 호출
 * set.add(2)                 | set += 2                | 연산자 오버로딩
 * map.get("key")             | map["key"]              | get 메소드에 대한 관례
 * file.use({f -> f.read()})  | file.use { it.read() }  | 람다를 괄호 밖으로 빼내는 관례
 * sb.append("yes")           | with (sb) {             | 수신 객체 지정 람다
 * sb.append("no")                  append("yes")
 *                                  append("no")
 *                              }
 */

/**
 * DSL 이라는 개념은 프로그래밍 언어라는 개념과 거의 마찬가지로 오래된 개념이다.
 * 가장 익숙한 DSL은 분명 SQL과 정규식일 것이다.
 * 이 두언어는 DB 조작, 문자열 조작이라는 특정 작업에 적합하다.
 * 하지만 전체 애플리케이션을 정규식이나 SQL을 사용해 작성하는 경우는 없다.
 *
 * DSL이 스스로 제공하는 기능을 제한함으로써 오히려 더 효율적으로 자신의 목표를 달성할 수 있다.
 *
 * DSL이 범용 프로그래밍 언어와 달리 더 선언적(declarative)이라는 점이 중요하다.
 * 범용 프로그래밍 언어는 보통 명령적(imperative)이다.
 * 명령적 언어는 어떤 연산을 완수하기 위해 필요한 각 단계를 순서대로 기술하는 반면,
 * 선언적 언어는 원하는 결과를 기술하기만 하고 그 결과를 달성하기 위해 필요한 세부 실행은 엔진에 맡긴다.
 * 실행 엔진이 결과를 얻는 과정을 전체적으로 한꺼번에 최적화하기 때문에 선언적 언어가 더 효율적인 경우가 자주 있다.
 *
 * 다만 단점이 있다면 DSL을 범용 언어로 만든 호스트 애플리케이션과 함께 조합하기가 어렵다는 점이다.
 * DSL은 자체 문법이 있기 때문에 다른 언어의 프로그램 안에 직접 포함시킬 수가 없다.
 */

/**
 * 내부 DSL
 * 독립적인 문법 구조를 가진 외부 DSL과는 반대로 내부 DSL은 범용 언어로 작성된 프로그램의 일부이며,
 * 범용 언어와 동일한 문법을 사용한다.
 * ex)
 * (Country join Customer)
 *      .slice(Country.name, Count(Customer.id))
 *      .selectAll()
 *      .groupBy(Country.name)
 *      .orderBy(Count(Customer.id), isAsc = false)
 *      .limit(1)
 *
 * 일반 API와 다른 점은 DSL에는 특정한 구조 또는 문법이 있다는 것이다.
 * 전형적인 라이브러리는 여러 메소드로 이루어지며, 함수 호출 시퀀스에는 아무런 구조가 없으며, 호출과 호출 사이에는 아무 맥락도 존재하지 않는다.
 * 그런 API를 명령-질의 API라고 부른다.
 *
 * 반대로 DSL의 메소드 호출은 DSL 문법에 의해 정해지는 더 커다란 구조에 속한다. 위의 SQL 예제에서도 확인할 수 있다.
 * DSL에서는 여러 함수 호출을 조합해서 연산을 만들며, 타입 검사기는 여러 함수 호출이 올바르게 조합됐는지 검사한다.
 */
fun main() {
    createHTML().table {
        val numbers = mapOf(1 to "one", 2 to "two")
        for ((num, string) in numbers) {
            tr {
                td { +"$num" }
                td { +string }
            }
        }
    }
}