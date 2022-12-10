package com.kutto.plataforma.web;

import com.kutto.plataforma.dto.EstadoCitaDto;
import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.service.EstadoCitaService;
import com.kutto.plataforma.service.TipoArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SeguridadController {

    @GetMapping("/login")
    public String login(Model model) throws Exception {

        return "login";
    }

}
