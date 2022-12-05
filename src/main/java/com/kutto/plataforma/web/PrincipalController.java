package com.kutto.plataforma.web;

import com.kutto.plataforma.service.*;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PrincipalController {

    @Autowired
    private PaisService paisService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private FuenteService fuenteService;

    @Autowired
    private ConferenciaService conferenciaService;

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/")
    public String inicioPage(Model model) throws Exception {

        model.addAttribute("conferencia", conferenciaService.obtenerConferenciaPorPais(Constante.COD_PAIS_REPUBLICA_DOMINICANA));
        return "home";
    }

    @GetMapping("/registroconferencia")
    public String registroConferenciaPage(Model model) throws Exception {

        model.addAttribute("conferencia", conferenciaService.obtenerConferenciaPorPais(Constante.COD_PAIS_REPUBLICA_DOMINICANA));
        model.addAttribute("paises", paisService.listarPaises());
        model.addAttribute("cargos", cargoService.listarCargos());
        model.addAttribute("fuentes", fuenteService.listarFuentes());

        String urlDecodeQR = Constante.URL_GENERATE_QR_CODE.concat(Base64.getEncoder().encodeToString(StringUtil.toStr(12321).getBytes()));

        byte[] qrCode = qrCodeService.generateQRCode(urlDecodeQR, 250, 250);

        String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCode);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMG_BASE_64", "data:image/png;base64,"+qrCodeBase64);

        model.addAttribute("parametros", parameters);

        return "registroconferencia";
    }

    @GetMapping("/kutto")
    public String kutto(Model model) throws Exception {

        return "kutto";
    }

    @GetMapping("/preguntasfrecuentes")
    public String preguntasfrecuentes(Model model) throws Exception {

        return "preguntasfrecuentes";
    }
}
