package com.cloud.utb.project.repo;
import com.cloud.utb.project.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

    // Aquí puedes agregar métodos personalizados si lo necesitas

}
