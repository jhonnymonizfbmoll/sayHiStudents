package com.fbmoll.sandbox.sayHiStudents.controller;

import com.fbmoll.sandbox.sayHiStudents.data.Student;
import com.fbmoll.sandbox.sayHiStudents.data.helpers.FileDataSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.*;

@RestController
public class MyRestController {

    private Logger log = LoggerFactory.getLogger(FileDataSingleton.class);

    @GetMapping("/getStudent")
    public Student getStudent(@RequestParam(value = "name", defaultValue = "pepito22") String name) {

        Student aux = new Student();
        aux.setName(name);
        aux.setMark(name.length());
        return aux;

    }

    @GetMapping("/createStudents")
    public List<Student> createStudent(@RequestParam(value = "q", defaultValue = "1") Integer nStudents) {

        ArrayList<Student> arrData = new ArrayList<>();

        for (int i = 0; i < nStudents; i++) {
            Student aux = new Student();
            aux.setName(String.format("Student %s", i));
            arrData.add(aux);

        }
    return  arrData;

    }


    @RequestMapping(value = "/base"
            , method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Map> getData(@RequestParam(value = "q", defaultValue = "1")
                                             Integer numParams){
        try {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < numParams; i++) {
                map.put("key"+i, "pepito"+i);
            }
            String[] arr = {"asd","123"};
            map.put("arrDAta", CollectionUtils.arrayToList(arr));
            return  new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e){
            log.error("register:get/",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
