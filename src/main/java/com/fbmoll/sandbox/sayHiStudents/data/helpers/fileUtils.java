package com.fbmoll.sandbox.sayHiStudents.data.helpers;

import com.fbmoll.sandbox.sayHiStudents.data.ConfigValues;
import com.fbmoll.sandbox.sayHiStudents.data.Product;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class fileUtils {
    static Logger Log = LoggerFactory.getLogger(fileUtils.class); //Conviene q no sea estatico, genera un fichero en el servidor.
    private static final String APP_PATH = ".fbmoll";


    public static File genUserFile(String fileName, String content){
        try {
            String pathSeparator = System.getProperty("path.separator");
            String realFileName = StringUtils.substringAfterLast(fileName, pathSeparator);
            String fullFileName = String.format("%s\\%s\\%s", System.getProperty("user.name"),APP_PATH,realFileName);
            return generateFile(fullFileName, content);
        }
        catch (Exception e){
            Log.error("gen user file failed!", e);
            return null;
        }


    }

    public  static File generateFile(String path, String content){
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        File file = null;
        try {
            file = new File(path);
            file.getParentFile().mkdirs();
            fileWriter = new FileWriter(path);
            printWriter = new PrintWriter(fileWriter) ;
            printWriter.println(content);
        } catch (IOException e) {
            Log.error("i/0 ERROR", e);
        } finally {
            if (file != null)
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    Log.error("i/o ERROR ", e);
                }
        }
     return file;
    }

    public static List<String> readFileLines(String filePath){

        ArrayList<String> rtn= new ArrayList<>();
        BufferedReader buffer = null;
        
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            buffer = new BufferedReader(fileReader);
            String fileLine = null;
            while ((fileLine = buffer.readLine()) != null)
                rtn.add(fileLine);
        }
        catch (IOException e) {
            Log.error("Can`t access to file", e);
        } finally {
            if (buffer != null)
                try {
                    buffer.close();
                } catch (IOException e) {
                    Log.error("Can`t close file", e);
                }
        }
        return rtn;
    }

//    public ConfigValues loadProperties(String path){
//        try{
//            ConfigValues configValues = new ConfigValues();
//            Properties properties = new Properties();
//            properties.load(new FileInputStream(path));
//            configValues.setName(properties.getProperty(ConfigurationProperties.configurationProperties.Name.toString()));
//            configValues.setPassword(properties.getProperty(ConfigurationProperties.configurationProperties.Password.toString()));
//            configValues.setPort(properties.getProperty(ConfigurationProperties.configurationProperties.Port.toString()));
//            configValues.setServer(properties.getProperty(ConfigurationProperties.configurationProperties.Server.toString()));
//            return configValues;
//
//        }catch (Exception e ){
//            fileUtils.Log.error("loading props", e);
//            return null;
//        }
//
//
//    }

//    public void saveProperties(ConfigValues data, String path) {
//        try {
//            Properties properties = new Properties();
//            properties.setProperty(ConfigurationProperties.configurationProperties.Name.toString(), data.getName());
//            properties.setProperty(ConfigurationProperties.configurationProperties.Password.toString(), data.getPassword());
//            properties.setProperty(ConfigurationProperties.configurationProperties.Server.toString(), data.getServer());
//            properties.setProperty(ConfigurationProperties.configurationProperties.Port.toString(), data.getPort());
//            properties.store(new FileOutputStream(path), "Properties file.");
//            // TODO 26/10/2020 asoto: Esto es muerte
//        } catch (Exception e) {
//            fileUtils.Log.error("Saving properties.", e);
//        }
//    }

//    public static File productsXml(List<Product> products){
//        File file = null;
//        try {
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.newDocument();
//
//            Element rootElement = doc.createElement("Products");
//            doc.appendChild(rootElement);
//
//            for (Product product : products) {
//                Element productElement = doc.createElement("Product");
//                rootElement.appendChild(productElement);
//
//                Attr valueAttr = doc.createAttribute("Value");
//                valueAttr.setValue(String.valueOf(product.getValue()));
//                productElement.setAttributeNode(valueAttr);
//
//                productElement.appendChild(doc.createTextNode(product.getName()));
//
//            }
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(doc);
//
//            String filePath = String.format("%s\\.fbmoll\\test.xml", System.getProperty("user.home"));
//            file = new File(filePath);
//
//            // Output to console for testing
//            StreamResult consoleResult = new StreamResult(file);
//            transformer.transform(source, consoleResult);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        return file;
//    }

}