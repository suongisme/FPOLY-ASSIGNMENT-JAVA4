<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${ pageContext.request.contextPath }" />
<div class="container__nav">
	<a class="nav__icon" href="${ context}/video/"><i class="fas fa-home"></i>Home</a> 
	<a class="nav__icon" href="${ context }/user/profile"><i class="fas fa-compass"></i>Profile</a>
	<a class="nav__icon" href="${ context }/user/loved"><i class="fas fa-heart"></i>Loved</a>
	<a class="nav__icon" href="${ context }/user/myvideo"><i class="fas fa-photo-video"></i>Library</a>
</div>
