package net.latam.empleo.service;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.latam.empleo.model.Vacante;


@Service
public class VacanteServiceImpl implements IVacanteService{

	private List<Vacante> lista=null;
	
	public VacanteServiceImpl() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		lista=new LinkedList<Vacante>();
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

	}
	
	@Override
	public List<Vacante> buscarTodas() {
		return lista;
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		for (Vacante v:lista) {
			if (v.getId()==idVacante) {
				return v;
			}
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);
		
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer idVacante) {
		// TODO Auto-generated method stub
		
	}

}
