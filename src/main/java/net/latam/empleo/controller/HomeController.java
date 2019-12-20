package net.latam.empleo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.latam.empleo.model.Vacante;

@Controller
public class HomeController {
    
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista=getVacantes();
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
	
	
	/**
	 * metodo que regresa una lista de objetos de tipo vacante
	 */
	private List<Vacante> getVacantes(){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> lista=new LinkedList<>();
		try {
			Vacante vacante1=new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setDescripcion("solicitamos Ing. civil para dseñar aeropuerto");
			vacante1.setFecha(sdf.parse("08-02-2019"));
			vacante1.setSalario(8500.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
			
			
			Vacante vacante2=new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador Auditor");
			vacante2.setDescripcion("Empresa gubernamental solicita director de finanzas de profesion Contador Auditor");
			vacante2.setFecha(sdf.parse("09-02-2019"));
			vacante2.setSalario(7500.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");
			
			Vacante vacante3=new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("ingeniero Civil Electrico");
			vacante3.setDescripcion("Empresa internacional solicita analista energetico de potencias para proyecto maritimo");
			vacante3.setFecha(sdf.parse("11-02-2019"));
			vacante3.setSalario(9900.0);
			vacante3.setDestacado(0);
			vacante3.setImagen("empresa3.png");
			
			Vacante vacante4=new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Periodista");
			vacante4.setDescripcion("Importante canal de television solicita periodista con poca experiencia laboral para integrarlo a su equipo de reportes");
			vacante4.setFecha(sdf.parse("12-02-2019"));
			vacante4.setSalario(9900.0);
			vacante4.setDestacado(1);
			
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		return lista;
	}
}
