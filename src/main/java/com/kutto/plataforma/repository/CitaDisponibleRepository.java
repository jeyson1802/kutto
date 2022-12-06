package com.kutto.plataforma.repository;

import com.kutto.plataforma.model.CitaDisponible;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface CitaDisponibleRepository extends CrudRepository<CitaDisponible, String> {

    @Query("select distinct(fechaReserva) from CitaDisponible where fechaReserva >= ?1 and disponible = 1 and activo = 1")
    List<Date> findFechaReservaDisponibles(Date fechaActual) throws Exception;

    List<CitaDisponible> findByFechaReservaAndDisponibleAndActivoOrderByHoraReservaAsc(Date fechaReserva, Integer disponible, Integer activo) throws Exception;

}