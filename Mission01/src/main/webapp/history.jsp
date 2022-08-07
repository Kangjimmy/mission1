<%@page import="dto.HistoryDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.WifiInfoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	  if (request.getParameter("id") != null) {
		  WifiInfoService service = new WifiInfoService();
		  service.deleteLocationHistory(Integer.valueOf(request.getParameter("id")));
	  }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link rel="stylesheet" href="css/style.css">
<script src="script/script.js"></script>
</head>
<body>
	<div id="main-title">
		<h2>와이파이 정보 구하기</h2>
	</div>
	<div id="index">
		<a href="http://localhost:8080">홈</a> |
		<a href="history.jsp">위치 히스토리 목록</a> |
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	</div>

	<table id="customers">
	  <tr>
	    <th>ID</th>
	    <th>X좌표</th>
	    <th>Y좌표</th>
	    <th>조회일자</th>
	    <th>비고</th>
	  </tr>
	  <%
	  
	  WifiInfoService service = new WifiInfoService();
	  ArrayList<HistoryDTO> dtoList = service.selectLocationHistory();
	  
	  if (dtoList.size() > 0) {
		  for (HistoryDTO dto : dtoList) {
			  %>
			  <tr>
			  	<td><%= dto.getId() %></td>
			  	<td><%= dto.getLat() %></td>
			  	<td><%= dto.getLnt() %></td>
			  	<td><%= dto.getQuery_date() %></td>
			  	<td style="text-align: center;">
			  		<input type="button" onclick="document.location.href='history.jsp?id=<%= dto.getId() %>'" value="삭제">
			  	</td>
			  </tr>
			  <% 
		  }
	  }
	  
	  %>
	</table>
</body>
</html>