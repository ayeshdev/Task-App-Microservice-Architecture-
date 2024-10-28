package com.ayesh.task_service.dao;

import com.ayesh.task_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<Task,Integer> {
}
