package com.kutto.plataforma.dto;

import com.kutto.plataforma.model.Articulo;
import com.kutto.plataforma.model.Comprobante;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

public class DetalleComprobanteDto {
    private String codigoComprobante;

    private ArticuloDto articulo;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal precioTotal;

    private Integer activo;

    private String usuarioRegistro;

    private Instant fechaRegistro;

    private String usuarioModificacion;

    private Instant fechaModificacion;

    public DetalleComprobanteDto() {
    }

    public String getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(String codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public ArticuloDto getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloDto articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Instant getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Instant fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Instant getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Instant fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public String toString() {
        return "DetalleComprobanteDto{" +
                "codigoComprobante='" + codigoComprobante + '\'' +
                ", articulo=" + articulo +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", precioTotal=" + precioTotal +
                ", activo=" + activo +
                ", usuarioRegistro='" + usuarioRegistro + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}