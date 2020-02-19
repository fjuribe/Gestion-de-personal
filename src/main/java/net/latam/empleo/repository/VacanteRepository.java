package net.latam.empleo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.latam.empleo.model.Vacante;


public interface VacanteRepository extends JpaRepository<Vacante, Integer> {
	

	List<Vacante> findByEstatus(String estatus);
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado,String estatus);
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1,double s2);
	List<Vacante> findByEstatusIn(String[] estatus);
	List<Vacante> findBySalarioAfter(double a1);
	
	
	
}
