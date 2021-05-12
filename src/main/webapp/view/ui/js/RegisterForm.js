const chkPass = document.querySelector('#show_password')

chkPass.addEventListener('change', () => {
    if (chkPass.checked) {
        password.type = 'text'
        confirm.type = 'text'
        return
    }

        password.type = 'password'
        confirm.type = 'password'

})


const avatar = document.querySelector('#photo')
const img = document.querySelector('#img')
avatar.addEventListener('change', function(e) {
	img.src = URL.createObjectURL(e.target.files[0])
})