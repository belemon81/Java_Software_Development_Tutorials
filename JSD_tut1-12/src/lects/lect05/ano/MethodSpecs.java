package lects.lect05.ano;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MethodSpecs {
    String type();

    String preCondition();

    String postCondition();

    String sideEffects();
}
