package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.CitaDto;
import com.kutto.plataforma.enums.EnumErrores;
import com.kutto.plataforma.exception.UnprocessableEntityException;
import com.kutto.plataforma.model.Cita;
import com.kutto.plataforma.model.CitaDisponible;
import com.kutto.plataforma.model.EstadoCita;
import com.kutto.plataforma.repository.CitaDisponibleRepository;
import com.kutto.plataforma.repository.CitaRepository;
import com.kutto.plataforma.repository.EstadoCitaRepository;
import com.kutto.plataforma.request.RequestModificarCita;
import com.kutto.plataforma.request.RequestRegistroReserva;
import com.kutto.plataforma.service.CitaService;
import com.kutto.plataforma.service.ParametricaService;
import com.kutto.plataforma.util.Constante;
import com.kutto.plataforma.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

        return listCitaDto.stream().findFirst().orElse(null);
    }

    @Override
    public List<CitaDto> listarCitas() throws Exception {

        List<Cita> listCita = citaRepository.findByActivo(Constante.COD_ACTIVO);

        List<CitaDto> listCitaDto = listCita.stream().map(cita -> modelMapper.map(cita, CitaDto.class)).collect(Collectors.toList());

        return  listCitaDto;
    }

    @Override
    public CitaDto modificarCita(RequestModificarCita requestModificarCita) throws Exception {

        Optional<EstadoCita> optionalEstadoCita = estadoCitaRepository.findById(requestModificarCita.getCodigoEstadoCita());

        Optional<Cita> optionalCita = citaRepository.findById(requestModificarCita.getCodigoCita());

        Cita cita = optionalCita.get();
        cita.setEstadoCita(optionalEstadoCita.get());
        cita.setObservaciones(requestModificarCita.getObervaciones());
        cita.setUsuarioModificacion(Constante.USUARIO_ADMIN);
        cita.setFechaModificacion(Instant.now());

        citaRepository.save(cita);

        CitaDto citaDto = new CitaDto();
        modelMapper.map (cita, citaDto);

        return citaDto;
    }

    @Override
    public void eliminarCita(String codigoCita) throws Exception {

        Optional<Cita> optionalCita = citaRepository.findById(codigoCita);
        Cita cita = optionalCita.get();
        cita.setActivo(Constante.COD_INACTIVO);
        citaRepository.save(cita);

        Optional<CitaDisponible> optionalCitaDisponible = citaDisponibleRepository.findByFechaReservaAndHoraReservaAndActivo(cita.getFecha(), cita.getHorario(), Constante.COD_ACTIVO);

        if(optionalCitaDisponible.isPresent()) {
            CitaDisponible citaDisponible = optionalCitaDisponible.get();
            citaDisponible.setDisponible(Constante.COD_INDICADOR_DISPONIBLE);
            citaDisponibleRepository.save(citaDisponible);
        }
    }
}
