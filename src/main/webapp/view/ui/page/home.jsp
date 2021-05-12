<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<div class="container__screen">
	<div class="screen__suggest-bar">
		<p style="background-color: gray; color: white;">ALL</p>
		<p>Martial Arts Movies</p>
		<p>Computer Programming</p>
		<p>Knowledge</p>
		<p>Human</p>
		<p>History</p>
		<p>Game shows</p>
		<p>Chinese television dramas</p>
		<p>Sun Wukong</p>
		<p>Vietnamese Cuisine</p>
		<p>Comedies</p>
		<p>Eating</p>
		<p>Weather</p>
		<p>Recently upload</p>
	</div>
	<div class="screen__videos">
		<c:forEach var="video" items="${ videos }">
			<c:set var="author" value="${ video.author }" />
			<div class="screen__video">
				<a class="video__poster" href="${contextPath}/video/watch?v=${video.id}">
					<img src="${contextPath}/${ video.thumbnailUrl }" alt="">
				</a>
				<div class="video__info">
					<div class="info__pic">
						<img class="acc__img" alt="" src="${contextPath}/${ author.avatar }">
					</div>
					<div class="info__desc">
						<p class="desc__title">${ video.title }</p>
						<p class="desc__author">${ author.fullName }</p>
						<span class="desc__view"> ${ video.view } views </span> 
						<span class="desc__date" style="margin-left: 10px;">
							<fmt:formatDate value="${ video.date }" type="date"/>
						</span>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
