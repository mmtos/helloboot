package tobyspring.helloboot;

import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// DispatcherServlet은 기본적으로 타입레벨 어노테이션부터 찾고 메서드를 핸들러로 등록한다.
@RequestMapping
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
