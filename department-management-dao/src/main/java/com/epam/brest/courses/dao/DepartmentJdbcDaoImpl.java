package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentJdbcDaoImpl implements DepartmentDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentJdbcDaoImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Department> getDepartments() {
        LOGGER.trace("Get all departments");
        List<Department> departments = namedParameterJdbcTemplate
                .query("SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME FROM DEPARTMENT d ORDER BY d.DEPARTMENT_NAME",
                        new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("DEPARTMENT_ID", departmentId);
        Department department =  namedParameterJdbcTemplate
                .queryForObject("SELECT * FROM DEPARTMENT WHERE DEPARTMENT_ID = :DEPARTMENT_ID",namedParameters,  new DepartmentRowMapper());
        return department;
    }

    @Override
    public Department addDepartment(Department department) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("DEPARTMENT_ID" , department.getDepartmentId())
                .addValue("DEPARTMENT_NAME", department.getDepartmentName());

       int save = namedParameterJdbcTemplate
                .update("INSERT INTO DEPARTMENT (DEPARTMENT_ID, DEPARTMENT_NAME) VALUES (:DEPARTMENT_ID, :DEPARTMENT_NAME)", params);
        return null;
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(Integer departmentId) {

    }

    private class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
            department.setDepartmentName(resultSet.getString("DEPARTMENT_NAME"));
            return department;
        }
    }
}
