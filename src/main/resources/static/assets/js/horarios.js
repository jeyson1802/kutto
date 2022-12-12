var datatable_horario;
var table;
var modal_horario;
var hid_codigo_horario;
var txt_fecha;
var txt_hora;
var sel_disponibilidad;
var btn_guardar;
var btn_agregar_horario;
var h5_titulo;
var form_registro_horario_validation;

$(document).ready(function(){
    inicializarVariables();
    inicializarComponentes();
    inicializarEventos();
});

function inicializarVariables() {

    datatable_horario = $("#datatable_horario");
    modal_horario = $("#modal_horario");
    hid_codigo_horario = $("#hid_codigo_horario");
    txt_fecha = $("#txt_fecha");
    txt_hora = $("#txt_hora");
    sel_disponibilidad = $("#sel_disponibilidad");
    btn_guardar = $("#btn_guardar");
    btn_agregar_horario = $("#btn_agregar_horario");
    h5_titulo = $("#h5_titulo");

}

function inicializarComponentes(){

    table = datatable_horario.DataTable({

            "ajax": {
                url: '/listarhorario',
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
                    "width": "20%",
                    "targets": [0],
                    "data": "codigoCitaDisponible"
                },
                {
                    "width": "20%",
                    "targets": [1],
                    "data": null,
                    "render":
                              function (data, type, row ) {
                                  return  $.date(data.fechaReserva);
                              }
                },
                {
                    "width": "20%",
                    "targets": [2],
                    "data": "horaReserva"
                },
                {
                    "width": "20%",
                    "targets": [3],
                    "data": null,
                    "render":
                              function (data, type, row ) {
                                  return  mostrarDisponibilidad(data.disponible);
                              }
                },
                {
                    "width": "20%",
                    "targets": [4],
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

    $('#datatable_horario tbody').on( 'click','.btn-edit', function (){
        var data = table.row( $(this).closest('tr')).data();
    	cargarCitaDisponible(data.codigoCitaDisponible);
    });

    $('#datatable_horario tbody').on( 'click','.btn-delete', function (){
         var data = table.row( $(this).closest('tr')).data();
         mostrarConfirmacion("¿Está seguro de eliminar el Horario?", "No se podrá revertir el cambio.", eliminarCitaDisponible, data.codigoCitaDisponible);
     });

     validacionFormularioRegistroHorario();
}

function inicializarEventos(){

   btn_guardar.on('click', function() {
        form_registro_horario_validation.validate().then(function(status) {
            if(status === 'Valid') {
               guardarCitaDisponible();
            }
        });
   });

   btn_agregar_horario.on('click', function() {
       	agregarCitaDisponible();
   });

}

function validacionFormularioRegistroHorario() {

    form_registro_horario_validation = FormValidation.formValidation(document.getElementById('form_horario'),
            {
                fields: {
                    txt_fecha: {
                        validators: {
                            notEmpty: {
                                message: 'La fecha es obligatoria.',
                            }
                        },
                    },
                    txt_hora: {
                        validators: {
                            notEmpty: {
                                message: 'La hora es obligatoria.',
                            }
                        },
                    },
                    sel_disponibilidad: {
                        validators: {
                            notEmpty: {
                                message: 'La disponibilidad es obligatoria.',
                            }
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
                                 case 'txt_fecha':
                                 case 'txt_hora':
                                 case 'sel_disponibilidad':
                                     return '.col-md-4';
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

function agregarCitaDisponible(){

    form_registro_horario_validation.resetForm(false);
    hid_codigo_horario.val(CADENA_VACIA);
    h5_titulo.html("Nuevo Horario");
    txt_fecha.val(CADENA_VACIA);
    txt_hora.val(CADENA_VACIA);
    sel_disponibilidad.prop("selectedIndex", 0);
    modal_horario.modal("show");
}

function cargarCitaDisponible(codigoCitaDisponible){

	$.ajax({
        type:"GET",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/buscarhorario?codigoCitaDisponible=' + codigoCitaDisponible,
        dataType: 'json',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                mostrarMensajeAdvertencia("No se pudo cargar el Horario", xhr.responseJSON.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr) {

            loadding(false);

			if(xhr.status == HttpStatus.OK){
                cargarModalCitaDisponible(result);
            }
        }
    });
}

function eliminarCitaDisponible(codigoCitaDisponible){

	$.ajax({
        type:"DELETE",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/eliminarhorario?codigoCitaDisponible=' + codigoCitaDisponible,
        dataType: 'text',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                var data = JSON.parse(xhr.responseText);
                mostrarMensajeAdvertencia("No se pudo eliminar el Horario", data.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr){

            loadding(false);

			if(xhr.status == HttpStatus.OK){

			    mostrarMensajeOK("Buen Trabajo!", "Horario Eliminado Satisfactoriamente");

			    table.clear();
                table.ajax.reload(null, true);

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
        }
    });
}

function cargarModalCitaDisponible(horario) {

    form_registro_horario_validation.resetForm(false);
    hid_codigo_horario.val(horario.codigoCitaDisponible);
    h5_titulo.html("Horario " + horario.codigoCitaDisponible);
    txt_fecha.val(horario.fechaReserva);
    txt_hora.val(horario.horaReserva);
    sel_disponibilidad.val(horario.disponible);
    modal_horario.modal("show");
}

function guardarCitaDisponible() {

    var horario = {}
    horario["codigoCitaDisponible"] = hid_codigo_horario.val();
    horario["fechaReserva"] = txt_fecha.val();
    horario["horaReserva"] = txt_hora.val();
    horario["disponible"] = sel_disponibilidad.val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        accept: 'text/plain',
        url: '/guardarhorario',
        data: JSON.stringify(horario),
        dataType: 'json',
        beforeSend: function (xhr) {
            loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                mostrarMensajeAdvertencia("No se pudo guardar el Horario", xhr.responseJSON.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {
                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success: function (result, textStatus, xhr) {

            loadding(false);

            if (xhr.status == HttpStatus.OK) {

                mostrarMensajeOK("Buen Trabajo!", "Horario Guardado Satisfactoriamente");

                table.clear();
                table.ajax.reload(null, true);

                modal_horario.modal("hide");

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
        }
    });

}

function mostrarDisponibilidad(codigo) {

    if(codigo == 1) {
        return "DISPONIBLE"
    } else {
        return "OCUPADO"
    }
}