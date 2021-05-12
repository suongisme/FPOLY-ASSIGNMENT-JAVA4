<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.pt15307.sof3011.constant.VideoConstant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:set var="user" value="${ sessionScope.user }" />
    <c:set var="contextPath" value="${ pageContext.request.contextPath }" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${ contextPath }/image/yt-icon.png">
    <link rel="stylesheet" href="${ contextPath }/view/ui/css/video.css">
    <link rel="stylesheet" href="${ contextPath }/view/ui/css/navigate.css">
    <title>${ video.title }</title>
</head>

<body>
   <%@ include file="page/navigate.jsp" %>
    <div class="container">
        <div class="container__main">
            <div class="main__video">
            	<!-- "https://www.youtube.com/embed/VSCiCmaYcUY" -->
                <iframe class="video__screen" src="${ fn:replace(video.youtubeUrl, 'watch?v=', 'embed/') }?autoplay=1&mute=1" ></iframe>

                <p class="video__title">${video.title }</p>

                <div class="video__info">
                    <div>
                        <span class="video__view">${ video.view } views</span>
                        <span class="video__date">
                        	<fmt:formatDate value="${ video.date }" type="date"/>
                        </span>
                    </div>
                    <div class="info__react">
                    
                    	<c:choose>
                    		<c:when test="${ user ne null }">
	                    		<p id="like">
	                            	<i style="color: ${ video.contains(true, user) ? 'red' : '' }" class="fas fa-heart"></i>
	                            	<span id="numberlike">${ numberLike }</span>
	                        	</p>
	                        	<p id="dislike">
	                            	<i style="color: ${ video.contains(false, user) ? 'red' : '' }" class="fas fa-heart-broken"></i>
	                            	<span id="numberdislike">${ numberDislike }</span>
	                        	</p>
                    		</c:when>
                    		
                    		<c:otherwise>
                    			<a href="${ contextPath }/user/login">
	                            	<i class="fas fa-heart"></i>
	                            	<span id="numberlike">${ numberLike }</span>
	                        	</a>
	                        	<a href="${ contextPath }/user/login">
	                            	<i class="fas fa-heart-broken"></i>
	                            	<span id="numberdislike">${ numberDisike }</span>
	                        	</a>
                    		</c:otherwise>
                    	</c:choose>
                        
                        <p id="react__share">
                            <i class="fas fa-share"></i>
                            SHARE
                        </p>
                        <a href="">
                            <i class="fas fa-plus-square"></i>
                            SAVE
                        </a>
                        <a href="">
                            <i class="fas fa-ellipsis-h"></i>
                        </a>
                    </div>

                </div>
					
				<div class="video__comment">
					<h4>Comments are turned off</h4>
				</div>
			
            </div>
        </div>
        <div class="container__list">
            <div class="suggestion">
                <p style="background-color: gray; color: white;">All</p>
                <p>Martial Arts Movies</p>
                <p>Computer Programming</p>
                <p>Knowledge</p>
                <p>Human</p>
                <p>History</p>
            </div>
            <c:forEach var="video" items="${ videos }">
            	<a class="list__video" href="${ contextPath }/video/watch?v=${video.id}">
	                <span class="video__poster">
	                    <img src="${ contextPath }/${ video.thumbnailUrl }" alt="">
	                </span>
	                <span class="list-video__info">
	                    <span class="info__title">${ video.title }</span>
	                    <span class="info__author block">${ video.author.fullName }</span>
	                    <span class="info__view">
	                        ${ video.view } views
	                    </span>
	                    <span class="info__date" style="margin-left: 10px;">
	                        <fmt:formatDate value="${ video.date }" type="date"/>
	                    </span>
	                </span>
	            </a>
            </c:forEach>
        </div>
    </div>
    
    <div id="share" class="share">
        <div id="share__background"></div>
        <div class="share__container">
            <div class="share-container__head">
                <span>Share</span>
                <i id="exit" class="fas fa-times"></i>
            </div>
            <div class="share-container__slide">
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/embeb.png" alt="Icon">
                    <span class="social__text">EMBED</span>
                </div>

                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/mail.png" alt="Icon">
                    <span class="social__text">Email</span>
                </div>

                <div class="social">

                    <img class="social__icon" src="${ contextPath }/image/icon-social/facebook.png" alt="Icon">
                    <span class="social__text">Facebook</span>
                </div>

                <div class="social">

                    <img class="social__icon" src="${ contextPath }/image/icon-social/twitter.png" alt="Icon">
                    <span class="social__text">Twitter</span>
                </div>
                
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/talk.png" alt="Icon">

                    <span class="social__text">카카오스토리</span>
                </div>
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/reddit.png" alt="Icon">

                    <span class="social__text">Reddit</span>
                </div>
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/ВКонтакте.png" alt="Icon">

                    <span class="social__text">ВКонтакте</span>
                </div>
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/Одноклассники.png" alt="Icon">

                    <span class="social__text">Одноклассники</span>
                </div>
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/Pinterest.png" alt="Icon">

                    <span class="social__text">Pinterest</span>
                </div>
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/Blogger.png" alt="Icon">

                    <span class="social__text">Blogger</span>
                </div>
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/Tumblr.png" alt="Icon">

                    <span class="social__text">Tumblr</span>
                </div>
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/LinkedIn.png" alt="Icon">

                    <span class="social__text">LinkedIn</span>
                </div>
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/Skyrock.png" alt="Icon">
                    <span class="social__text">Skyrock</span>
                </div>
                <div class="social">
                    <img class="social__icon" src="${ contextPath }/image/icon-social/Mix.png" alt="Icon">
                    <span class="social__text">Mix</span>
                </div>
                
            </div>

            <div class="share-container__link-video">
                <span>${ video.youtubeUrl }</span>
                <span>copy</span>
            </div>
        </div>
    </div>

	<script type="text/javascript" src="${ contextPath }/view/ui/js/likevideo.js"></script>
	<script type="text/javascript" src="${ contextPath }/view/ui/js/video.js"></script>
	<script type="text/javascript" src="${ contextPath }/view/ui/js/navigate.js"></script>
    <script type="text/javascript" src="${ contextPath }/fontawesome.js"></script>
</body>

</html>