package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
public class HelloControllerTest {
    //DI를 통해 의존 클래스의 문제에서 테스트를 고립시킬 수 있다.
    @Test
    void helloController(){
        // HelloService는 이미 다른곳에서 테스트 했기때문에 TestDummy를 만들어서 controller만 테스트한다.
        HelloController helloController = new HelloController(name -> name);
        String ret = helloController.hello("hi");
        assertThat(ret).isEqualTo("hi");
    }

    @Test
    void fail(){
        // HelloService는 이미 다른곳에서 테스트 했기때문에 TestDummy를 만들어서 controller만 테스트한다.
        // '단위'의 scope는 딱히 정해진건 없다. 보통 클래스 하나정도.
        HelloController helloController = new HelloController(name -> name);
        assertThatThrownBy(()-> helloController.hello(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()-> helloController.hello("  ")).isInstanceOf(IllegalArgumentException.class);

    }
}
