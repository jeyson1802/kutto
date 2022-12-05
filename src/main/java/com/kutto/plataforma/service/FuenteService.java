package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.FuenteDto;

import java.util.List;

public interface FuenteService {

    List<FuenteDto> listarFuentes() throws Exception;

}
