package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//요즘 관례
@Configuration(proxyBeanMethods = false)
public class WebServerConfig {
    @Bean
    ServletWebServerFactory customWebServerFactory(){
        // 직접 만들었다고 치자.
        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        webServerFactory.setPort(9090);
        return webServerFactory;
    }
}
