package registroTareas.mantenedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;


import registroTareas.mantenedor.controller.TareaController;
import registroTareas.mantenedor.repository.TareaRepository;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


@WebMvcTest(TareaController.class)
public class TareaControladorTest {
	
	
	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private TareaController tarea;
	
	/*@Test
	void testVerDetalles() {
		
		when(tarea.obtenerTareaId(1L)).thenReturn(DatosTareas.crearTarea001().orElseThrow());
		mockMVC.perform(get("/api/v1/1").contenType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}*/

	

}
