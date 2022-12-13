var datatable_venta;
var table;
var modal_venta;
var hid_codigo_venta;
var txt_titulo;
var txt_sku;
var txt_descripcion_corta;
var txt_descripcion_larga;
var sel_categoria;
var txt_precio;
var txt_stock;
var txt_observaciones;
var btn_guardar;
var btn_agregar_venta;
var file;
var img_venta;
var h5_titulo;
var form_registro_venta_validation;
var fila;

$(document).ready(function(){
    inicializarVariables();
    inicializarComponentes();
    inicializarEventos();
});

function inicializarVariables() {

    datatable_venta = $("#datatable_venta");
    modal_venta = $("#modal_venta");
    hid_codigo_venta = $("#hid_codigo_venta");
    txt_titulo = $("#txt_titulo");
    txt_sku = $("#txt_sku");
    txt_descripcion_corta = $("#txt_descripcion_corta");
    txt_descripcion_larga = $("#txt_descripcion_larga");
    sel_categoria = $("#sel_categoria");
    txt_precio = $("#txt_precio");
    txt_stock = $("#txt_stock");
    txt_observaciones = $("#txt_observaciones");
    btn_guardar = $("#btn_guardar");
    btn_agregar_venta = $("#btn_agregar_venta");
    file = $("#file");
    img_venta = $("#img_venta");
    h5_titulo = $("#h5_titulo");

}

function inicializarComponentes(){

    table = datatable_venta.DataTable({
    	"searching" : false,
    	"lengthChange" : false,
    	"paging" : false,
    	"info" : false,
        "language"  : {
           "url": "/language/Spanish.json"
        }
    });

    $('#datatable_venta tbody').on( 'click','.btn-delete', function (){
     	mostrarConfirmacion("¿Está seguro de eliminar el producto?", "No se podrá revertir el cambio.", eliminarProducto, this);
     });

     agregarFilaTabla(null);

     validacionFormularioRegistroArticulo();
}

function inicializarEventos(){

   btn_guardar.on('click', function() {

        form_registro_venta_validation.validate().then(function(status) {
            if(status === 'Valid') {
                guardarArticulo();
            }
        });

   });

   btn_agregar_venta.on('click', function() {
       	agregarArticulo();
   });

   file.on('change', function() {
       previsualizar(this);
   });

}

function eliminarProductosVacios() {

   $(".txtProducto").each(function () {
       var tr = $(this).closest('tr');
       var comboProd = $('#row-' + $(this).attr('data-id') + '-item').select2('data')[0];
       if ($(this).attr('data-id') != '0') {
           if (comboProd === undefined) {
               table.row(tr).remove().draw();
           }
       }
   });

}

