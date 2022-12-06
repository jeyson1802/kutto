package com.kutto.plataforma.dto;

import com.kutto.plataforma.model.EstadoCita;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;

public class CitaDto implements Serializable  {

    private String codigoCita;

    private EstadoCitaDto estadoCita;

    private Date fecha;

    private Time horario;

    private String nombres;

    private String documento;

    private String correo;

    private String observaciones;
    private Integer activo;

    private String usuarioRegistro;

    private Instant fechaRegistro;

    private String usuarioModificacion;

    private Instant fechaModificacion;

    public CitaDto() {
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public EstadoCitaDto getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCitaDto estadoCita) {
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
        return "CitaDto{" +
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