package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.ArticuloDto;
import com.kutto.plataforma.dto.CitaDto;
import com.kutto.plataforma.dto.PaginacionWrapperDto;
import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.enums.EnumErrores;
import com.kutto.plataforma.exception.UnprocessableEntityException;
import com.kutto.plataforma.model.Articulo;
import com.kutto.plataforma.model.Cita;
import com.kutto.plataforma.model.TipoArticulo;
import com.kutto.plataforma.repository.ArticuloRepository;
import com.kutto.plataforma.repository.TipoArticuloRepository;
import com.kutto.plataforma.request.RequestGuardarArticulo;
import com.kutto.plataforma.service.ArticuloService;
import com.kutto.plataforma.service.ParametricaService;
import com.kutto.plataforma.service.TipoArticuloService;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.time.Instant;
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

    @Autowired
    private ParametricaService parametricaService;

    @Autowired
    private TipoArticuloRepository tipoArticuloRepository;

    @Override
    public PaginacionWrapperDto<ArticuloDto> buscarArticulos(String codigoTipoArticulo, Pageable pageable) throws Exception {

        Page<Articulo> pageArticulo;

        if(StringUtil.isEmpty(codigoTipoArticulo)) {
            pageArticulo = articuloRepository.findByActivoOrderByCodigoArticuloDesc(Constante.COD_ACTIVO, pageable);
        } else {
            pageArticulo = articuloRepository.findByTipoArticuloCodigoTipoArticuloAndActivoOrderByCodigoArticuloDesc(codigoTipoArticulo, Constante.COD_ACTIVO, pageable);
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

    @Override
    public void eliminarArticulo(String codigoArticulo) throws Exception {

        Optional<Articulo> optionalArticulo = articuloRepository.findById(codigoArticulo);
        Articulo articulo = optionalArticulo.get();
        articulo.setActivo(Constante.COD_INACTIVO);
        articuloRepository.save(articulo);
    }

    @Override
    public List<ArticuloDto> listarArticulos() throws Exception {

        List<Articulo> listArticulo = articuloRepository.findByActivoOrderByCodigoArticuloDesc(Constante.COD_ACTIVO);

        List<ArticuloDto> listArticuloDto = listArticulo.stream().map(articulo -> modelMapper.map(articulo, ArticuloDto.class)).collect(Collectors.toList());

        return  listArticuloDto;
    }

    @Override
    public void guardarArticulo(RequestGuardarArticulo requestGuardarArticulo) throws Exception {

        Articulo articulo = new Articulo();

        if(StringUtil.isEmpty(requestGuardarArticulo.getCodigoArticulo())) {
            articulo.setCodigoArticulo(parametricaService.obtenerCodigoCorrelativoTabla(Constante.PREFIJO_ARTICULO, 3));
            articulo.setUsuarioRegistro(Constante.USUARIO_ADMIN);
            articulo.setFechaRegistro(Instant.now());
        } else {
            articulo = articuloRepository.findById(requestGuardarArticulo.getCodigoArticulo()).get();
            articulo.setUsuarioModificacion(Constante.USUARIO_ADMIN);
            articulo.setFechaModificacion(Instant.now());
        }

        if(!StringUtil.equiv(requestGuardarArticulo.getCodigoEstandar(), articulo.getCodigoEstandar())) {

            List<Articulo> listArticulo = articuloRepository.findByCodigoEstandarAndActivo(requestGuardarArticulo.getCodigoEstandar(), Constante.COD_ACTIVO);

            if(!StringUtil.isEmpty(listArticulo)) {

                throw new UnprocessableEntityException(EnumErrores.ERROR_422005.getCodigo(),
                        EnumErrores.getMensaje(EnumErrores.ERROR_422005.getCodigo()));
            }
        }

        Optional<TipoArticulo> optionalTipoArticulo = tipoArticuloRepository.findById(requestGuardarArticulo.getCodigoTipoArticulo());
        articulo.setTipoArticulo(optionalTipoArticulo.get());

        articulo.setCodigoEstandar(requestGuardarArticulo.getCodigoEstandar());
        articulo.setTitulo(requestGuardarArticulo.getTitulo());
        articulo.setDescripcionCorta(requestGuardarArticulo.getDescripcionCorta());
        articulo.setDescripcionLarga(requestGuardarArticulo.getDescripcionLarga());
        articulo.setObservaciones(requestGuardarArticulo.getObservaciones());
        articulo.setPrecioUnitario(requestGuardarArticulo.getPrecioUnitario());
        articulo.setStock(requestGuardarArticulo.getStock());
        articulo.setImagen(requestGuardarArticulo.getImagen());
        articulo.setActivo(Constante.COD_ACTIVO);

        articuloRepository.save(articulo);

    }

    @Override
    public List<ArticuloDto> listarArticulosPorTituloConStock(String titulo) throws Exception {

        List<Articulo> listArticulo = articuloRepository.findByTituloContainsAndStockGreaterThanAndActivo(titulo, 0, Constante.COD_ACTIVO);

        List<ArticuloDto> listArticuloDto = listArticulo.stream().map(articulo -> modelMapper.map(articulo, ArticuloDto.class)).collect(Collectors.toList());

        return  listArticuloDto;
    }

}
