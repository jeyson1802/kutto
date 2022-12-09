var datatable_articulos;
var table;
var modal_articulo;
var hid_codigo_articulo;
var txt_titulo;
var txt_sku;
var txt_descripcion_corta;
var txt_descripcion_larga;
var sel_categoria;
var txt_precio;
var txt_stock;
var txt_observaciones;
var btn_guardar;
var btn_agregar_articulo;
var file;
var img_articulo;
var h5_titulo;

$(document).ready(function(){
    inicializarVariables();
    inicializarComponentes();
    inicializarEventos();
});

function inicializarVariables() {

    datatable_articulos = $("#datatable_articulos");
    modal_articulo = $("#modal_articulo");
    hid_codigo_articulo = $("#hid_codigo_articulo");
    txt_titulo = $("#txt_titulo");
    txt_sku = $("#txt_sku");
    txt_descripcion_corta = $("#txt_descripcion_corta");
    txt_descripcion_larga = $("#txt_descripcion_larga");
    sel_categoria = $("#sel_categoria");
    txt_precio = $("#txt_precio");
    txt_stock = $("#txt_stock");
    txt_observaciones = $("#txt_observaciones");
    btn_guardar = $("#btn_guardar");
    btn_agregar_articulo = $("#btn_agregar_articulo");
    file = $("#file");
    img_articulo = $("#img_articulo");
    h5_titulo = $("#h5_titulo");

}

function inicializarComponentes(){

    table = datatable_articulos.DataTable({

            "ajax": {
                url: '/listararticulos',
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
                    "data": "codigoArticulo"
                },
                {
                    "width": "10px",
                    "targets": [1],
                    "data": "codigoEstandar"
                },
                {
                    "width": "10px",
                    "targets": [2],
                    "data": "titulo"
                },
                {
                    "width": "100px",
                    "targets": [3],
                    "data": "descripcionCorta"
                },
    			{
                    "width": "50px",
                    "targets": [4],
                    "data": "tipoArticulo.descripcion"
                },
    			{
                    "width": "50px",
                    "targets": [5],
                    "data": "precioUnitario"
                },
    			{
                    "width": "50px",
                    "targets": [6],
                    "data": "stock"
                },
                {
                    "width": "10px",
                    "targets": [7],
                    "data": "observaciones"
                },
                {
                    "width": "100px",
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

    $('#datatable_articulos tbody').on( 'click','.btn-edit', function (){
        var data = table.row( $(this).closest('tr')).data();
    	cargarArticulo(data.codigoArticulo);
    });

    $('#datatable_articulos tbody').on( 'click','.btn-delete', function (){
         var data = table.row( $(this).closest('tr')).data();
     	 eliminarArticulo(data.codigoArticulo);
     });
}

function inicializarEventos(){

   btn_guardar.on('click', function() {
       	guardarArticulo();
   });

   btn_agregar_articulo.on('click', function() {
       	agregarArticulo();
   });

   file.on('change', function() {
       previsualizar(this);
   });

}

function previsualizar(elemento) {
    
    var input = elemento;
    var url = $(elemento).val();
    var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();

    if (input.files && input.files[0] && (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) {

        var reader = new FileReader();
        reader.onload = function (e) {
           img_articulo.attr('src', e.target.result);
        }
       reader.readAsDataURL(input.files[0]);
    }
}

function agregarArticulo(){

    hid_codigo_articulo.val(CADENA_VACIA);
    h5_titulo.html("Nuevo Producto");
    txt_titulo.val(CADENA_VACIA);
    txt_sku.val(CADENA_VACIA);
    txt_descripcion_corta.val(CADENA_VACIA);
    txt_descripcion_larga.val(CADENA_VACIA);
    sel_categoria.prop("selectedIndex", 0);
    txt_precio.val(CADENA_VACIA);
    txt_stock.val(CADENA_VACIA);
    txt_observaciones.val(CADENA_VACIA);
    img_articulo.attr('src', "img/product/sinimagen.jpg");
    file.val(CADENA_VACIA);
    modal_articulo.modal("show");
}

function cargarArticulo(codigoArticulo){

	$.ajax({
        type:"GET",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/buscararticulo?codigoArticulo=' + codigoArticulo,
        data : null,
        dataType: 'text',
        beforeSend: function(xhr) {
        	//loadding(true);
        },
        success:function(result,textStatus,xhr){

			if(xhr.status == HttpStatus.OK){
                var data = JSON.parse(result);
                cargarModalArticulo(data);
            }
			//loadding(false);
        }
    });
}

function eliminarArticulo(codigoArticulo){

	$.ajax({
        type:"DELETE",
        contentType : "application/json",
        accept: 'text/plain',
        url : '/eliminararticulo?codigoArticulo=' + codigoArticulo,
        data : null,
        dataType: 'text',
        beforeSend: function(xhr) {
        	//loadding(true);
        },
        success:function(result,textStatus,xhr){

			if(xhr.status == HttpStatus.OK){
			    alert('ELIMINADO');

			    table.clear();
                table.ajax.reload(null, true);

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
            }
			//loadding(false);
        }
    });
}

function cargarModalArticulo(articulo) {

    hid_codigo_articulo.val(articulo.codigoArticulo);
    h5_titulo.html("Producto " + articulo.codigoArticulo);
    txt_titulo.val(articulo.titulo);
    txt_sku.val(articulo.codigoEstandar);
    txt_descripcion_corta.val(articulo.descripcionCorta);
    txt_descripcion_larga.val(articulo.descripcionLarga);
    sel_categoria.val(articulo.tipoArticulo.codigoTipoArticulo);
    txt_precio.val(articulo.precioUnitario);
    txt_stock.val(articulo.stock);
    txt_observaciones.val(articulo.observaciones);
    if(articulo.imagenString === null) {
        img_articulo.attr('src', "img/product/sinimagen.jpg");
    } else {
        img_articulo.attr('src', articulo.imagenString);
    }
    file.val(CADENA_VACIA);
    modal_articulo.modal("show");
}

function guardarArticulo() {

    var articulo = {}
    articulo["codigoArticulo"] = hid_codigo_articulo.val();
    articulo["codigoEstandar"] = txt_sku.val();
    articulo["titulo"] = txt_titulo.val();
    articulo["descripcionCorta"] = txt_descripcion_corta.val();
    articulo["descripcionLarga"] = txt_descripcion_larga.val();
    articulo["codigoTipoArticulo"] = sel_categoria.val();
    articulo["observaciones"] = txt_observaciones.val();
    articulo["precioUnitario"] = txt_precio.val();
    articulo["stock"] = txt_stock.val();
    articulo["imagen"] = btoa(img_articulo.attr('src'));

    var articuloJson = JSON.stringify(articulo);

    var formData = new FormData();

    formData.append('registro', new Blob([articuloJson], {
    	type: "application/json"
    }));

    $.ajax({
    	type:"POST",
    	contentType: false,
    	processData: false,
    	url : '/guardararticulo',
    	data: formData,
    	beforeSend: function(xhr) {
    		//loadding(true);
    	},
    	success:function(resultado,textStatus,xhr){

    		if(xhr.status == HttpStatus.OK) {

    		    alert('OK');
    			//mostrarNotificacion("El registro fue grabado correctamente.", "success");

    			table.clear();
                table.ajax.reload(null, true);

                modal_articulo.modal("hide");

                $('body,html').animate({
                    scrollTop: 0
                }, 800);
    		}

    		//loadding(false);
    	},
    	error: function (xhr, error, code){
    		alert('ERROR');
    		//mostrarMensajeError(xhr.responseText);
    		//loadding(false);
    	}
    });

}