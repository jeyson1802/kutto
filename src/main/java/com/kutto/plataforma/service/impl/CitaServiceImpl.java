package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.CitaDisponibleDto;
import com.kutto.plataforma.dto.CitaDto;
import com.kutto.plataforma.model.Cita;
import com.kutto.plataforma.model.CitaDisponible;
import com.kutto.plataforma.model.EstadoCita;
import com.kutto.plataforma.repository.CitaDisponibleRepository;
import com.kutto.plataforma.repository.CitaRepository;
import com.kutto.plataforma.repository.EstadoCitaRepository;
import com.kutto.plataforma.request.RequestRegistroReserva;
import com.kutto.plataforma.service.CitaDisponibleService;
import com.kutto.plataforma.service.CitaService;
import com.kutto.plataforma.service.ParametricaService;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.DateUtil;
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
public class CitaServiceImpl implements CitaService {

    private static final Logger logger = LogManager.getLogger(CitaServiceImpl.class);

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private CitaDisponibleRepository citaDisponibleRepository;

    @Autowired
    private EstadoCitaRepository estadoCitaRepository;

    @Autowired
    private ParametricaService parametricaService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CitaDto registrarReserva(RequestRegistroReserva requestRegistroReserva) throws Exception {

        Optional<EstadoCita> optionalEstadoCita = estadoCitaRepository.findById(Constante.COD_ESTADO_CITA_RESERVADO);

        Cita cita = new Cita();
        cita.setCodigoCita(parametricaService.obtenerCodigoCorrelativoTabla(Constante.PREFIJO_CITA, 4));
        cita.setEstadoCita(optionalEstadoCita.get());
        cita.setNombres(requestRegistroReserva.getNombres());
        cita.setDocumento(requestRegistroReserva.getDocumento());
        cita.setCorreo(requestRegistroReserva.getCorreo());
        cita.setActivo(Constante.COD_ACTIVO);
        cita.setUsuarioRegistro(Constante.USUARIO_WEB);
        cita.setFechaRegistro(Instant.now());

        Optional<CitaDisponible> optionalCitaDisponible = citaDisponibleRepository.findById(requestRegistroReserva.getCodigoCitaDisponible());
        CitaDisponible citaDisponible = optionalCitaDisponible.get();

        cita.setFecha(citaDisponible.getFechaReserva());
        cita.setHorario(citaDisponible.getHoraReserva());

        citaRepository.save(cita);

        citaDisponible.setDisponible(Constante.COD_INDICADOR_NO_DISPONIBLE);
        citaDisponibleRepository.save(citaDisponible);

        CitaDto citaDto = new CitaDto();
        modelMapper.map (cita, citaDto);

        return citaDto;
    }

    @Override
    public CitaDto buscarReserva(String busqueda) throws Exception {

        List<Cita> listCita = citaRepository.buscarPorCodigoCitaOrDocumento(busqueda);

        List<CitaDto> listCitaDto = listCita.stream().map(cita -> modelMapper.map(cita, CitaDto.class)).collect(Collectors.toList());

        return listCitaDto.stream().findAny().orElse(null);
    }
}
