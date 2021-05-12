<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="user" value="${ sessionScope.user }" />
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<div class="profile">
	<form action="${ contextPath }/user/profile" class="profile__form" method="POST" enctype="multipart/form-data">
		<div class="profile--padding-small profile__head">
			<div class="column">
				<p class="head__text--large">Basic info</p>
				<p class="head__text--small">Some info may be visible to other people using Google services.</p>
			</div>

			<div>
				<input id="toggle" type="checkbox" value="true" name="active" checked="${ user.active ? checked : '' }" hidden="true"> 
				<label for="toggle" class="active__toggle"> 
					<span></span>
				</label>
			</div>
		</div>

		<input type="hidden" value="${ user.id }" name="id">
		<input type="hidden" value="${ user.avatar }" name="avatar">
		

		<div class="profile--padding-small">
			<label for="">Fist Name</label> 
			<input class="text--large" type="text" name="firstName" value="${ user.firstName }">
		</div>

		<div class="profile--padding-small">
			<label for="">Last Name</label> 
			<input class="text--large" type="text" name="lastName" value="${ user.lastName }">
		</div>

		<div class="profile--padding-small">
			<label for="">Password</label> 
			<input class="text--large" type="password" name="password" value="${ user.password }">
		</div>

		<div class="profile--padding-small">
			<label for="">Email</label> 
			<input id="email-user" class="text--large" type="text" name="email" value="${ user.email }" readonly="readonly">
		</div>

		<div class="profile--padding-small">
			<label>Gender</label> 
			<select name="gender">
				<option class="gender" value="true" ${ user.gender ? selected : '' }>Male</option>
				<option class="gender" value="false" ${ user.gender ? '' : selected }>Female</option>
			</select>
		</div>
		
		<div class="profile--padding-small">
			<label for="avatar">Photo</label> 
			<input id="avatar" class="text--small" type="file" name="photo"> 
			<img id="photo" src="${ contextPath }/${ user.avatar }">
		</div>

		<div class="profile--padding-small profile__btn">
			<a class="btn__btnBack" href="${ contextPath }/video/">Go back</a>
			<button class="btnSubmit">Submit</button>
		</div>
	</form>
</div>