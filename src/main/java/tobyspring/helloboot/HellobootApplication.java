package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * curl http://localhost:8080/hello?name=jsh
 */

@ComponentScan
public class HellobootApplication {
    public static void main(String[] args) {
        // 스프링 컨테이너 refresh 시점에 톰캣 실행과정 통합
        AnnotationConfigWebApplicationContext acwac = new AnnotationConfigWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
                            .addMapping("/*");
                    // handler 매핑 방법은 서블릿에서 직접 설정하거나, xml로 적어주거나, 애너테이션을 쓰거나
                });
                webServer.start();
            }
        };

        // Assembler : 구체클래스간의 결합을 인터페이스로 약화시키고, 런타임 시점에 구체클래스를 사용할 수 있도록 DI해주는 역할을 말한다.
        // 스프링 컨테이너는 Assembler의 역할을 하고 있다.
        // Assembler의 구현 방법 1 : 생성자로 DI해주는 방법, Factory 호출시 전달, setter를 통해 주입
//        acwac.registerBean(HelloController.class);
//        acwac.registerBean(SimpleHelloService.class);
//        acwac.refresh(); // 이때 bean을 생성함.

        //annotation 기반 설정을 사용한 스프링 컨테이너는 더이상 registerBean을 사용하지 못함
        acwac.register(HellobootApplication.class);

        // 간편하고 표준적인 방식이지만, 빈이 많아질수록 어떤 빈들이 있는지 찾기 어렵다.
//        acwac.scan("tobyspring.helloboot");
        acwac.refresh();
    }
}
