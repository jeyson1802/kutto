var datatable_tipo_articulo;
var table;
var modal_tipo_articulo;
var hid_codigo_tipo_articulo;
var txt_descripcion;
var btn_guardar;
var btn_agregar_tipo_articulo;
var h5_titulo;
var form_registro_tipo_articulo_validation;

$(document).ready(function(){
    inicializarVariables();
    inicializarComponentes();
    inicializarEventos();
});

function inicializarVariables() {

    datatable_tipo_articulo = $("#datatable_tipo_articulo");
    modal_tipo_articulo = $("#modal_tipo_articulo");
    hid_codigo_tipo_articulo = $("#hid_codigo_tipo_articulo");
    txt_descripcion = $("#txt_descripcion");
    btn_guardar = $("#btn_guardar");
    btn_agregar_tipo_articulo = $("#btn_agregar_tipo_articulo");
    h5_titulo = $("#h5_titulo");

}

function inicializarComponentes(){

    table = datatable_tipo_articulo.DataTable({

            "ajax": {
                url: '/listartipoarticulo',
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
                    "width": "30%",
                    "targets": [0],
                    "data": "codigoTipoArticulo"
                },
                {
                    "width": "50%",
                    "targets": [1],
                    "data": "descripcion"
                },
                {
                    "width": "20%",
                    "targets": [2],
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

    $('#datatable_tipo_articulo tbody').on( 'click','.btn-edit', function (){
        var data = table.row( $(this).closest('tr')).data();
    	cargarTipoArticulo(data.codigoTipoArticulo);
    });

    $('#datatable_tipo_articulo tbody').on( 'click','.btn-delete', function (){
         var data = table.row( $(this).closest('tr')).data();
         mostrarConfirmacion("¿Está seguro de eliminar la categoría?", "No se podrá revertir el cambio.", eliminarTipoArticulo, data.codigoTipoArticulo);
     });

     validacionFormularioRegistroTipoArticulo();
}

function inicializarEventos(){

   btn_guardar.on('click', function() {
        form_registro_tipo_articulo_validation.validate().then(function(status) {
            if(status === 'Valid') {
               guardarTipoArticulo();
            }
        });
   });

   btn_agregar_tipo_articulo.on('click', function() {
       	agregarTipoArticulo();
   });

}

function validacionFormularioRegistroTipoArticulo() {

    form_registro_tipo_articulo_validation = FormValidation.formValidation(document.getElementById('form_tipo_articulo'),
            {
                fields: {
                    txt_descripcion: {
                        validators: {
                            notEmpty: {
                                message: 'La descripción es obligatoria.',
                            },
                            stringLength: {
                                max: 150,
                                message: 'La descripción no puede sobrepasar 150 caracteres.',
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
                                 case 'txt_descripcion':
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

function agregarTipoArticulo(){

    form_registro_tipo_articulo_validation.resetForm(false);
    hid_codigo_tipo_articulo.val(CADENA_VACIA);
    h5_titulo.html("Nueva Categoría");
    txt_descripcion.val(CADENA_VACIA);
    modal_tipo_articulo.modal("show");
}

function cargarTipoArticulo(codigoTipoArticulo){

	$.ajax({
        type:"GET",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/buscartipoarticulo?codigoTipoArticulo=' + codigoTipoArticulo,
        dataType: 'json',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                mostrarMensajeAdvertencia("No se pudo cargar la categoría", xhr.responseJSON.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr){

            loadding(false);

			if(xhr.status == HttpStatus.OK){
                cargarModalTipoArticulo(result);
            }
        }
    });
}

function eliminarTipoArticulo(codigoTipoArticulo){

	$.ajax({
        type:"DELETE",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/eliminartipoarticulo?codigoTipoArticulo=' + codigoTipoArticulo,
        dataType: 'text',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                var data = JSON.parse(xhr.responseText);
                mostrarMensajeAdvertencia("No se pudo eliminar la Categoría", data.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr){

            loadding(false);

			if(xhr.status == HttpStatus.OK){

			    mostrarMensajeOK("Buen Trabajo!", "Categoría Eliminada Satisfactoriamente");

			    table.clear();
                table.ajax.reload(null, true);

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
        }
    });
}

function cargarModalTipoArticulo(tipo_articulo) {

    form_registro_tipo_articulo_validation.resetForm(false);
    hid_codigo_tipo_articulo.val(tipo_articulo.codigoTipoArticulo);
    h5_titulo.html("Categoría " + tipo_articulo.codigoTipoArticulo);
    txt_descripcion.val(tipo_articulo.descripcion);
    modal_tipo_articulo.modal("show");
}

function guardarTipoArticulo() {

    var tipo_articulo = {}
    tipo_articulo["codigoTipoArticulo"] = hid_codigo_tipo_articulo.val();
    tipo_articulo["descripcion"] = txt_descripcion.val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        accept: 'text/plain',
        url: '/guardartipoarticulo',
        data: JSON.stringify(tipo_articulo),
        dataType: 'json',
        beforeSend: function (xhr) {
            loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                mostrarMensajeAdvertencia("No se pudo guardar la Categoría", xhr.responseJSON.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {
                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success: function (result, textStatus, xhr) {

            loadding(false);

            if (xhr.status == HttpStatus.OK) {

                mostrarMensajeOK("Buen Trabajo!", "Categoría Guardada Satisfactoriamente");

                table.clear();
                table.ajax.reload(null, true);

                modal_tipo_articulo.modal("hide");

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
        }
    });

}