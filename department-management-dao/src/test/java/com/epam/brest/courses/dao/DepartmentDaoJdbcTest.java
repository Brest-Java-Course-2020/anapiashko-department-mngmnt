package com.epam.brest.courses.dao;


import com.epam.brest.courses.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath:dao.xml"})
public class DepartmentDaoJdbcTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void getDepartments() {
        List<Department> departments = departmentDao.findAll();
        System.out.println(departments);
        assertNotNull(departments);
    }

    @Test
    public void getDepartmentById() {
       Optional<Department> department = departmentDao.findById(1);
        assertEquals("Development", department.get().getDepartmentName());
    }

    @Test public void addDepartment() {
        Department dep = new Department();
        //dep.setDepartmentId(4);
        dep.setDepartmentName("Name");
        departmentDao.create(dep);
    }

    @Test
    public void updateDepartment() {
    }

    @Test
    public void deleteDepartment() {
        departmentDao.delete(3);
        getDepartments();
    }
}