<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify selected event</title>
</head>
<body>
	<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
<form  action="<c:url value='EventController?action=updateEvent' />"  method="post">
			<table style="with: 100%">
				<tr>
					<td>Event Name</td>
					<td> <c:out value="${Selected_Event.m_event_name}" /></td>
					<td> <input name="usernameError" value="<c:out value='${errorMsgs.usernameError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Event Date</td>
					<td><input type="text" name="eventdate" value="<c:out value='${Selected_Event.m_event_date}' />"></td>
					<td> <input name="passwordError" value="<c:out value='${errorMsgs.passwordError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Start Time</td>
					<td><input type="text" name="starttime" value="<c:out value='${Selected_Event.m_start_time}' />"></td>
					<td> <input name="userLastError" value= "<c:out value='${errorMsgs.userLastError}' />" class="errorMsg"> </td>
				</tr>
					<tr>
					<td>Duration</td>
					<td><c:out value='${Selected_Event.m_duration}' /></td>
					<td> <input name="userFirstError" value="<c:out value='${errorMsgs.userFirstError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Location</td>
					<td>${Selected_Event.m_location}</td>
					<td> <input name="userPhoneError" value="<c:out value='${errorMsgs.userPhoneError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Attendees</td>
					<td><input type="text" name="attendees" value="<c:out value='${Selected_Event.m_numberofattendees}' />"></td>
					<td> <input name="userEmailError" value="<c:out value='${errorMsgs.userEmailError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
				<td>Capacity</td>
					<td><c:out value='${Selected_Event.m_capacity}' /></td>
					<td> <input name="userRoomnoError" value="<c:out value='${errorMsgs.userRoomnoError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
				<td>Coordinator</td>
					<td><c:out value='${Selected_Event.m_eventcoordinator}' /></td>
					<td> <input name="userDecknoError" value="<c:out value='${errorMsgs.userDecknoError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
				<td><label for="evetype">Type</label></td>
					<td><c:out value='${Selected_Event.m_type}' /></span>
					</td>
					<td> <input name="userSelectError" value="<c:out value='${errorMsgs.userSelectError}' />"class="errorMsg"> </td>
				</tr>
				</table>
			<input type="submit" value="Submit" onclick="return updateconfirm()"/>
			</form>
			<script type="text/javascript">
			function updateconfirm() {

				if(confirm("Are you sure you want to Update?"))
					return true;	
				else
				    history.go(-2);
					return false;
				}
			</script>
</body>
</html>