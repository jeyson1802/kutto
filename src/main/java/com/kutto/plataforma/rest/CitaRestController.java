package com.kutto.plataforma.rest;

import com.kutto.plataforma.dto.CitaDto;
import com.kutto.plataforma.dto.CitaDto;
import com.kutto.plataforma.request.RequestModificarCita;
import com.kutto.plataforma.request.RequestRegistroReserva;
import com.kutto.plataforma.service.CitaService;
import com.kutto.plataforma.service.CitaService;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.DateUtil;
import com.kutto.plataforma.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
public class CitaRestController {

    private static final Logger logger = LogManager.getLogger(CitaRestController.class);

    @Autowired
    private CitaService citaService;

    @GetMapping("/listarcitas")
    public ResponseEntity<List<CitaDto>> listarCitas() throws Exception {

        logger.info("Inicio listarCitas.......");
        List<CitaDto> litsCitaDto = citaService.listarCitas();
        logger.info("Fin listarCitas.......");

        return new ResponseEntity<>(litsCitaDto, HttpStatus.OK);
    }

    @PostMapping(value="/modificarcita")
    public ResponseEntity<CitaDto> modificarCita(@RequestBody RequestModificarCita requestModificarCita,
                                                    WebRequest request) throws Exception {

        logger.info("Inicio modificarCita.......");

        logger.info("requestModificarCita ==> " + requestModificarCita);

        CitaDto citaDto = citaService.modificarCita(requestModificarCita);

        logger.info("citaDto ==> " + citaDto);

        logger.info("Fin modificarCita.......");

        return new ResponseEntity<>(citaDto, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarcita")
    public ResponseEntity<String> eliminarCita(@RequestParam("codigoCita") String codigoCita) throws Exception {

        logger.info("Inicio eliminarCita.......");

        citaService.eliminarCita(codigoCita);

        logger.info("Fin eliminarCita.......");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
