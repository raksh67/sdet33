package my;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
//open vtiger login using username and password,click on contacts tab--->click on create contact plus icon--->enter mandatoy field last name
//click on save button--->verify actual lastname with expected lastname--->mouse hover to administarator icon and click through actions class
//and click on signout
public class CreateContactTestp {

	public static void main(String[] args) throws SQLException {
       Connection connection = null; String url =null; String username = null; String password=null;String lastname=null;
    try {
    //step:1 we should create the object for the driver and register the driver
    Driver d=new Driver();
    DriverManager.registerDriver(d);
    //get connection
    connection=DriverManager.getConnection("jdbc:mysql://localhost:8888/sdet33","root","root");
    //create statement
    Statement statement = connection.createStatement();
    //executequery
    ResultSet result = statement.executeQuery("select * from vtiger;");
    
    while(result.next()) 
    {
    	url=result.getString(1);
    	username=result.getString(2);
    	password=result.getString(3);
    	lastname=result.getString(4);
    	System.out.println(lastname);
    }
    

       WebDriverManager.chromedriver().setup();//?
       WebDriver driver = new ChromeDriver();
       driver.get(url);
       driver.findElement(By.name("user_name")).sendKeys(username);//username textfield
       driver.findElement(By.name("user_password")).sendKeys(password);//username textfield
       driver.findElement(By.id("submitButton")).click();//login button
       
       driver.findElement(By.linkText("Contacts")).click();//contacts tab
       driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();//create contact icon
       driver.findElement(By.name("lastname")).sendKeys(lastname);//last name textfield
       driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();//save button--->?y index
       
        String actResult = driver.findElement(By.id("dtlview_Last Name")).getText();//lastname textfield in contact information page
        if(actResult.equals(lastname))
        {
        	System.out.println("Test case is Pass");
        }
        else
        {
        	System.out.println("Test case is Fail");
        }
       Actions actions=new Actions(driver);
       
       actions.moveToElement(driver.findElement(By.xpath("//img[contains(@src,'/user.PNG')]"))).perform();//administrator icon
       driver.findElement(By.linkText("Sign Out")).click();//signout
       
    }
    finally {
    	connection.close();
    	System.out.println("connection is closed");
    }
	}

}