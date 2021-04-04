package functions;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.beust.jcommander.internal.Console;

import org.openqa.selenium.TakesScreenshot;

import Project.data.EventDAO;
import Project.model.Event;
import Project.model.User;
import Project.util.SQLConnection;

public class Project_Functions {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	public static WebDriver driver;
	public static Properties prop;
	public enum FunctionCoordinator {ListAllAssignedEvent,ViewEventDate,ViewAssignedDate}
	//public enum FunctionPassenger {ViewAllEvents,Viewmyreservations,Viewprofile,ViewEventSummary,SearchEventBasedontypedateandtime}
	
	public void takeScreenshot(WebDriver driver, String screenshotname) {
		  try
		  {
			  File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);			
			  FileUtils.copyFile(source, new File("./ScreenShots/" + screenshotname +".png"));
		  }
		  catch(IOException e) {}
		  try {
//			  Change the delay value to 1_000 to insert a 1 second delay after 
//			  each screenshot
			  Thread.sleep(1_000);
		} catch (InterruptedException e) {}
	}
	
	public WebDriver invokeCorrectBrowser () {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nikhi\\Downloads\\chromedriver_win32\\chromedriver.exe");
		return new ChromeDriver();
	}
	
	public void proj_registration(WebDriver driver,String username,String first_name, String last_name, String password, String phone,String email,String memtype,String room_number,String deck_number,String snapShotName) throws InterruptedException {
		//driver.findElement(By.xpath(prop.getProperty("login_Registration_Btn"))).click();
	
		driver.findElement(By.xpath(prop.getProperty("register_fname"))).clear();
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("register_fname"))).sendKeys(first_name);
		driver.findElement(By.xpath(prop.getProperty("register_lname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("register_lname"))).sendKeys(last_name);
		driver.findElement(By.xpath(prop.getProperty("register_username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("register_username"))).sendKeys(username);
		driver.findElement(By.xpath(prop.getProperty("register_password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("register_password"))).sendKeys(password);
		driver.findElement(By.xpath(prop.getProperty("register_email"))).clear();
		driver.findElement(By.xpath(prop.getProperty("register_email"))).sendKeys(email);
		driver.findElement(By.xpath(prop.getProperty("register_phone"))).clear();
		driver.findElement(By.xpath(prop.getProperty("register_phone"))).sendKeys(phone);
		driver.findElement(By.xpath(prop.getProperty("register_room_no"))).clear();
		driver.findElement(By.xpath(prop.getProperty("register_room_no"))).sendKeys(room_number);
		driver.findElement(By.xpath(prop.getProperty("register_deck_no"))).clear();
		driver.findElement(By.xpath(prop.getProperty("register_deck_no"))).sendKeys(deck_number);
		//(prop.getProperty("register_mem_type"));
		new Select(driver.findElement(By.xpath(prop.getProperty("register_mem_type")))).selectByVisibleText(memtype);
		driver.findElement(By.xpath(prop.getProperty("register_submit"))).click();
		takeScreenshot(driver,snapShotName);
	}
	public void verifyRegistration(WebDriver driver,String errorMsg,String usernameError,String first_nameError,String last_nameError,String passwordError,String phoneError,String emailError,String room_numberError,String deck_numberError) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("register_error"))).getAttribute("value").equals(errorMsg));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("register_username_error"))).getAttribute("value").equals(usernameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("register_fname_error"))).getAttribute("value").equals(first_nameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("register_lname_error"))).getAttribute("value").equals(last_nameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("register_password_error"))).getAttribute("value").equals(passwordError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("register_phone_error"))).getAttribute("value").equals(phoneError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("register_email_error"))).getAttribute("value").equals(emailError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("register_room_no_error"))).getAttribute("value").equals(room_numberError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("register_deck_no_error"))).getAttribute("value").equals(deck_numberError));
	}
	public void proj_login(WebDriver driver, String sUserName, String sPassword,String snapShotName) throws InterruptedException {
		
		driver.findElement(By.xpath(prop.getProperty("login_username"))).clear();
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("login_username"))).sendKeys(sUserName);
		driver.findElement(By.xpath(prop.getProperty("login_password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("login_password"))).sendKeys(sPassword);
		driver.findElement(By.xpath(prop.getProperty("login_submit"))).click();
		Thread.sleep(1_000);
		takeScreenshot(driver,snapShotName);
	}
	public void verifyLogin(WebDriver driver,String errorMsg1,String errorMsg2) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("login_password_error"))).getAttribute("value").equals(errorMsg2));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("login_username_error"))).getAttribute("value").equals(errorMsg1));
	}
	
	public void Coordinator_function(WebDriver driver,FunctionCoordinator function) {
		switch (function) {
		case ListAllAssignedEvent:
			
			driver.findElement(By.xpath(prop.getProperty("event_submit"))).click();
			break;

		}
	}

	

	public static String [][]  listcorevents(String name, String date, String time, int rows) throws SQLException {
		String query = "SELECT * FROM cruise_activity.events where EVENT_DATE > '"+date+"' && START_TIME > '"+time+"' && COORDINATOR = '"+name+"'";
		ArrayList<Event> UfromDB= ReturnMatchingEventsList(query);
		String [][] arrayDB = new String [rows-1][6];
		int i=0;
		for(Event p:UfromDB) {
			arrayDB[i][0]=p.getM_event_name();
			arrayDB[i][1]=p.getM_event_date();
			arrayDB[i][2]=p.getM_start_time();
			arrayDB[i][3]=p.getM_duration();
			arrayDB[i][4]=p.getM_location();
			arrayDB[i][5]=p.getM_numberofattendees();
			i++;
		}
		for (String[] strings : arrayDB) {
			//(strings);
		}
		return arrayDB;
	}
	
	private static ArrayList<Event> ReturnMatchingEventsList (String queryString) {
		ArrayList<Event> eventListInDB = new ArrayList<Event>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet eventList = stmt.executeQuery(queryString);
			while (eventList.next()) {
				Event event = new Event(); 
				event.setM_event_name(eventList.getString("EVENT_NAME"));
				event.setM_event_date(eventList.getString("EVENT_DATE"));
				event.setM_start_time(eventList.getString("START_TIME"));
				event.setM_duration(eventList.getString("DURATION"));
				event.setM_location(eventList.getString("LOCATION"));
				event.setM_numberofattendees(eventList.getString("ATTENDEES"));
				event.setM_capacity(eventList.getString("CAPACITY"));
				event.setM_eventcoordinator(eventList.getString("COORDINATOR"));
				event.setM_type(eventList.getString("TYPE"));
				
				 
				eventListInDB.add(event);	
			}
		} catch (SQLException e) {}
		//(eventListInDB);
		return eventListInDB;
	}
	
	public void verifystrings10(WebDriver driver,String specific_ename_col, String expectedName,String specific_edate_col, String expectedDate,
			String specific_starttime_col, String expectedStartTime,String specific_duration_col, String expectedDuration, 
			String specific_location_col, String expectedLocation, String specific_attendees_col, String expectedAttendees, 
			String specific_capacity_col, String expectedcapacity,String specific_coordinator_col, String expectedcname,
			String specific_type_col, String expectedtype,String snapShotName){
		assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_ename_col"))).getText().equals(expectedName));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_edate_col"))).getText().equals(expectedDate));
	    //(driver.findElement(By.xpath(prop.getProperty("specific_starttime_col"))).getText());
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_starttime_col"))).getText().equals(expectedStartTime));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_duration_col"))).getText().equals(expectedDuration));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_location_col"))).getText().equals(expectedLocation));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_attendees_col"))).getText().equals(expectedAttendees));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_capacity_col"))).getText().equals(expectedcapacity));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_coordinator_col"))).getText().equals(expectedcname));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_type_col"))).getText().equals(expectedtype));
	   // assertTrue(driver.findElement(By.xpath(prop.getProperty(header10OnPage))).getText().equals(expectedHeader10Name));
	    takeScreenshot(driver,snapShotName);
		
	}
	
	
	public void verifystrings10C(WebDriver driver,String specific_event_name, String expectedName,String specific_event_date, String expectedDate,
			String specific_event_start_time, String expectedStartTime,String specific_event_duration, String expectedDuration, 
			String specific_event_location, String expectedLocation, String specific_event_attendees, String expectedAttendees, 
			String specific_event_capacity, String expectedcapacity,String specific_event_cname, String expectedcname,
			String specific_event_type, String expectedtype,String snapShotName){
		assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_event_name"))).getText().equals(expectedName));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_event_date"))).getText().equals(expectedDate));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_event_start_time"))).getText().equals(expectedStartTime));
	   // //(driver.findElement(By.xpath(prop.getProperty("specific_starttime_col"))).getText());
	  //  assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_starttime_col"))).getText().equals(expectedStartTime));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_event_duration"))).getText().equals(expectedDuration));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_event_location"))).getText().equals(expectedLocation));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_event_attendees"))).getText().equals(expectedAttendees));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_event_capacity"))).getText().equals(expectedcapacity));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_event_cname"))).getText().equals(expectedcname));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("specific_event_type"))).getText().equals(expectedtype));
	   // assertTrue(driver.findElement(By.xpath(prop.getProperty(header10OnPage))).getText().equals(expectedHeader10Name));
	    takeScreenshot(driver,snapShotName);
		
	}
	
	public void filldate(WebDriver driver,String date,String time,String type,String snapShotName) throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("passenger_edate"))).clear();
		driver.findElement(By.xpath(prop.getProperty("passenger_edate"))).sendKeys(date);
		driver.findElement(By.xpath(prop.getProperty("passenger_etime"))).clear();
		driver.findElement(By.xpath(prop.getProperty("passenger_etime"))).sendKeys(time);
		driver.findElement(By.xpath(prop.getProperty("passenger_etype"))).clear();
		driver.findElement(By.xpath(prop.getProperty("passenger_etype"))).sendKeys(type);
		driver.findElement(By.xpath(prop.getProperty("passenger_submit"))).click();
		Thread.sleep(1_000);
		takeScreenshot(driver,snapShotName);
	}
	
	public void verifyRegister(WebDriver driver,String error, String error1,String error2) {
		//driver.findElement(By.xpath(prop.getProperty("error1"))).clear();
		assertTrue(driver.findElement(By.xpath(prop.getProperty("res_error1"))).getAttribute("value").equals(error));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("res_error2"))).getAttribute("value").equals(error1));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("res_attendees"))).getAttribute("value").equals(error2));
		//takeScreenshot(driver,snapShotName);
	}
	
	
	public void filldateC(WebDriver driver,String date,String time,String snapShotName) {
		driver.findElement(By.xpath(prop.getProperty("event_date"))).clear();
		driver.findElement(By.xpath(prop.getProperty("event_date"))).sendKeys(date);
		driver.findElement(By.xpath(prop.getProperty("event_time"))).clear();
		driver.findElement(By.xpath(prop.getProperty("event_time"))).sendKeys(time);
		driver.findElement(By.xpath(prop.getProperty("event_submit"))).click();
		
		takeScreenshot(driver,snapShotName);
	}
	
	public void verifyfilldate(WebDriver driver,String errorMsg1, String errorMsg2) {
		////(driver.findElement(By.xpath(prop.getProperty("event_date_error"))).getAttribute("value"));
		////(driver.findElement(By.xpath(prop.getProperty("event_time_error"))).getAttribute("value"));
		assertTrue(driver.findElement(By.xpath("html/body/table/tbody/tr/td/form/table/tbody/tr[3]/td[3]/input")).getAttribute("value").equals(errorMsg1));
		assertTrue(driver.findElement(By.xpath("html/body/table/tbody/tr/td/form/table/tbody/tr[4]/td[3]/input")).getAttribute("value").equals(errorMsg2));
		
	}
	public void verifyfilldateC(WebDriver driver,String errorMsg1, String errorMsg2) {
		////(driver.findElement(By.xpath(prop.getProperty("event_date_error"))).getAttribute("value"));
		////(driver.findElement(By.xpath(prop.getProperty("event_time_error"))).getAttribute("value"));
		//(driver.findElement(By.xpath("html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td[3]/input")).getAttribute("value"));
		//(driver.findElement(By.xpath("html/body/table/tbody/tr/td/form/table/tbody/tr[3]/td[3]/input")).getAttribute("value"));
		assertTrue(driver.findElement(By.xpath("html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td[3]/input")).getAttribute("value").equals(errorMsg1));
		assertTrue(driver.findElement(By.xpath("html/body/table/tbody/tr/td/form/table/tbody/tr[3]/td[3]/input")).getAttribute("value").equals(errorMsg2));
	}
	public void verifyfilldate(WebDriver driver,String errorMsg) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_error"))).getAttribute("value").equals(errorMsg));
	}
	public void verifyfilldatePassenger(WebDriver driver,String errorMsg) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("coordinatorviewAsgneventsummary_error"))).getAttribute("value").equals(errorMsg));
	}
	
	public void verifypaslistpage(WebDriver driver,String header) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("esummary_head"))).getText().equals(header));
	}
	
	public void verifystrings10(WebDriver driver,String header1OnPage, String expectedHeader1Name,String header2OnPage, String expectedHeader2Name,
			String header3OnPage, String expectedHeader3Name,String header4OnPage, String expectedHeader4Name, 
			String header5OnPage, String expectedHeader5Name, String header6OnPage, String expectedHeader6Name, 
			String header7OnPage, String expectedHeader7Name,String header8OnPage, String expectedHeader8Name,String header9OnPage, String expectedHeader9Name,
			String header10OnPage, String expectedHeader10Name,String snapShotName){
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header1OnPage))).getText().equals(expectedHeader1Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header2OnPage))).getText().equals(expectedHeader2Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header3OnPage))).getText().equals(expectedHeader3Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header4OnPage))).getText().equals(expectedHeader4Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header5OnPage))).getText().equals(expectedHeader5Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header6OnPage))).getText().equals(expectedHeader6Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header7OnPage))).getText().equals(expectedHeader7Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header8OnPage))).getText().equals(expectedHeader8Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header9OnPage))).getText().equals(expectedHeader9Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header10OnPage))).getText().equals(expectedHeader10Name));
	    takeScreenshot(driver,snapShotName);
		
	}
	
	public void verifypassengerHomepage(WebDriver driver,String header1,String header2,String header3,String header4) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("passenger_head1"))).getText().equals(header1));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("passenger_head2"))).getText().equals(header2));
		//(prop.getProperty("passenger_enter_date"));
		////(hea);
		//assertTrue(driver.findElement(By.xpath(prop.getProperty("passenger_enter_date"))).getText().equals(header3));
		//assertTrue(driver.findElement(By.xpath(prop.getProperty("passenger_enter_time"))).getText().equals(header4));
		//assertTrue(driver.findElement(By.xpath(prop.getProperty("psg_homepg_srchEventonDateTime_link"))).getText().equals(header5));
	}
	
	public void filldatetype(WebDriver driver,String date,String time,String type,String snapShotName) throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("event_date"))).clear();
		driver.findElement(By.xpath(prop.getProperty("event_date"))).sendKeys(date);
		driver.findElement(By.xpath(prop.getProperty("event_time"))).clear();
		driver.findElement(By.xpath(prop.getProperty("event_time"))).sendKeys(time);
		driver.findElement(By.xpath(prop.getProperty("event_submit"))).click();
		takeScreenshot(driver,snapShotName);
	}
	
	public static String [][] listeventdatepastype(String date,String time,String type,int listSize) throws SQLException, ParseException{
		String OLD_FORMAT = "MM/dd/yyyy";
		String NEW_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = sdf.parse(date);
		sdf.applyPattern(NEW_FORMAT);
		date = sdf.format(d);
		//SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
	    //SimpleDateFormat parseFormat = new SimpleDateFormat("hhmma");
	    //Date t = parseFormat.parse(time);
	    //time=displayFormat.format(t);
		ArrayList<Event> UfromDB=ReturnMatchingEventsList("SELECT * FROM cruise_activity.events where EVENT_DATE > '"+date+"' && START_TIME > '"+time+"' && TYPE ='"+type+"'");
		//UfromDB.addAll(ReturnMatchingEventsList("SELECT * FROM cruise_activity.events where EVENT_DATE > '"+date+"' && START_TIME > '"+time+"' && TYPE ='"+type+"'"));
		String [][] arrayDB = new String [listSize-1][6];
//		for(int j=0;j<UfromDB.size();j++) {
//			ArrayList<reserve> list=new ArrayList<reserve>();
//			list=ReturnMatchingreservelist("SELECT * FROM ship.reserve where eventcreateid="+UfromDB.get(j).getIdcreate());
//			UfromDB.get(j).setEstCap(String.valueOf(Integer.parseInt(UfromDB.get(j).getCapacity())-list.size()));
//			
//		}
		int i=0;
		for(Event p:UfromDB) {
			arrayDB[i][0]=p.getM_event_name();
			arrayDB[i][1]=p.getM_event_date();
			arrayDB[i][2]=p.getM_start_time();
			arrayDB[i][3]=p.getM_duration();
			arrayDB[i][4]=p.getM_location();
			arrayDB[i][5]=p.getM_capacity();
			i++;
		}
		return arrayDB;
	}
	
	public void Passenger_function(WebDriver driver,String header1,String header2,String header3,String header4,String header5,String header6,String header7,String header8) {
	  //  assertTrue(driver.findElement(By.xpath(prop.getProperty("prof_username"))).getText().equals(header1));
	    //(driver.findElement(By.xpath(prop.getProperty("prof_lname"))).getText());
	    //(header2);
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("prof_lname"))).getText().equals(header2));
	
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("prof_fname"))).getText().equals(header3));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("prof_phone"))).getText().equals(header4));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("prof_email"))).getText().equals(header5));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("prof_rno"))).getText().equals(header6));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("prof_deckno"))).getText().equals(header7));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("prof_mem"))).getText().equals(header8));
	    
	    
			
		}
	
	
	public void verifystrings8(WebDriver driver,String header2OnPage, String expectedHeader2Name,
			String header3OnPage, String expectedHeader3Name,String header4OnPage, String expectedHeader4Name, 
			String header5OnPage, String expectedHeader5Name, String header6OnPage, String expectedHeader6Name, 
			String header7OnPage, String expectedHeader7Name,String header8OnPage, String expectedHeader8Name,String snapShotName){
		//assertTrue(driver.findElement(By.xpath(prop.getProperty(header1OnPage))).getText().equals(expectedHeader1Name));
		//(driver.findElement(By.xpath(prop.getProperty(header2OnPage))).getText());
		//(expectedHeader2Name);
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header2OnPage))).getText().equals(expectedHeader2Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header3OnPage))).getText().equals(expectedHeader3Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header4OnPage))).getText().equals(expectedHeader4Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header5OnPage))).getText().equals(expectedHeader5Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header6OnPage))).getText().equals(expectedHeader6Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header7OnPage))).getText().equals(expectedHeader7Name));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(header8OnPage))).getText().equals(expectedHeader8Name));
	    takeScreenshot(driver,snapShotName);
	}
	
	public void updateprofileFill(WebDriver driver,String password,String first_name, String last_name,String phone,String email,String room_number,String deck_number,String snapShotName) throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("uprofile_password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("uprofile_password"))).sendKeys(password);
		driver.findElement(By.xpath(prop.getProperty("uprofile_fname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("uprofile_fname"))).sendKeys(first_name);
		driver.findElement(By.xpath(prop.getProperty("uprofile_lname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("uprofile_lname"))).sendKeys(last_name);
		driver.findElement(By.xpath(prop.getProperty("uprofile_email"))).clear();
		driver.findElement(By.xpath(prop.getProperty("uprofile_email"))).sendKeys(email);
		driver.findElement(By.xpath(prop.getProperty("uprofile_phone"))).clear();
		driver.findElement(By.xpath(prop.getProperty("uprofile_phone"))).sendKeys(phone);
		driver.findElement(By.xpath(prop.getProperty("uprofile_rno"))).clear();
		driver.findElement(By.xpath(prop.getProperty("uprofile_rno"))).sendKeys(room_number);
		driver.findElement(By.xpath(prop.getProperty("uprofile_deckno"))).clear();
		driver.findElement(By.xpath(prop.getProperty("uprofile_deckno"))).sendKeys(deck_number);
//		driver.findElement(By.xpath(prop.getProperty("psgUpdateInfo_saveProfile_btn"))).click();
		Thread.sleep(1_000);
		takeScreenshot(driver,snapShotName);
	}
	
	public void verifyupdate(WebDriver driver,String passwordError,String first_nameError, String last_nameError,String phoneError,String emailError,String room_numberError,String deck_numberError) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("uprofile_password_err"))).getAttribute("value").equals(passwordError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("uprofile_fname_err"))).getAttribute("value").equals(first_nameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("uprofile_lname_err"))).getAttribute("value").equals(last_nameError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("uprofile_phone_err"))).getAttribute("value").equals(phoneError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("uprofile_email_err"))).getAttribute("value").equals(emailError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("uprofile_rno_err"))).getAttribute("value").equals(room_numberError));
		assertTrue(driver.findElement(By.xpath(prop.getProperty("uprofile_deckno_err"))).getAttribute("value").equals(deck_numberError));
		
	}
	
	public static ArrayList<User> returnMatcingusers(String queryString){
		ArrayList<User> userListInDB=new ArrayList<User>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList=stmt.executeQuery(queryString);
			while(userList.next()) {
				User user=new User();
				//user.setId_user(userList.getInt("id_used"));
				user.setUsername(userList.getString("username"));
				user.setFirstname(userList.getString("first_name"));
				user.setLastname(userList.getString("last_name"));
				user.setMemtype(userList.getString("role"));
				user.setPassword(userList.getString("password"));
				user.setRoomno(String.valueOf(userList.getInt("room_number")));
				user.setPhone(userList.getString("phone"));
				user.setDeckno(String.valueOf(userList.getInt("decknumber")));
				//user.setMemtype(userList.getString("memtype"));
				user.setEmail(userList.getString("email"));
				userListInDB.add(user);
			}
		} catch (SQLException e) {}
		return userListInDB;
	}
	
	public User userinformation(String name) {
		ArrayList<User> fromDB=returnMatcingusers("SELECT * from user WHERE username='"+name+"'");
		User seluser=new User();
		seluser.setUser(fromDB.get(0).getUsername(), fromDB.get(0).getFirstname(), fromDB.get(0).getLastname(), fromDB.get(0).getPassword(), fromDB.get(0).getMemtype(), fromDB.get(0).getPhone(), fromDB.get(0).getEmail(), fromDB.get(0).getRoomno(), fromDB.get(0).getDeckno());
		//seluser.setId_user(fromDB.get(0).getId_user());
		return seluser;
	}
	
	public static String [][] listeventdatepas(String date,String time,String type,int listSize) throws SQLException, ParseException{
		String OLD_FORMAT = "MM/dd/yyyy";
		String NEW_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = sdf.parse(date);
		sdf.applyPattern(NEW_FORMAT);
		date = sdf.format(d);
		//SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
	    //SimpleDateFormat parseFormat = new SimpleDateFormat("hhmma");
	    //Date t = parseFormat.parse(time);
	    //time=displayFormat.format(t);
		ArrayList<Event> UfromDB=ReturnMatchingEventsList("SELECT * FROM cruise_activity.events where EVENT_DATE > '"+date+"' && START_TIME > '"+time+"' && TYPE = '"+type+"'");
		//UfromDB.addAll(ReturnMatchingEventsList("SELECT * FROM cruise_activity.events where EVENT_DATE > '"+date+"' && START_TIME > '"+time+"'"));
		String [][] arrayDB = new String [listSize-1][6];
//		for(int j=0;j<UfromDB.size();j++) {
//			ArrayList<reserve> list=new ArrayList<reserve>();
//			list=ReturnMatchingreservelist("SELECT * FROM ship.reserve where eventcreateid="+UfromDB.get(j).getIdcreate());
//			UfromDB.get(j).setEstCap(String.valueOf(Integer.parseInt(UfromDB.get(j).getCapacity())-list.size()));
//			
//		}
		int i=0;
		for(Event p:UfromDB) {
			arrayDB[i][0]=p.getM_event_name();
			arrayDB[i][1]=p.getM_event_date();
			arrayDB[i][2]=p.getM_start_time();
			arrayDB[i][3]=p.getM_duration();
			arrayDB[i][4]=p.getM_location();
			arrayDB[i][5]=p.getM_capacity();
			i++;
		}
		return arrayDB;
	}


	
}
