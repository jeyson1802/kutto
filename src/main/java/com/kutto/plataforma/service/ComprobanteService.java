package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.CitaDisponibleDto;
import com.kutto.plataforma.dto.ComprobanteDto;
import com.kutto.plataforma.request.RequestGuardarVenta;

public interface ComprobanteService {

    ComprobanteDto guardarVenta(RequestGuardarVenta requestGuardarVenta) throws Exception;

}
