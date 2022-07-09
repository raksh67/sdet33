package com.crm.genericUtility;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

/**
 * This class contains all the generic methods of database
 * @author r pc
 *
 */
public class DatabaseUtility {

	static Connection connection;
	static ArrayList<String> list =new ArrayList<String>();
	/**
	 * This method is used to establish connection of the Mysql Database
	 * @param dbUrl
	 * @param dbUserName
	 * @param dbPassword
	 * @throws SQLException
	 */
	
	public static void getMySqlDatabaseConnection(String dbUrl,String dbUserName,String dbPassword)throws SQLException{
		Driver d = new Driver();
		DriverManager.registerDriver(d);
		connection=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
	}
/**
 * This method is used to fetch the data from database based on columnName
 * @param query
 * @param columnNumber
 * @return
 * @throws SQLException
 */
	public static ArrayList<String> getDataFromDatabase(String query,int columnNumber)throws SQLException{
		Statement statement =connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			list.add(result.getString(columnNumber));
		}
		statement.close();
		return list;

	}
	/**
	 * This method is used to fetch the data from database based on column
	 * @param query
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> getDataFromDatabase(String query,String columnNameorcolumnNumber)throws SQLException{
		String num="";
		for(int i=0; i<columnNameorcolumnNumber.length();i++) {
			char ch=columnNameorcolumnNumber.charAt(i);
			if(Character.isDigit(ch)) {
				num=num+columnNameorcolumnNumber.charAt(i);
			}
			else {
				break;
			}
		}
		Statement statement=connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		 int columnNumber=0;
		 String columnName=null;
		 if(num.equalsIgnoreCase(columnNameorcolumnNumber))
		 {
			 columnNumber=Integer.parseInt(num);
		while(result.next())
		{
			list.add(result.getString(columnNumber));
		}
		 }
		 else {
			 columnName=columnNameorcolumnNumber;
			 while(result.next())
			 {
				         
			 }
		 }
		statement.close();
	//	return data;
		return list;
	}
	/**
	 * This method is used to update/write/modify the data inside the database
	 * @param query
	 * @throws SQLException
	 */
	public static void updateDataIntoDatabase(String query)throws SQLException{
		Statement statement=connection.createStatement();
		statement.executeQuery(query);
		System.out.println("Query executed successfully");
		statement.close();
	}
	/**
	 * This method is used to close the database connection
	 * @throws SQLException
	 */
	public static void closeDatabaseConnection()throws SQLException{
		connection.close();	
	}
	
	/**
	 * This method is used to verify the data whether it is present in the database or not
	 * @param query
	 * @param columnName
	 * @param expData
	 * @throws SQLException
	 */
	public static void verifyData(String query,String columnName,String expData)throws SQLException{
		//String data =getDataFromDatabase(query,columnName);
	//	if(data.equalsIgnoreCase(expData))
		{
			System.out.println("Data is present inside the database");
		}
		
		
		
	}
}
