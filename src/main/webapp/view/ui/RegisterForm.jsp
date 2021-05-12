<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
<head>
	<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${ contextPath }/view/ui/css/RegisterForm.css">
	<link rel="icon" href="${ contextPath }/image/gg.webp">
    <title>Youtube</title>
</head>
<body>
    <div class="register">
        <div class="register__form">
            <div class="icon-gg">
                <img src="${ contextPath }/image/gg.png" alt="">
            </div>
            <div class="form__title1">Create your Google Account</div>
            <div class="form__title2">to continue to YouTube</div>
            
            <form action="${ contextPath }/user/register" method="POST" enctype="multipart/form-data">
                <div class="form__name">
                    <input type="text" placeholder="First name" name="firstName" required="required">
                    <input type="text" placeholder="Last name" name="lastName" required="required">
                </div>

                <div class="form__email">
                	<h3 style="color: red;">* ${ status }</h3>
                    <input type="email" placeholder="Email" name="email" required="required">
                    <p>You need to confirm this email belongs to you</p>
                </div>
	
				<div>
					<select name="gender">
						<option class="gender" value="true" selected="selected">Male</option>
						<option class="gender" value="false">Female</option>
					</select>  
				</div>   
				
				<div class="form__pass">
                    <div>
                        <input id="password" type="password" placeholder="Password" name="password" required="required" minlength="8">
                        <input id="confirm" type="password"  placeholder="Confirm" name="confirm" required="required" minlength="8">
                    </div>
                    <p>Use 8 or more characters with a mix of letters, numbers &amp; symbols</p>
                </div>           
                
                <div class="form__photo">
                    <label for="photo" class="avatar__text">Your Photo: </label>
                    <input id="photo" class="avatar__choose" type="file" name="photo">
                    <img id="img">
                </div>

                <div class="form__checkbox">
                    <input id="show_password" type="checkbox">
                    <label for="show_password">Show password</label>
                </div>
                
                <div class="form__btn">
                    <a class="btn__login" href="${ contextPath }/user/login">Sign in instead</a>
                    <button id="btn-login" class="btn__register">Register</button>
                </div>
            </form>
        </div>
        <div class="register__logo">
            <div>
                <img src="https://ssl.gstatic.com/accounts/signup/glif/account.svg" alt="">
                <p>One account. All of Google working for you.</p>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="${ contextPath }/view/ui/js/RegisterForm-Validate.js"></script>
    <script type="text/javascript" src="${ contextPath }/view/ui/js/RegisterForm.js"></script>
</body>
</html>