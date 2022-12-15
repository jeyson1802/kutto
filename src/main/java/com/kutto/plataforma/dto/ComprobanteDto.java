package com.kutto.plataforma.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kutto.plataforma.model.Cliente;
import com.kutto.plataforma.model.DetalleComprobante;
import com.kutto.plataforma.model.TipoComprobante;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class ComprobanteDto {
    private String codigoComprobante;

    private TipoComprobanteDto tipoComprobante;

    private String codigoSerie;

    private Integer numero;

    private Date fechaEmision;

    private Date fechaVencimiento;

    private ClienteDto cliente;

    private String glosa;

    private BigDecimal subtotal;

    private BigDecimal igv;

    private BigDecimal total;

    private List<DetalleComprobanteDto> detalleComprobante;

    private Integer activo;

    private String usuarioRegistro;

    private Instant fechaRegistro;

    private String usuarioModificacion;

    private Instant fechaModificacion;

    public ComprobanteDto() {
    }

    public String getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(String codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public TipoComprobanteDto getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobanteDto tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getCodigoSerie() {
        return codigoSerie;
    }

    public void setCodigoSerie(String codigoSerie) {
        this.codigoSerie = codigoSerie;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<DetalleComprobanteDto> getDetalleComprobante() {
        return detalleComprobante;
    }

    public void setDetalleComprobante(List<DetalleComprobanteDto> detalleComprobante) {
        this.detalleComprobante = detalleComprobante;
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
        return "ComprobanteDto{" +
                "codigoComprobante='" + codigoComprobante + '\'' +
                ", tipoComprobante=" + tipoComprobante +
                ", codigoSerie='" + codigoSerie + '\'' +
                ", numero=" + numero +
                ", fechaEmision=" + fechaEmision +
                ", fechaVencimiento=" + fechaVencimiento +
                ", cliente=" + cliente +
                ", glosa='" + glosa + '\'' +
                ", subtotal=" + subtotal +
                ", igv=" + igv +
                ", total=" + total +
                ", detalleComprobante=" + detalleComprobante +
                ", activo=" + activo +
                ", usuarioRegistro='" + usuarioRegistro + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}