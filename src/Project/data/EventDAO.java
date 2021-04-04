package Project.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import Project.model.Event;
import Project.model.User;
import Project.util.SQLConnection;

public  class EventDAO {

	private static final Logger LOGGER = Logger.getLogger(EventDAO.class.getName());
	   
	static SQLConnection DBMgr = SQLConnection.getInstance();

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
	public static String selectcrd(String firstname,String lastname) {  
		String cuname = null;
		Statement stmt = null;   
		Connection conn = null;  
		try {
			conn = SQLConnection.getDBConnection();  
			stmt = conn.createStatement();
			String cusername="Select USERNAME from cruise_activity.system_user where FIRSTNAME = '"+firstname+"' and LASTNAME = '"+lastname+"'";
			ResultSet cuser = stmt.executeQuery(cusername);
			while (cuser.next()) {
			cuname = cuser.getString("USERNAME");
			//(cuname);
			
			
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuname;
	}
	
	public static void update(String cuname, String oldcname, String evename, String evedate, String evetime) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		//(" UPDATE cruise_activity.events SET COORDINATOR='"+cuname+"' WHERE COORDINATOR ='"+oldcname+"' && EVENT_NAME = '"+evename+"' && EVENT_DATE = '"+evedate+"' && START_TIME = '"+evetime+"' ");
		try {
		  
		stmt = conn.createStatement();
		int rows = stmt.executeUpdate(" UPDATE cruise_activity.events SET COORDINATOR='"+cuname+"' WHERE COORDINATOR ='"+oldcname+"' && EVENT_NAME = '"+evename+"' && EVENT_DATE = '"+evedate+"' && START_TIME = '"+evetime+"' ");
		conn.commit();
		//(rows);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
		  
	

	/*
	 * public static ArrayList<Event> searchEvent(String eventDate, String
	 * eventTime) { events = new ArrayList<Event>(); Event event = new Event();
	 * event.setEvent("Bowling 1", "09/17/2018", "23:59", "60", "10", "10",
	 * "lakshmi", "Atheletic", "Deck 8"); events.add(event);
	 * event.setEvent("Movie 1", "09/18/2018", "23:00", "25", "25", "25", "lakshmi",
	 * "Show", "Deck 3"); events.add(event); event.setEvent("Extreme Zipline",
	 * "09/17/2018", "22:00", "15", "15", "15", "Bhavana", "Atheletic", "Deck 15");
	 * events.add(event); event.setEvent("Skycourse Ropes", "09/17/2018", "21:00",
	 * "30", "8", "8", "Bhavana", "Athletic", "Deck 14"); events.add(event);
	 * event.setEvent("Ice Skating", "09/19/2018", "19:30", "60", "35", "35",
	 * "lakshmi", "Athletic", "Deck 9"); events.add(event); return events; }
	 */ 
	
	public static ArrayList<Event>  searchEvent(String eventDate, String eventTime ) {  
//		DateFormat d = new SimpleDateFormat("MM/dd/yyyy");
//		Date x = null;
//		try {
//			x = (Date) d.parse(eventDate);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		String evedate = df.format(x);
		String query = "SELECT * FROM cruise_activity.events where EVENT_DATE > '"+eventDate+"' && START_TIME > '"+eventTime+"'";	
		//(query);
		return ReturnMatchingEventsList(query);
	}
	
	public static ArrayList<Event>  searchEventForPassenger(String eventDate, String eventTime,String eventType ) {  
		String query = "SELECT * FROM cruise_activity.events where EVENT_DATE > '"+eventDate+"' && START_TIME > '"+eventTime+"' && TYPE ='"+eventType+"'";	
		//(query);
		return ReturnMatchingEventsList(query);
	}
	
	public static void insertEvent (Event event) {
		//("Inside insertEvent of EventDAO");
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();

			
			String old_date=event.getM_event_date();
			String feventDate=" ";
			
			DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Date date = (Date) parser.parse(old_date);
			
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    feventDate = formatter.format(date);
			
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String insertEvent = "INSERT INTO cruise_activity.events VALUES ('"  
					+ event.getM_event_name()  + "','"
					+ feventDate + "','"		
					+ event.getM_start_time() + "','"
					+ event.getM_duration()+"','"
					+ event.getM_location() + "','"
					+ event.getM_numberofattendees() + "','"
					+ event.getM_capacity() + "','"
					+ event.getM_eventcoordinator() + "','"
					+ event.getM_type() + "','"
					+event.getM_username()+ "')";
			
			//(insertEvent);
			int result=stmt.executeUpdate(insertEvent);	
			//("result of sql execution"+result);
			conn.commit(); 
		} catch (SQLException e) {}
	}
	
	public static void modifyEvent(Event event, String oldEventName, String evetime, String edate) {
		// TODO Auto-generated method stub
		String eventdate = event.getM_event_date();
		DateFormat d = new SimpleDateFormat("MM/dd/yyyy"); 
		Date x = null;
		try {
		x = (Date) d.parse(eventdate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String evefdt = df.format(x);
		
		
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		try {
			stmt = conn.createStatement();
			//("UPDATE cruise_activity.events SET EVENT_DATE='"+evefdt+"',START_TIME='"+event.getM_start_time()+"',ATTENDEES='"+event.getM_numberofattendees()+"' WHERE EVENT_NAME='"+oldEventName+"' && EVENT_DATE='"+edate+"' && START_TIME = '"+evetime+"' ");
			stmt.executeUpdate("UPDATE cruise_activity.events SET EVENT_DATE='"+evefdt+"',START_TIME='"+event.getM_start_time()+"',ATTENDEES='"+event.getM_numberofattendees()+"' WHERE EVENT_NAME='"+oldEventName+"' && EVENT_DATE='"+edate+"' && START_TIME = '"+evetime+"' ");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void modifyEventName(Event event, String oldEventName) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
		//("UPDATE cruise_activity.events SET EVENT_NAME = '"+event.getM_event_name()+"', EVENT_DATE='"+event.getM_event_date()+"',START_TIME='"+event.getM_start_time()+"',ATTENDEES='"+event.getM_numberofattendees()+"' WHERE EVENT_NAME='"+oldEventName+"'");
		Connection conn = SQLConnection.getDBConnection();
		try {
		stmt = conn.createStatement();
		//String updateStmt = ;
		//boolean res = stmt.execute(updateStmt);
		int rows = stmt.executeUpdate("UPDATE cruise_activity.events SET EVENT_NAME = '"+event.getM_event_name()+"', EVENT_DATE='"+event.getM_event_date()+"',START_TIME='"+event.getM_start_time()+"',ATTENDEES='"+event.getM_numberofattendees()+"' WHERE EVENT_NAME='"+oldEventName+"'");
		conn.commit();
		//("rows impacted: " + rows);
		//(stmt);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Boolean eventDateFull2(String date, String user,String type)  {  
		//("inside event date full 2"+date+":"+user+":"+type);
		String old_date=date;
		String feventDate=" ";
		
		DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date dateret = (Date) parser.parse(old_date);
		
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    feventDate = formatter.format(dateret);
		
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT COUNT(*) FROM cruise_activity.EVENTS WHERE EVENT_DATE='"+feventDate+"' AND USER = '"+user+"' AND TYPE='"+"Atheletic"+"'";
			//(queryString);
			ResultSet one = stmt.executeQuery(queryString);	
			//("count"+one.getInt(1));
			one.next();
			int val = one.getInt(1);
			
			//while(one.next()) {
				if (val > 0) return true;
				else return false; 
			//}
		} catch (SQLException e) {//(e);}
		return true;
	}
	}
	public static Boolean eventDateFull(String date, String user,String type)  {  
		//("inside event date full"+date+":"+user+":"+type);
		String old_date=date;
		String feventDate=" ";
		
		DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date dateret = (Date) parser.parse(old_date);
		
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    feventDate = formatter.format(dateret);
		
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT COUNT(*) FROM cruise_activity.EVENTS WHERE EVENT_DATE='"+feventDate+"' AND USER = '"+user+"' AND TYPE='"+"show"+"'";
			//("SELECT COUNT(*) FROM cruise_activity.EVENTS WHERE EVENT_DATE='"+feventDate+"' AND USER = '"+user+"' AND TYPE='"+"show"+"'");
			ResultSet one = stmt.executeQuery(queryString);	
			////(one.getInt(1));
			one.next();
			int val = one.getInt(1);
			
			//();

			//while(one.next()) {
				if (val > 1) return true;
				else return false; 
			//}
		} catch (SQLException e) {
			e.printStackTrace();
		
		
	}
		return true;
	}

	public static ArrayList<Event> searchEventForC(String feventDate, String eventTime, String cname) {
		// TODO Auto-generated method stub
		
		String query = "SELECT * FROM cruise_activity.events where EVENT_DATE > '"+feventDate+"' && START_TIME > '"+eventTime+"' && COORDINATOR = '"+cname+"'";	
		//(query);
		return ReturnMatchingEventsList(query);
	}
	

	
	/*
	 * public static ArrayList<Event> listEvents() {
	 * 
	 * return events; }
	 */

}
