const addFriend = document.querySelector('#friends-form');
const friendInput = document.querySelector('#invite-input');
const error = document.querySelector('.error');

function handleSubmit(event) {
    event.preventDefault();
    const name = friendInput.value.trim();
    if(name){
        fetch('/invite?username=' + name, {
            method: 'POST'
        });
        friendInput.value = '';
    }
}

addFriend.addEventListener('submit', handleSubmit);