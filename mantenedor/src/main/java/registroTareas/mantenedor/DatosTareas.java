package registroTareas.mantenedor;

import java.util.Date;
import java.util.Optional;

import registroTareas.mantenedor.model.Tarea;

public class DatosTareas {
	
	
	public static Optional<Tarea> crearTarea001(){
		Date fecha1 = new Date();
		return Optional.of(new Tarea(1L,"Tarea de Pruebas 1",fecha1,true));
		
	}
	
	public static Optional<Tarea> crearTarea003(){
		Date fecha3 = new Date();
		return Optional.of(new Tarea(3L,"Tarea de Pruebas 3",fecha3,true));
		
	}
	
	public static Optional<Tarea> crearTarea002(){
		Date fecha2 = new Date();
		return Optional.of(new Tarea(2L,"Tarea de Pruebas 2",fecha2,true));
		
	}
	

}
