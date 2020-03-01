package net.latam.empleo.service;
import java.util.List;

import org.springframework.data.domain.Example;

import net.latam.empleo.model.Vacante;

public interface IVacanteService {

	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	void guardar(Vacante vacante);
	List<Vacante> buscarDestacadas();
	void eliminar(Integer idVacante);
	
	/**
	 * filtro que hace la busqueda del objeto en jpa
	 * @param example
	 * @return
	 */
	List<Vacante> buscarByExample(Example<Vacante> example);
}
