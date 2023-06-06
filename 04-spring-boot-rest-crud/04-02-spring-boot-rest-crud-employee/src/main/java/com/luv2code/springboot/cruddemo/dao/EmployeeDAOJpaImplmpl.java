package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDAOJpaImplmpl implements EmployeeDAO {


    // define field for entityManager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImplmpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the result
        return employees;
    }

    @Override
    public Employee findById(int id) {
        // get employee
        Employee employee = entityManager.find(Employee.class, id);

        // return employee
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        // save employee
        Employee dbEmployee = entityManager.merge(employee);

        // return the dbEmployee
        return  dbEmployee;
    }

    @Override
    public void deleteById(int id) {

        // find employee by id
        Employee employee = entityManager.find(Employee.class, id);

        // remove employee
        entityManager.remove(employee);
    }
}
