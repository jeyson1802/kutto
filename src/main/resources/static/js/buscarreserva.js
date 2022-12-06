var txt_busqueda;
var btn_buscar;
var div_buscar;
var sec_message;
var h5_codigo_reserva;
var h2_data_nombres;
var h3_data_fecha;
var h3_data_hora;

var HttpStatus = {
	OK  				: 200,
	Accepted  			: 202,
	NoContent  			: 204,
	BadRequest			: 400,
	Forbidden			: 403,
	NotFound 			: 404,
	InternalServerError	: 500
};

$(document).ready(function(){
    inicializarVariables();
    inicializarEventos();
});

function inicializarVariables() {

    txt_busqueda = $("#txt_busqueda");
    btn_buscar = $("#btn_buscar");
    div_buscar = $("#div_buscar");
	sec_message = $("#sec_message");
    h5_codigo_reserva = $("#h5_codigo_reserva");
    h2_data_nombres = $("#h2_data_nombres");
    h3_data_fecha = $("#h3_data_fecha");
    h3_data_hora = $("#h3_data_hora");
}

function inicializarEventos(){

   btn_buscar.on('click', function() {
       	buscarReserva();
   });
}

function buscarReserva() {

    $.ajax({
    	    type:"GET",
    	    cache: false,
    	    contentType : "application/json",
    	    accept: 'text/plain',
    	    url : '/buscarreserva/' + txt_busqueda.val(),
    	    data : null,
    	    dataType: 'text',
    	    success:function(result,textStatus,xhr){
    	    
    	    	if(xhr.status == HttpStatus.OK){

                    var resultado = JSON.parse(result);

                    div_buscar.addClass("d-none");

                    h5_codigo_reserva.html('CODIGO DE RESERVA : ' + resultado.codigoCita);
                    h2_data_nombres.html(resultado.nombres);
                    h3_data_fecha.html($.date(resultado.fecha));
                    h3_data_hora.html(resultado.horario);
                    sec_message.removeClass("d-none");

                    $('body,html').animate({
                        scrollTop: 250
                    }, 800);

    	        } else if(xhr.status == HttpStatus.NoContent){
                    alert('No se ha encontrado una reserva con los datos enviados.');
    	        	console.log("llenarHorariosDisponibles, Accepted....");
    	        }
    	    },
    	    error: function (xhr, error, code){

    	    	console.log("llenarHorariosDisponibles, error...." + xhr.status);
    	    }
    });
}

$.date = function(dateObject) {
    var d = new Date(dateObject);
    var day = d.getDate();
    var month = d.getMonth() + 1;
    var year = d.getFullYear();
    if (day < 10) {
        day = "0" + day;
    }
    if (month < 10) {
        month = "0" + month;
    }
    var date = day + "/" + month + "/" + year;

    return date;
};

