package com.fbmoll.sandbox.sayHiStudents.data.helpers;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fbmoll.sandbox.sayHiStudents.data.Product;
import com.sun.org.apache.bcel.internal.ExceptionConst;
import com.sun.org.apache.xpath.internal.objects.XObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 *
 *Implements Singleton patter
 *  -private constructor
 *  - getInstance Method
 *  - defines an unique instance
 */


public class FileDataSingleton {
    private Logger log = LoggerFactory.getLogger(FileDataSingleton.class);
    private  static  FileDataSingleton instance = null;

    private static FileDataSingleton fileDataSingleton = new FileDataSingleton();

    public static   FileDataSingleton getInstance(){
     return fileDataSingleton;
    }

    private FileDataSingleton(){
        super();
    }

    public void serializeContent(String path, Object o){
        ObjectOutputStream serializer = null;
        try{
            if (o != null){
                serializer = new ObjectOutputStream(new FileOutputStream(path));
                serializer.writeObject(o);
            }

        } catch (Exception e) {
            log.error("cant serialize", e);
        } finally {

            if (serializer != null) {
                try {
                    serializer.close();
                } catch (IOException e) {
                    log.error("cant serialize", e);
                }
            }
         }
    }

    /**
     *
     * @param path file  location
     * @param <T> collection or data to receive
     * @return collection stored in serialization
     */
    public <T> T deserialize(String path){
        T o = null;
        ObjectInputStream  deserializer = null;
        try {
            deserializer = new ObjectInputStream(new FileInputStream(path));
            o = (T) deserializer.readObject();
        } catch (Exception e) {
            log.error("deserializing file", e);
        }finally {
            if (deserializer != null){
                try {
                    deserializer.close();
                } catch (Exception e) {
                   log.error("deserializing file", e);
                }


            }
        }
        return o;
    }


    /*
    XML
     */
    public Document generateRandomProductXML(){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.newDocument();
            // Root Element
            Element rootElement = doc.createElement("products");
            DummyUtils dummyUtils = new DummyUtils();
            List<Product> products = dummyUtils.getProducts();
            for (Product item : products){
                Element aux = doc.createElement("holas");
                aux.setAttribute("value",item.getValue().toString());
                aux.setTextContent(item.getName());
                rootElement.appendChild(aux);
            }
            doc.appendChild(rootElement);
            return doc;
        } catch (Exception e) {
            log.error("Creating XML", e);
            return  null;
        }
    }

    public File saveDocumentAsFile(String path, Document document, boolean showOuput){

        try {
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            File file = new File(path);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source,result);
            if (showOuput){
                //Output to console for testing
                StreamResult consoleResult = new StreamResult(System.out);
                transformer.transform(source, consoleResult);
            }
            return  file;

        } catch (Exception e) {
            log.error("Saving xml file" ,e);
            return null;
        }

    }
}
