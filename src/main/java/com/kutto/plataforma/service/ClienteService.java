package com.kutto.plataforma.service;

import com.kutto.plataforma.dto.ClienteDto;
import com.kutto.plataforma.dto.ClienteDto;
import com.kutto.plataforma.request.RequestGuardarCliente;

import java.util.List;

public interface ClienteService {

    List<ClienteDto> listarClientesPorNombresConTipoDocumento(String nombres, String codigoTipoPersona) throws Exception;

    List<ClienteDto> listarClientes() throws Exception;

    ClienteDto guardarCliente(RequestGuardarCliente requestGuardarCliente) throws Exception;

    ClienteDto buscarCliente(String codigoCliente) throws Exception;

    void eliminarCliente(String codigoCliente) throws Exception;

}
