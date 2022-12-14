package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.TipoComprobante;
import com.kutto.plataforma.model.TipoDocumento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento, String> {

    List<TipoDocumento> findByActivo(Integer activo) throws Exception;
}