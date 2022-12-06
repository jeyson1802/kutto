package com.kutto.plataforma.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

public class TipoArticuloDto implements Serializable {

    private String codigoTipoArticulo;

    private String descripcion;

    private Integer activo;

    private String usuarioRegistro;

    private Instant fechaRegistro;

    private String usuarioModificacion;

    private Instant fechaModificacion;

    public TipoArticuloDto() {
    }

    public String getCodigoTipoArticulo() {
        return codigoTipoArticulo;
    }

    public void setCodigoTipoArticulo(String codigoTipoArticulo) {
        this.codigoTipoArticulo = codigoTipoArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        return "TipoArticuloDto{" +
                "codigoTipoArticulo='" + codigoTipoArticulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", activo=" + activo +
                ", usuarioRegistro='" + usuarioRegistro + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}
