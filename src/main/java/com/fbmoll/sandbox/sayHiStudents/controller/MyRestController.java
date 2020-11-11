package com.fbmoll.sandbox.sayHiStudents.controller;

import com.fbmoll.sandbox.sayHiStudents.data.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {

    @GetMapping("/GetStudent")
    public Student getStudent(@RequestParam(value = "name", defaultValue = "pepito") String name) {

        Student aux = new Student();
        aux.setName(name);
        aux.setMark("8");
        return aux;

    }

    @GetMapping("/CreateStudents")
    public List<Student> CreateStudent(@RequestParam(value = "q", defaultValue = "1") Integer nStudents) {

        ArrayList<Student> arrData = new ArrayList<>();

        for (int i = 0; i < nStudents; i++) {
            Student aux = new Student();
            aux.setName(String.format("Student %s", i));
            arrData.add(aux);

        }
    return  arrData;

    }
}
