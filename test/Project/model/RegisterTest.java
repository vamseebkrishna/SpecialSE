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

		
		evt.validateEvent(action, evt, EerrMsgs);
		
		
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