const like = document.querySelector('#like')
const dislike = document.querySelector('#dislike')

const nlike = document.querySelector('#numberlike')
const ndislike = document.querySelector('#numberdislike')

like.addEventListener('click', function() {

    if (isLike()) {
        subtract(nlike, like.children[0])
        const request = new XMLHttpRequest();
    	request.open("DELETE", "http://localhost:8181/Assignment/react?state=like");
  		request.send();
        return
    }

	let method = 'POST'
	if (isDislike()) {
        subtract(ndislike, dislike.children[0])
        method = 'PUT'
    }

    add(nlike, like.children[0])
    
    const request = new XMLHttpRequest();
    request.open(method, "http://localhost:8181/Assignment/react?state=like");
  	request.send();
})

dislike.addEventListener('click', function() {

    if (isDislike()) {
        subtract(ndislike, dislike.children[0])
        const request = new XMLHttpRequest();
    	request.open("DELETE", "http://localhost:8181/Assignment/react?state=dislike");
  		request.send();
        return
    }
	
	let method = 'POST'
	if (isLike()) {
        subtract(nlike, like.children[0])
		method = 'PUT'
    }

    add(ndislike, dislike.children[0])
    const request = new XMLHttpRequest();
    request.open(method, "http://localhost:8181/Assignment/react?state=dislike");
  	request.send();
   
})

function isLike() {
	return like.children[0].style.color === 'red'
}

function isDislike() {
    return dislike.children[0].style.color === 'red'
}

function add(value,element) {
    value.textContent = parseInt(value.textContent) + 1
    element.style.color = 'red'
}

function subtract(value,element) {
    value.textContent = parseInt(value.textContent) - 1
    element.style.color = ''
}