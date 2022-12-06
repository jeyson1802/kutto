package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.TipoArticuloDto;

import java.util.List;

public interface ParametricaService {

    String obtenerCodigoCorrelativoTabla(String prefijo, int longitud) throws Exception;

}
