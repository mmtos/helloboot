package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import tobyspring.config.MySpringBootAnnotation;

@MySpringBootAnnotation
public class HellobootApplication {
    /*
    빈의 구분
    애플리케이션 빈 : 개발자가 직접 제공
        애플리케이션 로직 빈(by component scan)
        애플리케이션 인프라스트럭쳐 빈(by auto configuration) : dispatcher servlet, tomcat servlet web server factory
    컨테이너 인프라스트럭쳐 빈(스프링의 기능을 제공하기 위한 빈들), 부트스트래핑 과정에서 자동 등록
    */
    public static void main(String[] args) {
        SpringApplication.run(HellobootApplication.class, args);
    }
}
