package com.jdbc.spring.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.jdbc.spring.model.Department;

public interface DepartmentDao {
	void save(Department department);
    Department findById(int id);
    List<Department> findAll();
    void update(Department department);
    void delete(int id);
}
