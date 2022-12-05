package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Cargo;
import com.kutto.plataforma.model.Fuente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FuenteRepository extends CrudRepository<Fuente, Integer> {

    List<Fuente> findByEstadoOrderByIdAsc(String estado) throws Exception;
}