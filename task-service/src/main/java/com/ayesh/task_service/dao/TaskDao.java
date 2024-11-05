package com.ayesh.task_service.dao;

import com.ayesh.task_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task, Integer> {
    List<Task> findAllByOrderByCreatedAtDesc();

//    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrStatusContainingIgnoreCase(
//            String title, String description, String status);

    // Shortened search method with @Query
//    @Query("SELECT t FROM Task t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%')) " +
//            "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :description, '%')) " +
//            "OR LOWER(t.status) LIKE LOWER(CONCAT('%', :status, '%')) " +
//            "ORDER BY t.id DESC")
//    List<Task> searchTasks(String title, String description, String status);

    List<Task> findByTitleContainingIgnoreCase(@Param("title") String title);

    // Finds tasks by title, ordered by createdAt in descending order
    List<Task> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String title);
}
