package tobyspring.helloboot;

import java.util.Objects;

public class HelloController {
    private final HelloService helloService;

    //생성자나 setter가 없으면 스프링 컨테이너가 DI를 하지 못한다.
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
