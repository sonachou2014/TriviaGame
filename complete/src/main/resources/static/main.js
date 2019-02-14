
$(document).ready(function () {
    $("#apibtn").click(function () {
        $.ajax({
            url: 'http://jservice.io/api/random',
            dataType: 'json',
            success: function (data) {
                getAPI(data);
            }
        });
    });
})

function getAPI(data) {

    console.log(data);
    console.log(data[0].question);
    console.log(data[0].answer);


    var question = data[0].question;
    var answer = data[0].answer;


    var table = document.getElementById("chatbox");
    var row = table.insertRow(1);
    cell1 = row.insertCell(0);

    cell1.innerHTML = 'Trivia Master:' + data[0].question;


}
