package tobyspring.helloboot;

import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// DispatcherServlet은 기본적으로 타입레벨 어노테이션부터 찾고 메서드를 핸들러로 등록한다.
@RequestMapping
// 메타 애노테이션으로 @Component를 사용
// 이 컴포넌트가 어떤 성격의 빈인지 애너테이션의 이름으로 명시할 수 있다.
// 여러 개의 메타 애노테이션을 합성하여 함께 적용하기 위해서 사용
@MyComponent
@Controller
public class HelloController {
    private final HelloService helloService;

    //생성자나 setter가 없으면 스프링 컨테이너가 DI를 하지 못한다.
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    //기본값은 view를 찾는것
    @ResponseBody
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
