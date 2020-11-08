package Project.model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class RegisterTest {
	
	Event evt;
	EventErrorMsgs EerrMsgs;
	
	@Before
	public void setUp() throws Exception {
		evt = new Event();
		EerrMsgs = new EventErrorMsgs();
	}

	@Test
	@FileParameters("test/Project/model/reserve.csv")
	public void test(int testcaseNo, String action, String event_name, String event_date,String start_time, String duration, 
						String location, String numberofattendees, String capacity, String eventcoordinator, String type, 
						String username,String errorMsg, String eventDateError,  String eventTimeError, 
						String durationError,String typeError, String numberOfAttendeesError) {
		
		evt.setEvent(event_name, event_date,start_time,duration,location,numberofattendees,capacity,eventcoordinator,type);
		evt.setEventUser(username);
		System.out.println("PRINTING OUT EVENT OBJ ATTRIBUTES");
		System.out.println(evt.getM_capacity());
		System.out.println(evt.getM_duration());
		System.out.println(evt.getM_event_date());
		System.out.println(evt.getM_event_name());
		System.out.println(evt.getM_eventcoordinator());
		System.out.println(evt.getM_location());
		System.out.println(evt.getM_numberofattendees());
		System.out.println(evt.getM_start_time());
		System.out.println(evt.getM_type());
		System.out.println(evt.getM_username());
		
		
		evt.validateEvent(action, evt, EerrMsgs);
		
		System.out.println("DATE");
		System.out.println("retrived date from excel"+event_date);
		System.out.println("is date string set to null"+evt.getM_event_date().equals(""));
		System.out.println("set date error after validation"+EerrMsgs.getM_event_dateError());
		System.out.println("retrived date error from excel"+eventDateError);
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("TIME");
		System.out.println("retrived time from excel"+start_time);
		System.out.println("is time string set to null"+evt.getM_start_time().equals(""));
		System.out.println("set time error after validation"+EerrMsgs.getM_start_timeError());
		System.out.println("retrived time error from excel"+eventTimeError);
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("TYPE");
		System.out.println("retrived type from excel"+type);
		System.out.println("being set"+evt.getM_type());
		System.out.println("is type string set to null"+evt.getM_start_time().equals(""));
		System.out.println("error msg"+EerrMsgs.getM_typeError());
		System.out.println("error msg read from excel"+typeError);
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("ATTENDEES");
		System.out.println("retrived attendees from excel"+numberofattendees);
		System.out.println("being set"+evt.getM_numberofattendees());
		System.out.println("error msg"+EerrMsgs.getM_numberofattendeesError());
		System.out.println("error msg read from excel"+numberOfAttendeesError);
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("DURATION ERROR");
		System.out.println("duration from excel"+duration);
		System.out.println("duration being set"+evt.getM_duration());
		System.out.println("error msg"+EerrMsgs.getM_durationError());
		System.out.println("error msg read from excel"+durationError);
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		
		assertTrue(errorMsg.equals(EerrMsgs.getM_errorMsg()));
		assertTrue(eventDateError.equals(EerrMsgs.getM_event_dateError()));
		
		assertTrue(eventTimeError.equals(EerrMsgs.getM_start_timeError()));
		assertTrue(durationError.equals(EerrMsgs.getM_durationError()));
		//assertTrue(locationError.equals(EerrMsgs.getM_numberofattendeesError()));
		assertTrue(numberOfAttendeesError.equals(EerrMsgs.getM_numberofattendeesError()));
		//assertTrue(capacityError.equals(EerrMsgs.getM_capacityError()));
		//assertTrue(coordinatorError.equals(EerrMsgs.getM_eventcoordinatorError()));
		assertTrue(typeError.equals(EerrMsgs.getM_typeError()));
		
	}
	
	
}