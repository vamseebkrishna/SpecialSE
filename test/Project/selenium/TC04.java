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
public class TC04 extends Project_Functions{
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
	private Boolean arraysDiff (String [][] array1, String [][] array2) { // this method compares the contents of the two tables
		  Boolean diff=false || (array1.length!=array2.length);
		  for (int i=0;i<array1.length && !diff;i++) {
			 diff  = !array1[i][0].equals(array2[i][0]) || !array1[i][1].equals(array2[i][1]) || 
					 !array1[i][2].equals(array2[i][2]) || !array1[i][3].equals(array2[i][3]) ||
					 !array1[i][4].equals(array2[i][4]) || !array1[i][5].equals(array2[i][5]);
		  }
		  return diff;
	}
	private String [][] getTableContentsFromPage(int listSize) {
		 String [][] eventArray = new String[listSize-1][6];
		 for (int i=0; i<listSize-1; i++) {
			 eventArray[i][0]=driver.findElement(By.xpath(prop.getProperty("esummary_table_row")+(i+2)+
						prop.getProperty("esummary_ename_col"))).getText();
			 eventArray[i][1]=driver.findElement(By.xpath(prop.getProperty("esummary_table_row")+(i+2)+
						prop.getProperty("esummary_edate_col"))).getText();
			 eventArray[i][2]=driver.findElement(By.xpath(prop.getProperty("esummary_table_row")+(i+2)+
						prop.getProperty("esummary_starttime_col"))).getText();
			 eventArray[i][3]=driver.findElement(By.xpath(prop.getProperty("esummary_table_row")+(i+2)+
						prop.getProperty("esummary_duration_col"))).getText();
			 eventArray[i][4]=driver.findElement(By.xpath(prop.getProperty("esummary_table_row")+(i+2)+
						prop.getProperty("esummary_location_col"))).getText();
			 eventArray[i][5]=driver.findElement(By.xpath(prop.getProperty("esummary_table_row")+(i+2)+
						prop.getProperty("esummary_nofattendees_col"))).getText();
		 }
		 return eventArray;
	}
	@Test
	@FileParameters("test/Project/selenium/TC04a_test_cases.csv")
	public void TC04a(int testcaseNo,String date,String time,String error,String header, String type) throws Exception{
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		proj_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		//Passenger_function(driver,FunctionPassenger.ViewEventSummary);
		filldate(driver,date,time,type,methodName+" datefill test case "+testcaseNo);
		if(!error.equals("")) {
		//verifyfilldatePassenger(driver,error);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("emanager_logout"))).click();
		Thread.sleep(1_000);
		}
		else {
			verifypaslistpage(driver,header);
			WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("esummary_table")));
			int rows = eventTable.findElements(By.tagName("tr")).size();
			Thread.sleep(2_000);
			assertTrue(arraysDiff(getTableContentsFromPage(rows),listeventdatepas(date,time,type,rows)));
			driver.findElement(By.xpath(prop.getProperty("esummary_logout"))).click();
		}
	}
	@Test
	@FileParameters("test/Project/selenium/TC04b_test_cases.csv")
	public void TC04b(int testcaseNo,String date,String time,String type,String error,String header) throws Exception{
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		proj_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		//Passenger_function(driver,Functio nPassenger.SearchEventBasedontypedateandtime);
		//("1");
		filldate(driver,date,time,type,methodName+" datefill test case "+testcaseNo);
		if(!error.equals("")) {
		//verifyfilldate(driver,error);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("emanager_logout"))).click();
		Thread.sleep(1_000);
		}
		else {
			verifypaslistpage(driver,header);
			WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("esummary_table")));
			int rows = eventTable.findElements(By.tagName("tr")).size();
			Thread.sleep(2_000);
			assertTrue(arraysDiff(getTableContentsFromPage(rows),listeventdatepastype(date,time,type,rows)));
			driver.findElement(By.xpath(prop.getProperty("esummary_logout"))).click();
		}
	}
	@Test
	@FileParameters("test/Project/selenium/TC04c_test_cases.csv")
	public void TC04c(String testcaseNo,String expectedName,String expectedDate,String expectedStartTime,String expectedDuration,String expectedLocation,String expectedAttendees,String expectedcapacity,String expectedcname,String expectedtype, String selectRadio, String eventdate, String eventtime, String type) throws InterruptedException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		proj_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		//Passenger_function(driver,FunctionPassenger.ViewEventSummary);
		filldate(driver,eventdate,eventtime,type,methodName+" datefill test case "+testcaseNo);
		Thread.sleep(1_000);
		driver.findElement(By.xpath(selectRadio)).click();
		driver.findElement(By.xpath(prop.getProperty("esummary_viewselected"))).click();
		verifystrings10(driver,"specific_ename_col", expectedName,"specific_edate_col", expectedDate,
				 "specific_starttime_col",expectedStartTime,"specific_duration_col",expectedDuration, 
				"specific_location_col",expectedLocation,"specifi"
						+ "c_attendees_col", expectedAttendees, 
				"specific_capacity_col",expectedcapacity,"specific_coordinator_col",expectedcname,
				"specific_type_col", expectedtype,methodName+" verifySpecific2 test case "+testcaseNo);
		driver.findElement(By.xpath(prop.getProperty("specific_logout"))).click();
		Thread.sleep(1_000);
		
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
