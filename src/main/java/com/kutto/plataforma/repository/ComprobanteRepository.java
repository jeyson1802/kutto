package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Cliente;
import com.kutto.plataforma.model.Comprobante;
import com.kutto.plataforma.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComprobanteRepository extends JpaRepository<Comprobante, String> {

    List<Comprobante> findByActivo(Integer activo);
}