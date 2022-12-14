package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.model.Parametrica;
import com.kutto.plataforma.repository.ParametricaRepository;
import com.kutto.plataforma.service.ParametricaService;
import com.kutto.plataforma.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParametricaServiceImpl implements ParametricaService {

    private static final Logger logger = LogManager.getLogger(ParametricaServiceImpl.class);

    @Autowired
    private ParametricaRepository parametricaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String obtenerCodigoCorrelativoTabla(String prefijo, int longitud) throws Exception {

        Optional<Parametrica> optionalParametrica = parametricaRepository.findById(prefijo);
        Parametrica parametrica = optionalParametrica.get();
        Integer valor = parametrica.getValor();
        String correlativo = prefijo + StringUtils.leftPad(StringUtil.toStr(valor), longitud, "0");
        valor = valor + 1;
        parametrica.setValor(valor);
        parametricaRepository.save(parametrica);

        return  correlativo;
    }
}
