package tobyspring.helloboot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class HellobootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellobootApplication.class, args);
    }

    // ApplicationRunner
    // context완성 후 실행됨.
    @Bean
    ApplicationRunner applicationRunner(Environment env){
        // Environment Abstraction과 우선순위
        // 일반적으로 application.yml에 디폴트 프로퍼티, 시스템 환경변수로 오버라이딩 하는 방식을 주로 사용한다.
        return args -> {
            String name = env.getProperty("my.name");
            System.out.println("my name is " + name);
        };
    }
}
