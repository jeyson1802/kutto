package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo, String> {

    Page <Articulo> findByTipoArticuloCodigoTipoArticuloAndActivoOrderByCodigoArticuloDesc(String codigoTipoArticulo, Integer activo, Pageable pageable);

    Page<Articulo> findByActivoOrderByCodigoArticuloDesc(Integer activo, Pageable pageable);

    List<Articulo> findByActivoOrderByCodigoArticuloDesc(Integer activo);

    List<Articulo> findByTipoArticulo_CodigoTipoArticuloAndActivo(String codigoTipoArticulo, Integer activo);

    List<Articulo> findByCodigoEstandarAndActivo(String codigoEstandar, Integer activo);


}