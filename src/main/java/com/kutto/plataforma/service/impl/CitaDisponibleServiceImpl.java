package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.CitaDisponibleDto;
import com.kutto.plataforma.dto.CitaDisponibleDto;
import com.kutto.plataforma.model.CitaDisponible;
import com.kutto.plataforma.model.CitaDisponible;
import com.kutto.plataforma.repository.CitaDisponibleRepository;
import com.kutto.plataforma.request.RequestGuardarCitaDisponible;
import com.kutto.plataforma.service.CitaDisponibleService;
import com.kutto.plataforma.service.ParametricaService;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.DateUtil;
import com.kutto.plataforma.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaDisponibleServiceImpl implements CitaDisponibleService {

    private static final Logger logger = LogManager.getLogger(CitaDisponibleServiceImpl.class);

    @Autowired
    private CitaDisponibleRepository citaDisponibleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParametricaService parametricaService;

    @Override
    public List<Date> listarFechasDisponibles() throws Exception {

        List<Date> listFechasDisponibles = citaDisponibleRepository.findFechaReservaDisponibles(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        return listFechasDisponibles;

    }

    @Override
    public List<CitaDisponibleDto> listarHorariosDisponiblesPorFecha(Date fechaReserva) throws Exception {

        List<CitaDisponible> listCitaDisponible = citaDisponibleRepository.findByFechaReservaAndDisponibleAndActivoOrderByHoraReservaAsc(fechaReserva, Constante.COD_INDICADOR_DISPONIBLE, Constante.COD_ACTIVO);

        List<CitaDisponibleDto> listCitaDisponibleDto = listCitaDisponible.stream().map(citaDisponible -> modelMapper.map(citaDisponible, CitaDisponibleDto.class)).collect(Collectors.toList());

        return listCitaDisponibleDto;

    }

    @Override
    public List<CitaDisponibleDto> listarHorariosDisponibles() throws Exception {

        List<CitaDisponible> listCitaDisponible = citaDisponibleRepository.findByActivoOrderByCodigoCitaDisponibleDesc(Constante.COD_ACTIVO);

        List<CitaDisponibleDto> listCitaDisponibleDto = listCitaDisponible.stream().map(citaDisponible -> modelMapper.map(citaDisponible, CitaDisponibleDto.class)).collect(Collectors.toList());

        return listCitaDisponibleDto;

    }

    @Override
    public CitaDisponibleDto guardarCitaDisponible(RequestGuardarCitaDisponible requestGuardarCitaDisponible) throws Exception {

        CitaDisponible citaDisponible = new CitaDisponible();

        if(StringUtil.isEmpty(requestGuardarCitaDisponible.getCodigoCitaDisponible())) {
            citaDisponible.setCodigoCitaDisponible(parametricaService.obtenerCodigoCorrelativoTabla(Constante.PREFIJO_CITA_DISPONIBLE, 3));
            citaDisponible.setUsuarioRegistro(Constante.USUARIO_ADMIN);
            citaDisponible.setFechaRegistro(Instant.now());
        } else {
            citaDisponible = citaDisponibleRepository.findById(requestGuardarCitaDisponible.getCodigoCitaDisponible()).get();
            citaDisponible.setUsuarioModificacion(Constante.USUARIO_ADMIN);
            citaDisponible.setFechaModificacion(Instant.now());
        }

        citaDisponible.setFechaReserva(DateUtil.stringToDateYYYYMMDD(requestGuardarCitaDisponible.getFechaReserva()));
        citaDisponible.setHoraReserva(DateUtil.stringToTimeHHMM(requestGuardarCitaDisponible.getHoraReserva()));
        citaDisponible.setDisponible(requestGuardarCitaDisponible.getDisponible());
        citaDisponible.setActivo(Constante.COD_ACTIVO);

        citaDisponibleRepository.save(citaDisponible);

        CitaDisponibleDto citaDisponibleDto = new CitaDisponibleDto();
        modelMapper.map (citaDisponible, citaDisponibleDto);

        return citaDisponibleDto;

    }

    @Override
    public CitaDisponibleDto buscarCitaDisponible(String codigoCitaDisponible) throws Exception {

        Optional<CitaDisponible> optionalCitaDisponible = citaDisponibleRepository.findById(codigoCitaDisponible);

        if(optionalCitaDisponible.isPresent()) {
            return modelMapper.map(optionalCitaDisponible.get(), CitaDisponibleDto.class);
        } else {
            return new CitaDisponibleDto();
        }
    }


}
