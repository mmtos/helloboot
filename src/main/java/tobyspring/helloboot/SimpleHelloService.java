package tobyspring.helloboot;

public class SimpleHelloService implements HelloService {
    // refactor -> extract interface
    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
