package com.cloud.utb.project.controller;

import com.cloud.utb.project.controller.common.ApiControllerRoutes;
import com.cloud.utb.project.dto.common.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(ApiControllerRoutes.TASK_URI)
@RequiredArgsConstructor
public class TaskController extends GenericController {

    List<String> task = new LinkedList<>();

    @GetMapping
    public ResponseEntity<CustomResponse> getAll() {
        task.add("Task 1");
        task.add("Task 2");
        task.add("Task 3");
        return super.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getById(@PathVariable(name = "id", required = true) Long id) {
        return super.ok(task.get(id.intValue()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> delete(@PathVariable(name = "id", required = true) Long id) throws Exception {
        task.remove(id.intValue());
        return super.ok("Task deleted successfully!");
    }

}
