<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="list.ArrayList"%>
<%@ page import="list.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="fifthIframPageCSS.css">
<meta charset="EUC-KR">
<title>IframPage</title>
</head>
<body>
	<%
		ArrayList<Object[][]> finalList = (ArrayList<Object[][]>) session.getAttribute("outputOfTheTimeTable");
		Iterator<Object[][]> iteratorOfArray = finalList.listIterator();
		while (iteratorOfArray.hasNext()) {
			Object[][] array = iteratorOfArray.next();
	%>
	<table class="times">
		<thead>
			<tr>
				<th class="head"></th>
				<th class="bodyOftr">��</th>
				<th class="bodyOftr">ȭ</th>
				<th class="bodyOftr">��</th>
				<th class="bodyOftr">��</th>
				<th class="bodyOftr">��</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (int j = 1; j < 28; j++) {
			%>
			<tr height=25px>
				<%
					for (int i = 0; i < 6; i++) {
								if (array[i][j] != null) {
									if (i == 0) {
				%>
				<th class="head"><%=array[i][j]%></th>
				<%
					} else {
				%>
				<th class="bodyOftr"><%=array[i][j]%></th>
				<%
					}
								} else {
				%>
				<td class="bodyOftr"></td>
				<%
					}
							}
				%>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<%
		}
	%>
</body>
</html>