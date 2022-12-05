package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.CargoDto;
import com.kutto.plataforma.dto.RegistroConferenciaDto;
import com.kutto.plataforma.request.RequestRegistroConferencia;

import java.io.OutputStream;
import java.util.List;

public interface RegistroConferenciaService {

    RegistroConferenciaDto registrarParticipanteConferencia(RequestRegistroConferencia requestRegistroConferencia) throws Exception;

    void generarConstanciaParticipante(Integer idParticipante, OutputStream outputStream) throws Exception;

    void enviarEmailConstanciaParticipante(Integer idParticipante) throws Exception;

    void agregarContactoParticipanteSendinBlue(Integer idParticipante) throws Exception;

}
