package com.ayesh.task_service.model;

import lombok.Data;

@Data
public class Category {
    private Integer id;
    private String name;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
