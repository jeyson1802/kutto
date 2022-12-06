package com.kutto.plataforma.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;

public class CitaDisponibleDto implements Serializable  {
    private String codigoCitaDisponible;

    private Date fechaReserva;

    private Time horaReserva;

    private Integer disponible;
    private Integer activo;

    private String usuarioRegistro;

    private Instant fechaRegistro;

    private String usuarioModificacion;

    private Instant fechaModificacion;

    public CitaDisponibleDto() {
    }

    public String getCodigoCitaDisponible() {
        return codigoCitaDisponible;
    }

    public void setCodigoCitaDisponible(String codigoCitaDisponible) {
        this.codigoCitaDisponible = codigoCitaDisponible;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Time getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(Time horaReserva) {
        this.horaReserva = horaReserva;
    }

    public Integer getDisponible() {
        return disponible;
    }

    public void setDisponible(Integer disponible) {
        this.disponible = disponible;
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
        return "CitaDisponible{" +
                "codigoCitaDisponible='" + codigoCitaDisponible + '\'' +
                ", fechaReserva=" + fechaReserva +
                ", horaReserva=" + horaReserva +
                ", disponible=" + disponible +
                ", activo=" + activo +
                ", usuarioRegistro='" + usuarioRegistro + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}