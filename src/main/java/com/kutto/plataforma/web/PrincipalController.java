package com.kutto.plataforma.web;

import com.kutto.plataforma.dto.ArticuloDto;
import com.kutto.plataforma.dto.PaginacionWrapperDto;
import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.service.*;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.ImageUtil;
import com.kutto.plataforma.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class PrincipalController {

    @Autowired
    private TipoArticuloService tipoArticuloService;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private CitaDisponibleService citaDisponibleService;

    @GetMapping("/")
    public String inicioPage(Model model) throws Exception {

        return "home";
    }

    @GetMapping("/productos")
    public String productos(@RequestParam(value = "pageNo") int pageNo, @RequestParam("codigoTipoArticulo") String codigoTipoArticulo, Model model) throws Exception {

        List<TipoArticuloDto> listTipoArticuloDto = tipoArticuloService.listarTipoArticulo();
        model.addAttribute("listTipoArticulo", listTipoArticuloDto);

        Pageable pageable = PageRequest.of(pageNo - 1, 9);
        PaginacionWrapperDto<ArticuloDto> paginacionWrapperArticuloDto = articuloService.buscarArticulos(codigoTipoArticulo, pageable);

        model.addAttribute("cantidadxPagina", 9);
        model.addAttribute("desde", 0);
        model.addAttribute("hasta", 0);

        if(paginacionWrapperArticuloDto.getTotalElementos() > 0) {

            if(pageNo == 1) {
                model.addAttribute("desde", 1);
                if(paginacionWrapperArticuloDto.getTotalElementos() < 9) {
                    model.addAttribute("hasta", paginacionWrapperArticuloDto.getTotalElementos());
                } else {
                    model.addAttribute("hasta", 9);
                }
            } else {

                int desdeMin = (pageNo - 1) * 9 + (pageNo - 1);
                model.addAttribute("desde", desdeMin);

                int hastaMax = desdeMin + 9;
                if(hastaMax > paginacionWrapperArticuloDto.getTotalElementos()) {
                    model.addAttribute("hasta", paginacionWrapperArticuloDto.getTotalElementos());
                } else {
                    model.addAttribute("hasta", hastaMax);
                }
            }
        }

        model.addAttribute("cantidadxPagina", 9);
        model.addAttribute("paginaActual", pageNo);
        model.addAttribute("codigoTipoArticulo", codigoTipoArticulo);
        model.addAttribute("totalPaginas", paginacionWrapperArticuloDto.getTotalPaginas());
        model.addAttribute("totalElementos", paginacionWrapperArticuloDto.getTotalElementos());

        model.addAttribute("listArticulo", paginacionWrapperArticuloDto.getRegistros());

        model.addAttribute("imgUtil", new ImageUtil());

        return "productos";
    }

    @GetMapping("/producto")
    public String producto(@RequestParam("codigoArticulo") String codigoArticulo, @RequestParam("codigoTipoArticulo") String codigoTipoArticulo, Model model) throws Exception {

        ArticuloDto articuloDto = articuloService.buscarArticulo(codigoArticulo);
        model.addAttribute("articulo", articuloDto);

        Pageable pageable = PageRequest.of(0, 4);
        PaginacionWrapperDto<ArticuloDto> paginacionWrapperArticuloRelacionadoDto = articuloService.buscarArticulos(codigoTipoArticulo, pageable);

        model.addAttribute("codigoTipoArticulo", codigoTipoArticulo);
        model.addAttribute("listArticuloRelacionado", paginacionWrapperArticuloRelacionadoDto.getRegistros());

        model.addAttribute("imgUtil", new ImageUtil());

        return "productodetalle";
    }

    @GetMapping("/reserva")
    public String reserva(Model model) throws Exception {

        List<Date> fechasDisponibles = citaDisponibleService.listarFechasDisponibles();

        model.addAttribute("fechasDisponibles", fechasDisponibles);

        return "reserva";
    }

    @GetMapping("/buscar")
    public String buscar(Model model) throws Exception {

        return "buscarreserva";
    }

    @GetMapping("/adopcion")
    public String adopcion(Model model) throws Exception {

        return "adopcion";
    }
}
