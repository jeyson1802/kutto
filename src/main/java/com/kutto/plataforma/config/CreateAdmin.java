package com.kutto.plataforma.config;

import com.kutto.plataforma.enums.RolNombre;
import com.kutto.plataforma.model.Rol;
import com.kutto.plataforma.model.Usuario;
import com.kutto.plataforma.service.RolService;
import com.kutto.plataforma.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreateAdmin implements CommandLineRunner {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        /* usuario = new Usuario();
        String passwordEncoded = passwordEncoder.encode("admin");
        usuario.setNombreUsuario("admin");
        usuario.setPassword(passwordEncoded);
        Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
        Rol rolLogistica = rolService.getByRolNombre(RolNombre.ROLE_LOGISTICA).get();
        Rol rolAtencion = rolService.getByRolNombre(RolNombre.ROLE_ATENCION).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolAdmin);
        roles.add(rolLogistica);
        roles.add(rolAtencion);
        usuario.setRoles(roles);
        usuarioService.save(usuario);*/

        /*Usuario usuario = new Usuario();
        String passwordEncoded = passwordEncoder.encode("logistica");
        usuario.setNombreUsuario("logistica");
        usuario.setPassword(passwordEncoded);
        Rol rolLogistica = rolService.getByRolNombre(RolNombre.ROLE_LOGISTICA).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolLogistica);
        usuario.setRoles(roles);
        usuarioService.save(usuario);*/

        /*Usuario usuario = new Usuario();
        String passwordEncoded = passwordEncoder.encode("atencion");
        usuario.setNombreUsuario("atencion");
        usuario.setPassword(passwordEncoded);
        Rol rolAtencion = rolService.getByRolNombre(RolNombre.ROLE_ATENCION).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolAtencion);
        usuario.setRoles(roles);
        usuarioService.save(usuario);*/
    }
}
