const list = document.getElementsByClassName('selected-list');
const buttons = document.getElementsByClassName('friends-btn');
const conversation = document.querySelector('.conversation');
const friends = document.getElementsByClassName('friend-element');

function handleSelect(event){
    for(let item of buttons){
        item.classList.remove('active');
    }

    event.currentTarget.classList.add('active');

    for(let item of list){
        item.style.display = 'none';
    }

    let field = document.getElementById(event.currentTarget.value);
    field.style.display = 'block';
}

function handleFriend(event){
    destination = event.currentTarget.value;
    conversation.textContent = 'Conversation with: ' + event.currentTarget.value;
    conversation.style.color = '#fff';
}

for(let item of buttons){
    item.addEventListener('click', handleSelect);
}

for(let item of friends){
    item.addEventListener('click', handleFriend);
}