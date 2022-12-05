package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.PaisDto;
import com.kutto.plataforma.model.Pais;
import com.kutto.plataforma.repository.PaisRepository;
import com.kutto.plataforma.service.PaisService;
import com.kutto.plataforma.util.Constante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaisServiceImpl implements PaisService {

    private static final Logger logger = LogManager.getLogger(PaisServiceImpl.class);

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PaisDto> listarPaises() throws Exception {

        List<Pais> listPais = paisRepository.findByEstadoOrderByDescripcionAsc(Constante.COD_ESTADO_ACTIVO);

        List<PaisDto> listPaisDto = listPais.stream().map(pais -> modelMapper.map(pais, PaisDto.class)).collect(Collectors.toList());

        return listPaisDto;

    }
}
