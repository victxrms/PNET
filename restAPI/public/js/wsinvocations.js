function getAllReservas() {
    var myUrl = "http://127.0.0.1:5500/restAPI/public/rest.html";
    $.ajax({
        type: "GET",
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

function deleteReserva(reservaId) {
    var myUrl = "http://127.0.0.1:5500/restAPI/public/rest.html/" + reservaId;
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