const avt = document.querySelector('#avatar')
const img = document.querySelector('#photo')

avt.addEventListener('change', function(event) {
	const file = event.target.files[0]
	img.src = URL.createObjectURL(file)
})

const toggle = document.querySelector('#toggle')

toggle.addEventListener('change', function() {
    if (!toggle.checked) {
        let a = confirm('Your account will be disable. You will have to contact the administrators to re-active. If you are sure, click SUBMIT')

        if (!a) input.checked = true
    }
})