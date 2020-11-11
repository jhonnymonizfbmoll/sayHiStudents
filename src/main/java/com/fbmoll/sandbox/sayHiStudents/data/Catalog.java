package com.fbmoll.sandbox.sayHiStudents.data;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class Catalog implements Serializable {

    private String name;
    private List<Product> products;
    private double value;

    private void setValue(Double value) {
        this.value = value;
    }

    public double getValue() {
        double total = 0;
        if (products != null) {
            for (Product item : products) {
                if (item.getValue()!=null){
                    total += item.getValue();
                }
            }
        }
        setValue(total);
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

