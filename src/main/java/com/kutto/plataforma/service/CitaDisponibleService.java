package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.CitaDisponibleDto;

import java.util.Date;
import java.util.List;

public interface CitaDisponibleService {

    List<Date> listarFechasDisponibles() throws Exception;

    List<CitaDisponibleDto> listarHorariosDisponiblesPorFecha(Date fechaReserva) throws Exception;

}
