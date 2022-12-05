package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.Pais;
import com.kutto.plataforma.model.RegistroConferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroConferenciaRepository extends JpaRepository<RegistroConferencia, Integer> {

    RegistroConferencia findByCorreo(String correo) throws Exception;
}