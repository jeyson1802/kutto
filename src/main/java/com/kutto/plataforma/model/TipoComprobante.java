package com.kutto.plataforma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "tbl_tipo_comprobante")
public class TipoComprobante {
    @Id
    @Column(name = "V_COD_TIPO_COMPROBANTE", nullable = false)
    private String codigoTipoComprobante;

    @Column(name = "V_DESCRIPCION", nullable = false, length = 150)
    private String descripcion;

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


    public TipoComprobante() {
    }

    public String getCodigoTipoComprobante() {
        return codigoTipoComprobante;
    }

    public void setCodigoTipoComprobante(String codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
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
        return "TipoComprobante{" +
                "codigoTipoComprobante='" + codigoTipoComprobante + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", activo=" + activo +
                ", usuarioRegistro='" + usuarioRegistro + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}