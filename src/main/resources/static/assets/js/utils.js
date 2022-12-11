const CADENA_VACIA = "";
const ERROR_GENERICO = "Ocurrió un error inesperado. Por favor intentar nuevamente.";
var index = 4000;

var HttpStatus = {
	OK  				: 200,
	Accepted  			: 202,
	BadRequest			: 400,
	Forbidden			: 403,
	NotFound 			: 404,
	UnprocessableEntity : 422,
	InternalServerError	: 500
};

$.date = function(dateObject) {
    var dateAr = dateObject.split('-');
    var newDate = dateAr[2] + '/' + dateAr[1] + '/' + dateAr[0];
    return newDate;
};

function mostrarMensajeOK(titulo, mensaje) {

    Swal.fire(
        {
            title: titulo,
            text: mensaje,
            icon: 'success',
            showCancelButton: false,
            confirmButtonColor: '#0f9cf3'
        }
    );
}

function mostrarMensajeAdvertencia(titulo, mensaje) {

    Swal.fire(
        {
            title: titulo,
            text: mensaje,
            icon: 'warning',
            showCancelButton: false,
            confirmButtonColor: '#0f9cf3'
        }
    );
}

function mostrarMensajeError(mensaje) {

    Swal.fire(
        {
            title: "Error interno del sistema.",
            text: mensaje,
            icon: 'error',
            showCancelButton: false,
            confirmButtonColor: '#0f9cf3'
        }
    );
}

function mostrarConfirmacion(titulo, mensaje, callback, parametrocallback) {

    Swal.fire({
      title: titulo,
      text: mensaje,
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#0f9cf3',
      cancelButtonColor: '#f32f53',
      confirmButtonText: 'Confirmar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
          callback(parametrocallback);
      }
    });
}

function loadding(onOff) {

    if (onOff) {
        var div="<div id='loadding' class='box'><div class='image'><img src='assets/images/loading-kutto.svg'></div></div>";
        jQuery.blockUI({
            message: div,
            css: {
                border: 'none',
                padding: '0px',
                backgroundColor: ''
            },
            overlayCSS: {
                backgroundColor: 'black',
                opacity: 0.3
            },
            baseZ: index
        });

        index = index + 1;
    }
    else {
        jQuery.unblockUI();
    }
}