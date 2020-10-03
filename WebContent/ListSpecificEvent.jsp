<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<h1>SPECIFIC EVENT PAGE</h1>

	<table border="1" class="myTable">
		<tr class="myTableRow">
			<th class="myTableHead" style="width: 130px;">Selected Event</th>
			<th class="myTableHead" style="width: 130px;">Event Name</th>
			<th class="myTableHead" style="width: 130px;">Event Date</th>
			<th class="myTableHead" style="width: 105px;">Start Time</th>
			<th class="myTableHead" style="width: 74px;">Duration</th>
			<th class="myTableHead" style="width: 130px;">Location</th>
			<th class="myTableHead" style="width: 130px;">Attendees</th>
			<th class="myTableHead" style="width: 105px;">Capacity</th>
			<th class="myTableHead" style="width: 74px;">Coordinator</th>
			<th class="myTableHead" style="width: 74px;">Type</th>
			
		</tr>


		<tr class="myTableRow">
			
			<td class="myTableCell" style="width: 130px;">
			
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_event_name} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_event_date} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_start_time} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_duration} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_location} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_numberofattendees} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_capacity} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_eventcoordinator} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_type} </td> 

		</tr>
	</table>
	<a href="index.jsp"><input name="logoutButton" type="submit" value="Logout"></a>
	<a href="ChangeCoordinator.jsp"><input name="ChangeCoordinator" type="submit" value="Change Coordinator"></a>
	
	
</body>
</html>