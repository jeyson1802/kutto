package com.kutto.plataforma.model;

import javax.persistence.*;
import java.util.Date;
import java.sql.Time;
import java.time.Instant;

@Entity
@Table(name = "tbl_cita")
public class Cita {
    @Id
    @Column(name = "V_COD_CITA", nullable = false)
    private String codigoCita;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "V_COD_ESTADO_CITA", nullable = false)
    private EstadoCita estadoCita;

    @Column(name = "D_FEC_RESERVA", nullable = false)
    private Date fecha;

    @Column(name = "T_HORA_RESERVA", nullable = false)
    private Time horario;

    @Column(name = "V_NOMBRES", nullable = false, length = 250)
    private String nombres;

    @Column(name = "V_DOCUMENTO_IDENTIDAD", nullable = false, length = 250)
    private String documento;

    @Column(name = "V_CORREO", nullable = false, length = 250)
    private String correo;

    @Column(name = "V_OBSERVACIONES", length = 250)
    private String observaciones;
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

    public Cita() {
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        return "Cita{" +
                "codigoCita='" + codigoCita + '\'' +
                ", estadoCita=" + estadoCita +
                ", fecha=" + fecha +
                ", horario=" + horario +
                ", nombres='" + nombres + '\'' +
                ", documento='" + documento + '\'' +
                ", correo='" + correo + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", activo=" + activo +
                ", usuarioRegistro='" + usuarioRegistro + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}