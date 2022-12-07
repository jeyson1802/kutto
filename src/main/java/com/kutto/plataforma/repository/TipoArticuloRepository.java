package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.TipoArticulo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TipoArticuloRepository extends CrudRepository<TipoArticulo, String> {

    List<TipoArticulo> findByActivoOrderByDescripcionAsc(Integer activo) throws Exception;

}