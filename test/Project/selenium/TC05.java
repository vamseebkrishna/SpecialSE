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
public class TC05 extends Project_Functions{
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
	private String [][] getTableContentsFromPage(int listSize) {
		 String [][] eventArray = new String[listSize-1][6];
		 for (int i=0; i<listSize-1; i++) {
			 eventArray[i][0]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
						prop.getProperty("psgViewAllEvents_EventNameCol"))).getText();
			 eventArray[i][1]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
						prop.getProperty("psgViewAllEvents_EventDateCol"))).getText();
			 eventArray[i][2]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
						prop.getProperty("psgViewAllEvents_StartTimeCol"))).getText();
			 eventArray[i][3]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
						prop.getProperty("psgViewAllEvents_DurationCol"))).getText();
			 eventArray[i][4]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
						prop.getProperty("psgViewAllEvents_LocationCol"))).getText();
			 eventArray[i][5]=driver.findElement(By.xpath(prop.getProperty("psgViewAllEvents_EventsTable_Partial")+(i+2)+
						prop.getProperty("psgViewAllEvents_AvlSeatsCol"))).getText();
		 }
		 return eventArray;
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

	@Test
	@FileParameters("test/Project/selenium/TC05a_test_cases.csv")
	public void TC05a(int testcaseNo,String date,String time,String type,String error,String error1, String error2,String selectRadio) throws InterruptedException, SQLException, ParseException {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
		proj_login(driver,username,password,methodName+" PassengerHomePage "+testcaseNo);
		//Passenger_function(driver,FunctionPassenger.Viewmyreservations);
		//verifyRegistration
		filldate(driver,date,time,type,methodName+" datefill test case "+testcaseNo);
			//verifypaslistpage(driver,header);
			WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("esummary_table")));
			int rows = eventTable.findElements(By.tagName("tr")).size();
			Thread.sleep(2_000);
			//assertTrue(arraysDiff(getTableContentsFromPage(rows),listeventdatepastype(date,time,type,rows)));
			driver.findElement(By.xpath(selectRadio)).click();
			driver.findElement(By.xpath(prop.getProperty("esummary_viewselected"))).click();
			driver.findElement(By.xpath(prop.getProperty("register_event_link"))).click();
			if(!error.isEmpty()) {
			verifyRegister(driver, error, error1, error2);
			//driver.findElement(By.xpath(prop.getProperty("res_submit"))).click();
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
