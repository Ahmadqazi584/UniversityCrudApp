package com.jdbc.spring.dao;

import java.util.List;

import com.jdbc.spring.model.Student;

public interface StudentDao {
	void save(Student student);
    void update(Student student);
    void delete(int studentId);
    Student findById(int studentId);
    List<Student> getAll();
}
