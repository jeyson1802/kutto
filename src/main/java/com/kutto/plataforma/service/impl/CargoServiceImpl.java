package com.kutto.plataforma.service.impl;

import com.kutto.plataforma.dto.CargoDto;
import com.kutto.plataforma.model.Cargo;
import com.kutto.plataforma.repository.CargoRepository;
import com.kutto.plataforma.repository.CargoRepository;
import com.kutto.plataforma.service.CargoService;
import com.kutto.plataforma.service.CargoService;
import com.kutto.plataforma.util.Constante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl implements CargoService {

    private static final Logger logger = LogManager.getLogger(CargoServiceImpl.class);

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CargoDto> listarCargos() throws Exception {

        List<Cargo> listCargo = cargoRepository.findByEstadoOrderByIdAsc(Constante.COD_ESTADO_ACTIVO);

        List<CargoDto> listCargoDto = listCargo.stream().map(pais -> modelMapper.map(pais, CargoDto.class)).collect(Collectors.toList());

        return listCargoDto;

    }
}
