package com.kutto.plataforma.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeguridadController{

    @GetMapping("/login")
    public String login(Model model) throws Exception {

        return "login";
    }

    @GetMapping("/forbidden")
    public String forbidden(Model model) throws Exception {

        return "forbidden";
    }

}
