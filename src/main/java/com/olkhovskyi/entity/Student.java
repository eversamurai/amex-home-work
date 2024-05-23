package com.olkhovskyi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private LocalDate joinDate;
    private LocalDate birthDate;
    private String currentClass;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", joinDate=" + joinDate +
                ", birthDate=" + birthDate +
                ", currentClass='" + currentClass + '\'' +
                '}';
    }

    public Student(){}

    public Student(String name, LocalDate joinDate, LocalDate birthDate, String currentClass) {
        this.name = name;
        this.joinDate = joinDate;
        this.birthDate = birthDate;
        this.currentClass = currentClass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }
}
