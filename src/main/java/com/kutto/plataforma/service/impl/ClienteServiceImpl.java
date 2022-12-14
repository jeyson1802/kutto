package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.ArticuloDto;
import com.kutto.plataforma.dto.ClienteDto;
import com.kutto.plataforma.dto.PaginacionWrapperDto;
import com.kutto.plataforma.enums.EnumErrores;
import com.kutto.plataforma.exception.UnprocessableEntityException;
import com.kutto.plataforma.model.Articulo;
import com.kutto.plataforma.model.Cliente;
import com.kutto.plataforma.model.TipoArticulo;
import com.kutto.plataforma.repository.ArticuloRepository;
import com.kutto.plataforma.repository.ClienteRepository;
import com.kutto.plataforma.repository.TipoArticuloRepository;
import com.kutto.plataforma.request.RequestGuardarArticulo;
import com.kutto.plataforma.service.ArticuloService;
import com.kutto.plataforma.service.ClienteService;
import com.kutto.plataforma.service.ParametricaService;
import com.kutto.plataforma.util.Constante;
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

}
