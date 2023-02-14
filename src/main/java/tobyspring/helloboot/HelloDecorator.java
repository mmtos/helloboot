package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
//컨트롤러에 데코레이터를 주입하기 위해 우선순위를 설정해줌
//데코레이터가 여러개인 경우 하나하나 찾아서 우선순위를 갱신해주는게 귀찮을 수 있음
//그럴때는 자바코드로 하나하나 DI 설정해주는 방식도 괜찮음
@Primary
/**
 * 데코레이터는 동작의 추가가 목적이다.
 * DI방식으로 Controller가 service데코레이터를 이용하면,
 * 런타임에서 사용하던 HelloService구현체의 코드를 변경하지 않고도
 * 동작 추가가 가능하다. , 여러개의 데코레이터를 적용해도 상관없다!
 *
 * 프록시도 데코레이터와 구현방식은 똑같지만
 * 동작의 추가가 목적이라기 보다는 대상객체를 숨기는데 목적이 있다.
 * proxy 대상객체를 lazy loading하거나
 * 원격에 있는 대상 객체에 메시지를 보낸다거나 하는 역할을 한다.
 */
public class HelloDecorator implements HelloService{

    private final HelloService inner;

    public HelloDecorator(HelloService inner) {
        this.inner = inner;
    }

    @Override
    public String sayHello(String name) {

        return "*" + inner.sayHello(name) + "*";
    }
}
