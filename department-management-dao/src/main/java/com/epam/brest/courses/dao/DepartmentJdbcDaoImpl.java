package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class DepartmentJdbcDaoImpl implements DepartmentDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentJdbcDaoImpl.class);

    @Value("${department.select}")
    private String SELECT_ALL;

    @Value("${department.selectById}")
    private String SELECT_BY_ID;

    @Value("${department.insert}")
    private String INSERT;

    @Value("${department.update}")
    private String UPDATE;

    @Value("${department.delete}")
    private String DELETE;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Department> getDepartments() {
        LOGGER.info("Get all departments");
        List<Department> departments = namedParameterJdbcTemplate
                .query(SELECT_ALL, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("DEPARTMENT_ID", departmentId);
        Department department =  namedParameterJdbcTemplate
                .queryForObject(SELECT_BY_ID ,namedParameters,  new DepartmentRowMapper());
        return department;
    }

    @Override
    public Department addDepartment(Department department) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        params.addValue("DEPARTMENT_ID" , department.getDepartmentId())
                .addValue("DEPARTMENT_NAME", department.getDepartmentName());

       namedParameterJdbcTemplate.update(INSERT, params, keyHolder);
        department.setDepartmentId(Objects.requireNonNull(keyHolder.getKey()).intValue());
       return department;
    }

    @Override
    public void updateDepartment(Department department) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("DEPARTMENT_ID" , department.getDepartmentId())
                .addValue("DEPARTMENT_NAME", department.getDepartmentName());

        namedParameterJdbcTemplate.update(UPDATE, params);
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("DEPARTMENT_ID", departmentId);
        namedParameterJdbcTemplate.update(DELETE, namedParameters);
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
