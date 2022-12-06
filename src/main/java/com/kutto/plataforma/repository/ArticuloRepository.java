package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface ArticuloRepository extends JpaRepository<Articulo, String> {

    Page <Articulo> findByTipoArticuloCodigoTipoArticuloAndActivoOrderByPrecioUnitarioAsc(String codigoTipoArticulo, Integer activo, Pageable pageable);

    Page<Articulo> findByActivoOrderByPrecioUnitarioAsc(Integer activo, Pageable pageable);
}