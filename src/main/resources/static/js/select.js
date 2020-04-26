const list = document.getElementsByClassName('selected-list');
const buttons = document.getElementsByClassName('friends-btn');

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

for(let item of buttons){
    item.addEventListener('click', handleSelect);
}