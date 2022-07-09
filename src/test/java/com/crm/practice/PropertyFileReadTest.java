package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReadTest {

	public static void main(String[] args) throws IOException {
//convert the physical file into java readable object
		FileInputStream fis =new FileInputStream("./src/test/resources/commonData.properties");
		
		//step 2 create the object of properties
		Properties properties = new Properties();
		
		//step3:load all the keys
		properties.load(fis);
		
		//step 4: fetch the data
		String url = properties.getProperty("url");
		System.out.println(url);
		
		String username = properties.getProperty("userName");
		System.out.println(username);

		String password = properties.getProperty("password");
		System.out.println(password);
		
		String timeouts = properties.getProperty("timeouts");
		System.out.println(timeouts);
		
		String browser = properties.getProperty("browser");
		System.out.println(browser);

		


		


		
	}

}
