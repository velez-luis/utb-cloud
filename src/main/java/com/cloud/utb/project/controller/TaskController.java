package com.cloud.utb.project.controller;

import com.cloud.utb.project.controller.common.ApiControllerRoutes;
import com.cloud.utb.project.dto.common.CustomResponse;
import com.cloud.utb.project.dto.task.TaskUpdateDTO;
import com.cloud.utb.project.model.Task;
import com.cloud.utb.project.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiControllerRoutes.TASK_URI)// "/task"
@RequiredArgsConstructor
public class TaskController extends GenericController {

    //List<Task> task = new LinkedList<>();
    private final TaskRepo taskRepository; // Repositorio de JPA


    @GetMapping
    public ResponseEntity<CustomResponse> getAll() {
        //task.add(new Task(1L, LocalDateTime.now(),"Task 0", false));
        //task.add(new Task(2L, LocalDateTime.now(),"Task 1", false));
        //task.add(new Task(3L, LocalDateTime.now(),"Task 2", true));
        //task.add(new Task(4L, LocalDateTime.now(),"Task 3", false));
        List<Task> task = taskRepository.findAll();
        return super.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getById(@PathVariable(name = "id", required = true) Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return super.ok(task.get());
        } else {
            return super.noContent(); // Si no se encuentra la tarea, devolvemos "no content"
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@RequestBody Task task) {
        task.setIdTask(null);  // Asegúrate de no enviar un ID, ya que se autogenerará
        Task savedTask = taskRepository.save(task);  // Guardamos la tarea en la base de datos
        return super.ok(savedTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> update(@PathVariable(name = "id", required = true) Long id, @RequestBody Task task) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            existingTask.setDescripcion(task.getDescripcion());
            existingTask.setFecha(task.getFecha());
            existingTask.setCompletado(task.getCompletado());
            taskRepository.save(existingTask); // Actualizamos la tarea en la base de datos
            return super.ok("Task updated successfully!");
        } else {
            return super.noContent(); // Si no se encuentra la tarea, devolvemos "no content"
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomResponse> partialUpdate(@PathVariable Long id, @RequestBody TaskUpdateDTO taskUpdateDTO) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Actualiza solo los campos que han sido proporcionados
        if (taskUpdateDTO.getDescripcion() != null) {
            existingTask.setDescripcion(taskUpdateDTO.getDescripcion());
        }
        if (taskUpdateDTO.getCompletado() != null) {
            existingTask.setCompletado(taskUpdateDTO.getCompletado());
        }

        // Guardar la tarea actualizada
        Task savedTask = taskRepository.save(existingTask);

        return super.ok(savedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> delete(@PathVariable(name = "id", required = true) Long id) throws Exception {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            taskRepository.deleteById(id); // Eliminamos la tarea de la base de datos
            return super.ok("Task deleted successfully!");
        } else {
            return super.noContent(); // Si no se encuentra la tarea, devolvemos "no content"
        }
    }

}
