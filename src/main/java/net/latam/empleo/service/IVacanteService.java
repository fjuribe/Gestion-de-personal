package net.latam.empleo.service;
import java.util.List;
import net.latam.empleo.model.Vacante;

public interface IVacanteService {

	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	void guardar(Vacante vacante);
	List<Vacante> buscarDestacadas();
	void eliminar(Integer idVacante);
}
