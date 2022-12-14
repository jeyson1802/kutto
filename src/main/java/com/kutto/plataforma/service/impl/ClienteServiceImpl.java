package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.ArticuloDto;
import com.kutto.plataforma.dto.ClienteDto;
import com.kutto.plataforma.dto.ClienteDto;
import com.kutto.plataforma.dto.PaginacionWrapperDto;
import com.kutto.plataforma.enums.EnumErrores;
import com.kutto.plataforma.exception.UnprocessableEntityException;
import com.kutto.plataforma.model.*;
import com.kutto.plataforma.repository.ArticuloRepository;
import com.kutto.plataforma.repository.ClienteRepository;
import com.kutto.plataforma.repository.TipoArticuloRepository;
import com.kutto.plataforma.repository.TipoDocumentoRepository;
import com.kutto.plataforma.request.RequestGuardarArticulo;
import com.kutto.plataforma.request.RequestGuardarCliente;
import com.kutto.plataforma.service.ArticuloService;
import com.kutto.plataforma.service.ClienteService;
import com.kutto.plataforma.service.ParametricaService;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.DateUtil;
import com.kutto.plataforma.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger logger = LogManager.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParametricaService parametricaService;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public List<ClienteDto> listarClientesPorNombresConTipoDocumento(String nombres, String codigoTipoDocumento) throws Exception {

        String codigoTipoPersona = Constante.CADENA_VACIA;

        if(Arrays.asList(Constante.CODIGO_FACTURA).contains(codigoTipoDocumento)){
            codigoTipoPersona = Constante.CODIGO_PERSONAL_JURIDICA;
        }

        if(Arrays.asList(Constante.CODIGO_BOLETA).contains(codigoTipoDocumento)){
            codigoTipoPersona = Constante.CODIGO_PERSONAL_NATURAL;
        }

        List<Cliente> listCliente = clienteRepository.findByNombresContainsAndCodigoTipoPersonaAndActivo(nombres, codigoTipoPersona, Constante.COD_ACTIVO);

        List<ClienteDto> listClienteDto = listCliente.stream().map(cliente -> modelMapper.map(cliente, ClienteDto.class)).collect(Collectors.toList());

        return  listClienteDto;
    }

    @Override
    public List<ClienteDto> listarClientes() throws Exception {

        List<Cliente> listCliente = clienteRepository.findByActivo(Constante.COD_ACTIVO);

        List<ClienteDto> listClienteDto = listCliente.stream().map(cliente -> modelMapper.map(cliente, ClienteDto.class)).collect(Collectors.toList());

        return  listClienteDto;
    }

    @Override
    public ClienteDto guardarCliente(RequestGuardarCliente requestGuardarCliente) throws Exception {

        Cliente cliente = new Cliente();

        if(StringUtil.isEmpty(requestGuardarCliente.getCodigoCliente())) {

            cliente.setCodigoCliente(parametricaService.obtenerCodigoCorrelativoTabla(Constante.PREFIJO_CLIENTE, 4));
            cliente.setUsuarioRegistro(Constante.USUARIO_ADMIN);
            cliente.setFechaRegistro(Instant.now());

        } else {

            cliente = clienteRepository.findById(requestGuardarCliente.getCodigoCliente()).get();
            cliente.setUsuarioModificacion(Constante.USUARIO_ADMIN);
            cliente.setFechaModificacion(Instant.now());
        }

        Optional<TipoDocumento> optionalTipoDocumento = tipoDocumentoRepository.findById(requestGuardarCliente.getCodigoTipoDocumento());
        cliente.setTipoDocumento(optionalTipoDocumento.get());

        cliente.setCodigoTipoPersona(requestGuardarCliente.getCodigoTipoPersona());
        cliente.setNombres(requestGuardarCliente.getNombres());
        cliente.setCorreo(requestGuardarCliente.getCorreo());
        cliente.setDocumento(requestGuardarCliente.getDocumento());
        cliente.setDireccion(requestGuardarCliente.getDireccion());

        cliente.setActivo(Constante.COD_ACTIVO);

        clienteRepository.save(cliente);

        ClienteDto clienteDto = new ClienteDto();
        modelMapper.map (cliente, clienteDto);

        return clienteDto;

    }

    @Override
    public ClienteDto buscarCliente(String codigoCliente) throws Exception {

        Optional<Cliente> optionalCliente = clienteRepository.findById(codigoCliente);

        if(optionalCliente.isPresent()) {
            return modelMapper.map(optionalCliente.get(), ClienteDto.class);
        } else {
            return new ClienteDto();
        }
    }

    @Override
    public void eliminarCliente(String codigoCliente) throws Exception {

        Optional<Cliente> optionalCliente = clienteRepository.findById(codigoCliente);
        Cliente cliente = optionalCliente.get();
        cliente.setActivo(Constante.COD_INACTIVO);
        clienteRepository.save(cliente);
    }
}
