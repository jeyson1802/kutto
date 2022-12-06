package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.ArticuloDto;
import com.kutto.plataforma.dto.PaginacionWrapperDto;
import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.model.Articulo;
import com.kutto.plataforma.model.TipoArticulo;
import com.kutto.plataforma.repository.ArticuloRepository;
import com.kutto.plataforma.repository.TipoArticuloRepository;
import com.kutto.plataforma.service.ArticuloService;
import com.kutto.plataforma.service.TipoArticuloService;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    private static final Logger logger = LogManager.getLogger(ArticuloServiceImpl.class);

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaginacionWrapperDto<ArticuloDto> buscarArticulos(String codigoTipoArticulo, Pageable pageable) throws Exception {

        Page<Articulo> pageArticulo;

        if(StringUtil.isEmpty(codigoTipoArticulo)) {
            pageArticulo = articuloRepository.findByActivoOrderByPrecioUnitarioAsc(Constante.COD_ACTIVO, pageable);
        } else {
            pageArticulo = articuloRepository.findByTipoArticuloCodigoTipoArticuloAndActivoOrderByPrecioUnitarioAsc(codigoTipoArticulo, Constante.COD_ACTIVO, pageable);
        }

        List<ArticuloDto> listArticuloDto = pageArticulo.stream().map(articulo -> modelMapper.map(articulo, ArticuloDto.class)).collect(Collectors.toList());

        PaginacionWrapperDto<ArticuloDto> paginacionWrapperArticuloDto = new PaginacionWrapperDto<ArticuloDto>(listArticuloDto, pageArticulo.getTotalPages(), pageArticulo.getTotalElements());

        return paginacionWrapperArticuloDto;

    }

    @Override
    public ArticuloDto buscarArticulo(String codigoArticulo) throws Exception {

        Optional<Articulo> optionalArticulo = articuloRepository.findById(codigoArticulo);

        if(optionalArticulo.isPresent()) {
            return modelMapper.map(optionalArticulo.get(), ArticuloDto.class);
        } else {
            return new ArticuloDto();
        }
    }
}
