package com.kutto.plataforma.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

public class RequestGuardarTipoArticulo implements Serializable {

    private String codigoTipoArticulo;

    private String descripcion;


    public RequestGuardarTipoArticulo() {
    }

    public String getCodigoTipoArticulo() {
        return codigoTipoArticulo;
    }

    public void setCodigoTipoArticulo(String codigoTipoArticulo) {
        this.codigoTipoArticulo = codigoTipoArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "RequestGuardarTipoArticulo{" +
                "codigoTipoArticulo='" + codigoTipoArticulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
