package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.ComprobanteDto;
import com.kutto.plataforma.model.*;
import com.kutto.plataforma.repository.*;
import com.kutto.plataforma.request.RequestGuardarDetalleVenta;
import com.kutto.plataforma.request.RequestGuardarVenta;
import com.kutto.plataforma.service.ComprobanteService;
import com.kutto.plataforma.service.ParametricaService;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteServiceImpl implements ComprobanteService {

    private static final Logger logger = LogManager.getLogger(ComprobanteServiceImpl.class);

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParametricaService parametricaService;

    @Autowired
    private TipoComprobanteRepository tipoComprobanteRepository;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private DetalleComprobanteRepository detalleComprobanteRepository;

    @Override
    public ComprobanteDto guardarVenta(RequestGuardarVenta requestGuardarVenta) throws Exception {

        Comprobante comprobante = new Comprobante();

        comprobante.setCodigoComprobante(parametricaService.obtenerCodigoCorrelativoTabla(Constante.PREFIJO_COMPROBANTE, 5));
        Optional<TipoComprobante> optionalTipoComprobante = tipoComprobanteRepository.findById(requestGuardarVenta.getCodigoTipoComprobante());
        comprobante.setTipoComprobante(optionalTipoComprobante.get());

        comprobante.setCodigoSerie(requestGuardarVenta.getCodigoSerie());

        Optional<Serie> optionalSerie = serieRepository.findById(requestGuardarVenta.getCodigoSerie());
        Serie serie = optionalSerie.get();
        comprobante.setNumero(serie.getValor());
        serie.setValor(serie.getValor() + 1);

        comprobante.setFechaEmision(DateUtil.stringToDateYYYYMMDD(requestGuardarVenta.getFechaEmision()));
        comprobante.setFechaVencimiento(DateUtil.stringToDateYYYYMMDD(requestGuardarVenta.getFechaVencimiento()));

        Optional<Cliente> optionalCliente = clienteRepository.findById(requestGuardarVenta.getCodigoCliente());
        comprobante.setCliente(optionalCliente.get());

        comprobante.setGlosa(requestGuardarVenta.getGlosa());

        if(Arrays.asList(Constante.CODIGO_FACTURA).contains(requestGuardarVenta.getCodigoTipoComprobante())){
            comprobante.setSubtotal(requestGuardarVenta.getSubtotal());
            comprobante.setIgv(requestGuardarVenta.getIgv());
        }

        if(Arrays.asList(Constante.CODIGO_BOLETA).contains(requestGuardarVenta.getCodigoTipoComprobante())){
            comprobante.setSubtotal(BigDecimal.ZERO);
            comprobante.setIgv(BigDecimal.ZERO);
        }

        comprobante.setTotal(requestGuardarVenta.getTotal());

        comprobante.setActivo(Constante.COD_ACTIVO);
        comprobante.setUsuarioRegistro(Constante.USUARIO_ADMIN);
        comprobante.setFechaRegistro(Instant.now());

        Comprobante comprobantePersistido = comprobanteRepository.save(comprobante);
        serieRepository.save(serie);

        for(RequestGuardarDetalleVenta requestGuardarDetalleVenta : requestGuardarVenta.getDetalleComprobante()) {
            DetalleComprobante detalleComprobante = new DetalleComprobante();
            detalleComprobante.setCodigoDetalleComprobante(parametricaService.obtenerCodigoCorrelativoTabla(Constante.PREFIJO_DETALLE_COMPROBANTE, 7));

            detalleComprobante.setComprobante(comprobantePersistido);

            Optional<Articulo> optionalArticulo = articuloRepository.findById(requestGuardarDetalleVenta.getCodigoArticulo());
            Articulo articulo = optionalArticulo.get();
            articulo.setStock(articulo.getStock() - requestGuardarDetalleVenta.getCantidad());
            articulo.setUsuarioModificacion(Constante.USUARIO_ADMIN);
            articulo.setFechaModificacion(Instant.now());
            articuloRepository.save(articulo);
            detalleComprobante.setArticulo(articulo);

            detalleComprobante.setCantidad(requestGuardarDetalleVenta.getCantidad());
            detalleComprobante.setPrecioUnitario(requestGuardarDetalleVenta.getPrecioUnitario());
            detalleComprobante.setPrecioTotal(requestGuardarDetalleVenta.getPrecioTotal());

            detalleComprobante.setActivo(Constante.COD_ACTIVO);
            detalleComprobante.setUsuarioRegistro(Constante.USUARIO_ADMIN);
            detalleComprobante.setFechaRegistro(Instant.now());

            detalleComprobanteRepository.save(detalleComprobante);
        }

        ComprobanteDto comprobanteDto = new ComprobanteDto();
        modelMapper.map (comprobantePersistido, comprobanteDto);

        return comprobanteDto;

    }

}
