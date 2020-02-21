package net.latam.empleo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.latam.empleo.model.Vacante;
import net.latam.empleo.repository.VacanteRepository;
import net.latam.empleo.service.IVacanteService;

@Service
@Primary
public class VacantesServiceJpa implements IVacanteService{
	
	 @Autowired
	 private VacanteRepository vacantesRepo;

	@Override
	public List<Vacante> buscarTodas() {
		
		return vacantesRepo.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> optional=vacantesRepo.findById(idVacante);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		vacantesRepo.save(vacante);
		
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		
		return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
	}

	@Override
	public void eliminar(Integer idVacante) {
		vacantesRepo.deleteById(idVacante);
		
	}

}
