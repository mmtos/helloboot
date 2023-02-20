package tobyspring.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

// 스프링에 비슷한게 있다.
// 관례상 Conditional 메타 애너테이션의 경우 Conditional로 시작한다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Conditional(MyOnClassCondition.class)
public @interface ConditionalMyOnClass {
    String value();
}
