package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.PaisDto;
import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.model.Pais;
import com.kutto.plataforma.model.TipoArticulo;
import com.kutto.plataforma.repository.PaisRepository;
import com.kutto.plataforma.repository.TipoArticuloRepository;
import com.kutto.plataforma.service.PaisService;
import com.kutto.plataforma.service.TipoArticuloService;
import com.kutto.plataforma.util.Constante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoArticuloServiceImpl implements TipoArticuloService {

    private static final Logger logger = LogManager.getLogger(TipoArticuloServiceImpl.class);

    @Autowired
    private TipoArticuloRepository tipoArticuloRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoArticuloDto> listarTipoArticulo() throws Exception {

        List<TipoArticulo> listTipoArticulo = tipoArticuloRepository.findByActivoOrderByDescripcionAsc(Constante.COD_ACTIVO);

        List<TipoArticuloDto> listTipoArticuloDto = listTipoArticulo.stream().map(tipoArticulo -> modelMapper.map(tipoArticulo, TipoArticuloDto.class)).collect(Collectors.toList());

        return listTipoArticuloDto;

    }
}
