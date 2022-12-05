package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.ConferenciaDto;
import com.kutto.plataforma.model.Conferencia;
import com.kutto.plataforma.model.Pais;
import com.kutto.plataforma.repository.ConferenciaRepository;
import com.kutto.plataforma.repository.PaisRepository;
import com.kutto.plataforma.service.ConferenciaService;
import com.kutto.plataforma.util.Constante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConferenciaServiceImpl implements ConferenciaService {

    private static final Logger logger = LogManager.getLogger(ConferenciaServiceImpl.class);

    @Autowired
    private ConferenciaRepository conferenciaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ConferenciaDto obtenerConferenciaPorPais(Integer idPais) throws Exception {

        Conferencia conferencia = conferenciaRepository.findByPaisIdAndEstado(idPais, Constante.COD_ESTADO_ACTIVO);

        logger.info("conferencia ===> " + conferencia.toString());

        ConferenciaDto conferenciaDto = modelMapper.map(conferencia, ConferenciaDto.class);

        logger.info("conferenciaDto ===> " + conferenciaDto.toString());

        return conferenciaDto;
    }
}
