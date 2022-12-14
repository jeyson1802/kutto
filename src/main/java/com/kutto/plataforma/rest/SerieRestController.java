package com.kutto.plataforma.rest;

import com.kutto.plataforma.dto.SerieDto;
import com.kutto.plataforma.service.SerieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieRestController {

    private static final Logger logger = LogManager.getLogger(SerieRestController.class);

    @Autowired
    private SerieService serieService;

    @GetMapping("/listarseriesportipocomprobante")
    public ResponseEntity<List<SerieDto>> listarSeriesPoTipoComprobante(@RequestParam("tipocomprobante") String tipocomprobante) throws Exception {

        logger.info("Inicio listarseriespornombres.......");
        List<SerieDto> litsSerieDto = serieService.listarSeriesPorTipoComprobante(tipocomprobante);
        logger.info("Fin listarseriespornombres.......");

        return new ResponseEntity<>(litsSerieDto, HttpStatus.OK);
    }
}
