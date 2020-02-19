package net.latam.empleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.latam.empleo.model.Categoria;
import net.latam.empleo.service.ICategoriasService;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	@Qualifier("categoriaServiceJpa")
	private ICategoriasService categoriaService;

	// @GetMapping("/index")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> list = categoriaService.buscarTodas();
		model.addAttribute("categorias", list);
		return "categorias/listCategorias";
	}

	// @GetMapping("/create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String crear(Categoria categoria) {
		return "categorias/formCategoria";
	}

	// @PostMapping("/save")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes atribuAttributes) {
		if (result.hasErrors()) {

			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}
			return "categorias/formCategoria";
		}
		categoriaService.guardar(categoria);
		atribuAttributes.addFlashAttribute("msg", "Se guardo los datos de la categoria con exito!!");

		return "redirect:/categorias/index";
	}

}
