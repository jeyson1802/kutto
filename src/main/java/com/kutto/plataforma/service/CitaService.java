package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.CitaDisponibleDto;
import com.kutto.plataforma.dto.CitaDto;
import com.kutto.plataforma.request.RequestRegistroReserva;

import java.util.Date;
import java.util.List;

public interface CitaService {

    CitaDto registrarReserva(RequestRegistroReserva requestRegistroReserva) throws Exception;

    CitaDto buscarReserva(String busqueda) throws Exception;

}
