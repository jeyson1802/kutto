package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Cita;
import com.kutto.plataforma.model.EstadoCita;
import com.kutto.plataforma.model.TipoArticulo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstadoCitaRepository extends CrudRepository<EstadoCita, String> {

    List<EstadoCita> findByActivoOrderByDescripcionAsc(Integer activo) throws Exception;
}