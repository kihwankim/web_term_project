<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%
	request.setCharacterEncoding("EUC-KR");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="fourthPageCSS.css">
<meta charset="EUC-KR">
<title>Set The Professor's Name</title>
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
	<h1>�� 3rd. Choose your prior professor ��</h1>
	<%!int theNumberOfSubjects;
	String sub[];
	ArrayList<String> aList;%>
	<form method="post" action="FFOk">
		<div class="selectForText">
			<%
				theNumberOfSubjects = Integer.parseInt((String) session.getAttribute("numberOfSubs"));
			%>
			<!--���� ���Ͽ��� �޾ƿ���-->
			<br /> <br />
			<%
				sub = new String[theNumberOfSubjects];
				aList = (ArrayList<String>) session.getAttribute("List");//�ε��� �ϰ� ArrayList���� session�� ���� �̸� �� ����
				Iterator<String> iterator = aList.listIterator();
				int indexOfStr = 0;
				while (iterator.hasNext()) {
					sub[indexOfStr++] = iterator.next();
				}
				for (int index = 0; index < theNumberOfSubjects; index++) {
					if (sub[index] != null) {
			%><span class="titleOfTheSubject"> <%
 	out.println("<strong>" + sub[index] + "</strong>");
 %>
			</span>
			<%
				for (int numberOfProfess = 0; numberOfProfess < 4; numberOfProfess++) {
			%><br />
			<div class="packageOfTheSpanAndText">
				<input type="text" placeholder="profess<%=numberOfProfess + 1%>"
					name="profess<%out.print(index);
						out.print(numberOfProfess);%>"
					size="15">
			</div>
			<%
				}
						out.println("<br/><br/>");
					}
				}
			%>
			<br />
			<!-- ���� ���� ����� -->
			<input type="radio" name="emptyDay" value="��"><span>��</span>
			<input type="radio" name="emptyDay" value="ȭ"><span>ȭ</span>
			<input type="radio" name="emptyDay" value="��"><span>��</span>
			<input type="radio" name="emptyDay" value="��"><span>��</span>
			<input type="radio" name="emptyDay" value="��"><span>��</span>
			<input type="radio" name="emptyDay" value="N" checked="checked"><span>N</span>
			<br /> <input class="send" type="submit"
				value='show me my possible timeTable'>
			<!-- ��� ȭ������ �̵� ������ -->
		</div>
	</form>
	<div class="stageimg">
		<img class="stage" src="image/stage3.png" width="600px" height="150">
	</div>
</body>
</html>