function validacionFormularioRegistroArticulo() {

    form_registro_venta_validation = FormValidation.formValidation(document.getElementById('form_venta'),
            {
                fields: {
                    txt_titulo: {
                        validators: {
                            notEmpty: {
                                message: 'El título es obligatorio.',
                            },
                            stringLength: {
                                max: 100,
                                message: 'El título no puede sobrepasar 100 caracteres.',
                            },
                        },
                    },
                    txt_sku: {
                        validators: {
                            notEmpty: {
                                message: 'El código SKU es obligatorio.',
                            },
                            stringLength: {
                                max: 50,
                                message: 'El código SKU no puede sobrepasar 50 caracteres.',
                            },
                        },
                    },
                    txt_descripcion_corta: {
                        validators: {
                            notEmpty: {
                                message: 'La descripción corta es obligatoria.',
                            },
                            stringLength: {
                                max: 100,
                                message: 'La descripción corta no puede sobrepasar 100 caracteres.',
                            },
                        },
                    },
                    txt_descripcion_larga: {
                        validators: {
                            notEmpty: {
                                message: 'La descripción larga es obligatoria.',
                            },
                            stringLength: {
                                max: 250,
                                message: 'La descripción larga no puede sobrepasar 250 caracteres.',
                            },
                        },
                    },
                    sel_categoria: {
                        validators: {
                            notEmpty: {
                                message: 'La categoría es obligatoria.',
                            }
                        },
                    },
                    txt_precio: {
                        validators: {
                            notEmpty: {
                                message: 'El precio es obligatorio.',
                            },
                            stringLength: {
                                max: 13,
                                message: 'El precio no puede sobrepasar 13 caracteres.',
                            },
                            regexp: {
                                regexp: /^\d+(\.\d{1,2})?$/,
                                message: 'El precio debe tener formato de montos, con máximo 2 decimales.',
                            },
                        },
                    },
                    txt_stock: {
                        validators: {
                            notEmpty: {
                                message: 'El stock es obligatorio.',
                            },
                            integer: {
                                message: 'Debe ingresar un valor numérico.',
                            },
                            stringLength: {
                                max: 5,
                                message: 'El stock no puede sobrepasar 5 caracteres.',
                            },
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

function agregarFilaTabla(item){

    var filaTemp = 0;
    $(".txtProducto").each(function () {

        var tr = $(this).closest('tr');

        if ($(this).attr('data-id') != '0') {
            var comboProd = $('#row-' + $(this).attr('data-id') + '-item').select2('data')[0];

            if (comboProd != undefined) {
                filaTemp++;
            }
        }
    });

    fila = filaTemp;
    fila++;

    table.row.add([
                    '<div class="input-group">' +
                        '<select id="row-' + fila + '-item" data-id="' + fila + '" name="' + fila + '" class="form-control select2 txtProducto"></select>' +
                    '</div>',
                    '<input type="text" id="row-' + fila + '-cantidad" name="' + fila + '" value="" class="form-control text-right txtCantidad" disabled>',
                    '<input type="text" id="row-' + fila + '-UDM" name="' + fila + '" value="" class="form-control" disabled>',
                    '<input type="text" id="row-' + fila + '-PU" name="' + fila + '" value="" class="form-control text-right txtPrecioUnitario" disabled>',
                    '<input type="text" id="row-' + fila + '-PT" name="' + fila + '" value="" class="form-control text-right txtTotal" disabled>'
    ]).draw(true);

    $('#row-' + self.fila + '-item').select2({
        placeholder: 'Escriba el nombre del Producto',
        minimumInputLength: 3,
        width: '100%',
        language: "es",
        allowClear: true,
        ajax: {
            url: '/listararticulosportitulo',
            type: 'GET',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            delay: 250,
            data: function (params) {
                if (!params.term) params.term = '';
                var query = {
                    titulo: params.term
                }
                return query;
            },
            processResults: function (data, page) {

                var parsed = [];
                try {
                    parsed = $.map(data, function (item) {
                        return {
                            id: item.codigoArticulo,
                            text: item.titulo,
                            und: 'NIU',
                            precio: parseFloat(item.precioUnitario).toFixed(2),
                            stock: item.stock
                        };
                    });
                    //console.log(parsed);
                } catch (e) {
                    alert('Error en la búsqueda');
                }

                return {
                    results: parsed
                };
            }
        }
    }).on('select2:select', function (e) {
          e.preventDefault();
//        var data = e.params.data;
//        console.log(data);
//        var filaSel = $(e.currentTarget).attr('name');
//
//        $('#row-' + filaSel + '-UDM').val(data.und);
//        $('#row-' + filaSel + '-cantidad').removeAttr('disabled', false).val('1.00');
//        $('#row-' + filaSel + '-PU').removeAttr('disabled', false).val(data.precio);
//        $('#row-' + filaSel + '-PU').attr('title', 'PC: ' + self.$cboMoneda.val() + ' ' + data.compra);
//        if (data.stock == 0 && data.tipo == 1) {
//            alert('Esta agregando un producto que no contiene stock, de todas formas lo puede vender si es que se tratara de un error.\nLuego de esto es recomendable ajustar el Stock. ')
//        }
//        self.funciones.calcularComprobante(self);
          eliminarProductosVacios();
          agregarFilaTabla(null);
    }).on('select2:unselecting', function (e) {
          e.preventDefault();
//        self.funciones.calcularComprobante(self);
          eliminarProductosVacios();
          agregarFilaTabla(null);
    });

}

function previsualizar(elemento) {
    
    var input = elemento;
    var url = $(elemento).val();
    var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();

    if (input.files && input.files[0] && (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) {

        var reader = new FileReader();
        reader.onload = function (e) {
           img_venta.attr('src', e.target.result);
        }
       reader.readAsDataURL(input.files[0]);
    }
}

function agregarArticulo(){

    form_registro_venta_validation.resetForm(false);

    hid_codigo_venta.val(CADENA_VACIA);
    h5_titulo.html("Nuevo Producto");
    txt_titulo.val(CADENA_VACIA);
    txt_sku.val(CADENA_VACIA);
    txt_descripcion_corta.val(CADENA_VACIA);
    txt_descripcion_larga.val(CADENA_VACIA);
    sel_categoria.prop("selectedIndex", 0);
    txt_precio.val(CADENA_VACIA);
    txt_stock.val(CADENA_VACIA);
    txt_observaciones.val(CADENA_VACIA);
    img_venta.attr('src', "img/product/sinimagen.jpg");
    file.val(CADENA_VACIA);
    modal_venta.modal("show");
}

function cargarArticulo(codigoArticulo){

	$.ajax({
        type:"GET",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/buscarventa?codigoArticulo=' + codigoArticulo,
        dataType: 'json',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                mostrarMensajeAdvertencia("No se pudo cargar el producto", xhr.responseJSON.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr){
            loadding(false);
			if(xhr.status == HttpStatus.OK){
                cargarModalArticulo(result);
            }

        }
    });
}

function eliminarArticulo(codigoArticulo){

	$.ajax({
        type:"DELETE",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/eliminarventa?codigoArticulo=' + codigoArticulo,
        dataType: 'text',
        beforeSend: function(xhr) {
        	loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                var data = JSON.parse(xhr.responseText);
                mostrarMensajeAdvertencia("No se pudo eliminar el Producto", data.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success:function(result,textStatus,xhr){

			if(xhr.status == HttpStatus.OK){

                loadding(false);

			    mostrarMensajeOK("Buen Trabajo!", "Producto Eliminado Satisfactoriamente");

			    table.clear();
                table.ajax.reload(null, true);

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
        }
    });
}

function cargarModalArticulo(venta) {

    form_registro_venta_validation.resetForm(false);

    hid_codigo_venta.val(venta.codigoArticulo);
    h5_titulo.html("Producto " + venta.codigoArticulo);
    txt_titulo.val(venta.titulo);
    txt_sku.val(venta.codigoEstandar);
    txt_descripcion_corta.val(venta.descripcionCorta);
    txt_descripcion_larga.val(venta.descripcionLarga);
    sel_categoria.val(venta.tipoArticulo.codigoTipoArticulo);
    txt_precio.val(venta.precioUnitario);
    txt_stock.val(venta.stock);
    txt_observaciones.val(venta.observaciones);
    if(venta.imagenString === null) {
        img_venta.attr('src', "img/product/sinimagen.jpg");
    } else {
        img_venta.attr('src', venta.imagenString);
    }
    file.val(CADENA_VACIA);
    modal_venta.modal("show");
}

function guardarArticulo() {

    var venta = {}
    venta["codigoArticulo"] = hid_codigo_venta.val();
    venta["codigoEstandar"] = txt_sku.val();
    venta["titulo"] = txt_titulo.val();
    venta["descripcionCorta"] = txt_descripcion_corta.val();
    venta["descripcionLarga"] = txt_descripcion_larga.val();
    venta["codigoTipoArticulo"] = sel_categoria.val();
    venta["observaciones"] = txt_observaciones.val();
    venta["precioUnitario"] = txt_precio.val();
    venta["stock"] = txt_stock.val();
    venta["imagen"] = btoa(img_venta.attr('src'));

    var ventaJson = JSON.stringify(venta);

    var formData = new FormData();

    formData.append('registro', new Blob([ventaJson], {
    	type: "application/json"
    }));

    $.ajax({
    	type:"POST",
    	contentType: false,
    	processData: false,
    	url : '/guardarventa',
    	data: formData,
    	beforeSend: function(xhr) {
    		loadding(true);
    	},
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                var data = JSON.parse(xhr.responseText);
                mostrarMensajeAdvertencia("No se pudo guardar el Producto", data.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {

                mostrarMensajeError(ERROR_GENERICO);
            }

        },
    	success:function(resultado,textStatus,xhr){
            loadding(false);

    		if(xhr.status == HttpStatus.OK) {

    			mostrarMensajeOK("Buen Trabajo!", "Producto Guardado Satisfactoriamente");

    			table.clear();
                table.ajax.reload(null, true);

                modal_venta.modal("hide");

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
    		}

    	}
    });

}