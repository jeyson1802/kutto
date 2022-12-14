package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.ClienteDto;
import com.kutto.plataforma.dto.SerieDto;

import java.util.List;

public interface SerieService {

    List<SerieDto> listarSeriesPorTipoComprobante(String codigoTipoComprobante) throws Exception;

}
