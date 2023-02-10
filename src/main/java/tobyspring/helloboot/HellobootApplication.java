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

public class HellobootApplication {

    public static void main(String[] args) {
        // 스프링 컨테이너를 만들어 봅시다.
        GenericApplicationContext gac = new GenericApplicationContext();
        // Assembler : 구체클래스간의 결합을 인터페이스로 약화시키고, 런타임 시점에 구체클래스를 사용할 수 있도록 DI해주는 역할을 말한다.
        // 스프링 컨테이너는 Assembler의 역할을 하고 있다.
        // Assembler의 구현 방법 1 : 생성자로 DI해주는 방법, Factory 호출시 전달, setter를 통해 주입
        gac.registerBean(HelloController.class);
        gac.registerBean(SimpleHelloService.class);
        gac.refresh(); // 이때 bean을 생성함.

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            HelloController helloController = gac.getBean(HelloController.class);

            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어, 공통 기능
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");
                        String ret = helloController.hello(name);
                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret);
                    }else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
        });
        webServer.start();
    }

}
