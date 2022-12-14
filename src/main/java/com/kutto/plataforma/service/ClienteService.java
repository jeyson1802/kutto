package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.ClienteDto;

import java.util.List;

public interface ClienteService {

    List<ClienteDto> listarClientesPorNombresConTipoDocumento(String nombres, String codigoTipoPersona) throws Exception;

}
