<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Store</title>
</head>
<body>
	<%
		String name = (String) request.getAttribute("userName");
	%>
	<form action="/project3/Servlet" method="get">
	    <input type="hidden" value=<%=name%> name="userName">
		<%
			String selectionText = (String) request.getAttribute("dropDownOptions");
		%>
		Please select an Item:<br><%=selectionText%><br> 
		<input type="submit" value="Get Info!" name="getItemInfo">
		<input type="submit" value="View Cart" name="viewCartButton">
		<input type="submit" value="Log Out" name="logOutButton">
	</form>
</body>
</html>