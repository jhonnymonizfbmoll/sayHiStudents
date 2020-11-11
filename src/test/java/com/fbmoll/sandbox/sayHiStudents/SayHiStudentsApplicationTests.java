package com.fbmoll.sandbox.sayHiStudents;

import com.fbmoll.sandbox.sayHiStudents.data.ConfigValues;
import com.fbmoll.sandbox.sayHiStudents.data.helpers.DummyUtils;
import com.fbmoll.sandbox.sayHiStudents.data.Student;
import com.fbmoll.sandbox.sayHiStudents.data.helpers.fileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.File;
import java.util.List;

@SpringBootTest
class SayHiStudentsApplicationTests {


	@Test
	void tryCreateFile() {

		String filename = String.format("%s\\.fbmoll\\test.txt", System.getProperty("user.home"));
		File file = fileUtils.generateFile(filename, "dummy Content");
		Assert.notNull(file, "fichero nulo");

	}

	@Test
	void tryCreateAndReadFile() {
		String fileName = String.format("%s\\.fbmoll\\test.txt", System.getProperty("user.home"));
		File file = fileUtils.generateFile(fileName, "hola\nDonPimpon");
		List<String> fileContent = fileUtils.readFileLines(fileName);
		Assert.notEmpty(fileContent, "Tu fichero esta vacío!");
		Assert.isTrue(StringUtils.equals(fileContent.get(0), "hola"), "primera linea chunga");
		Assert.isTrue(StringUtils.equals(fileContent.get(1), "DonPimpon"), "segunda linea chunga");

	}


	@Test
	void validateProperties() {
		String fileName = String.format("%s\\.fbmoll\\myprops.properties", System.getProperty("user.home"));
		String stringBuilder = "name = myUser\n" + "password = myPass\n" + "server = localhost\n" + "port = 3306\n";
		File file = fileUtils.generateFile(fileName, stringBuilder);
		fileUtils fileUtils = new fileUtils();
		ConfigValues data = new ConfigValues();
		data.setName("myUser");
		data.setPassword("myPassword");
		data.setServer("localhost");
		data.setPort("3306");
		ConfigValues properties = fileUtils.loadProperties(fileName);
		Assert.isTrue(StringUtils.equals(data.getName(), properties.getName()), "Name is different.");
		Assert.isTrue(StringUtils.equals(data.getPassword(), properties.getPassword()), "Password is different.");
		Assert.isTrue(StringUtils.equals(data.getServer(), properties.getServer()), "Server is different.");
		Assert.isTrue(StringUtils.equals(data.getPort(), properties.getPort()), "Port is different.");
		Assert.isTrue(data.equals(properties), "Properties are different.");

	}

	@Test
	void tryDummyGenerator() {
		DummyUtils dummyUtils = new DummyUtils();
		List<Student> data = dummyUtils.genStudents(50);
		System.out.println("yuhu");


	}
}
