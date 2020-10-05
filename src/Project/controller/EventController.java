package Project.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

import Project.data.EventDAO;
import Project.model.*;

@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void getEventParam (HttpServletRequest request, Event event) {
		event.setEvent(request.getParameter("eventname"), request.getParameter("eventdate"), request.getParameter("starttime"), 
				request.getParameter("firstname"), request.getParameter("location"), request.getParameter("attendees"), 
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

			String eventDate = request.getParameter("event_date");
			String eventTime = request.getParameter("event_time");
			session.removeAttribute("errorMsgs");
			event.setEvent("", eventDate, eventTime, "", "", "", "", "", "");
			event.validateEvent(action, event, CerrorMsgs);
			if (CerrorMsgs.getM_errorMsg().equals("")) {
				if (!eventDate.equals("") && !eventTime.equals("")) {
					eventInDB = EventDAO.searchEvent();
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
					getServletContext().getRequestDispatcher("/EventSummaryPage.jsp").forward(request, response);
				}
			} else {
				session.setAttribute("event", event);
				session.setAttribute("errorMsgs", CerrorMsgs);
				getServletContext().getRequestDispatcher("/EventsManagerHomepage.jsp").forward(request, response);
			}


		}

		else if(action.equals("updateEventCoordinator")) {
			String FirstName = request.getParameter("FirstName");
			String LastName = request.getParameter("LastName");
			String x="jerry";
			EventDAO.update(FirstName,x);
			System.out.println("There are no error msgs, hence im in insert block");
			
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
			try {
				getServletContext().getRequestDispatcher("/ListSpecificEvent.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(action.equalsIgnoreCase("listSpecificEvent")) { // action=listSpecificCompany
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
			event.validateEvent("updateEvent", event, CerrorMsgs);
			String oldEventName = (String) session.getAttribute("eventName");
			if(!CerrorMsgs.getM_errorMsg().equals("")) {
				session.setAttribute("event", event);
				session.setAttribute("errorMsgs", CerrorMsgs);
				url = "/modifyevent.jsp";
			}
			else {
				//CerrorMsgs.setM_errorMsg("Modified Successfully");
				if(oldEventName.equals(event.getM_event_name())) {
					EventDAO.modifyEvent(event);
				}
				else {
					EventDAO.modifyEventName(event, oldEventName);
				}
				session.setAttribute("EVENTS", event);
				url = "/EventSummaryPage.jsp";
				
			}
			
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
			
		}

	}
