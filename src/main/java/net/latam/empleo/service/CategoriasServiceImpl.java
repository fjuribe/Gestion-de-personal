package net.latam.empleo.service;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.latam.empleo.model.Categoria;

@Service
//@Primary
public class CategoriasServiceImpl implements ICategoriasService{
	
	private List<Categoria> listCategoria=null;
	
	 public CategoriasServiceImpl() {
		listCategoria=new LinkedList<Categoria>();
		// Categoria 1
				Categoria cat1 = new Categoria();
				cat1.setId(1);
				cat1.setNombre("Contabilidad");
				cat1.setDescripcion("Descripcion de la categoria Contabilidad");
				
				// Categoria 2
				Categoria cat2 = new Categoria();
				cat2.setId(2);
				cat2.setNombre("Ventas");
				cat2.setDescripcion("Trabajos relacionados con Ventas");
				
							
				// Categoria 3
				Categoria cat3 = new Categoria();
				cat3.setId(3);
				cat3.setNombre("Comunicaciones");
				cat3.setDescripcion("Trabajos relacionados con Comunicaciones");
				
				// Categoria 4
				Categoria cat4 = new Categoria();
				cat4.setId(4);
				cat4.setNombre("Arquitectura");
				cat4.setDescripcion("Trabajos de Arquitectura");
				
				// Categoria 5
				Categoria cat5 = new Categoria();
				cat5.setId(5);
				cat5.setNombre("Educacion");
				cat5.setDescripcion("Maestros, tutores, etc");
				
				/**
				 * Agregamos los 5 objetos de tipo Categoria a la lista ...
				 */
				listCategoria.add(cat1);			
				listCategoria.add(cat2);
				listCategoria.add(cat3);
				listCategoria.add(cat4);
				listCategoria.add(cat5);

	}
    
	 


	@Override
	public void guardar(Categoria categoria) {
		listCategoria.add(categoria);
		
	}

	@Override
	public List<Categoria> buscarTodas() {
		return listCategoria;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for (Categoria l:listCategoria) {
			if (l.getId()==idCategoria) {
				return l;
			}
		}
		return null;
	}

}
