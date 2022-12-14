package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.dto.TipoDocumentoDto;
import com.kutto.plataforma.enums.EnumErrores;
import com.kutto.plataforma.exception.UnprocessableEntityException;
import com.kutto.plataforma.model.Articulo;
import com.kutto.plataforma.model.TipoArticulo;
import com.kutto.plataforma.model.TipoDocumento;
import com.kutto.plataforma.repository.ArticuloRepository;
import com.kutto.plataforma.repository.TipoArticuloRepository;
import com.kutto.plataforma.repository.TipoDocumentoRepository;
import com.kutto.plataforma.request.RequestGuardarTipoArticulo;
import com.kutto.plataforma.service.ParametricaService;
import com.kutto.plataforma.service.TipoArticuloService;
import com.kutto.plataforma.service.TipoDocumentoService;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private static final Logger logger = LogManager.getLogger(TipoDocumentoServiceImpl.class);

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoDocumentoDto> listarTipoDocumentoPorTipoPersona(String codigoTipoPersona) throws Exception {

        List<TipoDocumento> listTipoDocumento = tipoDocumentoRepository.findByActivo(Constante.COD_ACTIVO);

        List<TipoDocumentoDto> listTipoDocumentoDto = listTipoDocumento.stream().map(tipoDocumento -> modelMapper.map(tipoDocumento, TipoDocumentoDto.class)).collect(Collectors.toList());

        List<TipoDocumentoDto> listTipoDocumentoDtoFiltrado =  new ArrayList<>();

        if(Constante.CODIGO_PERSONAL_JURIDICA.equals(codigoTipoPersona)){
            listTipoDocumentoDtoFiltrado = listTipoDocumentoDto.stream().filter(e -> e.getCodigoTipoDocumento().equals(Constante.CODIGO_TIPO_DOCUMENTO_RUC)).collect(Collectors.toList());
        } else {
            listTipoDocumentoDtoFiltrado = listTipoDocumentoDto.stream().filter(e -> !e.getCodigoTipoDocumento().equals(Constante.CODIGO_TIPO_DOCUMENTO_RUC)).collect(Collectors.toList());
        }

        return listTipoDocumentoDtoFiltrado;

    }

}
