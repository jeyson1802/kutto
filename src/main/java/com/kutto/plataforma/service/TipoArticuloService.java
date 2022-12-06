package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.PaisDto;
import com.kutto.plataforma.dto.TipoArticuloDto;

import java.util.List;

public interface TipoArticuloService {

    List<TipoArticuloDto> listarTipoArticulo() throws Exception;

}
