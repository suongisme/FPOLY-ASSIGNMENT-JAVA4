<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<div class="container__screen">
	<c:choose>
		<c:when test="${ videos.size() gt 0 }">
			<c:forEach var="video" items="${ videos }">
				<a class="screen__video" href="${ contextPath }/video/watch?v=${ video.id }">
					<span class="video__pic">
						<img src="${ contextPath }/${ video.thumbnailUrl }" alt="${ video.title }">
					</span>
					<span class="video__info">
						<span class="info__title">${ video.title }</span>
						<span class="info__author">
							${ video.author.fullName }
						</span>
						
						<span>
							<i class="fas fa-check-circle"></i>
							<span>Loved</span>
						</span>
					</span>
				</a>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h3 class="not-loved">You haven't loved any videos</h3>
		</c:otherwise>
	</c:choose>
	
</div>
