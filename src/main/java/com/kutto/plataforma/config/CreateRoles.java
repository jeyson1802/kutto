package com.kutto.plataforma.config;

import com.kutto.plataforma.enums.RolNombre;
import com.kutto.plataforma.model.Rol;
import com.kutto.plataforma.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        /*Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolAtencion = new Rol(RolNombre.ROLE_ATENCION);
        Rol rolLogistica = new Rol(RolNombre.ROLE_LOGISTICA);
        rolService.save(rolAdmin);
        rolService.save(rolAtencion);
        rolService.save(rolLogistica);*/
    }
}
