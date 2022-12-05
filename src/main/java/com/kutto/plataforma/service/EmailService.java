package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.RegistroConferenciaDto;

import java.util.Map;

public interface EmailService {

    void sendMail(String template, String asunto, String destino, Map<String, Object> parametros, Map<String, byte[]> imagenesCorreo,  Map<String, byte[]> adjuntos) throws Exception;
}
