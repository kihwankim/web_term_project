<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
	if (session.getAttribute("token") != null) {
	response.sendRedirect("firstPage.html");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="logincss.css">
<title>login page</title>
</head>
<body>
	<h1>Login Page</h1>
	<div class="contents">
		<form action="loginOk.jsp" method="post">
			<label for="id">���̵� : </label><input type="text" name="id" id="id"
				value="<%if (session.getAttribute("id") != null)
				out.println(session.getAttribute("id"));%>">
			<br /> <label for="pw">��й�ȣ : </label><input type="password"
				name="pw" id="pw"><br /> <input type="submit" value="�α���">
			&nbsp;&nbsp; <input type="button" value="ȸ������"
				onclick="javascript:window.location='join.jsp'">
		</form>
	</div>
</body>
</html>