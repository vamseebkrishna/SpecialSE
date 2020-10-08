<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<input name="errMsgs"  value='${errorMsgs.errorMsg}' class="errorPane">
<input name="none"  value='${none}' class="errorPane">
	<form  action="<c:url value='UserController?action=login' />" method="post">  
	Username:<input type="text" name = 'username' value = "<c:out value='${user.username}' />" >
	<input name="usernameError"  value="<c:out value='${errorMsgs.usernameError}' />" class="errorMsg"><br/><br/>
	Password:<input type="password" name="password" value ="<c:out value='${user.password}' />">
	<input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}' />" class="errorMsg"><br/><br/>  
<input type="submit" value="submit"/> 
</form>
</body>
</html>