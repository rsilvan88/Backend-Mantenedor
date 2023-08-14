package registroTareas.mantenedor.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import registroTareas.mantenedor.model.Tarea;

public interface TareaService {
	
	public Tarea obtenerTareaId(Long id);
	
	public List<Tarea> listarTareas();
	
	public Tarea guardarTarea(Tarea tarea);
	
	public Tarea actualizarTarea(Long id,Tarea detallesTarea);
	
	public ResponseEntity<Map<String,Boolean>> eliminarTarea(Long id);
}
