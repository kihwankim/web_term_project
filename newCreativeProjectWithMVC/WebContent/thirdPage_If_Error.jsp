<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="thirdPageCSS.css">
<meta charset="EUC-KR">
<title>Please insert The Subject Name again</title>
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
	<%!String subsNum;%>
	<h1>Error is happen</h1>
	<br />
	<h2>please insert the right data of the subjects</h2>
	<form method="post" action="TFOk">
		<div class="divOfTheText">
			<%
				int i = 1;
				subsNum = (String) session.getAttribute("numberOfSubs");
				int theNumberOfSubs = Integer.parseInt(subsNum);
				while (i <= theNumberOfSubs) {
			%>
			<input type="text" placeholder="Prior Subject<%=i%>"
				name="<%out.print(i);%>" size="15"> <br />
			<%
				i++;
				}
			%>
			<!-- 30분동안 쿠키 저장, 쿠기 생성 -->
			<input class="submmitSubjects" type="submit" value="제출">
		</div>
	</form>
	<div class="stageimg">
		<img class="stage" src="image/stage2.png" width="600px" height="150">
	</div>
</body>
</html>