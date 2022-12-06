var txt_nombres;
var txt_correo;
var txt_documento;
var sel_fecha;
var sel_hora;
var btn_reservar;
var sec_breadcumb;
var sec_body;
var sec_message;
var h5_codigo_reserva;

var HttpStatus = {
	OK  				: 200,
	Accepted  			: 202,
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

    txt_nombres = $("#txt_nombres");
    txt_correo = $("#txt_correo");
    txt_documento = $("#txt_documento");
	sel_fecha = $("#sel_fecha");
    sel_hora = $("#sel_hora");
    btn_reservar = $("#btn_reservar");
    sec_breadcumb = $("#sec_breadcumb");
    sec_body = $("#sec_body");
    sec_message = $("#sec_message");
    h5_codigo_reserva = $("#h5_codigo_reserva");
}

function inicializarEventos(){

   sel_fecha.on('change', function() {
    	llenarHorariosDisponibles();
   });

   btn_reservar.on('click', function() {
       	registrarReserva();
   });
}

function llenarHorariosDisponibles() {

    $.ajax({
    	    type:"GET",
    	    cache: false,
    	    contentType : "application/json",
    	    accept: 'text/plain',
    	    url : '/horariosdisponibles/' + sel_fecha.val(),
    	    data : null,
    	    dataType: 'text',
    	    success:function(result,textStatus,xhr){
    	    
    	    	var resultado = JSON.parse(result);
    	    	
    	    	if(xhr.status == HttpStatus.OK){

    	    		var tam = resultado.length;

    	    		sel_hora.find('option').not(':first').remove();
    	    		
    	    		for(var i = 0; i < tam; i++){
    		    		sel_hora.append($('<option />').val(resultado[i].codigoCitaDisponible).html(resultado[i].horaReserva));
    	    		}

    	        } else if(xhr.status == HttpStatus.Accepted){

    	        	console.log("llenarHorariosDisponibles, Accepted....");
    	        }
    	    },
    	    error: function (xhr, error, code){

    	    	console.log("llenarHorariosDisponibles, error...." + xhr.status);
    	    }
    });
}


function registrarReserva() {

    var reserva = {}
    reserva["nombres"] = txt_nombres.val();
    reserva["correo"] = txt_correo.val();
    reserva["documento"] = txt_documento.val();
    reserva["codigoCitaDisponible"] = sel_hora.val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        accept: 'text/plain',
        url: '/registrarreserva',
        data: JSON.stringify(reserva),
        dataType: 'json',
        beforeSend: function (xhr) {
            //loadding(true);
        },
        error: function (xhr, status, error) {
            //loadding(false);

            alert('ERROR');

            if (xhr.status == HttpStatus.InternalServerError) {

                //mostrarAlertaError(mensajeGenericoError);
            }

        },
        success: function (result, textStatus, xhr) {

            //loadding(false);

            if (xhr.status == HttpStatus.OK) {

                sec_breadcumb.addClass("d-none");
                sec_body.addClass("d-none");

                h5_codigo_reserva.html('CODIGO DE RESERVA : ' + result.codigoCita);

                sec_message.removeClass("d-none");

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
        }
    });

}