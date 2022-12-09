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
    var d = new Date(dateObject);
    var day = d.getDate();
    var month = d.getMonth() + 1;
    var year = d.getFullYear();
    if (day < 10) {
        day = "0" + day;
    }
    if (month < 10) {
        month = "0" + month;
    }
    var date = day + "/" + month + "/" + year;

    return date;
};
