package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Articulo;
import com.kutto.plataforma.model.Serie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, String> {

    List<Serie>  findByTipoComprobante_CodigoTipoComprobanteAndActivo(String codigoTipoComprobante, Integer activo);

}