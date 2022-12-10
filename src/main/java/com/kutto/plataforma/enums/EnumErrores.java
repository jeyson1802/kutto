package com.kutto.plataforma.enums;


import org.apache.commons.lang3.StringUtils;

public enum EnumErrores {

    ERROR_422001(422001, "Existe una cita asignada a este horario, antes de eliminar el horario debe eliminar la cita."),
    ERROR_422002(422002, "Existe una cita asignada a este horario, no se puede modificar el horario a menos que elimine la cita."),
    ERROR_422003(422003, "Ya existe un horario con la misma fecha y hora."),
    ERROR_422004(422004, "Existen productos con la categoría seleccionada, antes de eliminar la categoría debe eliminar todos los productos asociados."),
    ERROR_422005(422005, "Ya existe un producto con el mismo código SKU, intentar con uno diferente.");

    private EnumErrores(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCodigo() {
        return code;
    }

    private void setCodigo(int code) {
        this.code = code;
    }

    public String getMensaje() {
        return msg;
    }

    private void setMensaje(String msg) {
        this.msg = msg;
    }

    public static String getMensaje(int code) {
        String msg = StringUtils.EMPTY;

        for (EnumErrores enumHTTP : EnumErrores.values()) {
            if (enumHTTP.code == code) {
                msg = enumHTTP.msg;
                break;
            }
        }

        return msg;
    }

}
