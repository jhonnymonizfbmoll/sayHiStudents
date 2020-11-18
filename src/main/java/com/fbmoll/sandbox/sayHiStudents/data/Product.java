package com.fbmoll.sandbox.sayHiStudents.data;

import java.io.Serializable;

public class Product  implements Serializable {

    private String name;
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Product() {}

    public Product(String name, Double value) {
        this.name = name;
        this.value = value;
    }

}
