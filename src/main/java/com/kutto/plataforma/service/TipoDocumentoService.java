package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.dto.TipoDocumentoDto;
import com.kutto.plataforma.request.RequestGuardarTipoArticulo;

import java.util.List;

public interface TipoDocumentoService {


    List<TipoDocumentoDto> listarTipoDocumentoPorTipoPersona(String codigoTipoPersona) throws Exception;

}
