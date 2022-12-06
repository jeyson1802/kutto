package com.kutto.plataforma.model;

import javax.persistence.*;
import java.util.Date;
import java.sql.Time;
import java.time.Instant;

@Entity
@Table(name = "tbl_cita_disponible")
public class CitaDisponible {
    @Id
    @Column(name = "V_COD_CITA_DISPONIBLE", nullable = false)
    private String codigoCitaDisponible;

    @Column(name = "D_FEC_RESERVA", nullable = false)
    private Date fechaReserva;

    @Column(name = "T_HORA_RESERVA", nullable = false)
    private Time horaReserva;

    @Column(name = "N_DISPONIBLE", nullable = false, length = 1)
    private Integer disponible;
    @Column(name = "N_ACTIVO", nullable = false, length = 1)
    private Integer activo;

    @Column(name = "V_COD_USU_REGISTRA", nullable = false, length = 20)
    private String usuarioRegistro;

    @Column(name = "D_FEC_REGISTRO", nullable = false)
    private Instant fechaRegistro;

    @Column(name = "V_COD_USU_MODIFICA", length = 20)
    private String usuarioModificacion;

    @Column(name = "D_FEC_MODIFICACION")
    private Instant fechaModificacion;

    public CitaDisponible() {
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