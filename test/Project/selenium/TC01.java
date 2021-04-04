package Project.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import functions.Project_Functions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class TC01 extends Project_Functions{
	private StringBuffer verificationErrors = new StringBuffer();
	public String sAppURL, sSharedUIMapPath, testDelay;
	
	@Before
	public void setUp() throws Exception {
		driver = invokeCorrectBrowser();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    prop = new Properties();	  
	    prop.load(new FileInputStream("./Configuration/SP_Configuration.properties"));
	    sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");
		testDelay=prop.getProperty("testDelay");
		prop.load(new FileInputStream(sSharedUIMapPath));
	    }
	
	@Test
	@FileParameters("test/Project/selenium/TC01a_testtttt.csv")
	public void TC01a(String testcaseNo,String username,String first_name, String last_name, String password, String phone,String email,String memtype,String room_number,String deck_number,
			String errorMsg,String usernameError,String first_nameError,String last_nameError,String passwordError,String phoneError,String emailError,String room_numberError,String deck_numberError) throws Exception {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		//(prop.getProperty("register_btn"));
		driver.findElement(By.xpath(prop.getProperty("register_btn"))).click();
	    if(!errorMsg.equals("")) {
	    	proj_registration(driver,username,first_name,last_name,password, phone,email,memtype,room_number,deck_number,methodName+" registationFuntion "+testcaseNo);
		    verifyRegistration(driver,errorMsg,usernameError,first_nameError,last_nameError,passwordError,phoneError,emailError,room_numberError,deck_numberError);
	        driver.findElement(By.xpath(prop.getProperty("registration_AppLink"))).click();
	    }
	    
	}
	
	@Test
	@FileParameters("test/Project/selenium/TC01b_test_cases.csv")
	public void TC01b(String testcaseNo,String username,String password,String errorMsg1, String errorMsg2) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
	    driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
	    proj_login(driver,username,password,methodName+" loginFuntion "+testcaseNo);
	    verifyLogin(driver,errorMsg1, errorMsg2);
	}
	
	
	@After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

}