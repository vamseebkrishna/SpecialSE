package Project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Project.data.UserDAO;
import Project.model.Event;
import Project.model.EventErrorMsgs;
import Project.model.User;
import Project.model.UserErrorMsgs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
	private static final long serialVersionUID = 1L;
	
	private void getUserParam(HttpServletRequest request, User user) {
		user.setUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("lastname"), 
				request.getParameter("firstname"), request.getParameter("phone"),request.getParameter("email"),
				request.getParameter("roomno"), request.getParameter("deckno"), request.getParameter("memtype"));
	}
	
	/*
	 * private void getUserParam (HttpServletRequest request, User user) {
	 * user.setUser(request.getParameter("username"),
	 * request.getParameter("password"), request.getParameter("lastname"),
	 * request.getParameter("firstname"),
	 * request.getParameter("phone"),request.getParameter("email"),
	 * request.getParameter("roomno"), request.getParameter("deckno"),
	 * request.getParameter("memtype")) }
	 */
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action"), url;
		session.removeAttribute("errorMsgs");
		
			doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//("in post");
		LOGGER.info("in post");
		String action=request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		User user = new User();
		user.setUser("", "", "", "", "", "", "", "", "");
		UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
		EventErrorMsgs CerrorMsgs = new EventErrorMsgs();
		int selectedUserIndex;
		if(action.equals("login")) {
		user.setUsername(request.getParameter("username"));
		session.setAttribute("currentUsername", user.getUsername());
		user.setPassword(request.getParameter("password"));
		user.validateUser(action, user, UerrorMsgs);
		if (!UerrorMsgs.getErrorMsg().equals("")) {
			session.setAttribute("errorMsgs",UerrorMsgs);
			url="/login.jsp"; 
		}
		else {
			//("user = "+user.getUsername());
			//("pass = "+user.getPassword());
			if(user.getUsername().equalsIgnoreCase("EventManager")) {
				String currentdate = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
				String currentTime =new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				Event event = new Event();
				
				event.setM_event_date(currentdate);
				event.setM_start_time(currentTime);
				//(event.getM_event_date());
				//(event.getM_start_time());
				event.validateEvent("listEvents", event, CerrorMsgs);
				session.setAttribute("eventerrmsg", CerrorMsgs);
				session.setAttribute("dateevent",event);
				//("HERE");
				getServletContext().getRequestDispatcher("/EventsManagerHomepage.jsp").forward(request, response);			
				}
			else if (user.getUsername().equalsIgnoreCase("sethgreen") || user.getUsername().equalsIgnoreCase("ivanadams") ||
					user.getUsername().equalsIgnoreCase("wadeallen") || user.getUsername().equalsIgnoreCase("jenniferaniston") ||
					user.getUsername().equalsIgnoreCase("margotrobbie") || user.getUsername().equalsIgnoreCase("angelinajolie") ||
					user.getUsername().equalsIgnoreCase("annehathaway") || user.getUsername().equalsIgnoreCase("emmastone")) {
				session.setAttribute("coordinator", user.getUsername());
				//("Im here with co-ordinator");
				String currentdate = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
				String currentTime =new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				Event event = new Event();
				
				event.setM_event_date(currentdate);
				event.setM_start_time(currentTime);
				//(event.getM_event_date());
				//(event.getM_start_time());
				event.validateEvent("listEvents", event, CerrorMsgs);
				session.setAttribute("eventerrmsg", CerrorMsgs);
				session.setAttribute("dateevent",event);
				//("HERE");
				getServletContext().getRequestDispatcher("/EventsCoordinatorHomepage.jsp").forward(request, response);
			}
			else {
			String currentdate = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
			String currentTime =new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
			Event event = new Event();
			
			event.setM_event_date(currentdate);
			event.setM_start_time(currentTime);
			//(event.getM_event_date());
			//(event.getM_start_time());
			event.validateEvent("listEvents", event, CerrorMsgs);
			session.setAttribute("eventerrmsg", CerrorMsgs);
			session.setAttribute("dateevent",event);
			//("HERE");
			
			ArrayList<User> UserinDB=new ArrayList<User>();
			UserinDB=UserDAO.Searchusername(user.getUsername());
			User seluser=new User();
			try {
				seluser.setUser(UserinDB.get(0).getUsername(), UserinDB.get(0).getPassword(), 
						UserinDB.get(0).getLastname(), UserinDB.get(0).getFirstname(), UserinDB.get(0).getPhone(), 
						UserinDB.get(0).getEmail(), UserinDB.get(0).getRoomno(), UserinDB.get(0).getDeckno(), UserinDB.get(0).getMemtype());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(seluser.getPassword().equals(request.getParameter("password"))) {
				session.setAttribute("loginU", seluser);
			}
			
			url="/passengerhomepage.jsp";
			}
		}
		}
		else if(action.equalsIgnoreCase("updateUser")) {
			getUserParam(request, user);
			String username = request.getParameter("username");
			//String username = request.getParameter("seluser");
			//String id = request.getParameter("id");
			user.validateUser(action, user, UerrorMsgs);
			String oldUsername = (String) session.getAttribute("currentUsername");
			if (!oldUsername.equals(user.getUsername()))
				if (UserDAO.duplicateUser(user.getUsername()))
					UerrorMsgs.setUsernameError("Username already in database");
			UerrorMsgs.setErrorMsg();
			session.setAttribute("user",user);
			if (!UerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("errorMsgs",UerrorMsgs);
				url="/updateprofile.jsp";
			}
			else {// if no error messages
				
				String mtype = UserDAO.getMtype(oldUsername);
				user.setMemtype(mtype);
				if (oldUsername.equals(user.getUsername()))
					UserDAO.modifyUser(user);
				else
					UserDAO.modifyUsername(user, oldUsername);
				session.setAttribute("loginU", user);
				
				UserErrorMsgs SerrorMsgs = new UserErrorMsgs();
				session.setAttribute("errorMsgs", SerrorMsgs);
				user.setUsername(username);
				
				if (mtype.equalsIgnoreCase("passenger")) {
					session.setAttribute("user", user);
					url="/passengerhomepage.jsp"; 
				}
//				else if (mtype.equals("Caterer Manager")) {
//					session.setAttribute("user", user);
//					url="/mgrHomepage.jsp";
//				}
//				else if (mtype.equals("User")) {
//					session.setAttribute("user", user);
//					url="/userHomepage.jsp"; 
//				}
//				else if (mtype.equals("Caterer Staff")) {
//					session.setAttribute("user", user);
//					url="/CatererStaffHome.jsp";
//				}
				
			}
		}
		else if(action.equals("register")) {
			getUserParam(request, user);
			user.validateUser(action, user, UerrorMsgs);
			//("inside register");
			//session.setAttribute("user",user);
			////(session.getAttribute(user.getFirstname()));
			if (!UerrorMsgs.getErrorMsg().equals("")) {
				//("hence im not in insert block");
				getUserParam(request,user);
				session.setAttribute("errorMsgs",UerrorMsgs);
				url="/register.jsp"; 
			}
		
			else {// if no error messages
				//("Before insert");
				UserDAO.insertUser(user);
				//("There are no error msgs, hence im in insert block");
				UserErrorMsgs SerrorMsgs = new UserErrorMsgs();
				//("No errors here!");
				session.setAttribute("errorMsgs", SerrorMsgs);
				session.setAttribute("reguser", user);
				
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
				url="/login.jsp";
			}
		}
		
		else if(action.equalsIgnoreCase("logout")) {
			session.removeAttribute("currentUsername");
			session.removeAttribute("errorMsgs");
			session.removeAttribute("eventerrmsg");
			session.removeAttribute("dateevent");
			session.removeAttribute("loginU");
			session.removeAttribute("user");
			session.removeAttribute("register");
			session.removeAttribute("Selected_Event");
			session.removeAttribute("eventName");
			session.removeAttribute("oldcname");
			session.removeAttribute("event");
			session.removeAttribute("EVENTS");
			//session.removeAttribute("oldcname");
			url="/index.jsp";
		}
		////(getServletContext().getRequestDispatcher(url));
		try {
			getServletContext().getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
				
			
		}
			
	}


