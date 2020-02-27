package com.epam.brest.courses.dao;


import com.epam.brest.courses.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath:dao.xml"})
public class DepartmentJdbcDaoImplTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void getDepartments() {
        List<Department> departments = departmentDao.getDepartments();
        System.out.println(departments);
        assertNotNull(departments);
    }

    @Test
    public void getDepartmentById() {
       Department department = departmentDao.getDepartmentById(1);
        assertEquals("Development", department.getDepartmentName());
    }

    @Test public void addDepartment() {
        Department dep = new Department();
        //dep.setDepartmentId(4);
        dep.setDepartmentName("Name");
        departmentDao.addDepartment(dep);
    }

    @Test
    public void updateDepartment() {
        Department department = new Department();
        department.setDepartmentId(2);
        department.setDepartmentName("hr");
        departmentDao.updateDepartment(department);
        getDepartments();
    }

    @Test
    public void deleteDepartment() {
        departmentDao.deleteDepartment(3);
        getDepartments();
    }
}