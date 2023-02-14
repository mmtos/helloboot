package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HelloApiTest {
    @Test
    // 단위테스트라기 보다는 통합적인 테스트
    void helloApi(){
        // RestTemplate는 200이 아닌 응답 발생시 예외를 던짐
        // TestRestTemplate는 응답 그대로 전달함
        TestRestTemplate rest = new TestRestTemplate();
        // body부분 내용을 string으로 가져온다.
        ResponseEntity<String> res =
                rest.getForEntity(
                        "http://localhost:8080/hello?name={name}"
                        , String.class
                        ,"spring");

        // status
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // content type
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body
        Assertions.assertThat(res.getBody()).isEqualTo("*Hello spring*");
    }

    @Test
    void fail(){
        TestRestTemplate rest = new TestRestTemplate();
        //RestTemplate rest = new RestTemplate(); // HttpServerErrorException$InternalServerError: 500
        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/hello",String.class);

        // status
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
