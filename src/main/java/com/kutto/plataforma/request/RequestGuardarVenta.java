package com.kutto.plataforma.request;

import com.kutto.plataforma.model.Cliente;
import com.kutto.plataforma.model.DetalleComprobante;
import com.kutto.plataforma.model.TipoComprobante;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RequestGuardarVenta implements Serializable {

    private String codigoTipoComprobante;

    private String codigoSerie;

    private String fechaEmision;

    private String fechaVencimiento;

    private String codigoCliente;

    private String glosa;

    private BigDecimal subtotal;

    private BigDecimal igv;

    private BigDecimal total;

    private List<RequestGuardarDetalleVenta> detalleComprobante;

    public RequestGuardarVenta() {
    }

    public String getCodigoTipoComprobante() {
        return codigoTipoComprobante;
    }

    public void setCodigoTipoComprobante(String codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
    }

    public String getCodigoSerie() {
        return codigoSerie;
    }

    public void setCodigoSerie(String codigoSerie) {
        this.codigoSerie = codigoSerie;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<RequestGuardarDetalleVenta> getDetalleComprobante() {
        return detalleComprobante;
    }

    public void setDetalleComprobante(List<RequestGuardarDetalleVenta> detalleComprobante) {
        this.detalleComprobante = detalleComprobante;
    }

    @Override
    public String toString() {
        return "RequestGuardarVenta{" +
                "codigoTipoComprobante='" + codigoTipoComprobante + '\'' +
                ", codigoSerie='" + codigoSerie + '\'' +
                ", fechaEmision='" + fechaEmision + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", codigoCliente='" + codigoCliente + '\'' +
                ", glosa='" + glosa + '\'' +
                ", subtotal=" + subtotal +
                ", igv=" + igv +
                ", total=" + total +
                ", detalleComprobante=" + detalleComprobante +
                '}';
    }
}
