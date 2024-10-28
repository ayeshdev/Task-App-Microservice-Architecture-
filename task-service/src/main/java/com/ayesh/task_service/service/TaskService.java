package com.ayesh.task_service.service;

import com.ayesh.task_service.dao.TaskDao;
import com.ayesh.task_service.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskDao taskDao;

    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            return new ResponseEntity<>(taskDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        try {
            taskDao.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
