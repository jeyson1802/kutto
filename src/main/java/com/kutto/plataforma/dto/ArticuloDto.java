package com.kutto.plataforma.dto;

import com.kutto.plataforma.model.TipoArticulo;
import com.kutto.plataforma.util.ImageUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;

public class ArticuloDto implements Serializable {

    private String codigoArticulo;

    private String codigoEstandar;

    private String titulo;

    private String descripcionCorta;

    private String descripcionLarga;

    private TipoArticuloDto tipoArticulo;

    private String observaciones;

    private BigDecimal precioUnitario;

    private Integer stock;

    private byte[] imagen;

    private Integer activo;

    private String usuarioRegistro;

    private Instant fechaRegistro;

    private String usuarioModificacion;

    private Instant fechaModificacion;

    private String imagenString;

    public ArticuloDto() {
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

    public TipoArticuloDto getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(TipoArticuloDto tipoArticulo) {
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

    public String getImagenString() {
        return new ImageUtil().getImgData(this.imagen);
    }

    @Override
    public String toString() {
        return "ArticuloDto{" +
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
