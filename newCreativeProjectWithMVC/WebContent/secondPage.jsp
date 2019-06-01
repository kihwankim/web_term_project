<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" type="text/css" href="secondPageCSS.css">
<title>Make The Time Table</title>
</head>
<body>
<%
 if(session.getAttribute("token") == null){
	 %>
	<script>
	alert("로그인 후 이용 바랍니다.");
	</script>
	 <%
	 response.sendRedirect("login.jsp");
 }
%>
	<h1>『 1st. Choose the number of subjects 』</h1>
	<br />
	<br />
	<form method="post" action="thirdPage.jsp">
		<div class="subjectDiv">
			<span>과목의 수를 입력 하세요 : &nbsp;</span><select name="HowManySubjects">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
			</select>
		</div>

		<input type="submit" class="submitNumberOfSubs" id="numberOfSubs"
			value="제출">
	</form>
	<div class="stageimg">
		<img class="stage" src="image/stage1.png" width = "600px" height = "150">
	</div>
</body>
</html>