<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product info</title>
</head>
<body>
	<%
		String name = (String) request.getAttribute("userName");
	%>
	<form action="/project3/Servlet" method="get">
	    <input type="hidden" value=<%=name%> name="userName">
		<%
			String item = (String) request.getAttribute("itemChoice");
			String desc = (String) request.getAttribute("itemDesc");
			String price = (String) request.getAttribute("price");
			String stock = (String) request.getAttribute("stock");
		%>
		Your item:<br><%=item%><br> 
		<br>Category: <%=desc%><br>
		<br>$<%=price%> per item<br>
		<br><%=stock%> in stock<br>
		<input type="number" value="numProd" name="amount" min="0" max="<%=stock%>">
		<input type="submit" value="Add to Cart" name="addButton"><br>
		<input type="submit" value="Go back" name="return">
		<input type="submit" value="Log Out" name="logOutButton">
	</form>
</body>
</html>