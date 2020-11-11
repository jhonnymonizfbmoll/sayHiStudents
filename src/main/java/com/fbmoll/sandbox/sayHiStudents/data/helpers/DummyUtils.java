package com.fbmoll.sandbox.sayHiStudents.data.helpers;

import com.fbmoll.sandbox.sayHiStudents.data.Product;
import com.fbmoll.sandbox.sayHiStudents.data.Student;
import org.jeasy.random.EasyRandom;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

public class DummyUtils {

    public List<Student> genStudents(int size) {
        ArrayList<Student> arrData = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            EasyRandom generator = new EasyRandom();
            arrData.add(generator.nextObject(Student.class));
        }
        return arrData;
    }

    public static List <Product> getProducts(){
       int numItems= (int) (Math.random() * 100);
       ArrayList<Product> aux = new ArrayList<>();
       EasyRandom gen = new EasyRandom();
        for (int i = 0; i < numItems; i++) {
            aux.add(gen.nextObject(Product.class));


        }
        return aux;
    }
}
