package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.EstadoCitaDto;
import com.kutto.plataforma.dto.TipoArticuloDto;

import java.util.List;

public interface EstadoCitaService {

    List<EstadoCitaDto> listarEstadoCita() throws Exception;

}
