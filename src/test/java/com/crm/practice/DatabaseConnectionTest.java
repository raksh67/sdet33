 package com.crm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;


public class DatabaseConnectionTest {
public static void main(String[]args) throws SQLException {
//step :1 we should create the object for the driver and register the driver
Driver driver=new Driver();
DriverManager.registerDriver(driver);
//get connection
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet33","root","root");
//create statement
Statement statement = connection.createStatement();
//execute query
ResultSet result = statement.executeQuery("select* from sdet33;");
statement.executeUpdate("insert into sdet33())
while(result.next()) {
	System.out.println(result.getString(2));
}
}
}
//fetch data from mysql---sdet33
//Raksha
//Deeksha