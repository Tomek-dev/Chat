const addFriend = document.querySelector('#friends-form');
const friendInput = document.querySelector('#invite-input');

function formSubmit(event) {
    event.preventDefault();
    const name = friendInput.value.trim();
    if(name){
        fetch('/invite?username=' + name, {
            method: 'POST'
        })
        friendInput.value = '';
    }
}

addFriend.addEventListener('submit', formSubmit);