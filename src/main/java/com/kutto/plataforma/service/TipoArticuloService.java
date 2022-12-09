package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.request.RequestGuardarTipoArticulo;

import java.util.List;

public interface TipoArticuloService {

    List<TipoArticuloDto> listarTipoArticulo() throws Exception;

    TipoArticuloDto buscarTipoArticulo(String codigoTipoArticulo) throws Exception;

    TipoArticuloDto guardarTipoArticulo(RequestGuardarTipoArticulo requestGuardarTipoArticulo) throws Exception;

    void eliminarTipoArticulo(String codigoTipoArticulo) throws Exception;

}
