const messageArea = document.querySelector('#chat-area');
const chatForm = document.querySelector('#chat-form');
const messageInput = document.querySelector('#message');
const errorElement = document.querySelector('.error');

var stompClient = null;

function connect(event){
    if(username){
        let socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
}

function onConnected() {
    stompClient.subscribe('/user/queue/private', onMessageReceived)
}

function onError(error){
    errorElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
}

function sendMessage(event) {
    let messageContent = messageInput.value.trim();
    if(messageContent && stompClient && destination) {
        let chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send('/app/chat/'+ destination +'.toUser', {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload){
    let message = JSON.parse(payload.body);

    let messageElement = document.createElement('div');

    if(message.type === 'CHAT'){
        messageElement.classList.add('chat-message');

        let usernameElement = document.createElement('span');
        let usernameText = document.createTextNode(message.sender + ':');
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    let textElement = document.createElement('p');
    let messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}
document.addEventListener('DOMContentLoaded', function() {
    connect();
});
chatForm.addEventListener('submit', sendMessage, true);