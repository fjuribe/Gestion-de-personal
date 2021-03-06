package net.latam.empleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.latam.empleo.model.Categoria;
import net.latam.empleo.service.ICategoriasService;
import net.latam.empleo.util.paginator.PageRenderCategorias;

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

	@GetMapping("/eliminar/{id}")
	public String eliminarCategoria(@PathVariable("id") int idCategoria,Model model,RedirectAttributes atribuAttributes) {
     System.out.println("Se ha eliminado la categoria de id:"+idCategoria);
     categoriaService.eliminar(idCategoria);
     atribuAttributes.addFlashAttribute("msg","se elimino con exito");
		return "redirect:/categorias/index";
		
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") int idCategoria,Model model) {
		Categoria optional=categoriaService.buscarPorId(idCategoria);
		model.addAttribute("categorias", optional);
		return "categorias/formCategoria";
		
	}
	
//	@GetMapping(value="/indexPaginate")
//	public String mostrarIndexPaginado(Model model,Pageable page) {
//		Page<Categoria> listaPage=categoriaService.buscarTodas(page);
//		model.addAttribute("categorias", listaPage);
//		return "categorias/listCategorias";		
//	}
	
	@GetMapping(value="/indexPaginate")
	public String mostrarIndexPaginado(Model model,@RequestParam(name="page",defaultValue="0") int page) {
		Pageable pageRequest = PageRequest.of(page, 7);
		Page<Categoria> lista=categoriaService.buscarTodas(pageRequest);
		PageRenderCategorias<Categoria> pageRender= new PageRenderCategorias<>("indexPaginate", lista);
		model.addAttribute("categorias", lista);
		model.addAttribute("page",pageRender);
		return "categorias/listCategorias";		
	}
	
	
}
