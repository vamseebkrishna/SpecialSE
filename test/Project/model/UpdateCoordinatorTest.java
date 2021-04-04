package Project.model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UpdateCoordinatorTest {
	Event evt;
	EventErrorMsgs EerrMsgs;
	
	@Before
	public void setUp() throws Exception {
		evt = new Event();
		EerrMsgs = new EventErrorMsgs();
	}
	@Test
	@FileParameters("test/Project/model/update.csv")
	public void test(int testcaseNo, String action, String event_name, String event_date,String start_time, String username) {
		evt.setEvent(event_name, event_date,start_time,"","","","","","");
		evt.setEventUser(username);
		evt.validateEvent(action, evt, EerrMsgs);
		
		
	}
	
}
