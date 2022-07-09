package my;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.Statement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationTestp {

	public static void main(String[] args) throws SQLException, IOException {
		Connection connection=null;
		WebDriver driver=null;
	
		//step1: fetch data from external file and store in variable
		try {
		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties=new Properties();
		properties.load(fis);
		String url = properties.getProperty("url");
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		String browser = properties.getProperty("browser");
		String timeout = properties.getProperty("timeout");
		String organizationName = properties.getProperty("myorg");
		long timeoutLong = Long.parseLong(timeout);

		//step 2: launch the browser
		WebDriver driver1=null;

		if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromiumdriver().setup();
			driver1=new ChromeDriver();
		}
		else {
			System.out.println("Browser is not Specified Properly");
		}

		Driver d=new Driver();
		DriverManager.registerDriver(d);
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet33", "root", "root");
		Statement statemnet=(Statement) connection.createStatement();
		java.sql.Statement statemet = null;
		ResultSet result=statemet.executeQuery("select * from vtiger");
		
		while(result.next()) {
			url=result.getNString(1);
			userName=result.getNString(2);
			password=result.getNString(3);
			organizationName=result.getNString(4);

		}
		//step 3:Do basic config for browser
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(timeoutLong, TimeUnit.SECONDS);

		//step4:Open the url and navigate to application
		driver1.get(url);

		//step5:login to the application
		driver1.findElement(By.name("user_name")).sendKeys(userName);//signout link
		driver1.findElement(By.name("user_password")).sendKeys(password);//username textfield
		driver1.findElement(By.id("submitButton")).click();//login button

		//step6:create organization
		
		driver1.findElement(By.linkText("Organization")).click();//organization tab
		driver1.findElement(By.xpath("//img[@alt='Create Organization...']")).click();//createorganizationplusicon
		driver1.findElement(By.name("accountname")).sendKeys("Organrak");//organizationname textfield
		driver1.findElement(By.name("button")).click();
		
//WebElement logo=driver1.findElement(By.xpath("//img[@src='themes/softed/images/users/user.PNG']"));/administratorclick

Actions actions=new Actions(driver1);
//actions.moveToElement(logo).click().perform();
driver1.findElement(By.linkText("Sign Out")).click();//signout link
		}
		finally {
			connection.close();
			System.out.println("connection is closed");
		}

}
}