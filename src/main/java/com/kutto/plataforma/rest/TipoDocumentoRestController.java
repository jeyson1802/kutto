package com.kutto.plataforma.rest;

import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.dto.TipoDocumentoDto;
import com.kutto.plataforma.request.RequestGuardarTipoArticulo;
import com.kutto.plataforma.service.TipoArticuloService;
import com.kutto.plataforma.service.TipoDocumentoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
public class TipoDocumentoRestController {

    private static final Logger logger = LogManager.getLogger(TipoDocumentoRestController.class);

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping("/listartipodocumentosportipopersona")
    public ResponseEntity<List<TipoDocumentoDto>> listarTipoDocumentoPorTipoPersona(@RequestParam("tipopersona") String tipopersona) throws Exception {

        logger.info("Inicio listarTipoDocumentoPorTipoPersona.......");
        List<TipoDocumentoDto> litsTipoArticuloDto = tipoDocumentoService.listarTipoDocumentoPorTipoPersona(tipopersona);
        logger.info("Fin listarTipoDocumentoPorTipoPersona.......");

        return new ResponseEntity<>(litsTipoArticuloDto, HttpStatus.OK);
    }

}
