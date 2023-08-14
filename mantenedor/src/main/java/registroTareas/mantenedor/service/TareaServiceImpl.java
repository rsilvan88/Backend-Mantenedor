package registroTareas.mantenedor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import registroTareas.mantenedor.exceptions.ResourceNotFoundException;
import registroTareas.mantenedor.model.Tarea;
import registroTareas.mantenedor.repository.TareaRepository;

@Service
public class TareaServiceImpl implements TareaService {


	@Autowired
	private TareaRepository userRepository;
	
	
	@Override
	public Tarea obtenerTareaId(Long id) {
		return userRepository.findById(id).get();
	}


	@Override
	public List<Tarea> listarTareas() {
		return userRepository.findAll();
	}


	@Override
	public Tarea guardarTarea(Tarea tarea) {
		return userRepository.save(tarea);
	}


	@Override
	public Tarea actualizarTarea(Long id, Tarea detallesTarea) {
		
		Tarea tarea = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("No existe la tarea con el identificador : " + id));
		
		
		// Se valida descripcion y fecha no sean null
					if(detallesTarea.getDescripcion() != null) {
					tarea.setDescripcion(detallesTarea.getDescripcion());
					}
					if(detallesTarea.getFechaCreacion() != null) {
					tarea.setFechaCreacion(detallesTarea.getFechaCreacion());
					}
					Tarea tareaUpdate = userRepository.save(tarea);
					
		
		return tareaUpdate;
	}


	@Override
	public ResponseEntity<Map<String,Boolean>> eliminarTarea(Long id) {
		Tarea tarea = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el identificador : " + id));

		userRepository.delete(tarea);
		
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		
		return ResponseEntity.ok(respuesta);
		 
	}
	


}
