package Project.model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class EventTest { 
	
	Event evt;
	EventErrorMsgs EerrMsgs;
	
	@Before
	public void setUp() throws Exception {
		evt = new Event();
		EerrMsgs = new EventErrorMsgs();
	}

	@Test
	@FileParameters("test/Project/model/event.csv")
	public void test(int testcaseNo, String action, String event_name, String event_date,String start_time, String duration, 
						String location, String numberofattendees, String capacity, String eventcoordinator, String type, 
						String username,String errorMsg, String eventDateError,  String eventTimeError, 
						String durationError, String numberOfAttendeesError,String typeError) {
		
		evt.setEvent(event_name, event_date,start_time,duration,location,numberofattendees,capacity,eventcoordinator,type);
		evt.setEventUser(username);
		
		
		evt.validateEventForPassenger(action, evt,type, EerrMsgs);
		//(testcaseNo);
		//("DATE");
		//("retrived date from excel"+event_date);
		//("is date string set to null"+evt.getM_event_date().equals(""));
		//("set date error after validation"+EerrMsgs.getM_event_dateError());
		//("retrived date error from excel"+eventDateError);
		//("-----------------------------------------------------------------------------------------------");
		//("TIME");
		//("retrived time from excel"+start_time);
		//("is time string set to null"+evt.getM_start_time().equals(""));
		//("set time error after validation"+EerrMsgs.getM_start_timeError());
		//("retrived time error from excel"+eventTimeError);
		//("-----------------------------------------------------------------------------------------------");
		//("TYPE");
		//("retrived type from excel"+type);
		//("being set"+evt.getM_type());
		//("is type string set to null"+evt.getM_start_time().equals(""));
		//("error msg"+EerrMsgs.getM_typeError());
		//("error msg read from excel"+typeError);
		//("-----------------------------------------------------------------------------------------------");
		//("ATTENDEES");
		//("retrived attendees from excel"+numberofattendees);
		//("being set"+evt.getM_numberofattendees());
		//("error msg"+EerrMsgs.getM_numberofattendeesError());
		//("error msg read from excel"+numberOfAttendeesError);
		//("-----------------------------------------------------------------------------------------------");
		//("DURATION ERROR");
		//("duration from excel"+duration);
		//("duration being set"+evt.getM_duration());
		//("error msg"+EerrMsgs.getM_durationError());
		//("error msg read from excel"+durationError);
		//("-----------------------------------------------------------------------------------------------");
		
		//(testcaseNo+"error msg:"+EerrMsgs.getM_errorMsg());
		assertTrue(errorMsg.equals(EerrMsgs.getM_errorMsg()));
		assertTrue(eventDateError.equals(EerrMsgs.getM_event_dateError()));
		//(testcaseNo+"Event time error:"+EerrMsgs.getM_start_timeError());
		assertTrue(eventTimeError.equals(EerrMsgs.getM_start_timeError()));
		assertTrue(durationError.equals(EerrMsgs.getM_durationError()));
		//assertTrue(locationError.equals(EerrMsgs.getM_numberofattendeesError()));
		assertTrue(numberOfAttendeesError.equals(EerrMsgs.getM_numberofattendeesError()));
		//assertTrue(capacityError.equals(EerrMsgs.getM_capacityError()));
		//assertTrue(coordinatorError.equals(EerrMsgs.getM_eventcoordinatorError()));
		assertTrue(typeError.equals(EerrMsgs.getM_typeError()));
		
	}
	
	
}