package br.com.devdojo.javaclient;

import br.com.devdojo.model.PageableResponse;
import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class JavaSpringClientTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri("http://localhost:8080/v1/protected/students")
                .basicAuthorization("hud", "banana").build();

//        Student student = restTemplate.getForObject("/{id}", Student.class, 8);
//        System.out.println(student);
//
//        ResponseEntity<Student> forEntityStudent = restTemplate.getForEntity("/{id}", Student.class, 8);
//        System.out.println(forEntityStudent.getBody());
//
//        Student[] students = restTemplate.getForObject("/", Student[].class);
//        System.out.println(Arrays.toString(students));
//
//        ResponseEntity<List<Student>> exchange = restTemplate.exchange("/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {});
//        System.out.println(exchange.getBody());

        ResponseEntity<PageableResponse<Student>> exchange = restTemplate.exchange("/?sort=id,desc&sort=name,asc", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Student>>() {
                });
        System.out.println(exchange);
    }
}
