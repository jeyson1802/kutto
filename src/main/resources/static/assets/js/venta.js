var datatable_venta;
var table;
var sel_tipo_comprobante;
var sel_serie;
var txt_fecha_emision;
var txt_fecha_vencimiento;
var sel_cliente;
var txt_documento;
var txt_email;
var txt_direccion;
var tr_subtotal;
var tr_igv;
var txt_igv;
var btn_guardar;
var txt_glosa;
var txt_subtotal;
var txt_monto_igv;
var txt_total;
var form_registro_venta_validation;
var fila;

$(document).ready(function(){
    inicializarVariables();
    inicializarComponentes();
    inicializarEventos();
});

function inicializarVariables() {

    datatable_venta = $("#datatable_venta");
    sel_tipo_comprobante = $("#sel_tipo_comprobante");
    sel_serie = $("#sel_serie");
    txt_fecha_emision = $("#txt_fecha_emision");
    txt_fecha_vencimiento = $("#txt_fecha_vencimiento");
    sel_cliente = $("#sel_cliente");
    txt_documento = $("#txt_documento");
    txt_email = $("#txt_email");
    txt_direccion = $("#txt_direccion");
    tr_subtotal = $("#tr_subtotal");
    tr_igv = $("#tr_igv");
    txt_glosa = $("#txt_glosa");
    btn_guardar = $("#btn_guardar");
    txt_subtotal = $("#txt_subtotal");
    txt_igv = $("#txt_igv");
    txt_monto_igv = $("#txt_monto_igv");
    txt_total = $("#txt_total");

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

     validacionFormularioRegistroVenta();

     consultarCliente();

     cargarSeries();
}

function inicializarEventos(){

   btn_guardar.on('click', function() {

        form_registro_venta_validation.validate().then(function(status) {
            if(status === 'Valid') {
                if(txt_documento.val() == CADENA_VACIA) {
                    mostrarMensajeAdvertencia("No se pudo guardar el Comprobante", "Debe seleccionar el cliente");
                    return;
                }
                if(txt_total.val() == '0.00') {
                    mostrarMensajeAdvertencia("No se pudo guardar el Comprobante", "Debe agregar al menos un producto");
                    return;
                }
                guardarVenta();
            }
        });

   });

   sel_tipo_comprobante.on('change', function() {
        cambioTipoComprobante();
        cargarSeries();
   });

    $('body').on('keyup', '.txtCantidad,.txtPrecioUnitario', function (e) {
       if (e.which == 13) return false;
       var n = $(this).val();
       if (n >= 0) {
           calcularComprobante();
       }
       else {
           $(this).val('');
       }
   });

}

