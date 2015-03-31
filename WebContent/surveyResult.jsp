<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- This is the template of a jsp for displaying the result -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey Result -- JSTL</title>
<link rel="stylesheet" type="text/css" href="css/surveystyle.css" media="screen" />

</head>
<body>
<h3> ${info} </h3>
<h4> Current Survey Results: </h4>

<h5> For Female Respondents</h5>
<c:forEach var="prefStatment" items="${preferences[1]}" varStatus="productCount">
	${prefStatment}<br>
</c:forEach>
<h5> For Male Respondents</h5>
<c:forEach var="prefStatment" items="${preferences[0]}" varStatus="productCount">
	${prefStatment}<br>
</c:forEach>
<br><br>
<a href="surveyForm.do">back to survey form</a><br><br>
<h4>number of activeSessions</h4>
<input type="text" name="activeSessions" value = "${activeSessions}" readonly/>
<h4>user role information</h4>
<p>${displayRoleInfo}</p>
<br/><br/>
<a href="surveylogout.do">log out</a><br><br>
</body>
</html>