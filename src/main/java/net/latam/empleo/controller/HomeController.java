package net.latam.empleo.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.latam.empleo.model.Vacante;

@Controller
public class HomeController {
    
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacante vacante=new Vacante();
		vacante.setNombre("Ingeniero de sistemas");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista=new LinkedList<String>();
		lista.add("Ingeniero de sistemas");
		lista.add("Abogado");
		lista.add("Arquitecto");
		lista.add("Vendedor");
		lista.add("Profesor");
		
		model.addAttribute("empleos",lista);
		return "listado";
		
	}
	
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
//		model.addAttribute("mensaje","Bienvenido a empleos App");
//		model.addAttribute("fecha",new Date());
		
		String nombre="Auxiliar de contabilidad";
		Date fechaPub=new Date();
		double salario=9000.0;
		boolean vigente=true;
		
		model.addAttribute("nombre",nombre);
		model.addAttribute("fecha", fechaPub);
		model.addAttribute("salario",salario);
		model.addAttribute("vigente", vigente);
		return "home";
		
	}
}
