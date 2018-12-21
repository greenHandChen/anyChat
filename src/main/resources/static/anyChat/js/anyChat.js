function initVue() {
    window.vm = new Vue({
        el: '#anyChat',
        data: {
            activeTab: 0,
            chatMsg: null,
            searchUserMsg:null,
            chatFriends: []
        },
        methods:{
            sendMsg: function () {
                anyChatSocket.send(this.chatMsg);
                this.chatMsg = null;
            },
            showSearchUserModal: function () {
                $('#search-user-modal').css('z-index','4').show();
            },
            hideSearchUserModal: function () {
                $('#search-user-modal').hide().css('z-index','-1');
            },
            searchUser: function () {
                console.log(this.searchUserMsg);
            },
            addFriend: function () {

            }
        }
    });
}

function initWebSocket() {
    window.anyChatSocket = null;
    var chatSession = {
        data: null,
        chatPosition: null
    };
    if ('WebSocket' in window) {
        anyChatSocket = new WebSocket("ws://" + window.location.host + "/cux");
    } else if ('MozWebSocket' in window) {
        anyChatSocket = new MozWebSocket("ws://" + window.location.host + "/cux");
    } else {
        anyChatSocket = new SockJS("http://" + window.location.host + "/sockjs/cux");
    }
    anyChatSocket.onopen = function (e) {
        console.log("websocket连接上");
    };
    anyChatSocket.onmessage = function (e) {
        console.log(e.data);
        chatSession = JSON.parse(e.data);
        $('#msg-conent').append(
            '<div class="cux-content" style="text-align: ' + chatSession.chatPosition + ';">' +
            '<img src="/static/img/self.png" class="cux-head">' +
            '<div class="cux-message">' + chatSession.data + '</div>' +
            '</div>')
            .scrollTop($('#msg-conent')[0].scrollHeight);
    };
    anyChatSocket.onerror = function (e) {
        console.log("websocket错误");
    };
    anyChatSocket.onclose = function (e) {
        console.log("websocket关闭");
    }
}

function initNavTavbs() {
    $('.cux-tab-icon').on('click',function (e) {
        $('.cux-tab-icon').removeClass('cux-tab-icon-active');
        $('.cux-tab-page').hide();
        $(e.target).addClass('cux-tab-icon-active');
        var tabPage = $(e.target).attr('cuxTab');
        if (tabPage === 'cux-tab-friend') {
            $.ajax({
                url:"http://localhost:8081/api/getChatFriends",
                dataType: "json",
                success: function (data) {
                    vm.chatFriends = data;
                },
                error: function (data) {

                }
            });
        }
        $('#' + tabPage).show();
    });
}

function initMenu() {
    $('#cux-menu').hover(function () {
        $('.cux-menu-bar').show();
    },function () {
        $('.cux-menu-bar').hide();
    });
    $('.cux-menu-bar').hover(function () {
        $('.cux-menu-bar').show();
    },function () {
        $('.cux-menu-bar').hide();
    });
    $('#cux-add-friend').on('click',function (e) {

    });
}
$(function () {
    initVue();
    initWebSocket();
    initNavTavbs();
    initMenu();
});