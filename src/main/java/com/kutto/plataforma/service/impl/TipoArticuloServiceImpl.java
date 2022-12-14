package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.enums.EnumErrores;
import com.kutto.plataforma.exception.UnprocessableEntityException;
import com.kutto.plataforma.model.Articulo;
import com.kutto.plataforma.model.TipoArticulo;
import com.kutto.plataforma.repository.ArticuloRepository;
import com.kutto.plataforma.repository.TipoArticuloRepository;
import com.kutto.plataforma.request.RequestGuardarTipoArticulo;
import com.kutto.plataforma.service.ParametricaService;
import com.kutto.plataforma.service.TipoArticuloService;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoArticuloServiceImpl implements TipoArticuloService {

    private static final Logger logger = LogManager.getLogger(TipoArticuloServiceImpl.class);

    @Autowired
    private TipoArticuloRepository tipoArticuloRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParametricaService parametricaService;

    @Override
    public List<TipoArticuloDto> listarTipoArticulo() throws Exception {

        List<TipoArticulo> listTipoArticulo = tipoArticuloRepository.findByActivoOrderByDescripcionAsc(Constante.COD_ACTIVO);

        List<TipoArticuloDto> listTipoArticuloDto = listTipoArticulo.stream().map(tipoArticulo -> modelMapper.map(tipoArticulo, TipoArticuloDto.class)).collect(Collectors.toList());

        return listTipoArticuloDto;

    }

    @Override
    public TipoArticuloDto buscarTipoArticulo(String codigoTipoArticulo) throws Exception {

        Optional<TipoArticulo> optionalTipoArticulo = tipoArticuloRepository.findById(codigoTipoArticulo);

        if(optionalTipoArticulo.isPresent()) {
            return modelMapper.map(optionalTipoArticulo.get(), TipoArticuloDto.class);
        } else {
            return new TipoArticuloDto();
        }
    }

    @Override
    public void eliminarTipoArticulo(String codigoTipoArticulo) throws Exception {

        List<Articulo> listArticulo = articuloRepository.findByTipoArticulo_CodigoTipoArticuloAndActivo(codigoTipoArticulo, Constante.COD_ACTIVO);

        if(!StringUtil.isEmpty(listArticulo)){

            throw new UnprocessableEntityException(EnumErrores.ERROR_422004.getCodigo(),
                    EnumErrores.getMensaje(EnumErrores.ERROR_422004.getCodigo()));
        }

        Optional<TipoArticulo> optionalTipoArticulo = tipoArticuloRepository.findById(codigoTipoArticulo);
        TipoArticulo tipoArticulo = optionalTipoArticulo.get();
        tipoArticulo.setActivo(Constante.COD_INACTIVO);
        tipoArticuloRepository.save(tipoArticulo);
    }

    @Override
    public TipoArticuloDto guardarTipoArticulo(RequestGuardarTipoArticulo requestGuardarTipoArticulo) throws Exception {

        TipoArticulo tipoArticulo = new TipoArticulo();

        if(StringUtil.isEmpty(requestGuardarTipoArticulo.getCodigoTipoArticulo())) {
            tipoArticulo.setCodigoTipoArticulo(parametricaService.obtenerCodigoCorrelativoTabla(Constante.PREFIJO_TIPO_ARTICULO, 3));
            tipoArticulo.setUsuarioRegistro(Constante.USUARIO_ADMIN);
            tipoArticulo.setFechaRegistro(Instant.now());
        } else {
            tipoArticulo = tipoArticuloRepository.findById(requestGuardarTipoArticulo.getCodigoTipoArticulo()).get();
            tipoArticulo.setUsuarioModificacion(Constante.USUARIO_ADMIN);
            tipoArticulo.setFechaModificacion(Instant.now());
        }

        tipoArticulo.setDescripcion(requestGuardarTipoArticulo.getDescripcion());
        tipoArticulo.setActivo(Constante.COD_ACTIVO);

        tipoArticuloRepository.save(tipoArticulo);

        TipoArticuloDto tipoArticuloDto = new TipoArticuloDto();
        modelMapper.map (tipoArticulo, tipoArticuloDto);

        return tipoArticuloDto;
    }
}
