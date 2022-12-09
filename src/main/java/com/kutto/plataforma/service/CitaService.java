package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.CitaDisponibleDto;
import com.kutto.plataforma.dto.CitaDto;
import com.kutto.plataforma.request.RequestModificarCita;
import com.kutto.plataforma.request.RequestRegistroReserva;

import java.util.Date;
import java.util.List;

public interface CitaService {

    CitaDto registrarReserva(RequestRegistroReserva requestRegistroReserva) throws Exception;

    CitaDto buscarReserva(String busqueda) throws Exception;

    List<CitaDto> listarCitas() throws Exception;

    CitaDto modificarCita(RequestModificarCita requestModificarCita) throws Exception;

}
