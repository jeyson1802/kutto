package com.kutto.plataforma.rest;

import com.kutto.plataforma.dto.CitaDisponibleDto;
import com.kutto.plataforma.request.RequestGuardarCitaDisponible;
import com.kutto.plataforma.service.CitaDisponibleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
public class HorarioRestController {

    private static final Logger logger = LogManager.getLogger(HorarioRestController.class);

    @Autowired
    private CitaDisponibleService citaDisponibleService;

    @GetMapping("/listarhorario")
    public ResponseEntity<List<CitaDisponibleDto>> listarCitaDisponible() throws Exception {

        logger.info("Inicio listarCitaDisponible.......");
        List<CitaDisponibleDto> litsCitaDisponibleDto = citaDisponibleService.listarHorariosDisponibles();
        logger.info("Fin listarCitaDisponible.......");

        return new ResponseEntity<>(litsCitaDisponibleDto, HttpStatus.OK);
    }

    @PostMapping("/guardarhorario")
    public ResponseEntity<CitaDisponibleDto> guardarCitaDisponible(@RequestBody RequestGuardarCitaDisponible requestGuardarCitaDisponible,
                                                                    WebRequest request) throws Exception {

        logger.info("Inicio guardarCitaDisponible.......");

        CitaDisponibleDto citaDisponibleDto = citaDisponibleService.guardarCitaDisponible(requestGuardarCitaDisponible);

        logger.info("Fin guardarCitaDisponible.......");

        return new ResponseEntity<>(citaDisponibleDto, HttpStatus.OK);

    }

    @GetMapping("/buscarhorario")
    public ResponseEntity<CitaDisponibleDto> buscarCitaDisponible(@RequestParam("codigoCitaDisponible") String codigoCitaDisponible) throws Exception {

        logger.info("Inicio buscarcitaDisponible.......");

        CitaDisponibleDto citaDisponibleDto = citaDisponibleService.buscarCitaDisponible(codigoCitaDisponible);

        logger.info("Fin buscarcitaDisponible.......");

        return new ResponseEntity<>(citaDisponibleDto, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarhorario")
    public ResponseEntity<String> eliminarCitaDisponible(@RequestParam("codigoCitaDisponible") String codigoCitaDisponible) throws Exception {

        logger.info("Inicio eliminarCitaDisponible.......");

        citaDisponibleService.eliminarCitaDisponible(codigoCitaDisponible);

        logger.info("Fin eliminarCitaDisponible.......");

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
