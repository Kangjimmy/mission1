<%@page import="dto.DistanceDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="service.WifiInfoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	<form action="index.jsp">
	<div id="search">
		LAT: <input id="lat" name="lat" value=<%= request.getParameter("lat") == null ? 0.0 : request.getParameter("lat") %>> ,
		LNT: <input id="lnt" name="lnt" value=<%= request.getParameter("lat") == null ? 0.0 : request.getParameter("lnt") %>>
		<button type="button" onclick="hello()">내 위치 가져오기</button>
		<button type="submit">근처 WIPI 정보 보기</button>
	</div>
	</form>
	
	<table id="customers">
	  <tr>
	    <th>거리(Km)</th>
	    <th>관리번호</th>
	    <th>자치구</th>
	    <th>와이파이명</th>
	    <th>도로명주소</th>
	    <th>상세주소</th>
	    <th>설치위치(층)</th>
	    <th>설치유형</th>
	    <th>설치기관</th>
	    <th>서비스구분</th>
	    <th>망종류</th>
	    <th>설치년도</th>
	    <th>실내외구분</th>
	    <th>WIFI접속환경</th>
	    <th>X좌표</th>
	    <th>Y좌표</th>
	    <th>작업일자</th>
	  </tr>
	  <% if (request.getParameter("lat") == null || request.getParameter("lnt") == null) { %>
	  <tr>
	  	<td id="no-location" colspan = "17">위치정보를 입력한 후에 조회해 주세요</td>
	  </tr>
	  <% } else {
		BigDecimal lnt = new BigDecimal(request.getParameter("lnt"));
		BigDecimal lat = new BigDecimal(request.getParameter("lat"));
	  	WifiInfoService service = new WifiInfoService();
	  	service.insertLocationHistory(lat, lnt);
	  	ArrayList<DistanceDTO> dtoList = service.selectWifiInfo(lnt, lat);
		for (DistanceDTO dto : dtoList) {
	  %>
	  <tr>
	  	<td><%= dto.getDistance() %></td>
	  	<td><%= dto.getX_SWIFI_MGR_NO() %></td>
	  	<td><%= dto.getX_SWIFI_WRDOFC() %></td>
	  	<td><%= dto.getX_SWIFI_MAIN_NM() %></td>
	  	<td><%= dto.getX_SWIFI_ADRES1() %></td>
	  	<td><%= dto.getX_SWIFI_ADRES2() %></td>
	  	<td><%= dto.getX_SWIFI_INSTL_FLOOR() %></td>
	  	<td><%= dto.getX_SWIFI_INSTL_TY() %></td>
	  	<td><%= dto.getX_SWIFI_INSTL_MBY() %></td>
	  	<td><%= dto.getX_SWIFI_SVC_SE() %></td>
	  	<td><%= dto.getX_SWIFI_CMCWR() %></td>
	  	<td><%= dto.getX_SWIFI_CNSTC_YEAR() %></td>
	  	<td><%= dto.getX_SWIFI_INOUT_DOOR() %></td>
	  	<td><%= dto.getX_SWIFI_REMARS3() %></td>
	  	<td><%= dto.getLAT() %></td>
	  	<td><%= dto.getLNT() %></td>
	  	<td><%= dto.getWORK_DTTM() %></td>
	  </tr>
	  <% }} %>
	</table>

</body>
</html>