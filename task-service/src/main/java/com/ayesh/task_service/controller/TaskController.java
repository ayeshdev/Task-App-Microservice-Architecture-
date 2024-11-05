package com.ayesh.task_service.controller;

import com.ayesh.task_service.model.Category;
import com.ayesh.task_service.model.Task;
import com.ayesh.task_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("all-tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("add-task")
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("get-task/{id}")
    public ResponseEntity<Task> getTaskById(@RequestParam("id") Integer id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("update-task/{id}")
    public ResponseEntity<String> updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("delete-task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Integer id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("all-categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return taskService.getAllCategories();
    }

    @GetMapping("search")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam(required = false) String title) {
        return taskService.searchTasks(title);
    }

}
