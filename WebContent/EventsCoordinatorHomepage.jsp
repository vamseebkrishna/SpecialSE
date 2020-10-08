<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Event Coordinator HomePage</h1>
	<table>
<tr>
	<td>
	<form action="<c:url value='EventController?action=listEventsForC' />" method="post">
	<table style="width: 1200px; ">
	<tr>
	<tr>
  	<td> Event date: </td>
 	<td> <input name="event_date" value="<c:out value='${dateevent.m_event_date}'/>" class="text45">  </td>
  	<td> <input name="event_date_error"  value="<c:out value='${errorMsgs.m_event_dateError}'/>"  class="errorMsg"> </td>
	</tr>
    <tr>
    <td> Event time: </td>
    <td> <input name="event_time" value="<c:out value='${dateevent.m_start_time}'/>" class="text16"> </td>
  	<td> <input name="event_time_error"  value=<c:out value='${errorMsgs.m_start_timeError}'/>"  class="errorMsg"> </td>
    </tr>
</table>
  <input type ="submit" value="Submit">
	</form>      
</td>
</tr>
</table>
<a href="index.jsp"><input name="logoutButton" type="submit" value="Logout"></a>
	
	<a href="index.jsp"><input name="logoutButton" type="submit" value="Logout"></a>
</body>
</html>