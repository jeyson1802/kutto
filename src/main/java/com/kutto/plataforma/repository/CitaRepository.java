package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface CitaRepository extends CrudRepository<Cita, String> {
    List<Cita> findByFechaAndHorarioAndActivo(Date fecha, Time horario, Integer activo);

    List<Cita> findByActivo(Integer activo);

    @Query("select a from Cita a where (a.codigoCita =?1 or a.documento =?1) and a.activo = 1")
    List<Cita> buscarPorCodigoCitaOrDocumento(String busqueda) throws Exception;

}