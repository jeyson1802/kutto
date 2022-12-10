package com.kutto.plataforma.service;

import com.kutto.plataforma.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> lista();

    Optional<Usuario> getById(int id);

    Optional<Usuario> getByNombreUsuario(String nombreUsuario);

    void save(Usuario usuario);

    boolean existsById(int id);

    boolean existsByNombreusuario(String nombreUsuario);

}
