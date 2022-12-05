package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.CargoDto;
import com.kutto.plataforma.dto.ConferenciaDto;
import com.kutto.plataforma.model.Conferencia;

import java.util.List;

public interface ConferenciaService {

    ConferenciaDto obtenerConferenciaPorPais(Integer idPais) throws Exception;

}
