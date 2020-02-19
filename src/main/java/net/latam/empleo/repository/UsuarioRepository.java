package net.latam.empleo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.latam.empleo.model.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
