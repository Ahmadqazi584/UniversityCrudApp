package com.jdbc.spring.daoimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.jdbc.spring.dao.DepartmentDao;
import com.jdbc.spring.model.Department;

public class DepartmentDaoImpl implements DepartmentDao {

	private JdbcTemplate jdbctemplate;
	
	private final String SQL_INSERT_DEPT = "INSERT INTO department (dept_name, dept_code) VALUES (?, ?)";
	private final String SQL_FIND_DEPT = "SELECT * FROM department WHERE dept_id = ?";
	private final String SQL_ALL_DEPT = "SELECT * FROM department";
	private final String SQL_UPDATE_DEPT = "UPDATE department SET dept_name = ?, dept_code = ? WHERE dept_id = ?";
	private final String SQL_DELETE_DEPT = "DELETE FROM department WHERE dept_id = ?";
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	    this.jdbctemplate = jdbcTemplate;
	}

	@Override
	public void save(Department department) {
		// TODO Auto-generated method stub
		jdbctemplate.update(SQL_INSERT_DEPT, department.getDepartmentname(), department.getDepartmentcode());
	}

	@Override
	public Department findById(int id) {
	    return jdbctemplate.queryForObject(SQL_FIND_DEPT, new Object[]{id}, (rs, rowNum) -> {
	        Department department = new Department();
	        department.setId(rs.getInt("dept_id"));
	        department.setDepartmentname(rs.getString("dept_name"));
	        department.setDepartmentcode(rs.getString("dept_code"));
	        return department;
	    });
	}


	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplate.query(SQL_ALL_DEPT, (rs, rowNum) -> {
	        Department department = new Department();
	        department.setId(rs.getInt("dept_id"));
	        department.setDepartmentname(rs.getString("dept_name"));
	        department.setDepartmentcode(rs.getString("dept_code"));
	        return department;
	    });
	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		jdbctemplate.update(SQL_UPDATE_DEPT, department.getDepartmentname(), department.getDepartmentcode(), department.getId());
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		jdbctemplate.update(SQL_DELETE_DEPT, id);
		
	}

}
