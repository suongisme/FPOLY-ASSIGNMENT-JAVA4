const password = document.querySelector('#password')
const confirm = document.querySelector('#confirm')
const btnLogin = document.querySelector('#btn-login')

password.addEventListener('keyup', check)

confirm.addEventListener('keyup', check)

function check() {
	if (!isMatch()) {
		changeBorderColor('red')
		btnLogin.disabled = true
		return
	}
	btnLogin.disabled = false
	changeBorderColor('#ccc')
}

function changeBorderColor(color) {
	password.style.borderColor = color
	confirm.style.borderColor = color
}

function isMatch() {
	const passwordVal = password.value
	const confirmVal = confirm.value
	
	return passwordVal === confirmVal
}