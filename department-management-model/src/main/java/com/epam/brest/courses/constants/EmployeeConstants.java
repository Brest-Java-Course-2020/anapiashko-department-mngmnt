package com.epam.brest.courses.constants;

/**
 * Contains employee constants.
 */
public final class EmployeeConstants {

    /**
     * Private constructor.
     */
    private EmployeeConstants() {

    }

    public static final String DEPARTMENT_ID = "departmentId";
    public static final String EMPLOYEE_ID = "employeeId";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String SALARY = "salary";


    /**
     * Max length of first name.
     */
    public static final int EMPLOYEE_FIRSTNAME_SIZE = 255;

    /**
     * Max length of second name.
     */
    public static final int EMPLOYEE_LASTNAME_SIZE = 255;

    /**
     * Max length of email.
     */
    public static final int EMPLOYEE_EMAIL_SIZE = 255;

}
