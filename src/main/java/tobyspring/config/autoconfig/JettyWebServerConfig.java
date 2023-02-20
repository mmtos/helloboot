package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import tobyspring.config.MyAutoConfiguration;
import tobyspring.config.autoconfig.JettyWebServerConfig.JettyCondition;

@MyAutoConfiguration
@Conditional(JettyCondition.class)
public class JettyWebServerConfig {
    @Bean
    // 빈의 이름은 메소드 이름을 따라간다.
    public ServletWebServerFactory jettyWebServerFactory() {
        return new JettyServletWebServerFactory();
    }


    static class JettyCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }
}
