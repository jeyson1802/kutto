package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.CargoDto;
import com.kutto.plataforma.dto.PaisDto;

import java.util.List;

public interface CargoService {

    List<CargoDto> listarCargos() throws Exception;

}
