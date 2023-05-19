function getAllReservas() {
    var myUrl = "http://192.168.3.58:8080/reservas";
    $.ajax({
        type: "GET",
        dataType: "json",
        url: myUrl,
        success: function(data) {
            var html = "";
            for (var i = 0; i < data.length; i++) {
                var reserva = data[i];
                html += "<h3>" + reserva._id + " - " + reserva.lugar + "</h3>";
                html += "<p>Dia: " + reserva.dia + "</p>";
                html += "<p>Numero de personas: " + reserva.num_personas + "</p>";
                html += "<p>Hora de entrada: " + reserva.hora_inicio + "</p>";
                html += "<p>Hora de salida: " + reserva.hora_fin + "</p>";
                html += "<hr>";
            }
            $("#resReserva").html(html);
        },
        error: function(res) {
            alert("ERROR " + res.statusText);
        }
    });
}


function deleteReserva(reservaId) {
    var myUrl ="http:/192.168.3.58:8080/reservas/" + reservaId;
    $.ajax({
        type: "DELETE",
        dataType: "json",
        url: myUrl,
        success: function(data) {
            $("#resReserva").html("<strong>Correcto:</strong> " + data.msg);
        },
        error: function(res) {
            alert("ERROR " + res.statusText);
        }
    });
}