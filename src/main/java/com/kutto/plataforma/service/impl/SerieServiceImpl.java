package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.SerieDto;
import com.kutto.plataforma.model.Serie;
import com.kutto.plataforma.repository.SerieRepository;
import com.kutto.plataforma.service.SerieService;
import com.kutto.plataforma.util.Constante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieServiceImpl implements SerieService {

    private static final Logger logger = LogManager.getLogger(SerieServiceImpl.class);

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SerieDto> listarSeriesPorTipoComprobante(String codigoTipoComprobante) throws Exception {

        List<Serie> listSerie = serieRepository.findByTipoComprobante_CodigoTipoComprobanteAndActivo(codigoTipoComprobante, Constante.COD_ACTIVO);

        List<SerieDto> listSerieDto = listSerie.stream().map(serie -> modelMapper.map(serie, SerieDto.class)).collect(Collectors.toList());

        return  listSerieDto;
    }

}
