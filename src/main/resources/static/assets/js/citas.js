var datatable_citas;
var table;
var modal_cita;
var txt_codigo_cita;
var txt_fecha;
var txt_hora;
var txt_nombres;
var txt_documento;
var txt_correo;
var sel_estado;
var txt_observaciones;
var btn_guardar;

$(document).ready(function(){
    inicializarVariables();
    inicializarComponentes();
    inicializarEventos();
});

function inicializarVariables() {

    datatable_citas = $("#datatable_citas");
    modal_cita = $("#modal_cita");
    txt_codigo_cita = $("#txt_codigo_cita");
    txt_fecha = $("#txt_fecha");
    txt_hora = $("#txt_hora");
    txt_nombres = $("#txt_nombres");
    txt_documento = $("#txt_documento");
    txt_correo = $("#txt_correo");
    sel_estado = $("#sel_estado");
    txt_observaciones = $("#txt_observaciones");
    btn_guardar = $("#btn_guardar");

}

function inicializarComponentes(){

    table = datatable_citas.DataTable({

            "ajax": {
                url: '/listarcitas',
                dataSrc: function (json) {
                	return json;
                },
                error: function (xhr, error, code){
                    alert('Hubo un error en el servidor.!!');
                }
            },
    		"order"         : [[0, 'desc']],
            "lengthMenu"	: [[10, 20, 30, -1], [10, 20, 30, "Todos"]],
            "autoWidth"		: false,
            "columnDefs"    : [
                {
                    "width": "5px",
                    "targets": [0],
                    "data": "codigoCita"
                },
                {
                    "width": "10px",
                    "targets": [1],
                    "data": "nombres"
                },
                {
                    "width": "10px",
                    "targets": [2],
                    "data": "documento"
                },
                {
                    "width": "75px",
                    "targets": [3],
                    "data": "correo"
                },
    			{
                    "width": "75px",
                    "targets": [4],
                    "data": null,
                    "render":
                              function (data, type, row ) {
                                  return  $.date(data.fecha);
                              }
                },
    			{
                    "width": "50px",
                    "targets": [5],
                    "data": "horario"
                },
    			{
                    "width": "50px",
                    "targets": [6],
                    "data": "estadoCita.descripcion"
                },
                {
                    "width": "10px",
                    "targets": [7],
                    "data": "observaciones"
                },
                {
                        "width": "10px",
                        "targets": [8],
                        "className": "dt-center",
                        "data": null,
                        "orderable": false,
    					"render":
                        function (data, type, row ) {
                        	return  "<div style='display:flex;justify-content:space-around;'>" +
                                    		    "<button title='Modificar' class='btn-edit btn btn-primary btn-rounded waves-effect waves-light'>" +
                                                     "Modificar" +
                                                 "</button>" +
                                    		"</div>";
                        }
                  }
             ],
             "language"  : {
                "url": "/language/Spanish.json"
             },
             "drawCallback": function () {
                  $('.dataTables_paginate > .pagination').addClass('pagination-rounded');
              }
    });

    $('#datatable_citas tbody').on( 'click','.btn-edit', function (){
        var data = table.row( $(this).closest('tr')).data();
    	cargarCita(data.codigoCita);
    });
}

function inicializarEventos(){

    btn_guardar.on('click', function() {
       	modificarCita();
   });

}

function cargarCita(codigoCita){

	$.ajax({
        type:"GET",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/buscarreserva/' + codigoCita,
        data : null,
        dataType: 'text',
        beforeSend: function(xhr) {
        	//loadding(true);
        },
        success:function(result,textStatus,xhr){

			if(xhr.status == HttpStatus.OK){
                var data = JSON.parse(result);
                cargarModalCita(data);
            }
			//loadding(false);
        }
    });
}

function cargarModalCita(cita){

    txt_codigo_cita.val(cita.codigoCita);
    txt_fecha.val($.date(cita.fecha));
    txt_hora.val(cita.horario);
    txt_nombres.val(cita.nombres);
    txt_documento.val(cita.documento);
    txt_correo.val(cita.correo);
    sel_estado.val(cita.estadoCita.codigoEstadoCita);
    txt_observaciones.val(cita.observaciones);
    modal_cita.modal("show");
}

function modificarCita() {

    var cita = {}
    cita["codigoCita"] = txt_codigo_cita.val();
    cita["obervaciones"] = txt_observaciones.val();
    cita["codigoEstadoCita"] = sel_estado.val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        accept: 'text/plain',
        url: '/modificarcita',
        data: JSON.stringify(cita),
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

                alert('OK');

                table.clear();
                table.ajax.reload(null, true);

                modal_cita.modal("hide");

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
        }
    });

}
