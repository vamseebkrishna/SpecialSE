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
import functions.Project_Functions.FunctionCoordinator;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TC06 extends Project_Functions{
	private StringBuffer verificationErrors = new StringBuffer();
	public String sAppURL, sSharedUIMapPath, testDelay,username,password;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);	  
	    }
	
	@Before
	public void setUp() throws Exception {
		driver = invokeCorrectBrowser();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    prop = new Properties();
	    prop.load(new FileInputStream("./Login/Login.properties"));
	    username = prop.getProperty("manager_username");
		password = prop.getProperty("manager_password");
	    prop.load(new FileInputStream("./Configuration/SP_Configuration.properties"));
	    sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");
		testDelay=prop.getProperty("testDelay");
		prop.load(new FileInputStream(sSharedUIMapPath));
	    }
	
	 private Boolean compareArray (String [][] array1, String [][] array2) { // this method compares the contents of the two tables
		  Boolean diff=false || (array1.length!=array2.length);
		  for (int i=0;i<array1.length && !diff;i++) {
			 diff  = !array1[i][0].equals(array2[i][0]) || !array1[i][1].equals(array2[i][1]) || 
					 !array1[i][2].equals(array2[i][2]) || !array1[i][3].equals(array2[i][3]) ||
					 !array1[i][4].equals(array2[i][4]);
		  }
		  return diff;
	  }
	 
	 private String [][] getTableContentsFromPage(int listSize) {
		 String fdate = null;
		 String [][] eventArray = new String[listSize-1][6];
		 for (int i=0; i<listSize-1; i++) {
			 String date = driver.findElement(By.xpath(prop.getProperty("tableHeader")+(i+2)+
						prop.getProperty("eventDateCol"))).getText();
			 DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
				try {
					Date dateret = (Date) parser.parse(date);
				
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					fdate = formatter.format(dateret);
				
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 
			 eventArray[i][0]=driver.findElement(By.xpath(prop.getProperty("tableHeader")+(i+2)+
						prop.getProperty("eventNameCol"))).getText();
			 eventArray[i][1]=fdate;
			 eventArray[i][2]=driver.findElement(By.xpath(prop.getProperty("tableHeader")+(i+2)+
						prop.getProperty("eventStarttimeCol"))).getText();
			 eventArray[i][3]=driver.findElement(By.xpath(prop.getProperty("tableHeader")+(i+2)+
						prop.getProperty("eventDurationCol"))).getText();
			 eventArray[i][4]=driver.findElement(By.xpath(prop.getProperty("tableHeader")+(i+2)+
						prop.getProperty("eventLocationCol"))).getText();
			 eventArray[i][5]=driver.findElement(By.xpath(prop.getProperty("tableHeader")+(i+2)+
						prop.getProperty("eventAttendeesCol"))).getText();
		 }
		 for (String[] strings : eventArray) {
			//(strings);
		}
		 return eventArray;
	 }
	 @Test
		@FileParameters("test/Project/selenium/TC06a_testt_cases.csv")
		public void TC06a(String testcaseNo,String eventname,String eventdate,String starttime,String duration,String location,String attendees, String header) throws Exception{
			String methodName= new Throwable().getStackTrace()[0].getMethodName();
			driver.get(sAppURL);
			//(prop.getProperty("login_btn"));
			driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
			proj_login(driver,username,password,methodName+" CoordinatorHomePage");
			driver.findElement(By.xpath(prop.getProperty("event_date"))).clear();
			driver.findElement(By.xpath(prop.getProperty("event_date"))).sendKeys(eventdate);
			driver.findElement(By.xpath(prop.getProperty("event_time"))).clear();
			driver.findElement(By.xpath(prop.getProperty("event_time"))).sendKeys(starttime);
			String feventDate=" ";
			
			DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Date dateret = (Date) parser.parse(eventdate);
			
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    feventDate = formatter.format(dateret);
			
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Coordinator_function(driver,FunctionCoordinator.ListAllAssignedEvent);
			Thread.sleep(1_000);
			assertTrue(driver.findElement(By.xpath(prop.getProperty("esummary_head"))).getText().equals(header));
			
			driver.findElement(By.xpath(prop.getProperty("eventsumry_logout"))).click();
		}
	 @Test
		@FileParameters("test/Project/selenium/TC06b_test_cases.csv")
		public void TC06b(String testcaseNo,String expectedName,String expectedDate,String expectedStartTime,String expectedDuration,String expectedLocation,String expectedAttendees,String expectedcapacity,String expectedcname,String expectedtype, String selectRadio, String eventdate, String eventtime) throws InterruptedException {
			String methodName= new Throwable().getStackTrace()[0].getMethodName();
			driver.get(sAppURL);
			//(prop.getProperty("login_btn"));
			driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
			proj_login(driver,username,password,methodName+" CoordinatorHomePage");
			driver.findElement(By.xpath(prop.getProperty("event_date"))).clear();
			driver.findElement(By.xpath(prop.getProperty("event_date"))).sendKeys(eventdate);
			driver.findElement(By.xpath(prop.getProperty("event_time"))).clear();
			driver.findElement(By.xpath(prop.getProperty("event_time"))).sendKeys(eventtime);
			Coordinator_function(driver,FunctionCoordinator.ListAllAssignedEvent);
			//(selectRadio);
			Thread.sleep(1_000);
			driver.findElement(By.xpath(selectRadio)).click();
			driver.findElement(By.xpath(prop.getProperty("submitchoice"))).click();
		//	verifystrings10(driver,"coordinatorspecificlist_EventnameRow",col2,"coordinatorspecificlist_LocationRow",col3,"coordinatorspecificlist_CapacityRow",col4,"coordinatorspecificlist_EstimatedRow",col5,"coordinatorspecificlist_DurationRow",col6,"coordinatorspecificlist_TypeRow",col7,"coordinatorspecificlist_DateRow",col8,
			//		"coordinatorspecificlist_TimeRow",col9,"coordinatorspecificlist_CoordinatorFname",col10,"coordinatorspecificlist_CoordinatorLname",col11,methodName+" verifySpecific2 test case "+testcaseNo);
			verifystrings10C(driver,"specific_event_name", expectedName,"specific_event_date", expectedDate,
					 "specific_event_start_time",expectedStartTime,"specific_event_duration",expectedDuration, 
					"specific_event_location",expectedLocation,"specific_event_attendees", expectedAttendees, 
					"specific_event_capacity",expectedcapacity,"specific_event_cname",expectedcname,
					"specific_event_type", expectedtype,methodName+" verifySpecific2 test case "+testcaseNo);
			driver.findElement(By.xpath(prop.getProperty("sevent_logout"))).click();
		}
	 @Test
		@FileParameters("test/Project/selenium/TC06c_test.csv")
		public void TC06c(String testcaseNo,String date,String time,String error1,String error2) throws InterruptedException, SQLException, ParseException {
			String methodName= new Throwable().getStackTrace()[0].getMethodName();
			driver.get(sAppURL);
			driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
			proj_login(driver,username,password,methodName+" CoordinatorHomePage");
			//Coordinator_function(driver,FunctionCoordinator.ViewEventDate);
			filldateC(driver,date,time,methodName+" datefill test case "+testcaseNo);
			if(!error1.equals("") || !error2.equals("")) {
			verifyfilldateC(driver,error1, error2);
			}
			else {
				//verifycorlistpage(driver,header);
				WebElement eventTable=driver.findElement(By.xpath(prop.getProperty("event_summary_for_c_table")));
				int rows = eventTable.findElements(By.tagName("tr")).size();
				Thread.sleep(2_000);
				//assertFalse(compareArray(getTableContentsFromPage(rows),listeventdate(date,time,rows)));
				driver.findElement(By.xpath(prop.getProperty("coordinatoreventpg_Logout_Btn"))).click();
			}
		}
	 @Test
		@FileParameters("test/Project/selenium/TC06d_test_cases.csv")
	 public void TC06d(String testcaseNo, String selectRadio, String eventdate, String eventtime, String updated_date, String updated_time) throws InterruptedException, SQLException, ParseException {
			String methodName= new Throwable().getStackTrace()[0].getMethodName();
			driver.get(sAppURL);
			driver.findElement(By.xpath(prop.getProperty("login_btn"))).click();
			proj_login(driver,username,password,methodName+" CoordinatorHomePage");
			driver.findElement(By.xpath(prop.getProperty("event_date"))).clear();
			driver.findElement(By.xpath(prop.getProperty("event_date"))).sendKeys(eventdate);
			driver.findElement(By.xpath(prop.getProperty("event_time"))).clear();
			driver.findElement(By.xpath(prop.getProperty("event_time"))).sendKeys(eventtime);
			//Coordinator_function(driver,FunctionCoordinator.ViewEventDate);
			Coordinator_function(driver,FunctionCoordinator.ListAllAssignedEvent);
			//(selectRadio);
			Thread.sleep(1_000);
			driver.findElement(By.xpath(selectRadio)).click();
			driver.findElement(By.xpath(prop.getProperty("esummary_viewselected"))).click();
			driver.findElement(By.xpath(prop.getProperty("specific_modify"))).click();
			//assertTrue(driver.findElement(By.xpath(prop.getProperty("modify_heading"))).getText().equals(header));
			Thread.sleep(1_000);
			driver.findElement(By.xpath(prop.getProperty("modify_edate"))).clear();
			driver.findElement(By.xpath(prop.getProperty("modify_edate"))).sendKeys(updated_date);
			
			driver.findElement(By.xpath(prop.getProperty("modify_submit"))).click();
			try {
				driver.findElement(By.xpath(prop.getProperty("uprofile_submit"))).click();
				//assertEquals(alert,driver.switchTo().alert().getText());

				driver.switchTo().alert().accept();
				}
				catch (org.openqa.selenium.UnhandledAlertException e) {
				}
			Thread.sleep(1_000);
			//assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_event_date"))).getText().equals(updated_date));
			driver.findElement(By.xpath(prop.getProperty("eventsumry_logout"))).click();
			
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
