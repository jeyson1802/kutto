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
var form_registro_cita_validation;

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
                    "data": "observaciones",
                    "visible": false
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
                                         "<button title='Modificar' class='btn-delete btn btn-danger btn-rounded waves-effect waves-light'>" +
                                             "Eliminar" +
                                         "</button>" +
                                    "</div>"
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

    $('#datatable_citas tbody').on( 'click','.btn-delete', function (){
        var data = table.row( $(this).closest('tr')).data();
        mostrarConfirmacion("¿Está seguro de eliminar la Cita?", "No se podrá revertir el cambio.", eliminarCita, data.codigoCita);
    });

    validacionFormularioRegistroCita();
}

function inicializarEventos(){

    btn_guardar.on('click', function() {

        form_registro_cita_validation.validate().then(function(status) {
            if(status === 'Valid') {
               modificarCita();
            }
        });
   });

}

function validacionFormularioRegistroCita() {

    form_registro_cita_validation = FormValidation.formValidation(document.getElementById('form_cita'),
            {
                fields: {
                    sel_estado: {
                        validators: {
                            notEmpty: {
                                message: 'La categoría es obligatoria.',
                            }
                        },
                    },
                    txt_observaciones: {
                        validators: {
                            stringLength: {
                                max: 250,
                                message: 'El stock no puede sobrepasar 250 caracteres.',
                            },
                        },
                    },
                },
                plugins: {
                    trigger: new FormValidation.plugins.Trigger(),
                    bootstrap5: new FormValidation.plugins.Bootstrap5({
                         rowSelector: function (field, ele) {
                             // field is the field name
                             // ele is the field element
                             switch (field) {
                                 case 'sel_estado':
                                     return '.col-md-4';
                                 case 'txt_observaciones':
                                     return '.col-md-12';
                                 default:
                                     return '.row';
                             }
                         },
                     }),
                    submitButton: new FormValidation.plugins.SubmitButton(),
                    icon: new FormValidation.plugins.Icon({
                        valid: 'fa fa-check',
                        invalid: 'fa fa-times',
                        validating: 'fa fa-refresh',
                    }),
                },
            }
        );
}

function cargarCita(codigoCita){

	$.ajax({
        type:"GET",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/buscarreserva/' + codigoCita,
        dataType: 'json',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                mostrarMensajeAdvertencia("No se pudo cargar la cita", xhr.responseJSON.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr){
            loadding(false);
			if(xhr.status == HttpStatus.OK){
                cargarModalCita(result);
            }

        }
    });
}

function eliminarCita(codigoCita){

	$.ajax({
        type:"DELETE",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/eliminarcita?codigoCita=' + codigoCita,
        dataType: 'text',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                var data = JSON.parse(xhr.responseText);
                mostrarMensajeAdvertencia("No se pudo eliminar la Cita", data.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr){

            loadding(false);

			if(xhr.status == HttpStatus.OK){

			    mostrarMensajeOK("Buen Trabajo!", "Cita Eliminada Satisfactoriamente");

			    table.clear();
                table.ajax.reload(null, true);

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
        }
    });
}

function cargarModalCita(cita){

    form_registro_cita_validation.resetForm(false);
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
            loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                var data = JSON.parse(xhr.responseText);
                mostrarMensajeAdvertencia("No se pudo eliminar la Cita", data.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success: function (result, textStatus, xhr) {

            loadding(false);

            if (xhr.status == HttpStatus.OK) {

                mostrarMensajeOK("Buen Trabajo!", "Cita Guardada Satisfactoriamente");

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
