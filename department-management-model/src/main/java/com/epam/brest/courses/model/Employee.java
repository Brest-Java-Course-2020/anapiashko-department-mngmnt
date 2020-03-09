package com.epam.brest.courses.model;

public class Employee {

    private Integer employeeId;

    private String firstname;

    private String lastname;

    private Double salary;

    private String email;

    private Integer departmentId;

    public final String getEmail() {
        return email;
    }

    public final Employee setEmail(final String email) {
        this.email = email;
        return this;
    }

    public final Integer getEmployeeId() {
        return employeeId;
    }

    public final Employee setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
        return  this;
    }

    public final String getFirstname() {
        return firstname;
    }

    public final Employee setFirstname(final String firstname) {
        this.firstname = firstname;
        return this;
    }

    public final String getLastname() {
        return lastname;
    }

    public final Employee setLastname(final String lastname) {
        this.lastname = lastname;
        return this;
    }

    public final Double getSalary() {
        return salary;
    }

    public final Employee setSalary(final Double salary) {
        this.salary = salary;
        return this;
    }

    public final Integer getDepartmentId() {
        return departmentId;
    }

    public final Employee setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    @Override
    public final String toString() {
        return "Employee{"
                + "employeeId=" + employeeId
                + ", firstname='" + firstname + '\''
                + ", lastname='" + lastname + '\''
                + ", salary=" + salary
                + ", departmentId=" + departmentId
                + '}';
    }
}
