<%@page import="service.WifiInfoService"%>
<%@page import="dto.RowDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.ApiCall"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ArrayList<RowDTO> dtoList = ApiCall.apiCall();
	
	WifiInfoService service = new WifiInfoService();
	service.truncateWifiInfo();
	int count = service.insertWifiInfo(dtoList);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="load-wifi">
		<h1 id="wifi-head"><%= count %>개의 WIFI 정보를 정상적으로 저장하였습니다.<br/></h1>
		<a href="http://localhost:8080">홈 으로 가기</a>
	</div>
</body>
</html>