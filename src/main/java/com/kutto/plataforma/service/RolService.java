package com.kutto.plataforma.service;

import com.kutto.plataforma.enums.RolNombre;
import com.kutto.plataforma.model.Rol;

import java.util.Optional;

public interface RolService {

    void save(Rol rol);

    Optional<Rol> getByRolNombre(RolNombre rolNombre);

    boolean existsByRolNombre(RolNombre rolNombre);

}
