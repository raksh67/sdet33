package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyDatabaseWRTGUITest {

	public static void main(String[] args) throws IOException, Throwable {
		Connection connection=null;
		try {
		Driver d=new Driver();
		DriverManager.registerDriver(d);
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
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
               long timeoutLong = Long.parseLong(timeouts);
               
               WebDriver driver=null ;
               
               if(browser.equalsIgnoreCase("Chrome"))
               {
            	   WebDriverManager.chromedriver().setup();
            	   driver=new ChromeDriver();
               }
               else if (browser.equalsIgnoreCase("firefox"))
               {
            	   WebDriverManager.firefoxdriver().setup();
            	   driver=new FirefoxDriver();

               }
               else {
            	   System.out.println("Browser is not specified properly");
               }
               
               driver.manage().window().maximize();
               driver.manage().timeouts().implicitlyWait(timeoutLong,TimeUnit.SECONDS);
               driver.get(url);
               driver.findElement(By.id("username")).sendKeys(username);
               driver.findElement(By.id("inputpassword")).sendKeys(password);
               driver.findElement(By.xpath("//button[.='Sign in']")).click();
               driver.findElement(By.linkText("Projects")).click();                                                                   
               String projName="kaprask";
               driver.findElement(By.name("projectName")).sendKeys(projName);
               driver.findElement(By.name("createdBy")).sendKeys("Raksha");
               WebElement status = driver.findElement(By.xpath("//label[.='project status']/following sibling::select"));
               
               
			Select select = new Select(status);
			select.selectByVisibleText("On Going");
			
			driver.findElement(By.xpath("//input[@value='Add Project']")).click();
			 Statement statement = connection.createStatement();
			 
			 //step 4 executequery
			 //for fetch data(DQL)
			 ResultSet result = statement.executeQuery("select * from project;");
			 while(result.next())
			 {
				 String projectName = result.getString("project_name");
				 if(projectName.equals(projName)) {
					 System.out.println("Date is stored in database");
				 }
			 }
			 driver.close();
	}

	finally {
		//step5: close connection
		connection.close();
		System.out.println("connection is closed");
				 }
			 }
}
