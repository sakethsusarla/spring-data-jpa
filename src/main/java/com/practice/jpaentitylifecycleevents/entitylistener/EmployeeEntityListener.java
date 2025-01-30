package com.practice.jpaentitylifecycleevents.entitylistener;

import com.practice.jpaentitylifecycleevents.model.Employee;
import jakarta.persistence.*;

import java.time.Instant;


public class EmployeeEntityListener {
    @PrePersist
    public void prePersist(Employee employee) {
        employee.setCreatedAt(Instant.now());
        employee.setUpdatedAt(Instant.now());
        System.out.printf("EmployeeEntityListener.prePersist %s%n", employee);
    }

    @PostPersist
    public void postPersist(Employee employee) {
        System.out.printf("EmployeeEntityListener.postPersist %s%n", employee);
    }

    @PreUpdate
    public void preUpdate(Employee employee) {
        employee.setUpdatedAt(Instant.now());
        System.out.printf("EmployeeEntityListener.preUpdate %s%n", employee);
    }


    @PostUpdate
    public void postUpdate(Employee employee) {
        System.out.printf("EmployeeEntityListener.postUpdate %s%n", employee);
    }

    @PreRemove
    public void preRemove(Employee employee) {
        System.out.printf("EmployeeEntityListener.preRemove %s%n", employee);
    }

    @PostRemove
    public void postRemove(Employee employee) {
        System.out.printf("EmployeeEntityListener.postRemove %s%n", employee);
    }

    @PostLoad
    public void postLoad(Employee employee) {
        System.out.printf("EmployeeEntityListener.postLoad %s%n", employee);
    }
}
