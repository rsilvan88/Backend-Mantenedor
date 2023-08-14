package registroTareas.mantenedor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import registroTareas.mantenedor.model.Tarea;
import registroTareas.mantenedor.service.TareaService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Capa Controller", description = "Operaciones para demostrar Swagger en Spring Boot")
public class TareaController {

	@Autowired
	private TareaService tareaService;

	@GetMapping("/tareas")
	public List<Tarea> listarTareas() {
		return tareaService.listarTareas();
	}

	// guarda una tarea
	@PostMapping("/tareas")
	public Tarea guardarTarea(@RequestBody Tarea tarea) {
		return tareaService.guardarTarea(tarea);
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
			
		        // Se valida descripcion y fecha no sean null
			if(detallesTarea.getDescripcion() != null) {
			tarea.setDescripcion(detallesTarea.getDescripcion());
			}
			if(detallesTarea.getFechaCreacion() != null) {
			tarea.setFechaCreacion(detallesTarea.getFechaCreacion());
			}
			tarea.setVigente(detallesTarea.isVigente());

	// Lista una tarea por id
	@GetMapping("/tareas/{id}")
	public ResponseEntity<Tarea> obtenerTareaId(@PathVariable Long id) {
		return new ResponseEntity<>(tareaService.obtenerTareaId(id), HttpStatus.OK);

	}

	// este metodo sirve para actualiza tarea
	@PutMapping("/tareas/{id}")
	public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea detallesTarea) {

		return new ResponseEntity<>(tareaService.actualizarTarea(id, detallesTarea), HttpStatus.OK);
	}

	// este metodo sirve para eliminar una tarea
	@DeleteMapping("/tareas/{id}")
	public ResponseEntity<ResponseEntity<Map<String, Boolean>>> eliminarTarea(@PathVariable Long id) {

		return ResponseEntity.ok(tareaService.eliminarTarea(id));

	}

}
