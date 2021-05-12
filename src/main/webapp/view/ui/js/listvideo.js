const btnDeletes = document.querySelectorAll('.btn-delete-video')

function addEventsListeners(elements) {
	elements.forEach(element => {
		element.addEventListener('click', (event) => {
			if(!confirm('Are you sure?')) 
				interceptor(event)
		})
	})
}

function interceptor(event) {
	event.preventDefault()
	event.stopPropagation()
}

if (btnDeletes) {
	addEventsListeners(btnDeletes)
}