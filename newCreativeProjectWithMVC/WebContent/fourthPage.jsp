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
		alert("로그인 후 이용 바랍니다.");
	</script>
	<%
		response.sendRedirect("login.jsp");
		}
	%>
	<h1>『 3rd. Choose your prior professor 』</h1>
	<%!int theNumberOfSubjects;
	String sub[];
	ArrayList<String> aList;%>
	<form method="post" action="FFOk">
		<div class="selectForText">
			<%
				theNumberOfSubjects = Integer.parseInt((String) session.getAttribute("numberOfSubs"));
			%>
			<!--섹션 파일에서 받아오기-->
			<br /> <br />
			<%
				sub = new String[theNumberOfSubjects];
				aList = (ArrayList<String>) session.getAttribute("List");//부득이 하게 ArrayList만듦 session에 과목 이름 다 저장
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
			<!-- 공강 설정 만들기 -->
			<input type="radio" name="emptyDay" value="월"><span>월</span>
			<input type="radio" name="emptyDay" value="화"><span>화</span>
			<input type="radio" name="emptyDay" value="수"><span>수</span>
			<input type="radio" name="emptyDay" value="목"><span>목</span>
			<input type="radio" name="emptyDay" value="금"><span>금</span>
			<input type="radio" name="emptyDay" value="N" checked="checked"><span>N</span>
			<br /> <input class="send" type="submit"
				value='show me my possible timeTable'>
			<!-- 결과 화면으로 이동 페이지 -->
		</div>
	</form>
	<div class="stageimg">
		<img class="stage" src="image/stage3.png" width="600px" height="150">
	</div>
</body>
</html>