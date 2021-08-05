### 코틀린 리플렉션 API 인터페이스 구조
```
                KAnnotatedElement
             /          |           \
        KClass      KCallable       KParameter
                    /        \
                KFunction    KProperty
            KFunction0        KMutableProperty
            KFunction1
            ...
            KProperty.Getter
            KMutableProperty.Setter
```