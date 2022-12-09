package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.ArticuloDto;

import com.kutto.plataforma.dto.PaginacionWrapperDto;
import com.kutto.plataforma.request.RequestGuardarArticulo;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ArticuloService {

    PaginacionWrapperDto<ArticuloDto> buscarArticulos(String codigoTipoArticulo, Pageable pageable) throws Exception;

    ArticuloDto buscarArticulo(String codigoArticulo) throws Exception;

    List<ArticuloDto> listarArticulos() throws Exception;

    void guardarArticulo(RequestGuardarArticulo requestGuardarArticulo) throws Exception;

    void eliminarArticulo(String codigoArticulo) throws Exception;

}
