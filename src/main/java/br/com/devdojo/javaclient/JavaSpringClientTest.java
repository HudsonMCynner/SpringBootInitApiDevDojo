package br.com.devdojo.javaclient;

import br.com.devdojo.model.Student;

import java.util.List;

public class JavaSpringClientTest {
    public static void main(String[] args) {

        Student studentPost = new Student();
        studentPost.setName("Jon Wick");
        studentPost.setEmail("jw@gmail.com");
        JavaClientDAO javaClientDAO = new JavaClientDAO();
//        System.out.println(javaClientDAO.findById(1));
        List<Student> students = javaClientDAO.lisAll();
        System.out.println(students);
//        System.out.println(javaClientDAO.save(studentPost));

    };
}
