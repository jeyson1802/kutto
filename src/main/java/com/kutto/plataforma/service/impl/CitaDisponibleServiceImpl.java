package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.CitaDisponibleDto;
import com.kutto.plataforma.dto.TipoArticuloDto;
import com.kutto.plataforma.model.CitaDisponible;
import com.kutto.plataforma.model.TipoArticulo;
import com.kutto.plataforma.repository.CitaDisponibleRepository;
import com.kutto.plataforma.service.CitaDisponibleService;
import com.kutto.plataforma.util.Constante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaDisponibleServiceImpl implements CitaDisponibleService {

    private static final Logger logger = LogManager.getLogger(CitaDisponibleServiceImpl.class);

    @Autowired
    private CitaDisponibleRepository citaDisponibleRepository;

    @Autowired
    private ModelMapper modelMapper;

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
}
