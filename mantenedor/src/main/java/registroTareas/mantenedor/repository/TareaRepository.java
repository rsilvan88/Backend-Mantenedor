package registroTareas.mantenedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import registroTareas.mantenedor.model.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
