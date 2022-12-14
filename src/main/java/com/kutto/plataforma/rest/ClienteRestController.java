package com.kutto.plataforma.rest;

import com.kutto.plataforma.dto.ClienteDto;
import com.kutto.plataforma.service.ClienteService;
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
public class ClienteRestController {

    private static final Logger logger = LogManager.getLogger(ClienteRestController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listarclientespornombres")
    public ResponseEntity<List<ClienteDto>> listarClientesPorNombresConTipoDocumento(@RequestParam("nombres") String nombres, @RequestParam("tipodocumento") String tipodocumento) throws Exception {

        logger.info("Inicio listarclientespornombres.......");
        List<ClienteDto> litsClienteDto = clienteService.listarClientesPorNombresConTipoDocumento(nombres, tipodocumento);
        logger.info("Fin listarclientespornombres.......");

        return new ResponseEntity<>(litsClienteDto, HttpStatus.OK);
    }
}
