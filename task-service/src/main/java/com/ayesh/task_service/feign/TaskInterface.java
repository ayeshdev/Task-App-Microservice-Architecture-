package com.ayesh.task_service.feign;


import com.ayesh.task_service.model.Category;
import com.ayesh.task_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("CATEGORY-SERVICE")
public interface TaskInterface {
    @GetMapping("category/all-categories")
    public ResponseEntity<List<Category>> getAllCategory();
}
