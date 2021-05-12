 <div class="video-form">
     <form action="${ action }" method="POST" enctype="multipart/form-data">
		<input id="videoId" type="hidden" value="${ video.id }" name="id">
		<input id="thumbnailUrl" type="hidden" value="${ video.thumbnailUrl }" name="thumbnailUrl">
		<input type="hidden" value="${ video.view }" name="view">
		
         <div class="column">
             <label class="column__title" for="">Title (required)</label>
             <input class="column__input" type="text" name="title" value="${ video.title }">
         </div>
 
         <div class="column">
             <label class="column__title" for="">Description</label>
             <textarea class="column__input" name="desc"  cols="30" rows="10"></textarea>
         </div>
 
         <div class="column">
             <label class="column__title" for="">Link Youtube</label>
             <input id="link-youtube" class="column__input" type="text" name="youtubeUrl" value="${ video.youtubeUrl }">
         </div>
         
         <input id="thumbnail" class="hidden" type="file" name="photo">
         
         <div>
         	<a class="btnBack" href="${ pageContext.request.contextPath }/user/myvideo">Back</a>
         	<button class="btnAdd">Submit</button>
         </div>
     </form>

	<div>
		<div id="video-youtube" class="video"></div>
		<div id="video-thumbnail"
			style="background-image: url(${ pageContext.request.contextPath }/${ video.thumbnailUrl });">
			<label id="thumbnail-add" for="thumbnail"> <i
				class="fas fa-images"></i> upload thumbnail
			</label>
		</div>
	</div>
</div>