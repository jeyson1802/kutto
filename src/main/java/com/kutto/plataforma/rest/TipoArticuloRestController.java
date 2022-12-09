package com.kutto.plataforma.rest;

import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.request.RequestGuardarTipoArticulo;
import com.kutto.plataforma.service.TipoArticuloService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
public class TipoArticuloRestController {

    private static final Logger logger = LogManager.getLogger(TipoArticuloRestController.class);

    @Autowired
    private TipoArticuloService tipoArticuloService;

    @GetMapping("/listartipoarticulo")
    public ResponseEntity<List<TipoArticuloDto>> listarTipoArticulo() throws Exception {

        logger.info("Inicio listarTipoArticulo.......");
        List<TipoArticuloDto> litsTipoArticuloDto = tipoArticuloService.listarTipoArticulo();
        logger.info("Fin listarTipoArticulo.......");

        return new ResponseEntity<>(litsTipoArticuloDto, HttpStatus.OK);
    }

    @PostMapping ("/guardartipoarticulo")
    public ResponseEntity<TipoArticuloDto> guardarTipoArticulo(@RequestBody RequestGuardarTipoArticulo requestGuardarTipoArticulo,
                                                      WebRequest request) throws Exception {

        logger.info("Inicio guardarTipoArticulo.......");

        TipoArticuloDto tipoArticuloDto = tipoArticuloService.guardarTipoArticulo(requestGuardarTipoArticulo);

        logger.info("Fin guardarTipoArticulo.......");

        return new ResponseEntity<>(tipoArticuloDto, HttpStatus.OK);

    }

    @GetMapping("/buscartipoarticulo")
    public ResponseEntity<TipoArticuloDto> buscarTipoArticulo(@RequestParam("codigoTipoArticulo") String codigoTipoArticulo) throws Exception {

        logger.info("Inicio buscartipoArticulo.......");

        TipoArticuloDto tipoArticuloDto = tipoArticuloService.buscarTipoArticulo(codigoTipoArticulo);

        logger.info("Fin buscartipoArticulo.......");

        return new ResponseEntity<>(tipoArticuloDto, HttpStatus.OK);
    }

    @DeleteMapping("/eliminartipoarticulo")
    public ResponseEntity<String> eliminarTipoArticulo(@RequestParam("codigoTipoArticulo") String codigoTipoArticulo) throws Exception {

        logger.info("Inicio eliminarTipoArticulo.......");

        tipoArticuloService.eliminarTipoArticulo(codigoTipoArticulo);

        logger.info("Fin eliminarTipoArticulo.......");

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
