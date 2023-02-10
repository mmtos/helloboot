package tobyspring.helloboot;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

public class HellobootApplication {

	public static void main(String[] args) throws Exception {
		// 1. embed 톰캣을 실행시키기
		TomcatServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory(8081);
		Connector anotherConnector = new Connector("HTTP/1.1");
		anotherConnector.setPort(8081);
		servletWebServerFactory.addAdditionalTomcatConnectors(anotherConnector);
		servletWebServerFactory.addConnectorCustomizers(connector -> {
			connector.setPort(8080);
		});
		WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
			// 서블릿 추가 및 URL 매핑
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp)
						throws ServletException, IOException {
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.setHeader("Content-Type", "text/plain");
					resp.getWriter().println("Hello Servlet");
				}
			}).addMapping("/hello");
		});
		webServer.start();
	}
}
