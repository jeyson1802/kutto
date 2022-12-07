package com.kutto.plataforma.rest;

import com.kutto.plataforma.dto.CitaDisponibleDto;
import com.kutto.plataforma.dto.CitaDto;
import com.kutto.plataforma.request.RequestRegistroReserva;
import com.kutto.plataforma.service.CitaDisponibleService;
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
public class ReservaRestController {

    private static final Logger logger = LogManager.getLogger(ReservaRestController.class);

    @Autowired
    private CitaDisponibleService citaDisponibleService;

    @Autowired
    private CitaService citaService;

    @GetMapping("/horariosdisponibles/{fechaReserva}")
    public ResponseEntity<List<CitaDisponibleDto>> horariosDisponiblesPorFecha(@PathVariable(Constante.PARAM_FECHA_RESERVA) String fechaReserva) throws Exception {

        logger.info("Inicio horariosDisponiblesPorFecha.......");
        List<CitaDisponibleDto> listCitaDisponibleDto = citaDisponibleService.listarHorariosDisponiblesPorFecha(DateUtil.stringToDateYYYYMMDD(fechaReserva));
        logger.info("Fin horariosDisponiblesPorFecha.......");

        return new ResponseEntity<>(listCitaDisponibleDto, HttpStatus.OK);
    }

    @GetMapping("/buscarreserva/{busqueda}")
    public ResponseEntity<CitaDto> buscarReserva(@PathVariable(Constante.PARAM_BUSQUEDA) String busqueda) throws Exception {

        logger.info("Inicio buscarReserva.......");
        CitaDto citaDto = citaService.buscarReserva(busqueda);

        if(StringUtil.isEmpty(citaDto)) {
            return new ResponseEntity<>(citaDto, HttpStatus.NO_CONTENT);
        }

        logger.info("Fin buscarReserva.......");

        return new ResponseEntity<>(citaDto, HttpStatus.OK);
    }

    @PostMapping(value="/registrarreserva")
    public ResponseEntity<CitaDto> registrarReserva(@RequestBody RequestRegistroReserva requestRegistroReserva,
                                                    WebRequest request) throws Exception {

        logger.info("Inicio registrarReserva.......");

        logger.info("requestRegistroReserva ==> " + requestRegistroReserva);

        CitaDto citaDto = citaService.registrarReserva(requestRegistroReserva);

        logger.info("citaDto ==> " + citaDto);

        logger.info("Fin registrarReserva.......");

        return new ResponseEntity<>(citaDto, HttpStatus.OK);
    }

}
