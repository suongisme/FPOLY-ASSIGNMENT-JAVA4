const show = document.querySelector('#show')
const user = document.querySelector('#user-form')

let isShow = true;

if (show) {
	show.addEventListener('click', function() {
	    if (isShow) {
	        user.style.display = 'flex'
	        isShow = false;
	        return
	    }
	
	    isShow = true
	    user.style.display = 'none'
	})
}