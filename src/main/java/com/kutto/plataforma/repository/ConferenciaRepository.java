package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Conferencia;
import com.kutto.plataforma.model.Pais;
import org.springframework.data.repository.CrudRepository;

public interface ConferenciaRepository extends CrudRepository<Conferencia, Integer> {

    Conferencia findByPaisIdAndEstado(Integer idPais, String estado) throws Exception;
}