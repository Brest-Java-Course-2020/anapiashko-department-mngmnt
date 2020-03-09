package com.epam.brest.courses.model;

public class Department {

    private Integer departmentId;

    private String departmentName;

    public final Integer getDepartmentId() {
        return departmentId;
    }

    public final Department setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
        return  this;
    }

    public final String getDepartmentName() {
        return departmentName;
    }

    public final Department setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    @Override
    public final String toString() {
        return "Department{"
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + '}';
    }
}
