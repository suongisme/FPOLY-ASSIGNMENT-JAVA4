<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="poly.pt15307.sof3011.constant.VideoConstant" %>    
<c:set var="user" value="${ sessionScope.user }" />
<c:set var="videos" value="${ user.videosMap.values() }" />
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<div class="channel">
    <div class="channel__top">
        <div class="info">
            
            <div class="info__avatar">
                <img src="${ contextPath }/${ user.avatar }" alt="">
            </div>
            
            <div class="info__profile">
                <p class="profile__name">${ user.fullName }</p>
                <p class="profile__email">${ user.email }</p>
            </div>
        </div>

        <div class="button">
            <a class="btnProfile" href="${ contextPath }/user/profile">PROFILE</a>
            <a class="btnAdd" href="${ contextPath }/video/add">ADD VIDEO</a>
        </div>

    </div>

    <div class="channel__videos">
       <table>
           <thead>
               <tr>
                   <th>Title</th>
                   <th>View</th>
                   <th>Date</th>
                   <th>Like</th>
                   <th>Dislike</th>
                   <th>Edit</th>
                   <th>Delete</th>
               </tr>
           </thead>

           <tbody>
           		<c:choose>
           			<c:when test="${ videos.size() ne 0 }">
            			<c:forEach var="video" items="${ videos }">
             		<tr>
                      <td>${ video.title }</td>
                      <td>${ video.view }</td>
                      <td>
                   			<fmt:formatDate value="${ video.date }" type="date"/>
                      </td>
                      <td>${ video.getNumber(VideoConstant.LIKE) }</td>
                      <td>${ video.getNumber(VideoConstant.DISLIKE) }</td>
                      <td>
                      		<a href="${ contextPath }/video/edit?v=${ video.id }">
                      			<i class="fas fa-edit"></i>
                      		</a>
                      	</td>
                      <td>
                      		<a class="btn-delete-video" href="${ contextPath }/video/delete?v=${ video.id }">
                      			<i class="fas fa-trash"></i>
                      		</a>
                      </td>
                  </tr>
            		</c:forEach>
           			</c:when>
           			<c:otherwise>
           				<tr >
           					<td colspan="5" style="height: 200px; font-size: 4rem; font-weight: bold;"> You have no video </td>
           				</tr>
           			</c:otherwise>
           		</c:choose>
          		
           </tbody>
       </table>
    </div>
</div>