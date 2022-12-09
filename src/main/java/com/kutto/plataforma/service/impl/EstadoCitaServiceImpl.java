package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.EstadoCitaDto;
import com.kutto.plataforma.model.EstadoCita;
import com.kutto.plataforma.repository.EstadoCitaRepository;
import com.kutto.plataforma.service.EstadoCitaService;
import com.kutto.plataforma.util.Constante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoCitaServiceImpl implements EstadoCitaService {

    private static final Logger logger = LogManager.getLogger(EstadoCitaServiceImpl.class);

    @Autowired
    private EstadoCitaRepository estadoCitaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EstadoCitaDto> listarEstadoCita() throws Exception {

        List<EstadoCita> listEstadoCita = estadoCitaRepository.findByActivoOrderByDescripcionAsc(Constante.COD_ACTIVO);

        List<EstadoCitaDto> listEstadoCitaDto = listEstadoCita.stream().map(tipoArticulo -> modelMapper.map(tipoArticulo, EstadoCitaDto.class)).collect(Collectors.toList());

        return listEstadoCitaDto;

    }
}
