package com.ayesh.task_service.service;

import com.ayesh.task_service.dao.TaskDao;
import com.ayesh.task_service.feign.TaskInterface;
import com.ayesh.task_service.model.Category;
import com.ayesh.task_service.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskDao taskDao;

    @Autowired
    TaskInterface taskInterface;

    public ResponseEntity<List<Category>> getAllCategories() {
        return taskInterface.getAllCategory();
    }


    public ResponseEntity<List<Task>> getAllTasks() {
        try {
//            List<Task> tasks = taskDao.findAll();

            // Sort tasks in descending order based on createdAt field
            List<Task> tasks = taskDao.findAllByOrderByCreatedAtDesc();
// Debug: Print sorted list to verify order
            tasks.forEach(task -> System.out.println(task.getCreatedAt()));
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addTask(Task task) {

        try {
            taskDao.save(task);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Task> getTaskById(Integer id) {
        try {
            return new ResponseEntity<>(taskDao.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateTask(Integer id, Task task) {
        Task existingTask = taskDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setCategoryId(task.getCategoryId());
        taskDao.save(existingTask);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTask(Integer id) {
        if (taskDao.existsById(id)) {
            taskDao.deleteById(id);
            return new ResponseEntity<>("Task Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Task>> searchTasks(String title) {
        List<Task> tasks;

        if (title != null && !title.isEmpty()) {
            tasks = taskDao.findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(title);
        } else {
            tasks = taskDao.findAllByOrderByCreatedAtDesc();
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


}
