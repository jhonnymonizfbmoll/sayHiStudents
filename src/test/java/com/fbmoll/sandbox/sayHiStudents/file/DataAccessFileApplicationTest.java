package com.fbmoll.sandbox.sayHiStudents.file;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fbmoll.sandbox.sayHiStudents.data.Catalog;
import com.fbmoll.sandbox.sayHiStudents.data.Product;
import com.fbmoll.sandbox.sayHiStudents.data.helpers.DummyUtils;
import com.fbmoll.sandbox.sayHiStudents.data.helpers.FileDataSingleton;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.w3c.dom.Document;

import java.io.File;
import java.util.List;


@SpringBootTest
public class DataAccessFileApplicationTest {

    @Test
    void generateXMLDocument(){
        FileDataSingleton fileDataSingleton = FileDataSingleton.getInstance();
        Document document = fileDataSingleton.generateRandomProductXML();
        String fileName = String.format("%s\\classic.xml", System.getProperty("user.home"));
        File file = fileDataSingleton.saveDocumentAsFile(fileName,document,true);
        Assert.notNull(file, "El fichero no puede ser nulo.");

    }
    @Test
    void trySerializer(){
        DummyUtils dummyUtils = new DummyUtils();
        String filename = String.format("%s\\.fbmoll\\serial.data", System.getProperty("user.home"));
        List<Product> products = dummyUtils.getProducts();
        FileDataSingleton.getInstance().serializeContent(filename,dummyUtils.getProducts());
        FileDataSingleton.getInstance().serializeContent(filename, products);
        Assert.notEmpty(products, "sin productos");
        List<Product> returnProducts = FileDataSingleton.getInstance().deserialize(filename);
        Assert.notEmpty(returnProducts, "Sin productos");
        Assert.isTrue(products.equals(returnProducts), "No son iguales");
        boolean isSAmeFirstName = StringUtils.equals(products.get(0).getName(), returnProducts.get(0).getName());
        Assert.isTrue(isSAmeFirstName, "No son iguales");
    }

//    @Test
//    void tryLombokCrazily(){
//        Catalog catalog = new Catalog();
//        DummyUtils dummyUtils = new DummyUtils();
//        catalog.setProducts(dummyUtils.getProducts());
//        catalog.setName("catalog serializable");
//
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.writeValue(new File(filename), catalog);
//        File file = new File("simple_bean.xml");
//
//    }



}
