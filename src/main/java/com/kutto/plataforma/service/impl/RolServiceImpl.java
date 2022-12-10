package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.enums.RolNombre;
import com.kutto.plataforma.model.Rol;
import com.kutto.plataforma.model.Usuario;
import com.kutto.plataforma.repository.RolRepository;
import com.kutto.plataforma.repository.UsuarioRepository;
import com.kutto.plataforma.service.RolService;
import com.kutto.plataforma.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    public void save(Rol rol){
        rolRepository.save(rol);
    }

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public boolean existsByRolNombre(RolNombre rolNombre){
        return rolRepository.existsByRolNombre(rolNombre);
    }
}
