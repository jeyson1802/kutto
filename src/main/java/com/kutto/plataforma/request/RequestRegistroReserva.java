package com.kutto.plataforma.request;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class RequestRegistroReserva implements Serializable {

    private String nombres;
    private String correo;
    private String documento;

    private String codigoCitaDisponible;

    public RequestRegistroReserva() {
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCodigoCitaDisponible() {
        return codigoCitaDisponible;
    }

    public void setCodigoCitaDisponible(String codigoCitaDisponible) {
        this.codigoCitaDisponible = codigoCitaDisponible;
    }

    @Override
    public String toString() {
        return "RequestRegistroReserva{" +
                "nombres='" + nombres + '\'' +
                ", correo='" + correo + '\'' +
                ", documento='" + documento + '\'' +
                ", codigoCitaDisponible='" + codigoCitaDisponible + '\'' +
                '}';
    }
}
