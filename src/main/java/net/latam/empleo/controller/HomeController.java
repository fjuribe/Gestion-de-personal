package net.latam.empleo.controller;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.latam.empleo.model.Usuario;
import net.latam.empleo.model.Vacante;
import net.latam.empleo.service.ICategoriasService;
import net.latam.empleo.service.IUsuariosService;
import net.latam.empleo.service.IVacanteService;

@Controller
public class HomeController {
    
	@Autowired
	private ICategoriasService serviceCategoria;
	
	
	
	@Autowired
	private IVacanteService serviceVacantes;
	
	
	@Autowired
	private IUsuariosService serviceUsuario;
	
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista=serviceVacantes.buscarTodas();
		model.addAttribute("vacantes",lista);
		return "tabla";
	}
	
	
	/**
	 * recuperando el usuario en este controlador
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session) {
		String username = auth.getName();
		System.out.println("Nombre del usuario :" + username);

		for (GrantedAuthority rol : auth.getAuthorities()) {
			System.out.println("ROL:" + rol.getAuthority());
		}

		if (session.getAttribute("usuario") == null) {

			Usuario usuario = serviceUsuario.buscarPorUsername(username);
			usuario.setPassword(null);
			System.out.println("Usuario:"+usuario);
			session.setAttribute("usuario", usuario);
			
		}
		return "redirect:/";
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
	public String buscar(@ModelAttribute("search") Vacante vacante,Model model) {
		System.out.println("buscando por:"+vacante);
		
		ExampleMatcher matcher=ExampleMatcher.matching().
				//where descripcion line '%?%'
				withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Vacante> example=Example.of(vacante,matcher);
		List<Vacante> lista=serviceVacantes.buscarByExample(example);
		model.addAttribute("vacantes", lista);
		return "home";
		
	}
	/**
	 * initBinding para string si los detecta vacios en el data binding los settea a Null
	 */
	@InitBinder
	public void initBinder(WebDataBinder bindir) {
		bindir.registerCustomEditor(String.class, new StringTrimmerEditor(true));
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
	 * es el formulario de login que habia hecho
	 * @return
	 */
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	/**
	 * para cerrar session
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
	logoutHandler.logout(request, null, null);
	return "redirect:/";
	}


	/**
	 * metodo que regresa una lista de objetos de tipo vacante
	 */
}
