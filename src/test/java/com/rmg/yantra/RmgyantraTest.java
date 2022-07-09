package com.rmg.yantra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RmgyantraTest {

	public static void main(String[] args)throws SQLException {
Connection connection = null;String project_id=null; String created_by =null;String created_on=null;String project_name=null;String status=null;String team_size=null;
//step:1 we should create the object for the driver and register the driver
Driver d =new Driver();
DriverManager .registerDriver(d);
//get connection
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
//create statement
Statement statement = connection.createStatement();
//executequery
 int result = statement.executeUpdate("insert into project(project_id,created_by,created_on,project_name,status,team_size)values('TY_PROJ_004','Raksha','24/03/2022','SDET33','completed','4')");
 if(result==1)
 {
	 System.out.println("rows got changed");
	 
 }
 else {
	 System.out.println("rows not changed");
 }
 
	 WebDriverManager.chromedriver().setup();

 WebDriver driver = new ChromeDriver();
 driver.get("http://localhost:8084/");
 driver.findElement(By.id("username")).sendKeys("rmgyantra");
 driver.findElement(By.id("inputpassword")).sendKeys("rmgy@9999");
 driver.findElement(By.xpath("//button [text()='sign in']")).click();
 driver.findElement(By.linkText("projects")).click();
 String expectedresult = "TY_PROJ_OO4";
 String actualresult=driver.findElement(By.xpath("//td[text()='TY_PROJ_004']")).getText();
 if(actualresult.equals(expectedresult))
 {
	 System.out.println("test script is pass");
 }
	}
}