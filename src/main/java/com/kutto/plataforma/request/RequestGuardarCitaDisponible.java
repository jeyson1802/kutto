package com.kutto.plataforma.request;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class RequestGuardarCitaDisponible implements Serializable {

    private String codigoCitaDisponible;

    private String fechaReserva;

    private String horaReserva;

    private Integer disponible;


    public RequestGuardarCitaDisponible() {
    }

    public String getCodigoCitaDisponible() {
        return codigoCitaDisponible;
    }

    public void setCodigoCitaDisponible(String codigoCitaDisponible) {
        this.codigoCitaDisponible = codigoCitaDisponible;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    public Integer getDisponible() {
        return disponible;
    }

    public void setDisponible(Integer disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "RequestGuardarCitaDisponible{" +
                "codigoCitaDisponible='" + codigoCitaDisponible + '\'' +
                ", fechaReserva=" + fechaReserva +
                ", horaReserva=" + horaReserva +
                ", disponible=" + disponible +
                '}';
    }
}
