package net.latam.empleo.controller;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.latam.empleo.model.Vacante;
import net.latam.empleo.service.ICategoriasService;
import net.latam.empleo.service.IVacanteService;
import net.latam.empleo.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	
	@Value("${personalapp.ruta.imagenes}")
	private String ruta;

	@Autowired
	private IVacanteService serviceVacantes;

	@Autowired
	@Qualifier("categoriaServiceJpa")
	private ICategoriasService serviceCategorias;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {

		// TODO 1: Obtener todas las vacantes (recuperarlas con la clase de servicio)
		List<Vacante> lista = serviceVacantes.buscarTodas();

		// TODO 2: Agregar el modelo al listado de vacantes
		model.addAttribute("vacantes", lista);

		// TODO 3: Renderizar las vacantes en la vista (integrar el archivo
		// template-empleos/listVacante.html)
		// TODO 4: Agregar al menu opcion llamada "vacantes" configuracion la URL
		// "vacantes/index"
		return "vacantes/listVacantes";

	}

	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		return "vacantes/formVacantes";
	}

	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart) {

		/* para ver los errores */
		if (result.hasErrors()) {

			// para mostrar los errores por consola
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}

			return "vacantes/formVacantes";
		}

		if (!multiPart.isEmpty()) {
			// String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "C:/springBoot/empleos/img-vacantes/"; // Windows
			
			String nombreImagen = Utileria.guardarArchivo(multiPart,ruta);
			if (nombreImagen != null) { // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}

		serviceVacantes.guardar(vacante);
		attributes.addFlashAttribute("msg", "registro guardado");
		System.out.println("objeto vacante:" + vacante);

		return "redirect:/vacantes/index";
	}

//	@PostMapping("/save")
//	public String guardar(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion,@RequestParam("categoria") int categoria,
//			@RequestParam("estatus") String estatus,@RequestParam("fecha") String fecha,@RequestParam("destacado") int destacado,@RequestParam("salario") double salario,
//			@RequestParam("detalles") String detalles) {
//		
//		System.out.println("Nombre vacante:"+nombre);
//		System.out.println("Descripción:"+descripcion);
//		System.out.println("Categoria:"+categoria);
//		System.out.println("Estatus:"+estatus);
//		System.out.println("Fecha Publicación:"+fecha);
//		System.out.println("Destacado:"+destacado);
//		System.out.println("Salario Ofrecido:"+salario);
//		System.out.println("Detalles:"+detalles);
//		
//		return "vacantes/listVacantes";
//	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVacante, Model model,RedirectAttributes attributes) {
		System.out.println("Borrando vacante con id:" + idVacante);		
		serviceVacantes.eliminar(idVacante);
		attributes.addFlashAttribute("msg", "La vacante fue eliminada");
		return "redirect:/vacantes/index";
	}

	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {

		Vacante vacante = serviceVacantes.buscarPorId(idVacante);

		System.out.println("vacante: " + vacante);
		model.addAttribute("vacante", vacante);

		// buscar los detalles de las vacantes en la BD
		return "detalle";

	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
