package com.kutto.plataforma.request;

import com.kutto.plataforma.dto.TipoArticuloDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;

public class RequestGuardarArticulo implements Serializable {

    private String codigoArticulo;

    private String codigoEstandar;

    private String titulo;

    private String descripcionCorta;

    private String descripcionLarga;

    private String codigoTipoArticulo;

    private String observaciones;

    private BigDecimal precioUnitario;

    private Integer stock;

    private byte[] imagen;

    public RequestGuardarArticulo() {
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

    public String getCodigoTipoArticulo() {
        return codigoTipoArticulo;
    }

    public void setCodigoTipoArticulo(String codigoTipoArticulo) {
        this.codigoTipoArticulo = codigoTipoArticulo;
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

    @Override
    public String toString() {
        return "RequestGuardarArticulo{" +
                "codigoArticulo='" + codigoArticulo + '\'' +
                ", codigoEstandar='" + codigoEstandar + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcionCorta='" + descripcionCorta + '\'' +
                ", descripcionLarga='" + descripcionLarga + '\'' +
                ", codigoTipoArticulo='" + codigoTipoArticulo + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", stock=" + stock +
                ", imagen=" + Arrays.toString(imagen) +
                '}';
    }
}
