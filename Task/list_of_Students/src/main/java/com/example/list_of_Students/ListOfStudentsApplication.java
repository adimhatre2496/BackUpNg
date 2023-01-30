package com.example.list_of_Students;

import com.example.list_of_Students.model.Student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;


@SpringBootApplication
public class ListOfStudentsApplication {
    private static List<Student> studentpas = Arrays.asList(
            new Student("Aditya", "pass"),
            new Student("Sunny", "pass"),
            new Student("Tzuyu", "pass"),
            new Student("Jhon", "fail"),
            new Student("Sana", "fail"),
            new Student("Tili", "fail")

    );

    private static Optional<List<Student>> createOptional() {
        return Optional.of(studentpas.stream().filter(student1 -> student1.getStatus().equalsIgnoreCase("pass")).collect(Collectors.toList()));
    }


    public static void main(String[] args) {
        SpringApplication.run(ListOfStudentsApplication.class, args);

        System.out.println(createOptional().get());

        Optional<List<Student>> studentList = createOptional();
        if (studentList.isPresent()) {
            System.out.println(studentList.get());
        } else {
            System.out.println(Collections.emptyList());
        }

        createOptional().ifPresentOrElse(student -> System.out.println(student),
                () -> System.out.println(Collections.emptyList()));// java 11

        createOptional().ifPresent(System.out::println);// java 8


    }

}
