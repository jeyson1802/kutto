const CADENA_VACIA = "";

var HttpStatus = {
	OK  				: 200,
	Accepted  			: 202,
	BadRequest			: 400,
	Forbidden			: 403,
	NotFound 			: 404,
	InternalServerError	: 500
};

$.date = function(dateObject) {
    var dateAr = dateObject.split('-');
    var newDate = dateAr[2] + '/' + dateAr[1] + '/' + dateAr[0];
    return newDate;
};
