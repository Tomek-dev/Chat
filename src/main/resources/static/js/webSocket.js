const messageArea = document.querySelector('#chat-area');
const chatForm = document.querySelector('#chat-form');
const messageInput = document.querySelector('#message');
const connectingElement = document.querySelector('.connecting');

function connect(event){
    username = username.innerText;

    if(username){
        let socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
}

function onConnected() {
    let username = [[${#authentication.getPrincipal().getUsername()}]]

    stompClient.subscribe('/topic/public', onMessageReceived)

    stompClient.sendMessage('/app/chat.addUser',
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )
}

function onError(error){
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = '';
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

}