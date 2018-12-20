var webSocket;
var chatSession = {
    data: null,
    chatPosition: null
};

function initWebSocket() {
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://" + window.location.host + "/cux");
    } else if ('MozWebSocket' in window) {
        webSocket = new MozWebSocket("ws://" + window.location.host + "/cux");
    } else {
        webSocket = new SockJS("http://" + window.location.host + "/sockjs/cux");
    }
    webSocket.onopen = function (evnt) {
        console.log("websocket连接上");
    };
    webSocket.onmessage = function (evnt) {
        console.log(evnt.data);
        chatSession = JSON.parse(evnt.data);
        $('#msg-conent').append(
            '<div class="cux-content" style="text-align: ' + chatSession.chatPosition + ';">' +
            '<img src="/static/img/self.png" class="cux-head">' +
            '<div class="cux-message">' + chatSession.data + '</div>' +
            '</div>')
            .scrollTop($('#msg-conent')[0].scrollHeight);
    };
    webSocket.onerror = function (evnt) {
        console.log("websocket错误");
    };
    webSocket.onclose = function (evnt) {
        console.log("websocket关闭");
    }
}

function initClickEvent() {
    $('#send-msg').on('click', function (event) {
        webSocket.send($('#msg-input').val());
        $('#msg-input').val(null);
    });
}

$(function () {
    initWebSocket();
    initClickEvent();
});