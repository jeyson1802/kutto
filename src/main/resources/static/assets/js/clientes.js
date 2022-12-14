var datatable_clientes;
var table;
var modal_cliente;
var hid_codigo_cliente;
var sel_tipo_persona;
var sel_tipo_documento;
var txt_documento;
var txt_nombres;
var txt_correo;
var txt_direccion;
var btn_guardar;
var btn_agregar_cliente;
var h5_titulo;
var form_registro_cliente_validation;

$(document).ready(function(){
    inicializarVariables();
    inicializarComponentes();
    inicializarEventos();
});

function inicializarVariables() {

    datatable_clientes = $("#datatable_clientes");
    modal_cliente = $("#modal_cliente");
    hid_codigo_cliente = $("#hid_codigo_cliente");
    sel_tipo_persona = $("#sel_tipo_persona");
    sel_tipo_documento = $("#sel_tipo_documento");
    txt_documento = $("#txt_documento");
    txt_nombres = $("#txt_nombres");
    txt_correo = $("#txt_correo");
    txt_direccion = $("#txt_direccion");
    btn_guardar = $("#btn_guardar");
    btn_agregar_cliente = $("#btn_agregar_cliente");
    h5_titulo = $("#h5_titulo");

}

function inicializarComponentes(){

    table = datatable_clientes.DataTable({

            "ajax": {
                url: '/listarclientes',
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
                    "width": "20px",
                    "targets": [0],
                    "data": "codigoCliente"
                },
                {
                    "width": "10px",
                    "targets": [1],
                    "data": null,
                    "render":
                              function (data, type, row ) {
                                  return  mostrarTipoPersona(data.codigoTipoPersona);
                              }
                },
                {
                    "width": "10px",
                    "targets": [2],
                    "data": "tipoDocumento.descripcion"
                },
                {
                    "width": "10px",
                    "targets": [3],
                    "data": "documento"
                },
                {
                    "width": "100px",
                    "targets": [4],
                    "data": "nombres"
                },
    			{
                    "width": "50px",
                    "targets": [5],
                    "data": "correo"
                },
    			{
                    "width": "50px",
                    "targets": [6],
                    "data": "direccion"
                },
                {
                    "width": "100px",
                    "targets": [7],
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

    $('#datatable_clientes tbody').on( 'click','.btn-edit', function (){
        var data = table.row( $(this).closest('tr')).data();
        if(data === undefined) {
            data = table.row( $(this).closest('tr').prev('tr')).data();
        }
    	cargarCliente(data.codigoCliente);
    });

    $('#datatable_clientes tbody').on( 'click','.btn-delete', function (){
        var data = table.row( $(this).closest('tr')).data();
        if(data === undefined) {
            data = table.row( $(this).closest('tr').prev('tr')).data();
        }
     	mostrarConfirmacion("¿Está seguro de eliminar el cliente?", "No se podrá revertir el cambio.", eliminarCliente, data.codigoCliente);
     });

     validacionFormularioRegistroCliente();

     cargarTipoDocumentos(null);
}

function inicializarEventos(){

   btn_guardar.on('click', function() {

        form_registro_cliente_validation.validate().then(function(status) {
            if(status === 'Valid') {
                guardarCliente();
            }
        });

   });

   btn_agregar_cliente.on('click', function() {
       	agregarCliente();
   });

   sel_tipo_persona.on('change', function() {
         cargarTipoDocumentos(null);
    });

}

function cargarTipoDocumentos(codigoTipoDocumento) {

    $.ajax({
        type:"GET",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/listartipodocumentosportipopersona?tipopersona=' + sel_tipo_persona.val(),
        dataType: 'json',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                mostrarMensajeAdvertencia("No se pudo cargar los tipos de documentos", xhr.responseJSON.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr) {

            loadding(false);

    		if(xhr.status == HttpStatus.OK){

                sel_tipo_documento.find('option').remove();

                var tam = result.length;
                for(var i = 0; i < tam; i++){
                	sel_tipo_documento.append($('<option />').val(result[i].codigoTipoDocumento).html(result[i].descripcion));
                }

                if(codigoTipoDocumento !== null) {
                    sel_tipo_documento.val(codigoTipoDocumento);
                }

            }
        }
    });
}

function validacionFormularioRegistroCliente() {

    form_registro_cliente_validation = FormValidation.formValidation(document.getElementById('form_cliente'),
            {
                fields: {
                    sel_tipo_persona: {
                        validators: {
                            notEmpty: {
                                message: 'El tipo de persona es obligatorio.',
                            },
                        },
                    },
                    sel_tipo_documento: {
                        validators: {
                            notEmpty: {
                                message: 'El tipo de documento es obligatorio.',
                            },
                        },
                    },
                    txt_documento: {
                        validators: {
                            notEmpty: {
                                message: 'El número de documento es obligatorio.',
                            },
                            stringLength: {
                                max: 20,
                                message: 'El número de documento no puede sobrepasar 20 caracteres.',
                            },
                        },
                    },
                    txt_nombres: {
                        validators: {
                            notEmpty: {
                                message: 'El nombre o razón social es obligatorio.',
                            },
                            stringLength: {
                                max: 250,
                                message: 'El nombre o razón social no puede sobrepasar 250 caracteres.',
                            },
                        },
                    },
                    txt_correo: {
                        validators: {
                            notEmpty: {
                                message: 'El correo es obligatorio.',
                            },
                            emailAddress: {
                                message: 'Debe ingresar un correo válido.',
                            },
                            stringLength: {
                                max: 200,
                                message: 'El correo corta no puede sobrepasar 200 caracteres.',
                            },
                        },
                    },
                    txt_direccion: {
                        validators: {
                            notEmpty: {
                                message: 'La dirección es obligatoria.',
                            },
                            stringLength: {
                                max: 250,
                                message: 'La dirección no puede sobrepasar 250 caracteres.',
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
                                 case 'txt_titulo':
                                 case 'txt_sku':
                                 case 'txt_descripcion_corta':
                                     return '.col-md-4';
                                 case 'txt_descripcion_corta':
                                 case 'txt_observaciones':
                                     return '.col-md-12';
                                 case 'sel_categoria':
                                 case 'txt_precio':
                                 case 'txt_stock':
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

function agregarCliente(){

    form_registro_cliente_validation.resetForm(false);

    hid_codigo_cliente.val(CADENA_VACIA);
    h5_titulo.html("Nuevo Cliente");
    sel_tipo_persona.prop("selectedIndex", 0);
    sel_tipo_persona.change();
    txt_documento.val(CADENA_VACIA);
    txt_nombres.val(CADENA_VACIA);
    txt_correo.val(CADENA_VACIA);
    txt_direccion.val(CADENA_VACIA);
    modal_cliente.modal("show");
}

function cargarCliente(codigoCliente){

	$.ajax({
        type:"GET",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/buscarcliente?codigoCliente=' + codigoCliente,
        dataType: 'json',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                mostrarMensajeAdvertencia("No se pudo cargar el cliente", xhr.responseJSON.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr){
            loadding(false);
			if(xhr.status == HttpStatus.OK){
                cargarModalCliente(result);
            }

        }
    });
}

function eliminarCliente(codigoCliente){

	$.ajax({
        type:"DELETE",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/eliminarcliente?codigoCliente=' + codigoCliente,
        dataType: 'text',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                var data = JSON.parse(xhr.responseText);
                mostrarMensajeAdvertencia("No se pudo eliminar el Cliente", data.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr){

			if(xhr.status == HttpStatus.OK){

                loadding(false);

			    mostrarMensajeOK("Buen Trabajo!", "Cliente Eliminado Satisfactoriamente");

			    table.clear();
                table.ajax.reload(null, true);

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
        }
    });
}

function cargarModalCliente(cliente) {

    form_registro_cliente_validation.resetForm(false);

    hid_codigo_cliente.val(cliente.codigoCliente);
    h5_titulo.html("Cliente " + cliente.codigoCliente);
    sel_tipo_persona.val(cliente.codigoTipoPersona);
    cargarTipoDocumentos(cliente.tipoDocumento.codigoTipoDocumento);
    txt_documento.val(cliente.documento);
    txt_nombres.val(cliente.nombres);
    txt_correo.val(cliente.correo);
    txt_direccion.val(cliente.direccion);
    modal_cliente.modal("show");
}

function guardarCliente() {

    var cliente = {}
    cliente["codigoCliente"] = hid_codigo_cliente.val();
    cliente["codigoTipoPersona"] = sel_tipo_persona.val();
    cliente["codigoTipoDocumento"] = sel_tipo_documento.val();
    cliente["documento"] = txt_documento.val();
    cliente["nombres"] = txt_nombres.val();
    cliente["correo"] = txt_correo.val();
    cliente["direccion"] = txt_direccion.val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        accept: 'text/plain',
        url: '/guardarcliente',
        data: JSON.stringify(cliente),
        dataType: 'json',
        beforeSend: function (xhr) {
            loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                mostrarMensajeAdvertencia("No se pudo guardar el Cliente", xhr.responseJSON.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {
                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success: function (result, textStatus, xhr) {

            loadding(false);

            if (xhr.status == HttpStatus.OK) {

                mostrarMensajeOK("Buen Trabajo!", "Cliente Guardado Satisfactoriamente");

                table.clear();
                table.ajax.reload(null, true);

                modal_cliente.modal("hide");

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
        }
    });

}

function mostrarTipoPersona(codigo) {

    if(codigo == 'TP01') {
        return "Persona Jurídica"
    } else {
        return "Persona Natural"
    }
}