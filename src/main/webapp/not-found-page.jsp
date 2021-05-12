<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Oops</title>
<link rel="icon" href="${ pageContext.request.contextPath }/image/not-found-icon-24.jpg">
</head>
<body>
	
	<div style="text-align: center;margin-top: 50px;">
		<h1 style="font-size: 5rem;color: blue;margin: 0;">404</h1>
		<p style="font-size: 3rem;font-weight: bold;margin: 20px 0;">Oops! Page not found</p>
		<p style="font-family: arial;">Sorry, but the page you are looking for is not found. Please make sure you have typed the current URL</p>
		<p style="color: red; font-weight: bold; font-size: 1.5rem">${ error }</p>
		<a style="text-decoration: none;background: blue;width: 103px;margin: auto;padding: 10px;color: white;font-weight: bold;" href="${ pageContext.request.contextPath }/video">Go home</a>
	</div>
	
</body>
</html>