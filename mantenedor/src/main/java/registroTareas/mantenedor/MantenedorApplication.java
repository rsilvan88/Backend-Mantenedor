package registroTareas.mantenedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@SpringBootApplication
public class MantenedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MantenedorApplication.class, args);
	}
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("Documentacion Mantenedor")
				.version("v1")
				.description("Se debe crear una app Web [front-backend] que permita realizar el registro de tareas, "
						+ "con las siguientes funcionalidades:"
						+ "- Listar Tareas"
						+ "- Agregar una tarea"
						+ "- Remover una tarea"
						+ "- Editar una tarea")
				.license(new License().name("Maven-Java-SprintBoot-Angular-MySQL").url("localhost:8080")));
				
				
	}

}
