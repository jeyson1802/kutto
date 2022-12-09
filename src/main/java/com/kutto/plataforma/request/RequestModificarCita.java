package com.kutto.plataforma.request;

import java.io.Serializable;

public class RequestModificarCita implements Serializable {

    private String codigoCita;
    private String obervaciones;
    private String codigoEstadoCita;


    public RequestModificarCita() {
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public String getObervaciones() {
        return obervaciones;
    }

    public void setObervaciones(String obervaciones) {
        this.obervaciones = obervaciones;
    }

    public String getCodigoEstadoCita() {
        return codigoEstadoCita;
    }

    public void setCodigoEstadoCita(String codigoEstadoCita) {
        this.codigoEstadoCita = codigoEstadoCita;
    }

    @Override
    public String toString() {
        return "RequestModificarCita{" +
                "codigoCita='" + codigoCita + '\'' +
                ", obervaciones='" + obervaciones + '\'' +
                ", codigoEstadoCita='" + codigoEstadoCita + '\'' +
                '}';
    }
}
