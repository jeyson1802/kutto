package com.kutto.plataforma.dto;

import com.kutto.plataforma.model.TipoComprobante;

import javax.persistence.*;
import java.time.Instant;

public class SerieDto {
    private String codigoSerie;

    private Integer valor;

    private TipoComprobanteDto tipoComprobante;

    private Integer activo;

    private String usuarioRegistro;

    private Instant fechaRegistro;

    private String usuarioModificacion;

    private Instant fechaModificacion;

    public SerieDto() {
    }

    public String getCodigoSerie() {
        return codigoSerie;
    }

    public void setCodigoSerie(String codigoSerie) {
        this.codigoSerie = codigoSerie;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public TipoComprobanteDto getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobanteDto tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
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
        return "Serie{" +
                "codigoSerie='" + codigoSerie + '\'' +
                ", valor=" + valor +
                ", tipoComprobante=" + tipoComprobante +
                ", activo=" + activo +
                ", usuarioRegistro='" + usuarioRegistro + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}