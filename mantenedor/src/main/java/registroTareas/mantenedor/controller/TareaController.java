package registroTareas.mantenedor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import registroTareas.mantenedor.exceptions.ResourceNotFoundException;
import registroTareas.mantenedor.model.Tarea;
import registroTareas.mantenedor.repository.TareaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins ="http://localhost:37851")
@Api(value = "Capa Controller", description = "Operaciones para demostrar Swagger en Spring Boot")
public class TareaController {

	@Autowired
	private TareaRepository repo;
    
	@GetMapping("/tareas")
    @ApiOperation(value = "Listar Tareas", notes = "Lista todas las tareas en BD")
	public List<Tarea> listarTareas() {
		return repo.findAll();
	}
	
	//guarda una tarea
	@PostMapping("/tareas")
	public Tarea guardarTarea(@RequestBody Tarea tarea) {
	return repo.save(tarea);		
	}
	//Lista una tarea por id
	@GetMapping("/tareas/{id}")
	public ResponseEntity<Tarea> obtenerTareaId(@PathVariable Long id){
		Tarea tarea = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe tarea con identificador"+ id) );
		return ResponseEntity.ok(tarea);
	}
	
	//este metodo sirve para actualiza tarea
		@PutMapping("/tareas/{id}")
		public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id,@RequestBody Tarea detallesTarea){
			Tarea tarea = repo.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe la tarea con el identificador : " + id));
			
			tarea.setDescripcion(detallesTarea.getDescripcion());
			tarea.setFechaCreacion(detallesTarea.getFechaCreacion());
			tarea.setVigente(detallesTarea.isVigente());

			
			Tarea tareaUpdate = repo.save(tarea);
			return ResponseEntity.ok(tareaUpdate);
	    }
		
		//este metodo sirve para eliminar una tarea
		@DeleteMapping("/tareas/{id}")
		public ResponseEntity<Map<String,Boolean>> eliminarTarea(@PathVariable Long id){
			Tarea tarea = repo.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el identificador : " + id));
			
			repo.delete(tarea);
			Map<String, Boolean> respuesta = new HashMap<>();
			respuesta.put("eliminar",Boolean.TRUE);
			return ResponseEntity.ok(respuesta);
	    }
	

}
