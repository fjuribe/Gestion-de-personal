package net.latam.empleo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.latam.empleo.model.Categoria;
import net.latam.empleo.repository.CategoriasRepository;
import net.latam.empleo.service.ICategoriasService;

@Service
@Primary
public class CategoriaServiceJpa implements ICategoriasService {
	

	@Autowired
	private CategoriasRepository repoCategorias;

	@Override
	public void guardar(Categoria categoria) {
		// TODO Auto-generated method stub
		repoCategorias.save(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return repoCategorias.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		Optional<Categoria> opcional=repoCategorias.findById(idCategoria);
		
		if(opcional.isPresent()) {
			return opcional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		
		repoCategorias.deleteById(idCategoria);
		
	}

}
