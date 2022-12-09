package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.CitaDisponibleDto;
import com.kutto.plataforma.request.RequestGuardarCitaDisponible;

import java.util.Date;
import java.util.List;

public interface CitaDisponibleService {

    List<Date> listarFechasDisponibles() throws Exception;

    List<CitaDisponibleDto> listarHorariosDisponiblesPorFecha(Date fechaReserva) throws Exception;

    List<CitaDisponibleDto> listarHorariosDisponibles() throws Exception;

    CitaDisponibleDto guardarCitaDisponible(RequestGuardarCitaDisponible requestGuardarCitaDisponible) throws Exception;

    CitaDisponibleDto buscarCitaDisponible(String codigoCitaDisponible) throws Exception;

}
