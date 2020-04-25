const messageArea = document.querySelector('#chat-area');
const chatForm = document.querySelector('#chat-form');
const messageInput = document.querySelector('#message');
const connectingElement = document.querySelector('.connecting');

var stompClient = null;

function connect(event){
    username = username.innerText;

    if(username){
        let socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
}

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived)

    stompClient.sendMessage('/app/chat.addUser',
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )
}

function onError(error){
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = '#6f42c1';
}

function sendMessage(event) {
    let messageContent = messageContent.value.trim();
    if(messageContent && stompClient) {
        let chatMessage = {
            sender: username,
            messageContent: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload){
    let message = JSON.parse(payload.body);

    let messageElement = document.createElement('li');

    if(message.type === 'JOIN'){
        messageElement.classList.add('event-message');
        message.content = message.sender + ' join!';
    }else if(message.type === 'LEAVE'){
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    }else{
        messageElement.classList.add('chat-message');

        let usernameElement = document.createElement('span');
        let usernameText = document.createTextNode(message.sender);
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
    connect
});
chatForm.addEventListener('submit', sendMessage, true);