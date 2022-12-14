package com.kutto.plataforma.rest;

import com.kutto.plataforma.dto.ArticuloDto;
import com.kutto.plataforma.dto.ComprobanteDto;
import com.kutto.plataforma.model.Comprobante;
import com.kutto.plataforma.request.RequestGuardarArticulo;
import com.kutto.plataforma.request.RequestGuardarVenta;
import com.kutto.plataforma.service.ArticuloService;
import com.kutto.plataforma.service.ComprobanteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComprobanteRestController {

    private static final Logger logger = LogManager.getLogger(ComprobanteRestController.class);

    @Autowired
    private ComprobanteService comprobanteService;

    @PostMapping ("/guardarventa")
    public ResponseEntity<ComprobanteDto> guardarVenta(@RequestBody RequestGuardarVenta requestGuardarVenta) throws Exception {

        logger.info("Inicio guardarVenta.......");

        ComprobanteDto comprobanteDto = comprobanteService.guardarVenta(requestGuardarVenta);

        logger.info("Fin guardarVenta.......");

        return new ResponseEntity<>(comprobanteDto, HttpStatus.OK);

    }

}
