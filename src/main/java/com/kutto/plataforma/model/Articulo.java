package com.kutto.plataforma.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

@Entity
@Table(name = "tbl_articulo")
public class Articulo {
    @Id
    @Column(name = "V_COD_ARTICULO", nullable = false)
    private String codigoArticulo;

    @Column(name = "V_COD_ESTANDAR", nullable = false, length = 50)
    private String codigoEstandar;

    @Column(name = "V_TITULO", nullable = false, length = 100)
    private String titulo;

    @Column(name = "V_DESCRIPCION_CORTA", nullable = false, length = 100)
    private String descripcionCorta;

    @Column(name = "V_DESCRIPCION_LARGA", nullable = false, length = 250)
    private String descripcionLarga;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "V_COD_TIPO", nullable = false)
    private TipoArticulo tipoArticulo;

    @Column(name = "V_OBSERVACIONES", nullable = false, length = 250)
    private String observaciones;

    @Column(name = "N_PRECIO_UNITARIO", nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "N_STOCK", nullable = false)
    private Integer stock;

    @Column(name = "B_IMAGEN", nullable = true)
    private byte[] imagen;

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

    public Articulo() {
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getCodigoEstandar() {
        return codigoEstandar;
    }

    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public TipoArticulo getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(TipoArticulo tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
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
        return "Articulo{" +
                "codigoArticulo='" + codigoArticulo + '\'' +
                ", codigoEstandar='" + codigoEstandar + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcionCorta='" + descripcionCorta + '\'' +
                ", descripcionLarga='" + descripcionLarga + '\'' +
                ", tipoArticulo=" + tipoArticulo +
                ", observaciones='" + observaciones + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", stock=" + stock +
                ", imagen=" + Arrays.toString(imagen) +
                ", activo=" + activo +
                ", usuarioRegistro='" + usuarioRegistro + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}