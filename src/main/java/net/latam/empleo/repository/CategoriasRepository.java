package net.latam.empleo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.latam.empleo.model.Categoria;


//public interface CategoriasRepository extends CrudRepository<Categoria, Integer>{
public interface CategoriasRepository extends JpaRepository<Categoria, Integer>{
	

	List<Categoria> findByNombre(String nombre);
	
}
