package com.ise.RMIS.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ise.RMIS.models.Employee;

class EmployeeHandlerTests {
    private static final String TEST_DATABASE_PATH = "src/main/resources/static/test-database.csv";
    private EmployeeHandler model;

    @BeforeEach
    void setUp() throws IOException {
        // Create a test database file
        File testFile = new File(TEST_DATABASE_PATH);

        if (testFile.exists()) {
            testFile.delete();
        }

        testFile.createNewFile();

        model = new EmployeeHandler(TEST_DATABASE_PATH);
    }

    @Test
    void testModelConstructor_ThrowsFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> new EmployeeHandler("nonexistent-file.csv"));
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee(1, "John Doe", 1000.0);

        // Add the employee to the model
        model.addEmployee(employee);

        // Retrieve the added employee
        Employee retrievedEmployee = model.getEmployee(1);

        // Assert that the retrieved employee is not null
        assertNotNull(retrievedEmployee);
        // Assert that the retrieved employee has the same ID as the added employee
        assertEquals(employee.getId(), retrievedEmployee.getId());
        // Assert that the retrieved employee has the same name as the added employee
        assertEquals(employee.getName(), retrievedEmployee.getName());
        // Assert that the retrieved employee has the same salary as the added employee
        assertEquals(employee.getSalary(), retrievedEmployee.getSalary(), 0.0);
    }

    @Test
    void testGetEmployee_ExistingEmployee() {
        Employee employee = new Employee(1, "John Doe", 1000.0);

        // Add the employee to the model
        model.addEmployee(employee);

        // Retrieve the existing employee
        Employee retrievedEmployee = model.getEmployee(1);

        // Assert that the retrieved employee is not null
        assertNotNull(retrievedEmployee);
        // Assert that the retrieved employee has the same ID as the added employee
        assertEquals(employee.getId(), retrievedEmployee.getId());
        // Assert that the retrieved employee has the same name as the added employee
        assertEquals(employee.getName(), retrievedEmployee.getName());
        // Assert that the retrieved employee has the same salary as the added employee
        assertEquals(employee.getSalary(), retrievedEmployee.getSalary(), 0.0);
    }

    @Test
    void testGetEmployee_NonexistentEmployee() {
        // Retrieve a non-existent employee
        Employee retrievedEmployee = model.getEmployee(1);

        // Assert that the retrieved employee is null
        assertNull(retrievedEmployee);
    }

    @Test
    void testGetAllEmployees() {
        Employee[] employees = {
                new Employee("John Doe", 40, 500),
                new Employee("Jane Smith", 37.5, 450),
                new Employee("Bob Johnson", 50, 890)
        };

        // Add employees to the model
        for (Employee employee : employees) {
            model.addEmployee(employee);
        }

        // Retrieve all employees
        Employee[] retrievedEmployees = model.getAllEmployees();

        // Assert that the retrieved employees array has the same length as the added
        // employees array
        assertEquals(employees.length, retrievedEmployees.length);

    }
}
