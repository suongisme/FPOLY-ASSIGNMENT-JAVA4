<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
<head>
	<c:set var="contextPath" value="${ pageContext.request.contextPath }"/>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${ contextPath }/view/ui/css/LoginForm.css">
    <link rel="icon" href="${ contextPath }/image/gg.webp">
    <title>Youtube</title>
</head>
<body>
    <div class="login">
        <div class="logo-gg">
            <img src="${ contextPath }/image/gg.png" alt="">
        </div>
        <div class="login__title">
            <p>Sign in</p>
        </div>
        <div class="login__title2">
            <p>to continue to YouTube</p>
        </div>

        <form action="${ contextPath }/user/login" method="POST">
            <input class="form__email" type="email" placeholder="Email" name="email" required="required">
            <input class="form__password" type="password" name="password" placeholder="Password" minlength="8" required="required">
            <a class="form__forgot-pass" href="">Forgot password?</a>
            <span>
                Not your computer? Use Guest mode to sign in privately.
                <a href="">Learn more</a>
            </span>
            <div class="form__btn">
                <a class="btn__create" href="${ contextPath }/user/register">Create account</a>
                <button class="btn__login">Login</button>
            </div>
        </form>
    	
    	<h4 class="status">${ status }</h4>
    </div>
</body>
</html>