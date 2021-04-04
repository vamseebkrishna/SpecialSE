package Project.selenium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Project.model.User;
import functions.Project_Functions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class TC03 extends Project_Functions{
	private StringBuffer verificationErrors = new StringBuffer();
	public String sAppURL, sSharedUIMapPath, testDelay,username,password;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);	
}
	@Before
	public void setUp() throws Exception {
		driver = invokeCorrectBrowser ();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    prop = new Properties();
	    prop.load(new FileInputStream("./Login/Login.properties"));
	    username = prop.getProperty("passenger_username");
		password = prop.getProperty("passenger_password");
	    prop.load(new FileInputStream("./Configuration/SP_Configuration.properties"));
	    sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");
		testDelay=prop.getProperty("testDelay");
		prop.load(new FileInputStream(sSharedUIMapPath));
	    }
	@Test
	
	@FileParameters("test/Project/selenium/TC03a_test_cases.csv")
	public void TC03a(String testcaseNo,String header1,String header2,String header3,String header4) throws Exception{
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		proj_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		verifypassengerHomepage(driver,header1,header2,header3,header4);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("passenger_logout"))).click();
		Thread.sleep(1_000);
	}
	
	@Test
	@FileParameters("test/Project/selenium/TC03b_test_cases.csv")
	public void TC03b(int testcaseNo,String header1,String header2,String header3,String header4,String header5,String header6,String header7,String header8) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		proj_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		//(driver);
		driver.findElement(By.xpath(prop.getProperty("view_pass_profile"))).click();
		Passenger_function(driver,header1,header2,header3,header4,header5,header6,header7,header8);
		//verifystrings8(driver,"prof_lname_head","Last Name","prof_fname_head","First Name","prof_phone_head","Phone","prof_email_head","Email","prof_rno_head","Room Number","prof_deckno_head","Deck number","prof_mem_head","Membership Type",methodName+" verifyprofilepage test case "+testcaseNo);
		//User User=userinformation(username);
		//verifystrings8(driver,"prof_fname",User.getFirstname(),"prof_lname",User.getLastname(),"prof_phone",User.getPhone(),"prof_email",User.getEmail(),"prof_rno",User.getRoomno(),"prof_deckno",User.getDeckno(),"prof_mem",User.getMemtype(),methodName+" verifyprofilepage test case "+testcaseNo);
		//driver.findElement(By.xpath(prop.getProperty("psgInfo_logout_btn"))).click();
		//Thread.sleep(1_000);
//		Thread.sleep(1_000);
//		//driver.findElement(By.xpath(prop.getProperty("passenger_logout"))).click();
//		Thread.sleep(1_000);
	}
	@Test
	@FileParameters("test/Project/selenium/TC03c_test_cases.csv")
	public void TC03c(int testcaseNo,String title,String alert) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		proj_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		driver.findElement(By.xpath(prop.getProperty("view_pass_profile"))).click();
		//Passenger_function(driver,FunctionPassenger.Viewprofile);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("Passenger_update_profile_link"))).click();
		//assertTrue(driver.findElement(By.xpath(prop.getProperty("psgUpdateInfo_Title"))).getText().equals(title));
		takeScreenshot(driver,methodName+" preclick "+testcaseNo);
		try {
		driver.findElement(By.xpath(prop.getProperty("uprofile_submit"))).click();
		assertEquals(alert,driver.switchTo().alert().getText());

		driver.switchTo().alert().accept();
		}
		catch (org.openqa.selenium.UnhandledAlertException e) {
		}
		Thread.sleep(1_000);
		//assertTrue(driver.findElement(By.xpath(prop.getProperty("psgUpdateInfo_nomoderror"))).getAttribute("value").equals(noModerror));
		takeScreenshot(driver,methodName+" postclick "+testcaseNo);//
		driver.findElement(By.xpath(prop.getProperty("passenger_logout"))).click();
		Thread.sleep(1_000);
	}
	@Test
	@FileParameters("test/Project/selenium/TC03d_test_cases.csv")
	public void TC03d(int testcaseNo,String psw,String fname,String lname,String phone,String email,String rnum,String dnum,String pswError,String fnameError,String lnameError,String phoneError,String emailError,String rnumError,String dnumError,String sucess) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		
		proj_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		driver.findElement(By.xpath(prop.getProperty("view_pass_profile"))).click();
		//Passenger_function(driver,FunctionPassenger.Viewprofile);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("Passenger_update_profile_link"))).click();
		takeScreenshot(driver,methodName+" preclick "+testcaseNo);
		if(!pswError.equals("")) {
			updateprofileFill(driver,psw,fname,lname,phone,email,rnum,dnum,methodName+" updateFuntionpreclick "+testcaseNo);
			try {
				driver.findElement(By.xpath(prop.getProperty("uprofile_submit"))).click();
				driver.switchTo().alert().accept();
			}
			catch (org.openqa.selenium.UnhandledAlertException e) {
			}
			takeScreenshot(driver,methodName+" updateFuntionpostclick "+testcaseNo);
			verifyupdate(driver,pswError,fnameError,lnameError,phoneError,emailError,rnumError,dnumError);
			driver.findElement(By.xpath(prop.getProperty("uprofile_logout"))).click();
			Thread.sleep(1_000);
		}
		else {
			int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 6;
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (random.nextFloat() * (rightLimit - leftLimit + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String generatedString = buffer.toString();
		    fname=fname+generatedString;
			updateprofileFill(driver,psw,fname,lname,phone,email,rnum,dnum,methodName+" updateFuntionpreclick "+testcaseNo);
			try {
				driver.findElement(By.xpath(prop.getProperty("uprofile_submit"))).click();
				driver.switchTo().alert().accept();
			}
			catch (org.openqa.selenium.UnhandledAlertException e) {
			}
			takeScreenshot(driver,methodName+" updateFuntionpostclick "+testcaseNo);
			//assertTrue(driver.findElement(By.xpath(prop.getProperty("psgInfo_errorMsg"))).getAttribute("value").equals(sucess));
	    	Thread.sleep(1_000);
	    	driver.findElement(By.xpath(prop.getProperty("uprofile_logout"))).click();
			Thread.sleep(1_000);
		}
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
