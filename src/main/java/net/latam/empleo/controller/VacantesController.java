package net.latam.empleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.latam.empleo.model.Vacante;
import net.latam.empleo.service.IVacanteService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	@Autowired
	private IVacanteService serviceVacantes;
	
	
	@GetMapping("/create")
	public String crear() {
		return "vacantes/formVacantes";
	}
	
	@PostMapping("/save")
	public String guardar(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion,@RequestParam("categoria") int categoria,
			@RequestParam("estatus") String estatus,@RequestParam("fecha") String fecha,@RequestParam("destacado") int destacado,@RequestParam("salario") double salario,
			@RequestParam("detalles") String detalles) {
		
		System.out.println("Nombre vacante:"+nombre);
		System.out.println("Descripción:"+descripcion);
		System.out.println("Categoria:"+categoria);
		System.out.println("Estatus:"+estatus);
		System.out.println("Fecha Publicación:"+fecha);
		System.out.println("Destacado:"+destacado);
		System.out.println("Salario Ofrecido:"+salario);
		System.out.println("Detalles:"+detalles);
		
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante,Model model) {
		System.out.println("Borrando vacante con id:"+idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
	
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante,Model model) {
		
		Vacante vacante=serviceVacantes.buscarPorId(idVacante);
		
		System.out.println("vacante: "+vacante);
		model.addAttribute("vacante", vacante);
		
		
		//buscar los detalles de las vacantes en la BD
		return "detalle";
		
	}
}
