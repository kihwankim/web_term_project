<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<!-- 이 위에 부분이 한글을 깨지지않게 해 주는 역할을 한다 -->
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" type="text/css" href="fifthPageCSS.css">
<title>TimeTables</title>
</head>
<body>
	<%
		if (session.getAttribute("token") == null) {
	%>
	<script>
		alert("로그인 후 이용 바랍니다.");
	</script>
	<%
		response.sendRedirect("login.jsp");
		}
	%>
	<h1>『 fin. It's Your TimeTable 』</h1>
	<%!int theNumberOfSubects;
	String sub[];%>
	<div class="TimeTable">
		<iframe class="iframePage" src="fifthIframePage.jsp"> </iframe>
	</div>
	<form>
		<br /> <input type="button" value='log out' onclick="onclick_log_out_button()">
	</form>
	<div class="stageimg">
		<img class="stage" src="image/stage4.png" width="600px" height="150">
	</div>
	<script src="GotoLogOutPage.js">
	</script>
</body>
</html>