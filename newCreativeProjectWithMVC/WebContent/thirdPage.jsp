<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="thirdPageCSS.css">
<meta charset="EUC-KR">
<title>Insert The Subjects Name</title>
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
	<h1>�� 2nd. Insert the name of subject ��</h1>
	<form method="post" action="TFOk">
		<div class="divOfTheText">
			<%
				int i = 1;
				subsNum = request.getParameter("HowManySubjects");
				int theNumberOfSubs = Integer.parseInt(subsNum);
				while (i <= theNumberOfSubs) {
			%>
			<input type="text" placeholder="Prior Subject<%=i%>"
				name="<%out.print(i);%>" size="12"> <br />
			<%
				i++;
				}
			%>

			<%
				session.setAttribute("numberOfSubs", subsNum);
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