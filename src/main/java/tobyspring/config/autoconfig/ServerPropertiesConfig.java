package tobyspring.config.autoconfig;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class ServerPropertiesConfig {
    @Bean
    public ServerProperties serverProperties(Environment env){
//        ServerProperties serverProperties = new ServerProperties();
//        serverProperties.setPort(Integer.parseInt(env.getProperty("port")));
//        serverProperties.setContextPath(env.getProperty("contextPath"));
//        return serverProperties;
        // 자동구성클래스들이 어떤 프로퍼티 클래스를 쓰는지 확인해보면 좋다.
        // 프로퍼티가 객체라면 .을 통해 확장할 수 있다.
        return Binder.get(env).bind("",ServerProperties.class).get();
    }
}
