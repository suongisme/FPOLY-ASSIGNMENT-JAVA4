const youtubeVideo = document.querySelector('#video-youtube')
const link = document.querySelector('#link-youtube')

link.addEventListener('keyup', loadImage)

function loadImage() {
    let url = link.value

    if (!url.startsWith('https://www.youtube.com/watch?v')) {
        if (youtubeVideo.childNodes[0])
            youtubeVideo.removeChild(youtubeVideo.childNodes[0])
        return
    }

    let embedUrl = url.replace('watch?v=','embed/')

    youtubeVideo.innerHTML = `<iframe src=${embedUrl} style='width: 100%; height: 100%; border: none; outline=none;'></iframe>`
}
loadImage();

const thumbnailAdd = document.querySelector('#video-thumbnail')
const thumbnailUrl = document.querySelector('#thumbnailUrl')
const videoId = document.querySelector('#videoId')
const thumbnail = document.querySelector('#thumbnail')

thumbnail.addEventListener('change', function(event) {
	const file = event.target.files[0]
    thumbnailAdd.style.backgroundImage = `url(${URL.createObjectURL(file)})`
    thumbnailUrl.value = `user-image/video/id-${videoId.value}`
    
})