package tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;
import tobyspring.config.autoconfig.TomcatWebServerConfig.TomcatCondition;

@MyAutoConfiguration
//@Conditional(TomcatCondition.class)
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
    @Bean("tomcatWebServerFactory")
    // 메서드 레벨에서도 @Conditional을 사용할 수 있다. Type 레벨의 @Conditional이 true일때만 유효하다.
    // DefferedSelector로 클래스를 로딩하는 이유 - 유저 config를 먼저 반영하기 위해서
    @ConditionalOnMissingBean // 같은 타입의 빈이 존재하지 않을때만 이 factory를 실행
    // 스프링부트에서 제공하는 configuration 클래스에 ConditionalOnMissingBean가 있으면
    // 사용자가 해당 빈을 대체할 수 있도록 허락한 것이다.
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    static class TomcatCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.getClassLoader());
        }
    }
}
