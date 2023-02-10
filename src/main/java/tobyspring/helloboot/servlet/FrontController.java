package tobyspring.helloboot.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tobyspring.helloboot.controller.HelloController;

public class FrontController extends HttpServlet{
    public static final String SERVLET_NAME = "front";

    public HelloController helloController = new HelloController();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 인증 보안 다국어 공통기능 처리

        // URL과 컨트롤러의 매핑
        if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
            // 바인딩 - DTO, Params 등
            String name = helloController.hello(req.getParameter("name"));
            res.setStatus(HttpStatus.OK.value());
            res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
            res.getWriter().println("Hello Servlet" + name);
        }else {
            res.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}
