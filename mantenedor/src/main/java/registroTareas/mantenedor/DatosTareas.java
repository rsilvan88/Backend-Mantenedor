package registroTareas.mantenedor;

import java.util.Date;
import java.util.Optional;

import registroTareas.mantenedor.model.Tarea;

public class DatosTareas {
	
	
	public static Optional<Tarea> crearTarea001(){
		return Optional.of(new Tarea(1L,"Tarea de Pruebas 1",new Date(),true));
		
	}
	
	public static Optional<Tarea> crearTarea002(){
		return Optional.of(new Tarea(2L,"Tarea de Pruebas 2",new Date(),true));
		
	}
	
	public static Optional<Tarea> crearTarea003(){
		return Optional.of(new Tarea(3L,"Tarea de Pruebas 3",new Date(),true));
		
	}
	

}
