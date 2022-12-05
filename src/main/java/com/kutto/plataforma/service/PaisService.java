package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.PaisDto;

import java.util.List;

public interface PaisService {

    List<PaisDto> listarPaises() throws Exception;

}
