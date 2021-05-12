<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
	<link rel="icon" href="${ contextPath }/image/yt-icon.png">
	<link rel="stylesheet" href="${ contextPath }/view/ui/css/layout.css">
	<link rel="stylesheet" href="${ contextPath }/view/ui/css/navigate.css">
	<link rel="stylesheet" href="${ contextPath }/view/ui/css/menu.css">
	<link rel="stylesheet" href="${ contextPath }/view/ui/css/${ cssPage }">
	<title>${ title }</title>
</head>
<body>
	<%@ include file="page/navigate.jsp" %>
	<div class="container">
		<%@ include file="page/menu.jsp" %>	
		<jsp:include page="${ page }"/>
	</div>
	
	<script type="text/javascript" src="${ contextPath }/view/ui/js/navigate.js"></script>
	<script type="text/javascript" src="${ contextPath }/fontawesome.js"></script>
	<script type="text/javascript" src="${ contextPath }/view/ui/js/${ jsPage }"></script>
</body>
</html>