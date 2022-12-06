package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface CitaRepository extends CrudRepository<Cita, String> {

    @Query("select a from Cita a where (a.codigoCita =?1 or a.documento =?1) and a.activo = 1")
    List<Cita> buscarPorCodigoCitaOrDocumento(String busqueda) throws Exception;

}