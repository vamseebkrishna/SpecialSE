 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Profile</title>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
<form action="<c:url value='UserController?action=updateUser' />" method="post">
			<table style="with: 100%">
				<tr>
					<td>Username</td>
					<td><input type="text" name="username" value="<c:out value='${loginU.username}' />"></td>
					<td> <input name="usernameError" value="<c:out value='${errorMsgs.usernameError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" value="<c:out value='${loginU.password}' />"></td>
					<td> <input name="passwordError" value="<c:out value='${errorMsgs.passwordError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastname" value="<c:out value='${loginU.lastname}'/>" ></td>
					<td> <input name="userLastError" value= "<c:out value='${errorMsgs.userLastError}' />" class="errorMsg"> </td>
				</tr>
					<tr>
					<td>First Name</td>
					<td><input type="text" name="firstname" value="<c:out value='${loginU.firstname}' />"></td>
					<td> <input name="userFirstError" value="<c:out value='${errorMsgs.userFirstError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="text" name="phone" value="<c:out value='${loginU.phone}' />"></td>
					<td> <input name="userPhoneError" value="<c:out value='${errorMsgs.userPhoneError}' />"class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" value="<c:out value='${loginU.email}' />"></td>
					<td> <input name="userEmailError" value="<c:out value='${errorMsgs.userEmailError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
				<td>Room Number</td>
					<td><input type="text" name="roomno" value="<c:out value='${loginU.roomno}' />"></td>
					<td> <input name="userRoomnoError" value="<c:out value='${errorMsgs.userRoomnoError}' />" class="errorMsg"> </td>
				</tr>
				<tr>
				<td>Deck Number</td>
					<td><input type="text" name="deckno" value="<c:out value='${loginU.deckno}' />"></td>
					<td> <input name="userDecknoError" value="<c:out value='${errorMsgs.userDecknoError}'/>" class="errorMsg"> </td>
				</tr>
				<tr>
				<td><label for="memtype">Membership Type</label></td>
					<td><span name = "memtype"><c:out value='${loginU.memtype}' /></span>
					</td>
					<td> <input name="userSelectError" value="<c:out value='${errorMsgs.userSelectError}' />" class="errorMsg"> </td>
				</tr>
				</table>
			<input type="submit" value="Submit" onclick="return updateconfirm()"/>
			</form>
			<script>
function updateconfirm() {

if(confirm("Are you sure you want to Update?"))
	return true;	
else
    history.go(-2);
	return false;
}
</script>
			<a href="index.jsp"><input name="logoutButton" type="submit" value="Logout"></a>
</body>
</html>