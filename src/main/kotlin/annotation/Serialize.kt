package annotation

import java.lang.StringBuilder
import kotlin.reflect.full.memberProperties

private fun StringBuilder.serializeObject(obj: Any) {
    val kClass = obj.javaClass.kotlin
    val properties = kClass.memberProperties
    append("{")
    properties.forEach { prop ->
        append(prop.name)
        append(": ")
        append(prop.get(obj))
        append(", ")
    }
    append("}")

}

// buildString은 StringBuilder를 생성해서 인자로 받은 람다에 넘긴다.
fun serialize(obj: Any): String = buildString { serializeObject(obj) }