package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Cita;
import com.kutto.plataforma.model.EstadoCita;
import org.springframework.data.repository.CrudRepository;

public interface EstadoCitaRepository extends CrudRepository<EstadoCita, String> {

}