package com.kutto.plataforma.request;

import com.kutto.plataforma.dto.TipoDocumentoDto;

public class RequestGuardarCliente {

    private String codigoCliente;

    private String codigoTipoPersona;

    private String codigoTipoDocumento;

    private String documento;

    private String nombres;

    private String correo;

    private String direccion;


    public RequestGuardarCliente() {
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoTipoPersona() {
        return codigoTipoPersona;
    }

    public void setCodigoTipoPersona(String codigoTipoPersona) {
        this.codigoTipoPersona = codigoTipoPersona;
    }

    public String getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(String codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "RequestGuardarCliente{" +
                "codigoCliente='" + codigoCliente + '\'' +
                ", codigoTipoPersona='" + codigoTipoPersona + '\'' +
                ", codigoTipoDocumento='" + codigoTipoDocumento + '\'' +
                ", documento='" + documento + '\'' +
                ", nombres='" + nombres + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}