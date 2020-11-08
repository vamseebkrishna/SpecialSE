package Project.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import Project.model.User;
import Project.model.UserErrorMsgs;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserTest {
	User user;
	UserErrorMsgs uerrors;
	
	@Before
	public void setUp() throws Exception {
		user = new User();
		uerrors = new UserErrorMsgs();
	}
	
	@Test
	@FileParameters("test/Project/model/User_test_cases.csv")
	public void test(String testcase, String action, String username,
			String password, String lastname, String firstname, String phone,
			String email, String roomno, String deckno,
			String memtype, String errorMsg, String usernameError, String passwordError, String userLastError,
			String userFirstError, String userPhoneError, String userEmailError, String userRoomnoError,
			String userDecknoError, String userSelectError) {
		user.setUser(username, password, lastname, firstname, phone,
				email, roomno, deckno, memtype);
		
		user.validateUser(action, user, uerrors);
		if(action.equals("updateUser")) {
			assertTrue(usernameError.equals(uerrors.getUsernameError()));
			System.out.println("Update user Username error" + uerrors.getUsernameError());
			assertTrue(passwordError.equals(uerrors.getPasswordError()));
			assertTrue(userLastError.equals(uerrors.getUserLastError()));
			assertTrue(userFirstError.equals(uerrors.getUserFirstError()));
			assertTrue(userPhoneError.equals(uerrors.getUserPhoneError()));
			assertTrue(userEmailError.equals(uerrors.getUserEmailError()));
			assertTrue(userRoomnoError.equals(uerrors.getUserRoomnoError()));
			assertTrue(userDecknoError.equals(uerrors.getUserDecknoError()));
			assertTrue(userSelectError.equals(uerrors.getUserSelectError()));
			
		}
		else
		{
		//System.out.println(uerrors.getErrorMsg());
		assertTrue(errorMsg.equals(uerrors.getErrorMsg()));
		//System.out.println(errorMsg);
		//System.out.println(uerrors.getErrorMsg());
		assertTrue(usernameError.equals(uerrors.getUsernameError()));
		
		assertTrue(passwordError.equals(uerrors.getPasswordError()));
	//	System.out.println();
		//System.out.println("Password error: "+ uerrors.getPasswordError());
		assertTrue(userLastError.equals(uerrors.getUserLastError()));
		assertTrue(userFirstError.equals(uerrors.getUserFirstError()));
		assertTrue(userPhoneError.equals(uerrors.getUserPhoneError()));
		assertTrue(userEmailError.equals(uerrors.getUserEmailError()));
		assertTrue(userRoomnoError.equals(uerrors.getUserRoomnoError()));
		assertTrue(userDecknoError.equals(uerrors.getUserDecknoError()));
		assertTrue(userSelectError.equals(uerrors.getUserSelectError()));
		}
	}
	
	
}
