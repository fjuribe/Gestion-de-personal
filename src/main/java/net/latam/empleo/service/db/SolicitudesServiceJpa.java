package net.latam.empleo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.latam.empleo.model.Solicitud;
import net.latam.empleo.repository.SolicitudesRepository;
import net.latam.empleo.service.ISolicitudesService;

@Service("solicitudesService")
public class SolicitudesServiceJpa implements ISolicitudesService {

	
	
	@Autowired
	private SolicitudesRepository solicitudesRepository;
	
	@Override
	public void guardar(Solicitud solicitud) {
		solicitudesRepository.save(solicitud);
		
	}

	@Override
	public void eliminar(Integer idSolicitud) {
       solicitudesRepository.deleteById(idSolicitud);		
	}

	@Override
	public List<Solicitud> buscarTodas() {
	
		return solicitudesRepository.findAll();
	}

	@Override
	public Solicitud buscarPorId(Integer idSolicitud) {
	    Optional<Solicitud> opcional=solicitudesRepository.findById(idSolicitud);
	    if (opcional.isPresent()) {
			return opcional.get();
		}
		return null;
	}

	@Override
	public Page<Solicitud> buscarTodas(Pageable page) {
		return solicitudesRepository.findAll(page);
	}	

}
