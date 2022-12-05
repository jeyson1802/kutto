package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.FuenteDto;
import com.kutto.plataforma.model.Fuente;
import com.kutto.plataforma.repository.FuenteRepository;
import com.kutto.plataforma.service.FuenteService;
import com.kutto.plataforma.util.Constante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuenteServiceImpl implements FuenteService {

    private static final Logger logger = LogManager.getLogger(FuenteServiceImpl.class);

    @Autowired
    private FuenteRepository cargoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FuenteDto> listarFuentes() throws Exception {

        List<Fuente> listFuente = cargoRepository.findByEstadoOrderByIdAsc(Constante.COD_ESTADO_ACTIVO);

        logger.info("listFuente ===> " + listFuente.toString());

        List<FuenteDto> listFuenteDto = listFuente.stream().map(pais -> modelMapper.map(pais, FuenteDto.class)).collect(Collectors.toList());

        logger.info("listFuenteDto ===> " + listFuenteDto.toString());

        return listFuenteDto;

    }
}
