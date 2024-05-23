package com.olkhovskyi.repository;

import com.olkhovskyi.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository  extends CrudRepository<Student, Long> {
    List<Student> findByName(String name);
    List<Student> findByCurrentClass(String currentClass);
}
