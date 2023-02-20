package tobyspring.study;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
import static org.assertj.core.api.Assertions.*;
public class ConditionalTest {
    @Test
    void conditional(){
        //1. true
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(Config1.class);
        ac.refresh();
        MyBean bean = ac.getBean(MyBean.class);

        //2. false
        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext();
        ac2.register(Config2.class);
        ac2.refresh();
        // 예외 발생함
        assertThatThrownBy(()->ac2.getBean(MyBean.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
    }
    @Test
    void conditional2() {
        //1. true
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(MyBean.class);
                    assertThat(context).hasSingleBean(Config1.class);
                });
        //2. false
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(Config1.class);
                    // 타입에 Conditional이 붙었기때문에 config빈도 등록되지 않는다.
                    assertThat(context).doesNotHaveBean(Config2.class);
                });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface TrueConditional{}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    @interface FalseConditional{}


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional{
        boolean value();
    }


    @Configuration
//    @TrueConditional
    @BooleanConditional(true)
    static class Config1{
        @Bean
        MyBean myBean(){
            return new MyBean();
        }
    }

    @Configuration
//    @FalseConditional
    @BooleanConditional(false)
    static class Config2{
        @Bean
        MyBean myBean(){
            return new MyBean();
        }
    }

    static class MyBean {}

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            //annotation element의 값을 활용하여 판단하는 방법
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
                    BooleanConditional.class.getName());
            return (Boolean) annotationAttributes.get("value");
        }
    }
}
