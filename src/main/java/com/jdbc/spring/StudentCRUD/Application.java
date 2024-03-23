package com.jdbc.spring.StudentCRUD;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdbc.spring.dao.*;
import com.jdbc.spring.model.*;

public class Application {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Scanner sc = new Scanner(System.in);
        int crudoption;
        int operationoption;
        
        System.out.println("===== Welcome to UniversityCrudApp =====");
        System.out.println();
        System.out.println("1. Department CRUD");
        System.out.println("2. Student CRUD");
        
        System.out.print("Enter the Option 1 or 2 : ");
        crudoption = sc.nextInt();
        System.out.println();
        
        if(crudoption == 1) {
        	System.out.println("1. Add Department");
        	System.out.println("2. Update Department");
        	System.out.println("3. Delete Department");
        	System.out.println("4. Show Department");
        	System.out.println("5. ShowAll Departments");
        	System.out.println();
        	Scanner input = new Scanner(System.in);
            System.out.print("Enter the Option 1 to 5 : ");
        	operationoption = input.nextInt();
        	DepartmentDao departmentDao = (DepartmentDao) context.getBean("departmentDao");
        	
        	if(operationoption == 1) {
        		Scanner inputvalue = new Scanner(System.in);
        		Department d = new Department();
        		d.setDepartmentname(inputvalue.nextLine());
        		d.setDepartmentcode(inputvalue.next());
        		
        		departmentDao.save(d);
        		System.out.print("Inserted Department Successfully");
        	
        	}else if(operationoption == 2) {
        		Scanner inputvalue = new Scanner(System.in);
        		Department d = new Department();
        		d.setId(inputvalue.nextInt());
        		d.setDepartmentname(inputvalue.nextLine());
        		d.setDepartmentcode(inputvalue.next());
        		
        		departmentDao.update(d);
        		System.out.print("Updated Department Successfully");
        		
        	}
        	else if(operationoption == 3) {
        		Scanner inputvalue = new Scanner(System.in);
        		
        		departmentDao.delete(inputvalue.nextInt());
        		System.out.print("Delete Department Successfully");	
        		
        	}else if(operationoption == 4) {
        		Scanner inputvalue = new Scanner(System.in);
        		Department d = departmentDao.findById(inputvalue.nextInt());
        		
        		System.out.println("Department Id : " + d.getId());
        		System.out.println("Department Name : " + d.getDepartmentname());
        		System.out.println("Department Code : " + d.getDepartmentcode());
        		
        	}else if(operationoption == 5) {
        		List<Department> departmentlist = departmentDao.findAll();
        		for(Department d : departmentlist) {
        			System.out.println("Department Id : " + d.getId());
            		System.out.println("Department Name : " + d.getDepartmentname());
            		System.out.println("Department Code : " + d.getDepartmentcode());
            		System.out.println();
        		}
        		
        	}else {
            	System.out.print("Wrong option selected");
        	}
        	
        }else if(crudoption == 2) {
        	System.out.println("1. Add Student");
        	System.out.println("2. Update Student");
        	System.out.println("3. Delete Student");
        	System.out.println("4. Show Student");
        	System.out.println("5. ShowAll Student");
        	System.out.println();
        	Scanner input = new Scanner(System.in);
            System.out.print("Enter the Option 1 to 5 : ");
        	operationoption = input.nextInt();
        	StudentDao studentDao = (StudentDao) context.getBean("studentDao");
        	DepartmentDao departmentDao = (DepartmentDao) context.getBean("departmentDao");
        	
        	if(operationoption == 1) {
        		Scanner inputvalue = new Scanner(System.in);
        		Student s = new Student();
        		s.setName(inputvalue.nextLine());
        		s.setEmail(inputvalue.next());
        		s.setPhone(inputvalue.next());
        		Department d = departmentDao.findById(inputvalue.nextInt());
        		s.setDepartment(d);
        		
        		studentDao.save(s);
        		System.out.print("Inserted Student Successfully");
        	
        	}else if(operationoption == 2) {
        		Scanner inputvalue = new Scanner(System.in);
        		Student s = new Student();
        		s.setId(inputvalue.nextInt());
        		s.setName(inputvalue.nextLine());
        		s.setEmail(inputvalue.next());
        		s.setPhone(inputvalue.next());
        		Department d = departmentDao.findById(inputvalue.nextInt());
        		s.setDepartment(d);        		
        		
        		studentDao.update(s);
        		System.out.print("Updated Student Successfully");
        		
        	}
        	else if(operationoption == 3) {
        		Scanner inputvalue = new Scanner(System.in);
        		
        		studentDao.delete(inputvalue.nextInt());
        		System.out.print("Delete Student Successfully");	
        		
        	}else if(operationoption == 4) {
        		Scanner inputvalue = new Scanner(System.in);
        		Student s = studentDao.findById(inputvalue.nextInt());
        		
        		System.out.println("Student Id : " + s.getId());
        		System.out.println("Name : " + s.getName());
        		System.out.println("Email : " + s.getEmail());
        		System.out.println("Phone : " + s.getPhone());
        		System.out.println("Department : " + s.getDepartment().getDepartmentname());

        		
        	}else if(operationoption == 5) {
        		List<Student> studentlist = studentDao.getAll();
        		for(Student s : studentlist) {
        			System.out.println("Student Id : " + s.getId());
            		System.out.println("Name : " + s.getName());
            		System.out.println("Email : " + s.getEmail());
            		System.out.println("Phone : " + s.getPhone());
            		System.out.println("Department : " + s.getDepartment().getDepartmentname());
            		System.out.println();
        		}
        		
        	}else {
            	System.out.print("Wrong option selected");
        	}
        	
        }else {
        	
        	System.out.print("Wrong option selected");
        
        }
    }
}
