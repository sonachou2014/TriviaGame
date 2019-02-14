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
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/main', function (greeting) {
            showMessage(JSON.parse(greeting.body).name, JSON.parse(greeting.body).text);
        });
    });
    loadScoreBoard();
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

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function showMessage(name, text) {
    $("#greetings").append("<tr><td>" + name + ": " + text + "</td></tr>");
    if (text == "abc") changeScore(name);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMsg(); });
});

function loadScoreBoard() {
    $.ajax({
        url: 'http://localhost:8080/scoreboard',
        dataType: 'json',
        success: function(data) {
          for (let i = 0; i < data.length; i++) {
            addRow(data[i].userId, data[i].score)
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
        url: 'http://localhost:8080/changeScore?userId=' + userId
    });
    $("#tbody").html("");
    loadScoreBoard();
}

$('document').ready(function(){

    $("#apibtn").click(function () {
        $.ajax({
            url: 'http://jservice.io/api/random',
            dataType: 'json',
            success: function (data) {
                console.log(data);
            }
        })
    });
})



