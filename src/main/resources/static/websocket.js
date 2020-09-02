var stompClient = null;
var connected = false;
var numResponses = 0;

function connect() {
    var socket = new SockJS('/chat');

    stompClient = Stomp.over(socket);
    stompClient.connect({},
        function(frame) {
            console.log('Connected: ' + frame);
            displayStatus('Connected');
            connected = true;

            setInterval(sendCurrentTime, 1000);

            stompClient.subscribe('/topic/messages', addResponse);
        },
        function() {
            displayStatus('Disconnected!!!!!');
            connected = false;
        }
    );
}

function sendCurrentTime() {
    if (connected) {
        var msg = new Date().toString();
        stompClient.send("/app/chat", {}, msg);
    }
}

function addResponse() {
    var response = document.getElementById('responses');
    response.innerHTML = ++numResponses;
}

function displayStatus(newStatus) {
    var status = document.getElementById('status');
    status.innerHTML = newStatus;
}
