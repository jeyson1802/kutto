package com.kutto.plataforma.request;

import com.kutto.plataforma.model.Articulo;
import com.kutto.plataforma.model.DetalleComprobante;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class RequestGuardarDetalleVenta implements Serializable {

    private String codigoArticulo;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal precioTotal;

    public RequestGuardarDetalleVenta() {
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "RequestGuardarDetalleVenta{" +
                "codigoArticulo='" + codigoArticulo + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", precioTotal=" + precioTotal +
                '}';
    }
}
