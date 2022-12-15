var datatable_ventas;
var table;
var modal_venta;

$(document).ready(function(){
    inicializarVariables();
    inicializarComponentes();
});

function inicializarVariables() {

    datatable_ventas = $("#datatable_ventas");
    modal_venta = $("#modal_venta");
}

function inicializarComponentes(){

    table = datatable_ventas.DataTable({

            "ajax": {
                url: '/listarventas',
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
                    "data": "tipoComprobante.descripcion"
                },
                {
                    "width": "20px",
                    "targets": [1],
                    "data": "codigoSerie"
                },
                {
                    "width": "20px",
                    "targets": [2],
                    "data": "numero"
                },
                {
                    "width": "30px",
                    "targets": [3],
                    "data": null,
                    "render":
                              function (data, type, row ) {
                                  return  $.date(data.fechaEmision);
                              }
                },
                {
                    "width": "30px",
                    "targets": [4],
                    "data": null,
                    "render":
                              function (data, type, row ) {
                                  return  $.date(data.fechaVencimiento);
                              }
                },
                {
                    "width": "50px",
                    "targets": [5],
                    "data": "cliente.nombres"
                },
                {
                    "width": "50px",
                    "targets": [6],
                    "data": "glosa"
                },
                {
                    "width": "20px",
                    "targets": [7],
                    "data": "subtotal"
                },
    			{
                    "width": "20px",
                    "targets": [8],
                    "data": "igv"
                },
    			{
                    "width": "20px",
                    "targets": [9],
                    "data": "total"
                }
             ],
             "language"  : {
                "url": "/language/Spanish.json"
             },
             "drawCallback": function () {
                  $('.dataTables_paginate > .pagination').addClass('pagination-rounded');
              }
    });

}
