<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="user" value="${ sessionScope.user }" />
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<div class="head">
	<div class="head__icon">
		<a class="icon__bars" href="#" ><i class="fas fa-bars"></i></a> 
		<a href="${ contextPath }/video"> <img class="icon__yt" src="${ contextPath }/image/yt.png" alt="icon"> </a>
	</div>
	<form action="${ contextPath }/video/search" class="head__search" method="GET">
		<input class="search__bar" type="text" placeholder="Search" name="s">
		<button class="search__button">
			<i class="fas fa-search"></i>
		</button>
		<a class="search__mic" href=""><i class="fas fa-microphone"></i></a>
	</form>

	<div class="head__acc">
		<a href="${ contextPath }/video/add"><i class="fas fa-video"></i></a> 
		<a href=""><i class="fas fa-th"></i></a> 
		<a href=""><i class="fas fa-bell"></i></a> 
		<c:choose>
			<c:when test="${ user eq null }">
				<a class="acc__text-login" href="${ contextPath }/user/login"> LOGIN </a>
			</c:when>
			
			<c:otherwise>
				<img id="show" class="acc__img" alt="" src="${ contextPath }/${ user.avatar }">	
			</c:otherwise>
		</c:choose>
		
	</div>
</div>

<%--display after click --%>
<div id="user-form" class="user">
  	<div class="user__info">
       <div class="user-info__pic">
           <img src="${ contextPath }/${ user.avatar }" alt="">
       </div>
       <div class="user-info__acc">
           <p class="acc__name">${ user.fullName }</p>
           <p class="acc__email">${ user.email }</p>
           <a href="${ contextPath }/user/profile" class="acc__action">Manage your account</a>
       </div>
   </div>
   <div class="user__action">
       <a class="action__line" href="${ contextPath }/video/myvideo">
           <i class="line__icon fas fa-id-badge"></i>
           <span class="line__text">Your chanel</span>
       </a>
       <div class="action__line">
           <i class="line__icon fab fa-bitcoin"></i>
           <p class="line__text">Purcharses and Membership</p>
       </div>
       <div class="action__line">
           <i class="line__icon fab fa-youtube-square"></i>
           <p class="line__text">Youtube Studio</p>
       </div>
       <a class="action__line" href="${ contextPath }/user/profile">
           <i class="line__icon fas fa-user"></i>
           <span class="line__text">Change password</span>
       </a>
       <a class="action__line" href="${ contextPath }/user/logout">
            <i class="line__icon fas fa-sign-out-alt"></i>
            <span class="line__text">Sign out</span>
        </a>
    </div>
    <div class="user__utils">
        <div class="utils__line">
            <i class="line__icon fas fa-sun"></i>
            <p class="line__text">Appearance: Device Theme</p>
        </div>
        <div class="utils__line">
            <i class="line__icon fas fa-language"></i>
            <p class="line__text">Language: English</p>
        </div>
        <div class="utils__line">
            <i class="line__icon fas fa-globe"></i>
            <p class="line__text">Location: VietNam</p>
        </div>
        <div class="utils__line">
            <i class="line__icon fas fa-cog"></i>
            <p class="line__text">Setting</p>
        </div>
        <div class="utils__line">
            <i class="line__icon fas fa-shield-virus"></i>
            <p class="line__text">Your data in youtube</p>
        </div>
        <div class="utils__line">
            <i class="line__icon fas fa-question-circle"></i>
            <p class="line__text">Help</p>
        </div>
    </div>
</div>