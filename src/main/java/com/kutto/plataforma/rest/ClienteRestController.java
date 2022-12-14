package com.kutto.plataforma.rest;

import com.kutto.plataforma.dto.ClienteDto;
import com.kutto.plataforma.dto.ClienteDto;
import com.kutto.plataforma.request.RequestGuardarCliente;
import com.kutto.plataforma.service.ClienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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

    @GetMapping("/listarclientes")
    public ResponseEntity<List<ClienteDto>> listarClientes() throws Exception {

        logger.info("Inicio listarclientespornombres.......");
        List<ClienteDto> litsClienteDto = clienteService.listarClientes();
        logger.info("Fin listarclientespornombres.......");

        return new ResponseEntity<>(litsClienteDto, HttpStatus.OK);
    }

    @PostMapping("/guardarcliente")
    public ResponseEntity<ClienteDto> guardarCliente(@RequestBody RequestGuardarCliente requestGuardarCliente,
                                                                   WebRequest request) throws Exception {

        logger.info("Inicio guardarCliente.......");

        ClienteDto clienteDto = clienteService.guardarCliente(requestGuardarCliente);

        logger.info("Fin guardarCliente.......");

        return new ResponseEntity<>(clienteDto, HttpStatus.OK);

    }

    @GetMapping("/buscarcliente")
    public ResponseEntity<ClienteDto> buscarCliente(@RequestParam("codigoCliente") String codigoCliente) throws Exception {

        logger.info("Inicio buscarcliente.......");

        ClienteDto clienteDto = clienteService.buscarCliente(codigoCliente);

        logger.info("Fin buscarcliente.......");

        return new ResponseEntity<>(clienteDto, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarcliente")
    public ResponseEntity<String> eliminarCliente(@RequestParam("codigoCliente") String codigoCliente) throws Exception {

        logger.info("Inicio eliminarCliente.......");

        clienteService.eliminarCliente(codigoCliente);

        logger.info("Fin eliminarCliente.......");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
