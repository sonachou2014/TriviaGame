var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    var name = $("#username").text();
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/topic/main', function (msg) {
//            console.log(msg);
            showMessage(JSON.parse(msg.body).name, JSON.parse(msg.body).text);
            if (JSON.parse(msg.body).name == "Trivia" && JSON.parse(msg.body).text == "correct!") {
                $("#tbody").html("");
                loadScoreBoard();
            }
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMsg() {
    stompClient.send("/app/msg", {}, JSON.stringify({'name': $("#username").text(), 'text': $("#text").val()}));
}

function showMessage(name, text) {
    $("#greetings").append("<tr><td>" + name + ": " + text + "</td></tr>");
    updateScroll();
}

$(function () {
    $("form").on('submit', function (e) {e.preventDefault();});
    //$( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMsg();});
    connect();
    loadScoreBoard();
});

function loadScoreBoard() {
    $.ajax({
        url: 'http://localhost:8080/scoreboard',
        dataType: 'json',
        success: function(data) {
        console.log(data);
        data.sort(compare);
        console.log(data);
          for (let i = 0; i < data.length; i++) {
            addRow(data[i].name, data[i].score)
          }
        }
    });
}

function addRow(userId, score) {
    let row = $("<tr></tr>");
    let userIdCell = $("<td></td>").text(userId);
    let scoreCell = $("<td></td>").text(score);

    $(row).append(userIdCell);
    $(row).append(scoreCell);
    $("#tbody").append(row);
}

function changeScore(userId) {
    $.ajax({
        url: 'http://localhost:8080/changeScore?userId=' + userId});
    $("#tbody").html("");
    loadScoreBoard();
}

function updateScroll(){
	var chatList = document.getElementById("chatbox-container");
	chatList.scrollTop = chatList.scrollHeight;
}
function compare(a,b) {
  if (a.score > b.score)
    return -1;
  if (a.score < b.score)
    return 1;
  return 0;
}
