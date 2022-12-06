package com.kutto.plataforma.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class PaginacionWrapperDto<T> implements Serializable {

    private List<T> registros;

    private int totalPaginas;

    private long totalElementos;

    public PaginacionWrapperDto(List<T> registros, int totalPaginas, long totalElementos) {
        this.registros = registros;
        this.totalPaginas = totalPaginas;
        this.totalElementos = totalElementos;
    }

    public List<T> getRegistros() {
        return registros;
    }

    public void setRegistros(List<T> registros) {
        this.registros = registros;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }
}
