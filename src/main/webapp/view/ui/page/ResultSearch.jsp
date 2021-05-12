<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container__screen">
	<c:forEach var="video" items="${ videos }">
		<a class="screen__video" href="${ pageContext.request.contextPath }/video/watch?v=${ video.id }">
			<span class="video__pic">
				<img src="${ video.thumbnailUrl }" alt="${ video.title }">
			</span>
			<span class="video__info">
				<span class="info__title">${ video.title }</span>
				<span class="info__view"> ${ video.view } views	&nbsp;-	&nbsp; </span> 
				<span class="info__date">  
					<fmt:formatDate value="${ video.date }" type="date"/>	
				</span>
				<span class="info__author">
					<img class="info-author__avatar" alt="" src="${ video.uploadBy.avatar }">
					${ video.uploadBy.fullName }
				</span>
				<span class="info__desc">
					thienduongamthuc #thienduongamthucmua6
					#thienduongamthuc6 Thiên đường ẩm thực 6|Tập 12 Full: Liêu Hà Trinh
				</span>
			</span>
		</a>
	</c:forEach>
</div>
