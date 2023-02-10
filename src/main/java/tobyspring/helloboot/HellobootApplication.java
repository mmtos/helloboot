package tobyspring.helloboot;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

    public static void main(String[] args) {
        // 스프링 컨테이너를 만들어 봅시다.
        GenericWebApplicationContext gwac = new GenericWebApplicationContext();
        // Assembler : 구체클래스간의 결합을 인터페이스로 약화시키고, 런타임 시점에 구체클래스를 사용할 수 있도록 DI해주는 역할을 말한다.
        // 스프링 컨테이너는 Assembler의 역할을 하고 있다.
        // Assembler의 구현 방법 1 : 생성자로 DI해주는 방법, Factory 호출시 전달, setter를 통해 주입
        gwac.registerBean(HelloController.class);
        gwac.registerBean(SimpleHelloService.class);
        gwac.refresh(); // 이때 bean을 생성함.

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet", new DispatcherServlet(gwac))
                    .addMapping("/*");
            // handler 매핑 방법은 서블릿에서 직접 설정하거나, xml로 적어주거나, 애너테이션을 쓰거나
        });
        webServer.start();
    }

}
