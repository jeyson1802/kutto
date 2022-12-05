package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Cargo;
import com.kutto.plataforma.model.Pais;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CargoRepository extends CrudRepository<Cargo, Integer> {

    List<Cargo> findByEstadoOrderByIdAsc(String estado) throws Exception;
}