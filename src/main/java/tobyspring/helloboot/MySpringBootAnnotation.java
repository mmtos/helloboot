package tobyspring.helloboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tobyspring.config.autoconfig.DispatcherServletConfig;
import tobyspring.config.autoconfig.TomcatWebServerConfig;

// RetentionPolicy.CLASS 클래스파일까진 남아있지만 로딩 후엔 남아있지 않음
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ComponentScan
@Configuration
// 스프링 3.0부터 있었음 파라미터로 넘겨진 @Component가 붙은 클래스를
// 컨테이너 구성정보에 추가
// @Import를 이용하면 컴포넌트 스캔 바깥에 있는 @Configuration 클래스를 사용할 수 있다!
@Import({TomcatWebServerConfig.class, DispatcherServletConfig.class})
// 최상위에 설정파일들을 계속 추가하는 방식은 파일 내용이 계속 길어지는 문제가 있음.
// 가독성도 떨어짐

public @interface MySpringBootAnnotation {
}
