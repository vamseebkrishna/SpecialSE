package Project.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

import Project.data.EventDAO;
import Project.data.UserDAO;
import Project.model.*;

@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void getEventParam (HttpServletRequest request, Event event) {
		event.setEvent(request.getParameter("eventname"), request.getParameter("eventdate"), request.getParameter("starttime"), 
				request.getParameter("duration"), request.getParameter("location"), request.getParameter("attendees"), 
				request.getParameter("capacity"), request.getParameter("coordinator"), request.getParameter("evetype"));
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entered doPost in Controller");
		String action = request.getParameter("action"), url = "";
		HttpSession session = request.getSession();
		Event event = new Event();
		EventErrorMsgs CerrorMsgs = new EventErrorMsgs();
		session.removeAttribute("errorMsgs"); // didnt understand
		ArrayList<Event> eventInDB = new ArrayList<Event>();
		int selectedEventIndex;

		if (action.equalsIgnoreCase("listEvents")) {
			String feventDate = null;
			String eventDate = request.getParameter("event_date");
			String eventTime = request.getParameter("event_time");
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			
			DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Date date = (Date) parser.parse(eventDate);
			
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				feventDate = formatter.format(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			
			//String d = sdf.format(eventDate);
//			try {
//				java.util.Date langDate = sdf.parse("eventDate");
//				java.sql.Date sqlDate = new java.sql.Date(langDate.getTime());
//			} catch (ParseException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			session.removeAttribute("errorMsgs");
			event.setEvent("", eventDate, eventTime, "", "", "", "", "", "");
			event.validateEvent(action, event, CerrorMsgs);
			if (CerrorMsgs.getM_errorMsg().equals("")) {
				if (!eventDate.equals("") && !eventTime.equals("")) {
					eventInDB = EventDAO.searchEvent(feventDate, eventTime);
					
					for (int i = 0; i < eventInDB.size(); i++) {
						String fdateItem = null;
						String dateItem = eventInDB.get(i).getM_event_date();
						if(dateItem != null) {
							DateFormat p = new SimpleDateFormat("yyyy-MM-dd");
							try {
								Date d = (Date) p.parse(dateItem);
								DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
								fdateItem = f.format(d);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						eventInDB.get(i).setM_event_date(fdateItem);
					}
					
				//	session.setAttribute("EVENTS", );
					System.out.println("SIZE"+eventInDB.size());
					System.out.println("came till here");
					System.out.println(eventInDB.get(0).getM_event_date());
					System.out.println(eventInDB.get(0).getM_start_time());
					Event e = eventInDB.get(0);
					System.out.println("THERE IS NO ERROR hence entered this block");
					session.setAttribute("EVENTS", eventInDB);
					//System.out.println(session.getAttribute("EVENTS"));
					//ArrayList<Event> eventslist=session.getAttribute("EVENTS");
					url="/EventSummaryPage.jsp";
				}
			} else {
				session.setAttribute("event", event);
				session.setAttribute("errorMsgs", CerrorMsgs);
				getServletContext().getRequestDispatcher("/EventsManagerHomepage.jsp").forward(request, response);
			}


		}
		else if (action.equalsIgnoreCase("listEventsForPassengers")) {
			String feventDate = null;
			String eventDate = request.getParameter("event_date");
			String eventTime = request.getParameter("event_time");
			String eventType = request.getParameter("event_type");
			String currentuser = request.getParameter("current_user");
			System.out.println("Retrived session current username"+currentuser);
			System.out.println("Retrived session event time"+eventTime);
			System.out.println("Retrived session session event type"+eventType);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			
			DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Date date = (Date) parser.parse(eventDate);
			
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				feventDate = formatter.format(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			

			
			session.removeAttribute("errorMsgs");
			event.setEvent("", eventDate, eventTime, "", "", "", "", "", eventType);
			event.validateEventForPassenger(action,  event, eventType, CerrorMsgs);
			if (CerrorMsgs.getM_errorMsg().equals("")) {
				if (!eventDate.equals("") && !eventTime.equals("") && !eventType.equals("")) {
					eventInDB = EventDAO.searchEventForPassenger(feventDate, eventTime,eventType);
					
					for (int i = 0; i < eventInDB.size(); i++) {
						String fdateItem = null;
						String dateItem = eventInDB.get(i).getM_event_date();
						if(dateItem != null) {
							DateFormat p = new SimpleDateFormat("yyyy-MM-dd");
							try {
								Date d = (Date) p.parse(dateItem);
								DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
								fdateItem = f.format(d);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						eventInDB.get(i).setM_event_date(fdateItem);
					}
					
				//	session.setAttribute("EVENTS", );
					System.out.println("SIZE"+eventInDB.size());
					System.out.println("came till here");
					System.out.println(eventInDB.get(0).getM_event_date());
					System.out.println(eventInDB.get(0).getM_start_time());
					System.out.println(eventInDB.get(0).getM_type());
					Event e = eventInDB.get(0);
					System.out.println("THERE IS NO ERROR hence entered this block");
					session.setAttribute("EVENTS", eventInDB);
					
					
					User currentUserReturned=UserDAO.returnUser(currentuser);
					System.out.println("User obj retured"+currentUserReturned);
					session.setAttribute("current_user", currentUserReturned);
					System.out.println("printing from Event controller while going to evemt sum page"+currentUserReturned.getUsername());
					
					url="/EventSummaryPage.jsp";
				}
			} else {
				session.setAttribute("event", event);
				session.setAttribute("eventerrmsg", CerrorMsgs);
				getServletContext().getRequestDispatcher("/passengerhomepage.jsp").forward(request, response);
			}


		}

		else if(action.equals("updateEventCoordinator")) {
			//ArrayList<Event> list = new ArrayList<Event>();
			String FirstName = request.getParameter("FirstName");
			String LastName = request.getParameter("LastName");
			ArrayList<Event> list = new ArrayList<Event>();
			Event e = new Event();
			list=(ArrayList<Event>)session.getAttribute("EVENTS");
			String evename = (String) session.getAttribute("eventName");
			String evedate = (String) session.getAttribute("updatedDate");
			
			String evetime = (String) session.getAttribute("updatedTime");
			String oldcname = (String) session.getAttribute("oldcname");
			String newcname = EventDAO.selectcrd(FirstName, LastName);
			EventDAO.update(newcname, oldcname, evename, evedate, evetime);
			
			e.setEvent((String) session.getAttribute("eventName"), list.get((int) session.getAttribute("selectedEventIndex")).getM_event_date(), 
					list.get((int) session.getAttribute("selectedEventIndex")).getM_start_time(), list.get((int) session.getAttribute("selectedEventIndex")).getM_duration(), 
					list.get((int) session.getAttribute("selectedEventIndex")).getM_location(), list.get((int) session.getAttribute("selectedEventIndex")).getM_numberofattendees(), list.get((int) session.getAttribute("selectedEventIndex")).getM_capacity(), 
					newcname, list.get((int) session.getAttribute("selectedEventIndex")).getM_type());
			session.setAttribute("Selected_Event", e); 
			System.out.println(e.getM_eventcoordinator());
			url="/ListSpecificEvent.jsp";
			//System.out.println("There are no error msgs, hence im in insert block");
			url = "/ListSpecificEvent.jsp";
			//			
		}
		else if(action.equalsIgnoreCase("listSpecificEvent")) { // action=listSpecificCompany
			ArrayList<Event> companyInDB = new ArrayList<Event>();
			Event selectedEvent= new Event();
			
			  if (request.getParameter("radioEvent") != null) 
			  { 
				  selectedEventIndex = Integer.parseInt(request.getParameter("radioEvent")) - 1; 
				  session.setAttribute("selectedEventIndex", selectedEventIndex);
				  companyInDB=(ArrayList<Event>)session.getAttribute("EVENTS");
				  selectedEvent.setEvent(companyInDB.get(selectedEventIndex).getM_event_name(),
				  companyInDB.get(selectedEventIndex).getM_event_date(),
				  companyInDB.get(selectedEventIndex).getM_start_time(),
				  companyInDB.get(selectedEventIndex).getM_duration(),
				  companyInDB.get(selectedEventIndex).getM_location(),
				  companyInDB.get(selectedEventIndex).getM_numberofattendees(),
				  companyInDB.get(selectedEventIndex).getM_capacity(),
				  companyInDB.get(selectedEventIndex).getM_eventcoordinator(),
				  companyInDB.get(selectedEventIndex).getM_type() ); 
				  session.setAttribute("Selected_Event", selectedEvent); 
				  System.out.println(selectedEvent.getM_event_name());
				  session.setAttribute("eventName", selectedEvent.getM_event_name());
				  session.setAttribute("oldcname", selectedEvent.getM_eventcoordinator());
				  url = "/ListSpecificEvent.jsp"; 
			  
			  } 
			/*
			 * else { // without selecting a company // if
			 * (request.getParameter("ListSelectedCompanyButton") != null) { String
			 * errorMsgs = "Please select a company"; session.setAttribute("errorMsgs",
			 * errorMsgs); url = "/EventsManagerHomepage.jsp"; }
			 */
			  
			  
			}
		else if(action.equalsIgnoreCase("updateEvent"))
		{
			getEventParam(request, event);
			
			String newdate = event.getM_event_date();
			String newtime = event.getM_start_time();
			session.setAttribute("updatedTime", newtime);
			DateFormat ab = new SimpleDateFormat("MM/dd/yyyy"); 
			Date bc = null;
			try {
			bc = (Date) ab.parse(newdate);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
			DateFormat cd = new SimpleDateFormat("yyyy-MM-dd");
			String updatedDate = cd.format(bc);
			session.setAttribute("updatedDate", updatedDate);
			event.validateEvent("updateEvent", event, CerrorMsgs);
			String oldEventName = (String) session.getAttribute("eventName");
			if(!CerrorMsgs.getM_errorMsg().equals("")) {
				session.setAttribute("event", event);
				session.setAttribute("errorMsgs", CerrorMsgs);
				url = "/modifyevent.jsp";
			}
			else {
				//CerrorMsgs.setM_errorMsg("Modified Successfully");
				ArrayList<Event> li = new ArrayList<Event>();
				li=(ArrayList)session.getAttribute("EVENTS");
				String evetime = li.get((int) session.getAttribute("selectedEventIndex")).getM_start_time();
				String evedate = li.get((int) session.getAttribute("selectedEventIndex")).getM_event_date();
				DateFormat a = new SimpleDateFormat("MM/dd/yyyy"); 
				Date b = null;
				try {
				b = (Date) a.parse(evedate);
				}
				catch (ParseException e) {
					e.printStackTrace();
				}
				DateFormat c = new SimpleDateFormat("yyyy-MM-dd");
				String edate = c.format(b);
				
					EventDAO.modifyEvent(event, oldEventName, evetime, edate);
					event.setM_capacity(li.get(0).getM_capacity());
					event.setM_duration(li.get(0).getM_duration());
					event.setM_event_name((String) session.getAttribute("eventName"));
					event.setM_eventcoordinator(li.get(0).getM_eventcoordinator());
					event.setM_location(li.get(0).getM_location());
					event.setM_numberofattendees(li.get(0).getM_numberofattendees());
					event.setM_type(li.get(0).getM_type());
				session.setAttribute("Selected_Event", event);
				url = "/ListSpecificEvent.jsp";
				
			}
			
		}
		else if(action.equalsIgnoreCase("listEventsForC")) {
				String feventDate = null;
				String eventDate = request.getParameter("event_date");
				String eventTime = request.getParameter("event_time");
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				
				String cname = (String) session.getAttribute("coordinator");
				DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
				try {
					Date date = (Date) parser.parse(eventDate);
				
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					feventDate = formatter.format(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				
				//String d = sdf.format(eventDate);
//				try {
//					java.util.Date langDate = sdf.parse("eventDate");
//					java.sql.Date sqlDate = new java.sql.Date(langDate.getTime());
//				} catch (ParseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
				session.removeAttribute("errorMsgs");
				event.setEvent("", eventDate, eventTime, "", "", "", "", "", "");
				event.validateEvent(action, event, CerrorMsgs);
				if (CerrorMsgs.getM_errorMsg().equals("")) {
					if (!eventDate.equals("") && !eventTime.equals("")) {
						eventInDB = EventDAO.searchEventForC(feventDate, eventTime, cname);
						
						for (int i = 0; i < eventInDB.size(); i++) {
							String fdateItem = null;
							String dateItem = eventInDB.get(i).getM_event_date();
							if(dateItem != null) {
								DateFormat p = new SimpleDateFormat("yyyy-MM-dd");
								try {
									Date d = (Date) p.parse(dateItem);
									DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
									fdateItem = f.format(d);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							eventInDB.get(i).setM_event_date(fdateItem);
						}
						
					//	session.setAttribute("EVENTS", );
						System.out.println("SIZE"+eventInDB.size());
						System.out.println("came till here");
						System.out.println(eventInDB.get(0).getM_event_date());
						System.out.println(eventInDB.get(0).getM_start_time());
						Event e = eventInDB.get(0);
						System.out.println("THERE IS NO ERROR hence entered this block");
						session.setAttribute("EVENTS", eventInDB);
						//System.out.println(session.getAttribute("EVENTS"));
						//ArrayList<Event> eventslist=session.getAttribute("EVENTS");
						url="/EventSummaryForC.jsp";
					}
				} else {
					session.setAttribute("event", event);
					session.setAttribute("errorMsgs", CerrorMsgs);
					getServletContext().getRequestDispatcher("/EventsCoordinatorHomepage.jsp").forward(request, response);
				}


			}
		else if(action.equalsIgnoreCase("listSpecificEventForC")) { // action=listSpecificCompany
			ArrayList<Event> companyInDB = new ArrayList<Event>();
			Event selectedEvent= new Event();
			
			  if (request.getParameter("radioEvent") != null) 
			  { 
				  selectedEventIndex = Integer.parseInt(request.getParameter("radioEvent")) - 1; 
				  companyInDB=(ArrayList<Event>)session.getAttribute("EVENTS");
				  selectedEvent.setEvent(companyInDB.get(selectedEventIndex).getM_event_name(),
				  companyInDB.get(selectedEventIndex).getM_event_date(),
				  companyInDB.get(selectedEventIndex).getM_start_time(),
				  companyInDB.get(selectedEventIndex).getM_duration(),
				  companyInDB.get(selectedEventIndex).getM_location(),
				  companyInDB.get(selectedEventIndex).getM_numberofattendees(),
				  companyInDB.get(selectedEventIndex).getM_capacity(),
				  companyInDB.get(selectedEventIndex).getM_eventcoordinator(),
				  companyInDB.get(selectedEventIndex).getM_type() ); 
				  session.setAttribute("Selected_Event", selectedEvent); 
				  System.out.println(selectedEvent.getM_event_name());
				  session.setAttribute("eventName", selectedEvent.getM_event_name());
				  session.setAttribute("oldcname", selectedEvent.getM_eventcoordinator());
				  url = "/ListSpecificEventForC.jsp"; 
			  
			  } 
			/*
			 * else { // without selecting a company // if
			 * (request.getParameter("ListSelectedCompanyButton") != null) { String
			 * errorMsgs = "Please select a company"; session.setAttribute("errorMsgs",
			 * errorMsgs); url = "/EventsManagerHomepage.jsp"; }
			 */
			  
			  
			}
		else if (action.equalsIgnoreCase("Reserve")) {
			System.out.println("Inside Reserve Event block of Evenet Controller");
			getEventParam(request, event);
			Event e=(Event)session.getAttribute("selected_event_obj");
			String no_of_attendees=request.getParameter("attendees");
			e.setM_numberofattendees(no_of_attendees);	
			String currentuser = request.getParameter("current_user");
			e.setEventUser(currentuser);
			event.validateEvent(action, e, CerrorMsgs);
			if (!CerrorMsgs.getM_errorMsg().equals("")) {
				
				System.out.println("Error msgs found");
				session.setAttribute("errorMsgs",CerrorMsgs);
				url="/RegisterEventsInputs.jsp";
			}
			else {// if no error messages
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_capacity());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_numberofattendees());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_event_date());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_event_name());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_start_time());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_type());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_start_time());
			
				EventDAO.insertEvent(e);
				
			System.out.println("Event Succesfully Registered");
			
				url="";
			}
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
			
		}

	}
