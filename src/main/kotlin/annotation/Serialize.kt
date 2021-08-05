package annotation

import java.lang.StringBuilder
import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

private fun StringBuilder.serializeObject(obj: Any) {
    append("{")
    obj.javaClass.kotlin.memberProperties.filter {
        it.findAnnotation<JsonExclude>() == null
    }.forEach { prop ->
        serializeObject(prop, obj)
        append(", ")
    }
    append("}")
}

private fun StringBuilder.serializeObject(prop: KProperty1<Any, *>, obj: Any) {
    val jsonName = prop.findAnnotation<JsonName>()
    val propName = jsonName?.name ?: prop.name
    append(propName)
    append(": ")
    append(prop.get(obj))
}

// buildString은 StringBuilder를 생성해서 인자로 받은 람다에 넘긴다.
fun serialize(obj: Any): String = buildString { serializeObject(obj) }

// annotations는 소스 상 해당 요소에 적용된 (@Retention을 RUNTIME으로 지정한) 모든 애노테이션 인스턴스의 콜렉션이다.
inline fun <reified T> KAnnotatedElement.findAnnotation(): T? = annotations.filterIsInstance<T>().firstOrNull()

data class Person5(
    @JsonName("alias") val firstName: String,
    val age: Int,
    @JsonExclude val temp: String
)

fun main() {
    val person = Person5("Alice", 29, "temp")
    val kClass = person.javaClass.kotlin

    // JsonExclude 애노테이션이 없는 프로퍼티만 가져오기
    val properties = kClass.memberProperties.filter { it.findAnnotation<JsonExclude>() == null }
    println(properties)
}