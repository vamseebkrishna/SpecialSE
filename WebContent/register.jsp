<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
<form  method="post" action="UserController?action=register">
			<table style="with: 100%">
				<tr>
					<td>Username</td>
					<td><input type="text" name="username" value="${user.username}"/></td>
					<td> <input name="usernameError" value='${errorMsgs.usernameError}' class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" value="${user.password}"/></td>
					<td> <input name="passwordError" value='${errorMsgs.passwordError}' class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastname" value="${user.lastname}"/></td>
					<td> <input name="userLastError" value= '${errorMsgs.userLastError}' class="errorMsg"> </td>
				</tr>
					<tr>
					<td>First Name</td>
					<td><input type="text" name="firstname" value="${user.firstname}"/></td>
					<td> <input name="userFirstError" value='${errorMsgs.userFirstError}' class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="text" name="phone" value="${user.phone}"/></td>
					<td> <input name="userPhoneError" value='${errorMsgs.userPhoneError}' class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" value="${user.email}"/></td>
					<td> <input name="userEmailError" value='${errorMsgs.userEmailError}' class="errorMsg"> </td>
				</tr>
				<tr>
				<td>Room Number</td>
					<td><input type="text" name="roomno" value="${user.roomno}"/></td>
					<td> <input name="userRoomnoError" value='${errorMsgs.userRoomnoError}' class="errorMsg"> </td>
				</tr>
				<tr>
				<td>Deck Number</td>
					<td><input type="text" name="deckno" value="${user.deckno}"/></td>
					<td> <input name="userDecknoError" value='${errorMsgs.userDecknoError}' class="errorMsg"> </td>
				</tr>
				<tr>
				<td><label for="memtype">Membership Type</label></td>
					<td>
						<select name="memtype" id="cars">
  							<option value="Passenger">Passenger</option>
  							<option value="Event Manager">Event Manager</option>
  							<option value="Event Coordinator">Event Coordinator</option>
						</select>
					</td>
					<td> <input name="userSelectError" value='${errorMsgs.userSelectError}' class="errorMsg"> </td>
				</tr>
				</table>
			<input type="submit" value="Submit" />
			</form>
			<table>
			
				<tr>
					<td name="name" value='${reguser.username}'></td>
				</tr>
			</table>
			<a href="index.jsp"><input name="logoutButton" type="submit" value="Logout"></a>
</body>
</html>