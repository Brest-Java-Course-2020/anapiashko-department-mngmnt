package com.epam.brest.courses.dao;


import com.epam.brest.courses.model.Department;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.brest.courses.constants.DepartmentConstants.DEPARTMENT_NAME_SIZE;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath:dao.xml"})
public class DepartmentDaoJdbcIT {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentDaoJdbcIT(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Test
    public void getDepartments() {
        List<Department> departments = departmentDao.findAll();
        System.out.println(departments);
        assertNotNull(departments);
    }

    @Test
    public void shouldFindDepartmentById() {

        // given
        Department department = new Department()
                .setDepartmentName(RandomStringUtils.randomAlphabetic(DEPARTMENT_NAME_SIZE));
        Integer id = departmentDao.create(department);

        // when
        Optional<Department> optionalDepartment = departmentDao.findById(id);

        // then
        Assertions.assertTrue(optionalDepartment.isPresent());
        Assertions.assertEquals(optionalDepartment.get().getDepartmentId(), id);
        Assertions.assertEquals(optionalDepartment.get().getDepartmentName(), department.getDepartmentName());
    }

    @Test
    public void shouldCreateDepartment() {
        Department entity = new Department()
                .setDepartmentName(RandomStringUtils.randomAlphabetic(DEPARTMENT_NAME_SIZE));
        Integer id = departmentDao.create(entity);
        assertNotNull(id);
    }

    @Test
    public void shouldUpdateDepartment() {

        // given
        Department department = new Department()
                .setDepartmentName(RandomStringUtils.randomAlphabetic(DEPARTMENT_NAME_SIZE));
        Integer id = departmentDao.create(department);
        assertNotNull(id);

        Optional<Department> departmentOptional = departmentDao.findById(id);
        Assertions.assertTrue(departmentOptional.isPresent());

        departmentOptional.get().
                setDepartmentName(RandomStringUtils.randomAlphabetic(DEPARTMENT_NAME_SIZE));

        // when
        int result = departmentDao.update(departmentOptional.get());

        // then
        assertTrue(1 == result);

        Optional<Department> updatedDepartmentOptional = departmentDao.findById(id);
        Assertions.assertTrue(updatedDepartmentOptional.isPresent());
        Assertions.assertEquals(updatedDepartmentOptional.get().getDepartmentId(), id);
        Assertions.assertEquals(updatedDepartmentOptional.get().getDepartmentName(),departmentOptional.get().getDepartmentName());

    }

    @Test
    public void shouldDeleteDepartment() {
        // given
        Department department = new Department()
                .setDepartmentName(RandomStringUtils.randomAlphabetic(DEPARTMENT_NAME_SIZE));
        Integer id = departmentDao.create(department);

        List<Department> departments = departmentDao.findAll();
        assertNotNull(departments);

        // when
        int result = departmentDao.delete(id);

        // then
        assertTrue(result == 1);

        List<Department> currentDepartments = departmentDao.findAll();
        assertNotNull(currentDepartments);

        assertTrue(departments.size()-1 == currentDepartments.size());
    }

}