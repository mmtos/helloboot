package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

// 단위테스트를 통해 고립된 테스트가 가능하다.
public class HelloServiceTest {
    @Test
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();
        String ret = helloService.sayHello("Test");
        assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator(){
        HelloDecorator helloService = new HelloDecorator(name->name);
        String ret = helloService.sayHello("Test");
        assertThat(ret).isEqualTo("*Test*");
    }
}
