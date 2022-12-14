package com.kutto.plataforma.web;

import com.kutto.plataforma.dto.ArticuloDto;
import com.kutto.plataforma.dto.EstadoCitaDto;
import com.kutto.plataforma.dto.PaginacionWrapperDto;
import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.service.ArticuloService;
import com.kutto.plataforma.service.CitaDisponibleService;
import com.kutto.plataforma.service.EstadoCitaService;
import com.kutto.plataforma.service.TipoArticuloService;
import com.kutto.plataforma.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private EstadoCitaService estadoCitaService;

    @Autowired
    private TipoArticuloService tipoArticuloService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ATENCION')")
    @GetMapping("/citas")
    public String admin(Model model) throws Exception {

        List<EstadoCitaDto> listaEstados = estadoCitaService.listarEstadoCita();

        model.addAttribute("listaEstados", listaEstados);

        return "citas";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_LOGISTICA')")
    @GetMapping("/articulos")
    public String articulos(Model model) throws Exception {

        List<TipoArticuloDto> listTipoArticuloDto = tipoArticuloService.listarTipoArticulo();
        model.addAttribute("listTipoArticulo", listTipoArticuloDto);

        return "articulos";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/categoria")
    public String tipoarticulo(Model model) throws Exception {

        return "tipoarticulo";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/horarios")
    public String horarios(Model model) throws Exception {

        return "horarios";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ATENCION')")
    @GetMapping("/venta")
    public String venta(Model model) throws Exception {

        return "venta";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ATENCION')")
    @GetMapping("/clientes")
    public String clientes(Model model) throws Exception {

        return "clientes";
    }
}