function consultarCliente() {


    sel_cliente.select2({
        placeholder: 'Escriba el nombre del Cliente',
        minimumInputLength: 3,
        width: '100%',
        language: "es",
        allowClear: true,
        ajax: {
            url: '/listarclientespornombres',
            type: 'GET',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            delay: 250,
            data: function (params) {
                if (!params.term) params.term = '';
                var query = {
                    nombres: params.term,
                    tipodocumento: sel_tipo_comprobante.val()
                }
                return query;
            },
            processResults: function (data, page) {

                var parsed = [];
                try {
                    parsed = $.map(data, function (item) {
                        return {
                            id: item.codigoCliente,
                            text: item.nombres,
                            documento: item.documento,
                            correo: item.correo,
                            direccion: item.direccion
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
          var data = e.params.data;
          txt_documento.val(data.documento);
          txt_email.val(data.correo);
          txt_direccion.val(data.direccion);

    }).on('select2:unselecting', function (e) {
          e.preventDefault();
          txt_documento.val(CADENA_VACIA);
          txt_email.val(CADENA_VACIA);
          txt_direccion.val(CADENA_VACIA);
          consultarCliente();
    });
}

function cambioTipoComprobante() {

    var tipoComporbante = sel_tipo_comprobante.val();

    if(tipoComporbante == TipoComprobante.FACTURA) {
        tr_subtotal.show();
        tr_igv.show();
    }

    if(tipoComporbante == TipoComprobante.BOLETA) {
        tr_subtotal.hide();
        tr_igv.hide();
    }
}

function cargarSeries() {

    $.ajax({
        type:"GET",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/listarseriesportipocomprobante?tipocomprobante=' + sel_tipo_comprobante.val(),
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

                sel_serie.find('option').remove();

                result.forEach(serie => {
                    sel_serie.append($('<option />').val(serie.codigoSerie).html(serie.codigoSerie));
                });

            }
        }
    });
}

function calcularComprobante() {

    var total = 0;

    $(".txtProducto").each(function () {

        var tr = $(this).closest('tr');
        if ($(this).attr('data-id') != '0') {

            var comboProd = $('#row-' + $(this).attr('data-id') + '-item').select2('data')[0];

            if (comboProd != undefined) {
                var p = parseFloat(tr.find('.txtPrecioUnitario').val());
                var c = parseFloat(tr.find('.txtCantidad').val());
                var st = parseFloat(p * c);

                tr.find('.txtTotal').val(st.toFixed(2));
                total += st;
            }
            else {
                tr.find('.txtTotal').val('');
            }
        }
    });

    total = (total).toFixed(2);
    var igv = ((parseFloat(txt_igv.val()) / 100) + 1).toFixed(2);
    var subTotal = (total / igv).toFixed(2);
    var igvSubTotal = (total - subTotal).toFixed(2);

    txt_subtotal.val(subTotal);
    txt_monto_igv.val(igvSubTotal);
    txt_total.val(total);

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

function validacionFormularioRegistroVenta() {

    form_registro_venta_validation = FormValidation.formValidation(document.getElementById('form_venta'),
            {
                fields: {
                    sel_tipo_comprobante: {
                        validators: {
                            notEmpty: {
                                message: 'El Tipo de Comprobante es obligatorio.',
                            },
                        },
                    },
                    sel_serie: {
                        validators: {
                            notEmpty: {
                                message: 'La serie es obligatorio.',
                            },
                        },
                    },
                    txt_fecha_emision: {
                        validators: {
                            notEmpty: {
                                message: 'La fecha de emisión es obligatoria.',
                            },
                        },
                    },
                    txt_fecha_vencimiento: {
                        validators: {
                            notEmpty: {
                                message: 'La fecha de vencimiento es obligatoria.',
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
                    '<input type="text" id="row-' + fila + '-cantidad" name="' + fila + '" value="" class="form-control text-end txtCantidad" disabled>',
                    '<input type="text" id="row-' + fila + '-UDM" name="' + fila + '" value="" class="form-control" disabled>',
                    '<input type="text" id="row-' + fila + '-PU" name="' + fila + '" value="" class="form-control text-end txtPrecioUnitario" disabled>',
                    '<input type="text" id="row-' + fila + '-PT" name="' + fila + '" value="" class="form-control text-end txtTotal" disabled>'
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
          var data = e.params.data;
          var filaSel = $(e.currentTarget).attr('name');

          $('#row-' + filaSel + '-UDM').val(data.und);
          $('#row-' + filaSel + '-cantidad').removeAttr('disabled', false).val('1');
          $('#row-' + filaSel + '-PU').removeAttr('disabled', false).val(data.precio);

          calcularComprobante();
          eliminarProductosVacios();
          agregarFilaTabla(null);
    }).on('select2:unselecting', function (e) {
          e.preventDefault();
          calcularComprobante();
          eliminarProductosVacios();
          agregarFilaTabla(null);
    });

}

function guardarVenta() {

    var venta = {}
    venta["codigoTipoComprobante"] = sel_tipo_comprobante.val();
    venta["codigoSerie"] = sel_serie.val();
    venta["fechaEmision"] = txt_fecha_emision.val();
    venta["fechaVencimiento"] = txt_fecha_vencimiento.val();
    venta["codigoCliente"] = sel_cliente.val();
    venta["glosa"] = txt_glosa.val();
    venta["subtotal"] = txt_subtotal.val();
    venta["igv"] = txt_monto_igv.val();
    venta["total"] = txt_total.val();

    var detalle = [];
    $(".txtProducto").each(function (index, obj) {

        var idFila = obj.name;
        var comboProd = $('#row-' + idFila + '-item').select2('data')[0];
        if (comboProd != undefined) {
            var fila = {
                codigoArticulo: comboProd.id,
                cantidad: $('#row-' + idFila + '-cantidad').val(),
                precioUnitario: $('#row-' + idFila + '-PU').val(),
                precioTotal: parseFloat($('#row-' + idFila + '-PU').val() * $('#row-' + idFila + '-cantidad').val()).toFixed(2)
            };
            detalle.push(fila);
        }
    });

    venta["detalleComprobante"] = detalle;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        accept: 'text/plain',
        url: '/guardarventa',
        data: JSON.stringify(venta),
        dataType: 'json',
        beforeSend: function (xhr) {
            loadding(true);
        },
        error: function (xhr, status, error) {
            loadding(false);

            if (xhr.status === HttpStatus.UnprocessableEntity) {
                mostrarMensajeAdvertencia("No se pudo guardar el Comprobante", xhr.responseJSON.message);
            }

            if (xhr.status == HttpStatus.InternalServerError) {
                mostrarMensajeError(ERROR_GENERICO);
            }

        },
        success: function (result, textStatus, xhr) {

            loadding(false);

            if (xhr.status == HttpStatus.OK) {

                mostrarMensajeOK("Comprobante Generado Satisfactoriamente", result.tipoComprobante.descripcion + " - " + result.codigoSerie + " - " + result.numero);

                limpiarPantalla();

            }
        }
    });

}

function limpiarPantalla() {

    sel_tipo_comprobante.prop("selectedIndex", 0);
    sel_tipo_comprobante.change();
    txt_fecha_emision.val(CADENA_VACIA);
    txt_fecha_vencimiento.val(CADENA_VACIA);
    sel_cliente.val(CADENA_VACIA);
    txt_documento.val(CADENA_VACIA);
    txt_email.val(CADENA_VACIA);
    txt_direccion.val(CADENA_VACIA);

    form_registro_venta_validation.resetForm(false);

    table.clear().draw();

    agregarFilaTabla(null);
    consultarCliente();
}