package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Articulo;
import com.kutto.plataforma.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    List<Cliente> findByNombresContainsAndCodigoTipoPersonaAndActivo(String nombres, String codigoTipoPersona, Integer activo);

    List<Cliente> findByActivo(Integer activo);

}