package com.ayesh.task_service.controller;

import com.ayesh.task_service.model.Task;
import com.ayesh.task_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("allTasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("add")
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTaskById(@RequestParam("id") Integer id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        return taskService.updateTask(id,task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Integer id) {
        return taskService.deleteTask(id);
    }
}
