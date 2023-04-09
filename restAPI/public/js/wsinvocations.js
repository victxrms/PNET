function getAllReservas() {
    var myUrl = "http://127.0.0.1:8080/reservas";
    $.ajax({
        type: "GET",
        dataType: "json",
        url: myUrl,
        success: function(data) {
            var reservaHtml = "<ul>";
            for (var i = 0; i < data.length; i++) {
                reservaHtml += "<li>" + JSON.stringify(data[i]) + "</li>";
            }
            reservaHtml += "</ul>";
            $("#resReserva").html(reservaHtml);
        },
        error: function(res) {
            alert("ERROR " + res.statusText);
        }
    });
}


function deleteReserva(reservaId) {
    var myUrl ="http://127.0.0.1:8080/reservas/" + reservaId;
    $.ajax({
        type: "DELETE",
        dataType: "json",
        url: myUrl,
        success: function(data) {
	    	$("#resReserva").html(JSON.stringify(data));
        },
        error: function(res) {
            alert("ERROR " + res.statusText);
        }
    });
}