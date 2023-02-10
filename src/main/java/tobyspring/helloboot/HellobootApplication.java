package tobyspring.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

public class HellobootApplication {

	public static void main(String[] args) throws Exception {
		// 1. embed 톰캣을 실행시키기
//		new Tomcat().start();
		TomcatServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
		WebServer webServer = servletWebServerFactory.getWebServer();
		webServer.start();
	}
}
