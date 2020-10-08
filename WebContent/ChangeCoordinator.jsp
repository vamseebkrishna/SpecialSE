<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<c:url value = 'EventController?action=updateEventCoordinator' />" method="post">
	FirstName:<input type="text" name = 'FirstName' value = "<c:out value='${user.username}'/>" >
	LastName:<input type="text" name="LastName" value ="<c:out value= '${user.password}'/>" >
<input type="submit" value="submit"/> 
</form>
</body>
</html>