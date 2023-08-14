package registroTareas.mantenedor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import registroTareas.mantenedor.controller.TareaController;
import registroTareas.mantenedor.model.Tarea;
import registroTareas.mantenedor.service.TareaService;

@WebMvcTest(TareaController.class)
public class TareaTest {
	

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TareaService tareaService;
	
	@Test
	public void getTareaByIdTest() throws Exception{
	
		
		when(tareaService.obtenerTareaId(1L)).thenReturn(DatosTareas.crearTarea001().orElseThrow());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tareas/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.descripcion").value("Tarea de Pruebas 1"))
		//Validaciones de fechas distintas.andExpect(jsonPath("$.fechaCreacion").value(LocalDate.now().toString()))
		.andExpect(jsonPath("$.vigente").value(true))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		verify(tareaService).obtenerTareaId(1L);
		
	}
	
	@Test
	public void guardarTareaTest() throws Exception{
	
		Tarea tarea = new Tarea();
		tarea.setIdentificador(2L);
		tarea.setDescripcion("Tarea 2");
		tarea.setFechaCreacion(new Date());
		tarea.setVigente(true);
		
		
		when(tareaService.guardarTarea(any(Tarea.class))).thenReturn(tarea);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tareas").content(new ObjectMapper().writeValueAsString(tarea))
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.identificador").exists())
		.andExpect(jsonPath("$.descripcion").value("Tarea 2"))
		.andExpect(jsonPath("$.vigente").value(true));
		
		
	}
	
	@Test
	public void listarTareas() throws Exception{
		
		List<Tarea> tareas = Arrays.asList(DatosTareas.crearTarea001().orElseThrow(), DatosTareas.crearTarea002().orElseThrow() );
		when(tareaService.listarTareas()).thenReturn(tareas);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tareas").content(new ObjectMapper().writeValueAsString(tareas))
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].descripcion").value("Tarea de Pruebas 1"))
		.andExpect(jsonPath("$[1].descripcion").value("Tarea de Pruebas 2"))
		.andExpect(jsonPath("$[0].vigente").value(true))
		.andExpect(jsonPath("$[1].vigente").value(true))
		.andExpect(jsonPath("$",hasSize(2)));
		
		verify(tareaService).listarTareas();
		
		
	}
	
	@Test
	public void eliminarTareaTest() throws Exception{
		
		Map<String, Boolean> respuestaOK = new HashMap<>();
		respuestaOK.put("eliminar",Boolean.TRUE);
		
		when(tareaService.eliminarTarea(1L)).thenReturn(ResponseEntity.ok(respuestaOK));
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tareas/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		
		verify(tareaService).eliminarTarea(1L);
		
	}
	//utilizacion de assert de junit para pruebas en esta ocasión
	@Test
	public void actualizarTareaTest() throws Exception {
		
		Long idTarea= 1L;
		Long idTarea2= 3L;

		
		Tarea detalleTarea = new Tarea();
		detalleTarea.setIdentificador(idTarea);
		detalleTarea.setDescripcion("update tarea nombre");
		
		when(tareaService.actualizarTarea(any(),any())).thenReturn(detalleTarea);
		
		Tarea resultado = tareaService.actualizarTarea(idTarea, detalleTarea);
		
		verify(tareaService).actualizarTarea(idTarea, detalleTarea);
		assertEquals(idTarea, resultado.getIdentificador());
		assertEquals(detalleTarea.getDescripcion(), resultado.getDescripcion());
		

		
	}
	
	

}
