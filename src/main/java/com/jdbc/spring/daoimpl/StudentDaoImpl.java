package com.jdbc.spring.daoimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.jdbc.spring.dao.DepartmentDao;
import com.jdbc.spring.dao.StudentDao;
import com.jdbc.spring.model.Department;
import com.jdbc.spring.model.Student;

public class StudentDaoImpl implements StudentDao {

	private JdbcTemplate jdbcTemplate; 
	private DepartmentDao myCustomDepartmentDao;
	private final String SQL_INSERT_STUDENT = "INSERT INTO student (name, email, phone, dept_id) VALUES (?, ?, ?, ?)";
	private final String SQL_UPDATE_STUDENT = "UPDATE student SET name = ?, email = ?, phone = ?, dept_id = ? WHERE student_id = ?";
	private final String SQL_DELETE_STUDENT = "DELETE FROM student WHERE student_id = ?";
	private final String SQL_FIND_STUDENT = "SELECT * FROM student WHERE student_id = ?";
	private final String SQL_GETALL_STUDENT = "SELECT * FROM student";
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	}
	
	public void setMyCustomDepartmentDao(DepartmentDao myCustomDepartmentDao) {
        this.myCustomDepartmentDao = myCustomDepartmentDao;
    }

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(SQL_INSERT_STUDENT, student.getName(), student.getEmail(), student.getPhone(), student.getDepartment().getId());
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getName(), student.getEmail(), student.getPhone(), student.getDepartment().getId(), student.getId());
	}

	@Override
	public void delete(int studentId) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(SQL_DELETE_STUDENT, studentId);
	}

	@Override
	public Student findById(int studentId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SQL_FIND_STUDENT, new Object[] { studentId }, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("student_id"));
            student.setName(rs.getString("name"));
            student.setEmail(rs.getString("email"));
            student.setPhone(rs.getString("phone"));
            // Assuming you have implemented DepartmentDAO to fetch department details
            Department department = myCustomDepartmentDao.findById(rs.getInt("dept_id"));
            student.setDepartment(department);
            return student;
        });
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(SQL_GETALL_STUDENT, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("student_id"));
            student.setName(rs.getString("name"));
            student.setEmail(rs.getString("email"));
            student.setPhone(rs.getString("phone"));
            // Assuming you have implemented DepartmentDao to fetch department details
            Department department = myCustomDepartmentDao.findById(rs.getInt("dept_id"));
            student.setDepartment(department);
            return student;
        });
	}

}
