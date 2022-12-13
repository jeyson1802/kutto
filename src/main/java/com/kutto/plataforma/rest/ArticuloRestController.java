package com.kutto.plataforma.rest;

import com.kutto.plataforma.dto.ArticuloDto;
import com.kutto.plataforma.request.RequestGuardarArticulo;
import com.kutto.plataforma.service.ArticuloService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticuloRestController {

    private static final Logger logger = LogManager.getLogger(ArticuloRestController.class);

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/listararticulos")
    public ResponseEntity<List<ArticuloDto>> listarArticulos() throws Exception {

        logger.info("Inicio listarArticulos.......");
        List<ArticuloDto> litsArticuloDto = articuloService.listarArticulos();
        logger.info("Fin listarArticulos.......");

        return new ResponseEntity<>(litsArticuloDto, HttpStatus.OK);
    }

    @PostMapping ("/guardararticulo")
    public ResponseEntity<String> guardarArticulo(@RequestPart("registro")RequestGuardarArticulo requestGuardarArticulo) throws Exception {

        logger.info("Inicio guardarArticulo.......");

        articuloService.guardarArticulo(requestGuardarArticulo);

        logger.info("Fin guardarArticulo.......");

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/buscararticulo")
    public ResponseEntity<ArticuloDto> buscarArticulo(@RequestParam("codigoArticulo") String codigoArticulo) throws Exception {

        logger.info("Inicio buscararticulo.......");

        ArticuloDto articuloDto = articuloService.buscarArticulo(codigoArticulo);

        logger.info("Fin buscararticulo.......");

        return new ResponseEntity<>(articuloDto, HttpStatus.OK);
    }

    @DeleteMapping("/eliminararticulo")
    public ResponseEntity<String> eliminarArticulo(@RequestParam("codigoArticulo") String codigoArticulo) throws Exception {

        logger.info("Inicio eliminarArticulo.......");

        articuloService.eliminarArticulo(codigoArticulo);

        logger.info("Fin eliminarArticulo.......");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listararticulosportitulo")
    public ResponseEntity<List<ArticuloDto>> listarArticulosPorTitulo(@RequestParam("titulo") String titulo) throws Exception {

        logger.info("Inicio listarArticulos.......");
        List<ArticuloDto> litsArticuloDto = articuloService.listarArticulosPorTituloConStock(titulo);
        logger.info("Fin listarArticulos.......");

        return new ResponseEntity<>(litsArticuloDto, HttpStatus.OK);
    }
}
