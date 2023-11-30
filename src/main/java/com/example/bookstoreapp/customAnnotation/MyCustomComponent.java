package com.example.bookstoreapp.customAnnotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Component
public @interface MyCustomComponent {

    @AliasFor(annotation = Component.class)
    String value() default "";

    // Diğer özel öznitelikleri buraya ekleyebilirsiniz.
}
