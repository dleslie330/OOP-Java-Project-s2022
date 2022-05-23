<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>my cart</title>
</head>
	<body> 
		<%
			String name = (String) request.getAttribute("userName");
			String size = (String) request.getAttribute("cartSize");
			String total = (String) request.getAttribute("cartTotal");
		%>
		<form action="/project3/Servlet" method="get">
			<input type="hidden" value=<%=name%> name="userName">
			<%
				String selectionText = (String) request.getAttribute("displayCart");
			%>
			Select the item you wish to remove from your cart:<br><%=selectionText%><br>
			<input type="submit" value="Remove from cart" name="removeCartButton"><br>
			Cart total of <%=size%> items: $<%=total%>
			<input type="submit" value="Check out" name="checkOutButton"><br>
			<input type="submit" value="Go back" name="return">
			<input type="submit" value="Log Out" name="logOutButton">
		</form>
	</body>
</html>