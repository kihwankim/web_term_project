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
		alert("�α��� �� �̿� �ٶ��ϴ�.");
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
			<!-- 30�е��� ��Ű ����, ��� ���� -->
			<input class="submmitSubjects" type="submit" value="����">
		</div>
	</form>
	<div class="stageimg">
		<img class="stage" src="image/stage2.png" width="600px" height="150">
	</div>
</body>
</html>