package tobyspring.study;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.assertj.core.api.Assertions.*;
/**
 * @Configuration을 이해하기 위한 학습 테스트
 */
public class ConfigurationTest {
    @Test
    void configuration(){
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 b1 = ac.getBean(Bean1.class);
        Bean2 b2 = ac.getBean(Bean2.class);

        // 기본적으로 싱글턴임. 기본 자바와 동일하게 동작하지 않아서 정확한 이해가 필요함.
        assertThat(b1.common).isSameAs(b2.common);
    }

    @Test
    void myConfig(){
        MyConfig myConfig = new MyConfig();
        assertThat(myConfig.bean1().common).isNotSameAs(myConfig.bean2().common);
    }

    @Test
    void myConfigProxy(){
        MyConfig myConfig = new MyConfigProxy();
        assertThat(myConfig.bean1().common).isSameAs(myConfig.bean2().common);
    }

    //합성이 아니라 확장하는 방식으로 만들었음
    static class MyConfigProxy extends MyConfig{
        private Common common;

        @Override
        Common common(){
            if(this.common == null) this.common = super.common();
            return this.common;
        }
    }

    //proxyBeanMethods가 true인 경우 MyConfig자체가 Bean으로 추가되는게 아니라
    // MyConfig의 Proxy가 대신 Bean으로 추가된다.
    // 이 클래스에는 3개의 beanMethod가 있다.
    @Configuration(proxyBeanMethods = true)
    static class MyConfig{
        @Bean
        Common common(){
            return new Common();
        }

        @Bean
        Bean1 bean1(){
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2(){
            return new Bean2(common());
        }
    }

    // false 옵셥은 스프링 5.2에서 추가됨.
    //여러개의 빈 객체가 만들어질 가능성이 없다면, 불필요한 proxy를 굳이 적용할 필요가 없다.
    @Configuration(proxyBeanMethods = false)
    static class MyConfigNoProxy{
        @Bean
        Common common(){
            return new Common();
        }

        @Bean
        Bean1 bean1(){
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2(){
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {

    }
}
