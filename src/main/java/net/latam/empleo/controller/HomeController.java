package net.latam.empleo.controller;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.latam.empleo.model.Usuario;
import net.latam.empleo.model.Vacante;
import net.latam.empleo.service.ICategoriasService;
import net.latam.empleo.service.IVacanteService;

@Controller
public class HomeController {
    
	@Autowired
	private ICategoriasService serviceCategoria;
	
	
	
	@Autowired
	private IVacanteService serviceVacantes;
	
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista=serviceVacantes.buscarTodas();
		model.addAttribute("vacantes",lista);
		return "tabla";
	}
	
	
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
//	List<Vacante> lista=serviceVacantes.buscarTodas();
//		model.addAttribute("vacantes",lista);
		return "home";	
	}
	
	
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarReguistro(Usuario usuario,RedirectAttributes attributes) {
		return "redirect:/usuarios/index";
		
	}
	
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante) {
		System.out.println("buscando por:"+vacante);
		return "home";
		
	}
	
	
	
	/**
	 * model atributte sirve para agregar al modelo para agregar todos los los atributtos que queremos pero deben estar en el controlador
	 * @param model
	 */
	@ModelAttribute
	public void setGenericos(Model model) {
		Vacante vacantesearch=new Vacante();
		vacantesearch.reset();
		model.addAttribute("vacantes",serviceVacantes.buscarDestacadas());
		model.addAttribute("categorias", serviceCategoria.buscarTodas());
		model.addAttribute("search", vacantesearch);
	}
	/**
	 * metodo que regresa una lista de objetos de tipo vacante
	 */
}
