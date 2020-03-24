package net.latam.empleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.latam.empleo.model.Solicitud;


public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
