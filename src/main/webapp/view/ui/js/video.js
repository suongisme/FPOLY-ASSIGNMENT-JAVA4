const shareBackground = document.querySelector('#share__background')
const share = document.querySelector('#share')
const reactShare = document.querySelector('#react__share')
const exit = document.querySelector('#exit')

exit.addEventListener('click', function() {
    share.style.display = 'none'
})

shareBackground.addEventListener('click', function() {
    share.style.display = 'none'
})

reactShare.addEventListener('click', function() {
    share.style.display = 'flex'
})